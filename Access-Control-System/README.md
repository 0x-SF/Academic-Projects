# BoolGuard ACS — Boolean Algebra Access Control System

> **University Project — Class 145**
> Boolean Algebra Application: Access Control System

---

## developer

| Name | Student ID |
|------|-----------|
| Nawaf Abdullah Al-Ameeri | ******** |
| Muhammad Adel Khayat | ******** | 
| Muhammad Ubaidullah Al-Sheikh | ******** | 

---

## Project Description

This project demonstrates how **Boolean Algebra** is applied to a real-world **Access Control / Authentication System**. The system uses Boolean gates (AND, OR, NOT, XOR) to evaluate login credentials and decide whether access should be granted or denied.

---

## Boolean Algebra Concepts Used

| Gate | Symbol | Python | Usage in System |
|------|--------|--------|-----------------|
| AND  | ∧ | `and` | All credentials must be correct |
| OR   | ∨ | `or`  | Guest: valid login OR recognized guest |
| NOT  | ¬ | `not` | Deny access when NOT authorized |
| XOR  | ⊕ | `^`   | Detect privilege escalation difference |

### Core Logic Expressions

```
ACCESS    = username_exists AND password_correct AND account_active
ADMIN     = ACCESS AND is_admin AND otp_valid
USER      = ACCESS AND is_user
GUEST     = ACCESS OR is_guest
DENIED    = NOT(ACCESS)
PRIVILEGE = partial_admin XOR otp_valid
```

---

## Project Structure

```
boolean_access_control/
│
├── app.py                    # Main Flask application + Boolean logic engine
├── requirements.txt          # Python dependencies
├── README.md                 # This file
│
├── templates/
│   ├── base.html             # Base layout with navbar & footer
│   ├── login.html            # Login page with demo credentials
│   ├── result.html           # Access result + Boolean trace
│   ├── dashboard.html        # Role-based dashboard + truth tables
│   └── explain.html          # Boolean theory explanation page
│
└── static/
    ├── css/
    │   └── style.css         # Cybersecurity-themed stylesheet
    └── js/
        └── main.js           # UI interactions
```

---

## How to Run

### 1. Install Python (if not installed)
Download Python 3.8+ from https://python.org

### 2. Install Flask
```bash
pip install flask
```
Or install all dependencies:
```bash
pip install -r requirements.txt
```

### 3. Run the application
```bash
python app.py
```

### 4. Open in browser
```
http://127.0.0.1:5000
```

---

## Demo Credentials

| Role  | Username | Password  | OTP    | Access Level |
|-------|----------|-----------|--------|--------------|
| Admin | admin    | Admin@123 | 123456 | Full Access  |
| User  | alice    | Alice@456 | —      | Standard     |
| Guest | guest    | guest     | —      | Read-Only    |
| Banned| banned   | banned    | —      | Denied       |

---

## Features

1. **Login Page** — Username, password, and OTP (for admin) input
2. **Boolean Logic Engine** — `evaluate_access()` function applies AND/OR/NOT/XOR
3. **Step-by-Step Trace** — Every login shows the Boolean evaluation steps
4. **Role-Based Access** — Admin / User / Guest with different privileges
5. **Theory Page** — Explains Boolean gates with truth tables and flowchart
6. **Interactive Dashboard** — Shows access privileges and interactive truth tables
7. **Cybersecurity UI** — Animated grid, scan-line effect, monospace fonts
8. **REST API** — `/api/truth-table` returns gate data as JSON

---

## How Boolean Algebra Works Here

```python
def evaluate_access(username, password_hash, otp=""):
    # A: Does username exist?
    A = username in USERS                         # CHECK

    # B: Is password correct?
    B = (user["password"] == password_hash)       # CHECK

    # C: Is account active?
    C = user["active"]                            # CHECK

    # Basic authentication: A AND B AND C
    basic_auth = bool_AND(A, B, C)                # AND gate

    if bool_NOT(basic_auth):                      # NOT gate → deny
        return ACCESS_DENIED

    # Admin path: also needs OTP
    otp_valid = (otp == DEMO_OTP)
    admin_access = bool_AND(basic_auth, is_admin, otp_valid)  # AND

    # Guest path: valid login OR is_guest
    guest_access = bool_OR(basic_auth, is_guest)              # OR

    # XOR: detect partial vs full admin privilege
    privilege_diff = bool_XOR(partial_admin, otp_valid)       # XOR
```

---

## Presentation Outline

### Nawaf Abdullah Al-Ameeri (********)
- Introduction & Boolean Theory
- History of Boolean Algebra (George Boole, 1854)
- Binary values (0/1 = False/True)
- The four gates: AND, OR, NOT, XOR

### Muhammad Adel Khayat (********)
- System Architecture & Live Demo
- Flask project structure
- User database and password hashing
- Demo: login as each role, show the Boolean trace

### Muhammad Ubaidullah Al-Sheikh (********)
- Boolean Logic Engine Deep Dive
- Walk through `evaluate_access()` function
- How each gate is used
- Real-world cybersecurity applications

---

## References

- Boole, G. (1854). *An Investigation of the Laws of Thought*
- Rosen, K. (2018). *Discrete Mathematics and Its Applications*, 8th Ed.
- Flask Documentation: https://flask.palletsprojects.com
- NIST Access Control Guide: https://csrc.nist.gov/publications/detail/sp/800-162/final
