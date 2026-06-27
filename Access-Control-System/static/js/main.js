// BoolGuard ACS — Main JavaScript
// Boolean Algebra Access Control System — Class 145

// Animate binary strip on explain page
document.querySelectorAll('.binary-strip span').forEach((el, i) => {
  el.style.setProperty('--i', i);
  setInterval(() => {
    el.textContent = Math.random() > 0.5 ? '1' : '0';
  }, 1200 + i * 200);
});

// Typing effect for formula bar
const formulaEl = document.querySelector('.formula-text');
if (formulaEl) {
  const original = formulaEl.innerHTML;
  formulaEl.style.opacity = '0';
  setTimeout(() => {
    formulaEl.style.transition = 'opacity 0.5s';
    formulaEl.style.opacity = '1';
  }, 300);
}

// Add stagger animation to trace steps
document.querySelectorAll('.trace-step').forEach((el, i) => {
  el.style.animationDelay = `${i * 0.08}s`;
});

// Terminal blink cursor effect on inputs
document.querySelectorAll('.field-input').forEach(input => {
  input.addEventListener('focus', () => {
    input.parentElement.style.boxShadow = '0 0 20px rgba(0,212,255,0.3)';
  });
  input.addEventListener('blur', () => {
    input.parentElement.style.boxShadow = '';
  });
});

// OTP input — only allow digits
const otpInput = document.querySelector('.otp-input');
if (otpInput) {
  otpInput.addEventListener('input', () => {
    otpInput.value = otpInput.value.replace(/\D/g, '');
  });
}

// Console message for curious students
console.log('%c BoolGuard ACS ', 'background:#00d4ff; color:#040d14; font-weight:bold; font-size:16px; padding:4px 12px;');
console.log('%c Boolean Algebra Access Control System', 'color:#7aa8c8');
console.log('%c Class 146 | Mohammed Khayat · Nawaf Al-Ameeri · Mohammed Al-shaikh', 'color:#456880');
console.log('%c Boolean Logic: AND ∧  OR ∨  NOT ¬  XOR ⊕', 'color:#00ff88; font-family:monospace');
