package com.auca.taxtype;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
public class TaxType implements Identifiable, Validatable {
    private String taxTypeId;
    private String taxCode;
    private String taxName;
    private BigDecimal rate;
    private String filingFrequency;
    private boolean active;
    public TaxType() {}
    public TaxType(String taxTypeId, String taxCode, String taxName, BigDecimal rate, String filingFrequency, boolean active) {
        this.taxTypeId = taxTypeId;
        this.taxCode = taxCode;
        this.taxName = taxName;
        this.rate = rate;
        this.filingFrequency = filingFrequency;
        this.active = active;
    }
    public String getTaxTypeId() { return taxTypeId; }
    public void setTaxTypeId(String id) { this.taxTypeId = id; }
    public String getTaxCode() { return taxCode; }
    public void setTaxCode(String code) { this.taxCode = code; }
    public String getTaxName() { return taxName; }
    public void setTaxName(String name) { this.taxName = name; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
    public String getFilingFrequency() { return filingFrequency; }
    public void setFilingFrequency(String freq) { this.filingFrequency = freq; }
    public void setActive(boolean active) { this.active = active; }
    @Override
    public String getId() { return taxTypeId; }
    @Override
    public String getDisplayName() { return taxName + " (" + taxCode + ") - " + rate + "%"; }
    @Override
    public boolean isActive() { return active; }
    @Override
    public void validate() throws ValidationException {
        if (taxTypeId == null || taxTypeId.isEmpty()) throw new ValidationException("Tax type ID is required.");
        if (taxCode == null || taxCode.isEmpty()) throw new ValidationException("Tax code is required.");
        if (taxName == null || taxName.isEmpty()) throw new ValidationException("Tax name is required.");
        if (rate == null || rate.compareTo(BigDecimal.ZERO) < 0 || rate.compareTo(new BigDecimal("100")) > 0) {
            throw new ValidationException("Rate must be between 0 and 100.");
        }
    }
    @Override
    public void printDetails() {
        System.out.println("TaxType: " + taxTypeId + ", Code: " + taxCode + ", Name: " + taxName + ", Rate: " + rate + "%, Freq: " + filingFrequency + ", Active: " + active);
    }
    @Override
    public String getSummary() {
        return taxCode + " - " + taxName + " (" + rate + "%)";
    }
}
