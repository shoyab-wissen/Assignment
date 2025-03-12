import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'converter',
})
export class ConverterPipe implements PipeTransform {
  transform(currency: number, ...args: string[]): string {
    const country = args[0] || 'USD';
    // map of currency codes to their respective conversion rates from inr
    const conversionRates: { [key: string]: number } = {
      USD: 83.45, // 1 US Dollar to INR
      EUR: 90.62, // 1 Euro to INR
      GBP: 106.91, // 1 British Pound to INR
      JPY: 0.55, // 1 Japanese Yen to INR
      AUD: 55.12, // 1 Australian Dollar to INR
      CAD: 61.34, // 1 Canadian Dollar to INR
      CHF: 94.78, // 1 Swiss Franc to INR
      CNY: 11.52, // 1 Chinese Yuan to INR
      SGD: 62.31, // 1 Singapore Dollar to INR
      AED: 22.72, // 1 UAE Dirham to INR
      SAR: 22.25, // 1 Saudi Riyal to INR
      MYR: 17.81, // 1 Malaysian Ringgit to INR
      THB: 2.34, // 1 Thai Baht to INR
      RUB: 0.92, // 1 Russian Ruble to INR
      NZD: 50.63, // 1 New Zealand Dollar to INR
    };

    return (currency * conversionRates[country]).toFixed(2);
  }
}
