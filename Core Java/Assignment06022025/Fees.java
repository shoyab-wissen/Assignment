package Assignment06022025;

public class Fees {
    private double totalFees;
    private double feesPaid;
    private double feesPending;

    public Fees(double totalFees, double feesPaid) {
        this.totalFees = totalFees;
        this.feesPaid = feesPaid;
        this.feesPending = this.totalFees - this.feesPaid;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public double getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(double feesPaid) {
        this.feesPaid = feesPaid;
        setFeesPending();
    }

    public double getFeesPending() {
        return feesPending;
    }

    private void setFeesPending() {
        this.feesPending = this.totalFees - this.feesPaid;
    }

    @Override
    public String toString() {
        return "Fees [totalFees=" + totalFees + ", feesPaid=" + feesPaid + ", feesPending=" + feesPending + "]";
    }

}
