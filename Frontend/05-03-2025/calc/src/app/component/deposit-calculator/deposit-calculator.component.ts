import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposit-calculator',
  imports: [FormsModule, CommonModule],
  templateUrl: './deposit-calculator.component.html',
  styleUrls: ['./deposit-calculator.component.css'],
})
export class DepositCalculatorComponent {
  principal: number | null = null;
  tenure: number | null = null;
  readonly INTEREST_RATE = 7;
  readonly MAX_TENURE = 10;
  maturityResult: string = '';

  calculateMaturity(): void {
    if (!this.validateInputs()) {
      return;
    }

    const rate = this.INTEREST_RATE / 100;
    const maturityAmount =
      (this.principal as number) * Math.pow(1 + rate, this.tenure as number);

    this.maturityResult = `Maturity: ₹${Math.round(
      maturityAmount
    ).toLocaleString('en-IN')}`;
  }

  private validateInputs(): boolean {
    if (!this.principal || this.principal <= 0) {
      alert('Please enter a valid principal amount');
      return false;
    }

    if (!this.tenure || this.tenure <= 0) {
      alert('Please enter a valid tenure');
      return false;
    }

    if (this.tenure > this.MAX_TENURE) {
      alert(`Maximum tenure is ${this.MAX_TENURE} years`);
      return false;
    }

    return true;
  }
}
