package com.auca.taxobligation;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.taxtype.TaxType;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;
public class TaxObligation implements Identifiable, Validatable {
    private String obligationId;
    private RegisteredTaxpayer taxpayer;
    private TaxType taxType;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private LocalDate dueDate;
    public TaxObligation() {}
    public TaxObligation(String obligationId, RegisteredTaxpayer taxpayer, TaxType taxType,
                         LocalDate periodStart, LocalDate periodEnd, LocalDate dueDate) {
        this.obligationId = obligationId;
        this.taxpayer = taxpayer;
        this.taxType = taxType;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.dueDate = dueDate;
    }
    public String getObligationId() { return obligationId; }
    public void setObligationId(String id) { this.obligationId = id; }
    public RegisteredTaxpayer getTaxpayer() { return taxpayer; }
    public void setTaxpayer(RegisteredTaxpayer tp) { this.taxpayer = tp; }
    public TaxType getTaxType() { return taxType; }
    public void setTaxType(TaxType tt) { this.taxType = tt; }
    public LocalDate getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDate start) { this.periodStart = start; }
    public LocalDate getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDate end) { this.periodEnd = end; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate due) { this.dueDate = due; }
    @Override
    public String getId() { return obligationId; }
    @Override
    public String getDisplayName() {
        return obligationId + " [" + (taxType != null ? taxType.getTaxCode() : "N/A") + "]";
    }
    @Override
    public boolean isActive() {
        return taxpayer != null && taxpayer.isActive() && taxType != null && taxType.isActive();
    }
    @Override
    public void validate() throws ValidationException {
        if (obligationId == null || obligationId.isEmpty()) throw new ValidationException("Obligation ID is required.");
        if (taxpayer == null) throw new ValidationException("Taxpayer reference is required.");
        if (taxType == null) throw new ValidationException("Tax type reference is required.");
        if (periodStart == null || periodEnd == null) throw new ValidationException("Period dates are required.");
        if (periodStart.isAfter(periodEnd)) throw new ValidationException("Period start cannot be after period end.");
        if (dueDate == null) throw new ValidationException("Due date is required.");
    }
    @Override
    public void printDetails() {
        System.out.println("Obligation: " + obligationId + ", Taxpayer: " + (taxpayer != null ? taxpayer.getTin() : "N/A") + ", Tax: " + (taxType != null ? taxType.getTaxCode() : "N/A") + ", Due: " + dueDate);
    }
    @Override
    public String getSummary() {
        return obligationId + " - " + (taxType != null ? taxType.getTaxCode() : "N/A") + " (Due: " + dueDate + ")";
    }
}
