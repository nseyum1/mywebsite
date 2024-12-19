import java.util.Date;

public class PayStatement {
    private int statementId;
    private int empId;
    private Date payDate;
    private double amountPaid;

    // Constructor
    public PayStatement(int statementId, int empId, Date payDate, double amountPaid) {
        this.statementId = statementId;
        this.empId = empId;
        this.payDate = payDate;
        this.amountPaid = amountPaid;
    }

    // Getters and Setters
    public int getStatementId() {
        return statementId;
    }

    public void setStatementId(int statementId) {
        this.statementId = statementId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "PayStatement [statementId=" + statementId + ", empId=" + empId +
                ", payDate=" + payDate + ", amountPaid=" + amountPaid + "]";
    }
}
