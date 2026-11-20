package com.auca.taxdeclaration;
import com.auca.taxobligation.TaxObligation;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
public class TaxDeclaration implements Identifiable, Validatable {
    private String declarationId;
    private TaxObligation obligation;
    private LocalDate filingDate;
    private BigDecimal taxableAmount;
    private BigDecimal declaredTax;
    private String status;
    public TaxDeclaration() {}
    public TaxDeclaration(String declarationId, TaxObligation obligation, LocalDate filingDate,
                          BigDecimal taxableAmount, BigDecimal declaredTax, String status) {
        this.declarationId = declarationId;
        this.obligation = obligation;
        this.filingDate = filingDate;
        this.taxableAmount = taxableAmount;
        this.declaredTax = declaredTax;
        this.status = status;
    }
    public String getDeclarationId() { return declarationId; }
    public void setDeclarationId(String id) { this.declarationId = id; }
    public TaxObligation getObligation() { return obligation; }
    public void setObligation(TaxObligation obl) { this.obligation = obl; }
    public LocalDate getFilingDate() { return filingDate; }
    public void setFilingDate(LocalDate date) { this.filingDate = date; }
    public BigDecimal getTaxableAmount() { return taxableAmount; }
    public void setTaxableAmount(BigDecimal amt) { this.taxableAmount = amt; }
    public BigDecimal getDeclaredTax() { return declaredTax; }
    public void setDeclaredTax(BigDecimal tax) { this.declaredTax = tax; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal calculateExpectedTax() {
        if (obligation == null || obligation.getTaxType() == null || taxableAmount == null) return BigDecimal.ZERO;
        return taxableAmount.multiply(obligation.getTaxType().getRate()).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
    @Override
    public String getId() { return declarationId; }
    @Override
    public String getDisplayName() { return declarationId + " (" + status + ") - " + declaredTax + " RWF"; }
    @Override
    public boolean isActive() { return !"REJECTED".equalsIgnoreCase(status); }
    @Override
    public void validate() throws ValidationException {
        if (declarationId == null || declarationId.isEmpty()) throw new ValidationException("Declaration ID is required.");
        if (obligation == null) throw new ValidationException("Obligation reference is required.");
        if (filingDate == null) throw new ValidationException("Filing date is required.");
        if (taxableAmount == null || taxableAmount.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Taxable amount must be >= 0.");
        if (declaredTax == null || declaredTax.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Declared tax must be >= 0.");
    }
    @Override
    public void printDetails() {
        System.out.println("Declaration: " + declarationId + ", Obligation: " + (obligation != null ? obligation.getId() : "N/A") + ", Taxable: " + taxableAmount + ", DeclaredTax: " + declaredTax + ", Status: " + status);
    }
    @Override
    public String getSummary() {
        return declarationId + " - Tax: " + declaredTax + " RWF (" + status + ")";
    }
}
