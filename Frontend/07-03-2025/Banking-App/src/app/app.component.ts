import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  registerForm: FormGroup;
  loginForm: FormGroup;
  isLogin = false;
  isRegistered = false;
  loggedInUser = '';
  registeredUsers: any[] = [];

  constructor(private fb: FormBuilder) {
    // Initialize registration form
    this.registerForm = this.fb.group(
      {
        customerId: [
          '',
          [Validators.required, Validators.pattern('^[0-9]{7}$')],
        ],
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(5),
            Validators.pattern('^[a-zA-Z]+$'),
          ],
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
            this.passwordValidator,
          ],
        ],
        confirmPassword: ['', Validators.required],
        accountNo: [
          '',
          [Validators.required, Validators.pattern('^[0-9]{6,}$')],
        ],
      },
      { validators: this.passwordMatchValidator }
    );

    // Initialize login form
    this.loginForm = this.fb.group({
      customerId: ['', [Validators.required, Validators.pattern('^[0-9]{7}$')]],
      password: ['', Validators.required],
    });
  }

  // Custom validator for password requirements
  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) {
      return null;
    }

    const hasUpperCase = /[A-Z]/.test(value);
    const hasLowerCase = /[a-z]/.test(value);
    const hasSpecialChar = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(value);
    const isValid = hasUpperCase && hasLowerCase && hasSpecialChar;

    return isValid ? null : { invalidPassword: true };
  }

  // Custom validator to check password match
  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  // Toggle between login and registration forms
  toggleForm(): void {
    this.isLogin = !this.isLogin;
  }

  // Submit registration form
  onRegister(): void {
    if (this.registerForm.valid) {
      const userData = {
        customerId: this.registerForm.value.customerId,
        username: this.registerForm.value.username,
        password: this.registerForm.value.password,
        accountNo: this.registerForm.value.accountNo,
      };

      this.registeredUsers.push(userData);
      this.isRegistered = true;
      this.isLogin = true;
      this.registerForm.reset();
    } else {
      this.markFormGroupTouched(this.registerForm);
    }
  }

  // Submit login form
  onLogin(): void {
    if (this.loginForm.valid) {
      const customerId = this.loginForm.value.customerId;
      const password = this.loginForm.value.password;

      const user = this.registeredUsers.find(
        (u) => u.customerId === customerId && u.password === password
      );

      if (user) {
        this.loggedInUser = user.username;
      } else {
        alert('Invalid credentials. Please try again.');
      }
    } else {
      this.markFormGroupTouched(this.loginForm);
    }
  }

  // Logout user
  logout(): void {
    this.loggedInUser = '';
    this.loginForm.reset();
  }

  // Helper to mark all form controls as touched
  markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach((control) => {
      control.markAsTouched();
      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}
