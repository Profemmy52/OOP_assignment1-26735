package com.auca.taxassessment;
import com.auca.taxdeclaration.TaxDeclaration;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
public class TaxAssessment implements Identifiable, Validatable {
    private String assessmentId;
    private TaxDeclaration declaration;
    private BigDecimal assessedTax;
    private BigDecimal adjustmentAmount;
    private LocalDate assessmentDate;
    private String status;
    public TaxAssessment() {}
    public TaxAssessment(String assessmentId, TaxDeclaration declaration, BigDecimal assessedTax,
                         BigDecimal adjustmentAmount, LocalDate assessmentDate, String status) {
        this.assessmentId = assessmentId;
        this.declaration = declaration;
        this.assessedTax = assessedTax;
        this.adjustmentAmount = adjustmentAmount;
        this.assessmentDate = assessmentDate;
        this.status = status;
    }
    public String getAssessmentId() { return assessmentId; }
    public void setAssessmentId(String id) { this.assessmentId = id; }
    public TaxDeclaration getDeclaration() { return declaration; }
    public void setDeclaration(TaxDeclaration decl) { this.declaration = decl; }
    public BigDecimal getAssessedTax() { return assessedTax; }
    public void setAssessedTax(BigDecimal tax) { this.assessedTax = tax; }
    public BigDecimal getAdjustmentAmount() { return adjustmentAmount; }
    public void setAdjustmentAmount(BigDecimal adj) { this.adjustmentAmount = adj; }
    public LocalDate getAssessmentDate() { return assessmentDate; }
    public void setAssessmentDate(LocalDate date) { this.assessmentDate = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotalAmount() {
        BigDecimal tax = (assessedTax != null) ? assessedTax : BigDecimal.ZERO;
        BigDecimal adj = (adjustmentAmount != null) ? adjustmentAmount : BigDecimal.ZERO;
        return tax.add(adj);
    }
    @Override
    public String getId() { return assessmentId; }
    @Override
    public String getDisplayName() { return assessmentId + " - Total: " + getTotalAmount() + " RWF (" + status + ")"; }
    @Override
    public boolean isActive() { return !"CANCELLED".equalsIgnoreCase(status); }
    @Override
    public void validate() throws ValidationException {
        if (assessmentId == null || assessmentId.isEmpty()) throw new ValidationException("Assessment ID is required.");
        if (declaration == null) throw new ValidationException("Declaration reference is required.");
        if (assessedTax == null || assessedTax.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Assessed tax must be >= 0.");
        if (adjustmentAmount == null) throw new ValidationException("Adjustment amount is required.");
        if (assessmentDate == null) throw new ValidationException("Assessment date is required.");
    }
    @Override
    public void printDetails() {
        System.out.println("Assessment: " + assessmentId + ", Total: " + getTotalAmount() + " RWF, Date: " + assessmentDate + ", Status: " + status);
    }
    @Override
    public String getSummary() {
        return assessmentId + " - Total: " + getTotalAmount() + " RWF [" + status + "]";
    }
}
