import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import {
  MatButtonToggle,
  MatButtonToggleGroup,
} from '@angular/material/button-toggle';
import { MatFormField, MatHint, MatLabel } from '@angular/material/form-field';

interface LoanConfig {
  interest: number;
  maxTenure: number;
  minAmount: number;
  maxAmount: number;
}

@Component({
  selector: 'app-loan-calculator',
  imports: [CommonModule, FormsModule],
  templateUrl: './loan-calculator.component.html',
  styleUrls: ['./loan-calculator.component.css'],
})
export class LoanCalculatorComponent {
  applicant: string = '';
  loanType: string = 'HOME';
  tenure: number | null = null;
  amount: number | null = null;
  emiResult: string = '';

  loanConfigs: { [key: string]: LoanConfig } = {
    HOME: {
      interest: 9,
      maxTenure: 30,
      minAmount: 500000,
      maxAmount: 10000000,
    },
    CAR: {
      interest: 11,
      maxTenure: 7,
      minAmount: 100000,
      maxAmount: 1500000,
    },
    PERSONAL: {
      interest: 15,
      maxTenure: 5,
      minAmount: 10000,
      maxAmount: 500000,
    },
  };

  get currentConfig(): LoanConfig {
    return this.loanConfigs[this.loanType];
  }

  get tenurePlaceholder(): string {
    return `Enter tenure (max ${this.currentConfig.maxTenure} years)`;
  }

  get amountPlaceholder(): string {
    return `Enter amount (${this.currentConfig.minAmount.toLocaleString(
      'en-IN'
    )}-${this.currentConfig.maxAmount.toLocaleString('en-IN')})`;
  }

  onLoanTypeChange(loanType: string): void {
    this.loanType = loanType;
  }

  calculateEMI(): void {
    if (!this.validateInputs()) {
      return;
    }

    const config = this.currentConfig;
    const monthlyInterestRate = config.interest / (12 * 100);
    const tenureMonths = (this.tenure as number) * 12;
    const emi =
      ((this.amount as number) *
        monthlyInterestRate *
        Math.pow(1 + monthlyInterestRate, tenureMonths)) /
      (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

    this.emiResult = `EMI: ₹${Math.round(emi).toLocaleString(
      'en-IN'
    )} for ${tenureMonths} months at ${config.interest}% interest rate`;
  }

  private validateInputs(): boolean {
    const config = this.currentConfig;

    if (!this.applicant) {
      alert('Please enter applicant name');
      return false;
    }

    if (!this.tenure || this.tenure <= 0) {
      alert('Please enter a valid tenure');
      return false;
    }

    if (this.tenure > config.maxTenure) {
      alert(
        `Maximum tenure for ${this.loanType} loan is ${config.maxTenure} years`
      );
      return false;
    }

    if (!this.amount || this.amount <= 0) {
      alert('Please enter a valid loan amount');
      return false;
    }

    if (this.amount < config.minAmount || this.amount > config.maxAmount) {
      alert(
        `Loan amount for ${
          this.loanType
        } must be between ${config.minAmount.toLocaleString(
          'en-IN'
        )} and ${config.maxAmount.toLocaleString('en-IN')}`
      );
      return false;
    }

    return true;
  }
}
