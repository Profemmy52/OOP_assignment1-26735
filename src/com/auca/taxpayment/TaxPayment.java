package com.auca.taxpayment;
import com.auca.taxassessment.TaxAssessment;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
public class TaxPayment implements Identifiable, Validatable {
    private String paymentId;
    private TaxAssessment assessment;
    private String paymentReference;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    public TaxPayment() {}
    public TaxPayment(String paymentId, TaxAssessment assessment, String paymentReference,
                      BigDecimal amount, LocalDate paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.assessment = assessment;
        this.paymentReference = paymentReference;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String id) { this.paymentId = id; }
    public TaxAssessment getAssessment() { return assessment; }
    public void setAssessment(TaxAssessment ass) { this.assessment = ass; }
    public String getPaymentReference() { return paymentReference; }
    public void setPaymentReference(String ref) { this.paymentReference = ref; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amt) { this.amount = amt; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate date) { this.paymentDate = date; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String method) { this.paymentMethod = method; }
    @Override
    public String getId() { return paymentId; }
    @Override
    public String getDisplayName() { return paymentId + " - " + amount + " RWF (" + paymentReference + ")"; }
    @Override
    public boolean isActive() { return true; }
    @Override
    public void validate() throws ValidationException {
        if (paymentId == null || paymentId.isEmpty()) throw new ValidationException("Payment ID is required.");
        if (assessment == null) throw new ValidationException("Assessment reference is required.");
        if (paymentReference == null || paymentReference.isEmpty()) throw new ValidationException("Payment reference is required.");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new ValidationException("Payment amount must be > 0.");
        if (paymentDate == null) throw new ValidationException("Payment date is required.");
    }
    @Override
    public void printDetails() {
        System.out.println("Payment: " + paymentId + ", Ref: " + paymentReference + ", Amount: " + amount + " RWF, Date: " + paymentDate + ", Method: " + paymentMethod);
    }
    @Override
    public String getSummary() {
        return paymentId + " - " + amount + " RWF [" + paymentReference + "]";
    }
}
