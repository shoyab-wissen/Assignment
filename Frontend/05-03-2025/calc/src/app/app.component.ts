import { Component } from '@angular/core';
import { LoanCalculatorComponent } from './component/loan-calculator/loan-calculator.component';
import { FormsModule } from '@angular/forms';
import { DepositCalculatorComponent } from './component/deposit-calculator/deposit-calculator.component';
import { CommonModule } from '@angular/common';
import { PipesComponent } from './component/pipes/pipes.component';
import { SipComponent } from './component/sip/sip.component';

@Component({
  selector: 'app-root',
  imports: [
    LoanCalculatorComponent,
    FormsModule,
    CommonModule,
    DepositCalculatorComponent,
    PipesComponent,
    SipComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Financial Calculator';
  activeTab: string = 'loan'; // Default active tab

  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }
}
