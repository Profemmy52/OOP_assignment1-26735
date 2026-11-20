package com.auca.taxrefund;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.taxassessment.TaxAssessment;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
public class TaxRefund implements Identifiable, Validatable {
    private String refundId;
    private RegisteredTaxpayer taxpayer;
    private TaxAssessment assessment;
    private BigDecimal requestedAmount;
    private LocalDate requestDate;
    private String status;
    public TaxRefund() {}
    public TaxRefund(String refundId, RegisteredTaxpayer taxpayer, TaxAssessment assessment,
                     BigDecimal requestedAmount, LocalDate requestDate, String status) {
        this.refundId = refundId;
        this.taxpayer = taxpayer;
        this.assessment = assessment;
        this.requestedAmount = requestedAmount;
        this.requestDate = requestDate;
        this.status = status;
    }
    public String getRefundId() { return refundId; }
    public void setRefundId(String id) { this.refundId = id; }
    public RegisteredTaxpayer getTaxpayer() { return taxpayer; }
    public void setTaxpayer(RegisteredTaxpayer tp) { this.taxpayer = tp; }
    public TaxAssessment getAssessment() { return assessment; }
    public void setAssessment(TaxAssessment ass) { this.assessment = ass; }
    public BigDecimal getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(BigDecimal amt) { this.requestedAmount = amt; }
    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate date) { this.requestDate = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    @Override
    public String getId() { return refundId; }
    @Override
    public String getDisplayName() { return refundId + " (" + status + ") - " + requestedAmount + " RWF"; }
    @Override
    public boolean isActive() { return !"REJECTED".equalsIgnoreCase(status); }
    @Override
    public void validate() throws ValidationException {
        if (refundId == null || refundId.isEmpty()) throw new ValidationException("Refund ID is required.");
        if (taxpayer == null) throw new ValidationException("Taxpayer reference is required.");
        if (assessment == null) throw new ValidationException("Assessment reference is required.");
        if (requestedAmount == null || requestedAmount.compareTo(BigDecimal.ZERO) <= 0) throw new ValidationException("Requested amount must be > 0.");
        if (requestDate == null) throw new ValidationException("Request date is required.");
    }
    @Override
    public void printDetails() {
        System.out.println("Refund: " + refundId + ", Taxpayer: " + (taxpayer != null ? taxpayer.getTin() : "N/A") + ", Amount: " + requestedAmount + " RWF, Status: " + status);
    }
    @Override
    public String getSummary() {
        return refundId + " - " + requestedAmount + " RWF [" + status + "]";
    }
}
