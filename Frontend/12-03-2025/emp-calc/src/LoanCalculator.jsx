import React, { useState, useEffect } from 'react';
import './App.css';
import './LoanCalculator.css';

const LoanCalculator = () => {
  const [loanType, setLoanType] = useState('Home');
  const [principal, setPrincipal] = useState('');
  const [tenure, setTenure] = useState('');
  const [emi, setEmi] = useState(null);
  const [error, setError] = useState('');
  const [showAmortization, setShowAmortization] = useState(false);
  const [amortizationSchedule, setAmortizationSchedule] = useState([]);
  const [totalInterest, setTotalInterest] = useState(0);
  const [totalPayment, setTotalPayment] = useState(0);

  const loanDetails = {
    Home: { interest: 9, minPrincipal: 500000, maxPrincipal: 10000000, maxTenure: 30 },
    Car: { interest: 10, minPrincipal: 100000, maxPrincipal: 5000000, maxTenure: 7 },
    Personal: { interest: 12, minPrincipal: 50000, maxPrincipal: 2000000, maxTenure: 5 },
  };

  // Reset fields when loan type changes
  useEffect(() => {
    setPrincipal('');
    setTenure('');
    setEmi(null);
    setError('');
    setShowAmortization(false);
    setAmortizationSchedule([]);
  }, [loanType]);

  const calculateAmortizationSchedule = (principal, monthlyInterest, emi, totalMonths) => {
    let balance = principal;
    let schedule = [];
    let totalInterestPaid = 0;

    for (let month = 1; month <= totalMonths; month++) {
      const interestPayment = balance * monthlyInterest;
      const principalPayment = emi - interestPayment;
      balance -= principalPayment;
      totalInterestPaid += interestPayment;

      schedule.push({
        month,
        emi: emi.toFixed(2),
        principalPayment: principalPayment.toFixed(2),
        interestPayment: interestPayment.toFixed(2),
        balance: Math.max(0, balance).toFixed(2),
      });

      if (balance <= 0) break;
    }

    setTotalInterest(totalInterestPaid.toFixed(2));
    setTotalPayment((principal + totalInterestPaid).toFixed(2));
    return schedule;
  };

  const handleCalculate = (e) => {
    e.preventDefault();
    setError('');
    setShowAmortization(false);
    const { interest, minPrincipal, maxPrincipal, maxTenure } = loanDetails[loanType];
    const p = parseFloat(principal);
    const t = parseFloat(tenure);

    if (isNaN(p) || p < minPrincipal || p > maxPrincipal) {
      setError(`Principal should be between ₹${minPrincipal.toLocaleString()} and ₹${maxPrincipal.toLocaleString()}`);
      return;
    }
    if (isNaN(t) || t <= 0 || t > maxTenure) {
      setError(`Tenure should be between 1 and ${maxTenure} years`);
      return;
    }

    const monthlyInterest = interest / 12 / 100;
    const n = t * 12; // months
    const calculatedEmi = (p * monthlyInterest * Math.pow(1 + monthlyInterest, n)) /
                          (Math.pow(1 + monthlyInterest, n) - 1);
    setEmi(calculatedEmi.toFixed(2));
    
    // Calculate amortization schedule
    const schedule = calculateAmortizationSchedule(p, monthlyInterest, calculatedEmi, n);
    setAmortizationSchedule(schedule);
  };

  // Format currency for display
  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('en-IN', {
      style: 'currency',
      currency: 'INR',
      maximumFractionDigits: 0
    }).format(amount);
  };

  return (
    <div className="loan-calculator">
      <h1>Loan EMI Calculator</h1>
      
      <div className="calculator-container">
        <form onSubmit={handleCalculate} className="form-container">
          <div className="form-group">
            <label>Loan Type:</label>
            <select value={loanType} onChange={(e) => setLoanType(e.target.value)}>
              {Object.keys(loanDetails).map((type) => (
                <option key={type} value={type}>{type} Loan</option>
              ))}
            </select>
          </div>
          
          <div className="form-group">
            <label>Interest Rate:</label>
            <div className="input-with-suffix">
              <input
                type="text"
                value={`${loanDetails[loanType].interest}`}
                disabled
                className="disabled-input"
              />
              <span className="input-suffix">%</span>
            </div>
          </div>
          
          <div className="form-group">
            <label>Principal Amount:</label>
            <div className="input-with-suffix">
              <span className="input-prefix">₹</span>
              <input
                type="number"
                value={principal}
                onChange={(e) => setPrincipal(e.target.value)}
                placeholder={`${loanDetails[loanType].minPrincipal.toLocaleString()} - ${loanDetails[loanType].maxPrincipal.toLocaleString()}`}
                required
              />
            </div>
            <div className="range-hint">
              Min: {formatCurrency(loanDetails[loanType].minPrincipal)} | 
              Max: {formatCurrency(loanDetails[loanType].maxPrincipal)}
            </div>
          </div>
          
          <div className="form-group">
            <label>Tenure:</label>
            <div className="input-with-suffix">
              <input
                type="number"
                value={tenure}
                onChange={(e) => setTenure(e.target.value)}
                placeholder={`1 - ${loanDetails[loanType].maxTenure}`}
                required
              />
              <span className="input-suffix">Years</span>
            </div>
            <div className="range-hint">
              Max: {loanDetails[loanType].maxTenure} Years
            </div>
          </div>
          
          {error && <p className="error-message">{error}</p>}
          
          <button type="submit" className="calculate-btn">Calculate EMI</button>
        </form>

        {emi !== null && (
          <div className="results-container">
            <div className="emi-result">
              <h2>Monthly EMI</h2>
              <div className="emi-amount">₹{parseInt(emi).toLocaleString()}</div>
              
              <div className="payment-details">
                <div className="detail-item">
                  <span>Principal Amount:</span>
                  <span>{formatCurrency(principal)}</span>
                </div>
                <div className="detail-item">
                  <span>Total Interest:</span>
                  <span>{formatCurrency(totalInterest)}</span>
                </div>
                <div className="detail-item total">
                  <span>Total Payment:</span>
                  <span>{formatCurrency(totalPayment)}</span>
                </div>
              </div>
              
              <button 
                className="toggle-schedule-btn"
                onClick={() => setShowAmortization(!showAmortization)}
              >
                {showAmortization ? 'Hide Payment Schedule' : 'Show Payment Schedule'}
              </button>
            </div>
            
            {showAmortization && (
              <div className="amortization-schedule">
                <h3>Payment Schedule</h3>
                <div className="schedule-table-container">
                  <table className="schedule-table">
                    <thead>
                      <tr>
                        <th>Month</th>
                        <th>EMI</th>
                        <th>Principal</th>
                        <th>Interest</th>
                        <th>Balance</th>
                      </tr>
                    </thead>
                    <tbody>
                      {amortizationSchedule.map((payment) => (
                        <tr key={payment.month}>
                          <td>{payment.month}</td>
                          <td>₹{parseInt(payment.emi).toLocaleString()}</td>
                          <td>₹{parseInt(payment.principalPayment).toLocaleString()}</td>
                          <td>₹{parseInt(payment.interestPayment).toLocaleString()}</td>
                          <td>₹{parseInt(payment.balance).toLocaleString()}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default LoanCalculator;