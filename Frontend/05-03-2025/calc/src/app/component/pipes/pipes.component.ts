import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ConverterPipe } from '../../pipe/currrency/converter.pipe';
import { CircleareaPipe } from '../../pipe/circlearea.pipe';
import { CirclediameterPipe } from '../../pipe/circlediameter.pipe';
import { CircleperimiterPipe } from '../../pipe/circleperimiter.pipe';

@Component({
  selector: 'app-pipes',
  imports: [
    CommonModule,
    FormsModule,
    ConverterPipe,
    CircleareaPipe,
    CirclediameterPipe,
    CircleperimiterPipe,
  ],
  templateUrl: './pipes.component.html',
  styleUrl: './pipes.component.css',
})
export class PipesComponent {
  currency: number = 0;
  currencies: string[] = ['USD', 'EUR', 'GBP'];
  country: string = 'USD';
  radius: number = 1;
}
