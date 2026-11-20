package com.auca.penalty;
import com.auca.taxobligation.TaxObligation;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Penalty implements Identifiable, Validatable {
    private String penaltyId;
    private TaxObligation obligation;
    private String penaltyType;
    private BigDecimal baseAmount;
    private BigDecimal penaltyRate;
    private BigDecimal penaltyAmount;
    public Penalty() {}
    public Penalty(String penaltyId, TaxObligation obligation, String penaltyType,
                   BigDecimal baseAmount, BigDecimal penaltyRate, BigDecimal penaltyAmount) {
        this.penaltyId = penaltyId;
        this.obligation = obligation;
        this.penaltyType = penaltyType;
        this.baseAmount = baseAmount;
        this.penaltyRate = penaltyRate;
        this.penaltyAmount = penaltyAmount;
    }
    public String getPenaltyId() { return penaltyId; }
    public void setPenaltyId(String id) { this.penaltyId = id; }
    public TaxObligation getObligation() { return obligation; }
    public void setObligation(TaxObligation obl) { this.obligation = obl; }
    public String getPenaltyType() { return penaltyType; }
    public void setPenaltyType(String type) { this.penaltyType = type; }
    public BigDecimal getBaseAmount() { return baseAmount; }
    public void setBaseAmount(BigDecimal base) { this.baseAmount = base; }
    public BigDecimal getPenaltyRate() { return penaltyRate; }
    public void setPenaltyRate(BigDecimal rate) { this.penaltyRate = rate; }
    public BigDecimal getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(BigDecimal amt) { this.penaltyAmount = amt; }
    public BigDecimal calculatePenalty() {
        if (baseAmount == null || penaltyRate == null) return BigDecimal.ZERO;
        return baseAmount.multiply(penaltyRate).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
    @Override
    public String getId() { return penaltyId; }
    @Override
    public String getDisplayName() { return penaltyId + " (" + penaltyType + ") - " + penaltyAmount + " RWF"; }
    @Override
    public boolean isActive() { return true; }
    @Override
    public void validate() throws ValidationException {
        if (penaltyId == null || penaltyId.isEmpty()) throw new ValidationException("Penalty ID is required.");
        if (obligation == null) throw new ValidationException("Obligation reference is required.");
        if (penaltyType == null || penaltyType.isEmpty()) throw new ValidationException("Penalty type is required.");
        if (baseAmount == null || baseAmount.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Base amount must be >= 0.");
        if (penaltyRate == null || penaltyRate.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Penalty rate must be >= 0.");
    }
    @Override
    public void printDetails() {
        System.out.println("Penalty: " + penaltyId + ", Type: " + penaltyType + ", Base: " + baseAmount + ", Rate: " + penaltyRate + "%, Amount: " + penaltyAmount + " RWF");
    }
    @Override
    public String getSummary() {
        return penaltyId + " - " + penaltyType + " (" + penaltyAmount + " RWF)";
    }
}
