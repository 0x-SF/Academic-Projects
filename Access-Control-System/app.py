"""

Boolean Algebra Access Control System
======================================
University Project — Class 145

Team Members:
  - Mohammed Khayat         (44714789)
  - Nawaf Abdullah Al-Ameeri (44714014)
  - Mohammed Al-shaikh      (44711569)

This system demonstrates Boolean Algebra concepts (AND, OR, NOT, XOR)
applied to a real-world Access Control / Authentication scenario.
"""

from flask import Flask, render_template, request, redirect, url_for, session, jsonify
import hashlib
import random
import time

app = Flask(__name__)
app.secret_key = "boolean_algebra_project_145"

# ──────────────────────────────────────────────
#  USER DATABASE  (simulated)
# ──────────────────────────────────────────────
USERS = {
    "segfault": {
        "password": hashlib.sha256("Admin@135".encode()).hexdigest(),
        "role": "admin",
        "otp_secret": "654321",
        "active": True,
    },
    "m-khayat": {
        "password": hashlib.sha256("Mm@121212".encode()).hexdigest(),
        "role": "user",
        "otp_secret": None,
        "active": True,
    },
    "mohammed": {
        "password": hashlib.sha256("try@12121".encode()).hexdigest(),
        "role": "guest",
        "otp_secret": None,
        "active": True,
    },
    "banned": {
        "password": hashlib.sha256("banned".encode()).hexdigest(),
        "role": "user",
        "otp_secret": None,
        "active": False,          # account disabled → NOT authorized
    },
}

# Demo OTP (in production you'd use TOTP/pyotp)
DEMO_OTP = "654321"

# ──────────────────────────────────────────────
#  BOOLEAN ALGEBRA LOGIC ENGINE
# ──────────────────────────────────────────────

def bool_AND(*conditions):
    """Boolean AND — all conditions must be True."""
    return all(conditions)

def bool_OR(*conditions):
    """Boolean OR — at least one condition must be True."""
    return any(conditions)

def bool_NOT(condition):
    """Boolean NOT — inverts the condition."""
    return not condition

def bool_XOR(a, b):
    """Boolean XOR — exactly one condition must be True."""
    return a ^ b

def evaluate_access(username: str, password_hash: str, otp: str = "") -> dict:
    """
    Core access-control decision using Boolean Algebra.

    Returns a dict with:
      - granted  : bool
      - role     : str
      - steps    : list[dict]  — trace of each Boolean operation
      - message  : str
    """
    steps = []

    # ── Condition A: username exists ──────────────────────────────
    username_exists = username in USERS
    steps.append({
        "op": "CHECK",
        "label": "Username Exists",
        "expression": f'"{username}" IN database',
        "result": username_exists,
    })

    if bool_NOT(username_exists):
        steps.append({
            "op": "NOT",
            "label": "NOT username_exists → Access Denied",
            "expression": "NOT(username_exists) = TRUE",
            "result": False,
        })
        return _deny("Unknown username.", steps)

    user = USERS[username]

    # ── Condition B: password correct ─────────────────────────────
    password_correct = (user["password"] == password_hash)
    steps.append({
        "op": "CHECK",
        "label": "Password Correct",
        "expression": "hash(input) == stored_hash",
        "result": password_correct,
    })

    # ── Condition C: account active ───────────────────────────────
    account_active = user["active"]
    steps.append({
        "op": "CHECK",
        "label": "Account Active",
        "expression": "user.active == True",
        "result": account_active,
    })

    # ── Core gate: A AND B AND C ──────────────────────────────────
    basic_auth = bool_AND(username_exists, password_correct, account_active)
    steps.append({
        "op": "AND",
        "label": "Basic Authentication",
        "expression": "username_exists AND password_correct AND account_active",
        "result": basic_auth,
    })

    if bool_NOT(basic_auth):
        reason = "Wrong password." if not password_correct else "Account is disabled."
        steps.append({
            "op": "NOT",
            "label": "NOT basic_auth → Access Denied",
            "expression": "NOT(basic_auth) = TRUE",
            "result": False,
        })
        return _deny(reason, steps)

    role = user["role"]

    # ── Role-based gates ──────────────────────────────────────────
    is_admin = (role == "admin")
    is_user  = (role == "user")
    is_guest = (role == "guest")

    steps.append({
        "op": "CHECK",
        "label": f'Role Identified: "{role.upper()}"',
        "expression": f'role == "{role}"',
        "result": True,
    })

    # ── Admin path: basic_auth AND is_admin AND otp_valid ─────────
    if is_admin:
        otp_valid = (otp.strip() == DEMO_OTP)
        steps.append({
            "op": "CHECK",
            "label": "OTP Valid",
            "expression": f'entered_otp == expected_otp  →  "{otp}" == "{DEMO_OTP}"',
            "result": otp_valid,
        })

        admin_access = bool_AND(basic_auth, is_admin, otp_valid)
        steps.append({
            "op": "AND",
            "label": "Admin Full Access",
            "expression": "basic_auth AND is_admin AND otp_valid",
            "result": admin_access,
        })

        # XOR demo: admin with OTP XOR admin without OTP
        partial_admin = bool_AND(basic_auth, is_admin)
        xor_result    = bool_XOR(partial_admin, otp_valid)
        steps.append({
            "op": "XOR",
            "label": "XOR — Partial vs Full Admin",
            "expression": "partial_admin XOR otp_valid  (True when exactly one is True)",
            "result": xor_result,
        })

        if bool_NOT(otp_valid):
            steps.append({
                "op": "NOT",
                "label": "NOT otp_valid → Full Access Denied",
                "expression": "NOT(otp_valid) = TRUE → downgrade to partial",
                "result": False,
            })
            return _deny("OTP is incorrect. Full access denied.", steps, role="admin_partial")

        return _grant("Full Admin Access Granted.", steps, role="admin")

    # ── Regular user path: basic_auth AND is_user ─────────────────
    if is_user:
        user_access = bool_AND(basic_auth, is_user)
        steps.append({
            "op": "AND",
            "label": "User Access",
            "expression": "basic_auth AND is_user",
            "result": user_access,
        })
        return _grant("User Access Granted.", steps, role="user")

    # ── Guest path: basic_auth OR is_guest (OR demo) ──────────────
    if is_guest:
        guest_access = bool_OR(basic_auth, is_guest)
        steps.append({
            "op": "OR",
            "label": "Guest Access",
            "expression": "basic_auth OR is_guest",
            "result": guest_access,
        })
        return _grant("Guest Access Granted (limited).", steps, role="guest")

    return _deny("No matching role.", steps)


def _grant(message, steps, role="user"):
    return {"granted": True,  "role": role, "steps": steps, "message": message}

def _deny(message, steps, role="none"):
    return {"granted": False, "role": role, "steps": steps, "message": message}


# ──────────────────────────────────────────────
#  ROUTES
# ──────────────────────────────────────────────

@app.route("/")
def index():
    return redirect(url_for("login"))


@app.route("/login", methods=["GET", "POST"])
def login():
    error = None
    if request.method == "POST":
        username      = request.form.get("username", "").strip().lower()
        password      = request.form.get("password", "")
        otp           = request.form.get("otp", "")
        password_hash = hashlib.sha256(password.encode()).hexdigest()

        result = evaluate_access(username, password_hash, otp)

        session["username"]   = username
        session["role"]       = result["role"]
        session["granted"]    = result["granted"]
        session["steps"]      = result["steps"]
        session["message"]    = result["message"]

        return redirect(url_for("result"))

    return render_template("login.html", error=error)


@app.route("/result")
def result():
    if "username" not in session:
        return redirect(url_for("login"))
    return render_template(
        "result.html",
        username = session["username"],
        role     = session["role"],
        granted  = session["granted"],
        steps    = session["steps"],
        message  = session["message"],
    )


@app.route("/dashboard")
def dashboard():
    if not session.get("granted"):
        return redirect(url_for("login"))
    return render_template("dashboard.html",
                           username=session["username"],
                           role=session["role"])


@app.route("/explain")
def explain():
    return render_template("explain.html")


@app.route("/logout")
def logout():
    session.clear()
    return redirect(url_for("login"))


@app.route("/api/truth-table")
def truth_table():
    """Return truth table data for Boolean gates (used by JS)."""
    return jsonify({
        "AND": [[0,0,0],[0,1,0],[1,0,0],[1,1,1]],
        "OR":  [[0,0,0],[0,1,1],[1,0,1],[1,1,1]],
        "NOT": [[0,1],[1,0]],
        "XOR": [[0,0,0],[0,1,1],[1,0,1],[1,1,0]],
    })


# ──────────────────────────────────────────────
if __name__ == "__main__":
    print("\n" + "="*55)
    print("  Boolean Algebra Access Control System")
    print("  Class 145 — University Project")
    print("="*55)
    print("\n  Demo Credentials:")
    print("  ┌─────────────────────────────────────────────┐")
    print("  │  Role   │ Username │ Password  │ OTP        │")
    print("  ├─────────────────────────────────────────────┤")
    print("  │  Admin  │ segfault │ Admin@135 │ 654321     │")
    print("  │  User   │ m-khayat │ Mm@121211 │ (none)     │")
    print("  │  Guest  │ mohammed │ try@12121 │ (none)     │")
    print("  │  Banned │ banned   │ banned    │ (disabled) │")
    print("  └─────────────────────────────────────────────┘")
    print("\n  Open: http://127.0.0.1:5000\n")
    app.run(debug=True)
