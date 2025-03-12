import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-sip',
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  standalone: true,
  templateUrl: './sip.component.html',
  styleUrl: './sip.component.css',
})
export class SipComponent {
  sipForm: FormGroup;
  totalAmount: number | null = null;
  investedAmount: number = 0;
  returns: number = 0;

  constructor(private fb: FormBuilder) {
    this.sipForm = this.fb.group({
      monthlySip: [null, [Validators.required, Validators.min(1)]],
      duration: [null, [Validators.required, Validators.min(1)]],
      roi: [8, [Validators.required, Validators.min(-25), Validators.max(25)]],
    });
  }

  calculateSIP() {
    if (this.sipForm.valid) {
      const monthlySip = this.sipForm.value.monthlySip;
      const duration = this.sipForm.value.duration;
      const roi = this.sipForm.value.roi / 100;
      const monthlyRate = roi / 12;
      const totalMonths = duration * 12;

      this.investedAmount = monthlySip * totalMonths;

      if (roi === 0) {
        this.totalAmount = this.investedAmount;
        this.returns = 0;
      } else {
        // SIP calculation formula: P × ({[1 + i]^n - 1} / i) × (1 + i)
        this.totalAmount =
          monthlySip *
          ((Math.pow(1 + monthlyRate, totalMonths) - 1) / monthlyRate) *
          (1 + monthlyRate);
        this.returns = this.totalAmount - this.investedAmount;
      }
    }
  }
}
