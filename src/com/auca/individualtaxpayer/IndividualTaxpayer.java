package com.auca.individualtaxpayer;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
public class IndividualTaxpayer extends RegisteredTaxpayer {
    private String nationalId;
    private String occupation;
    private String businessName;
    private LocalDate businessStartDate;
    private BigDecimal annualTurnover;
    private boolean resident;
    public IndividualTaxpayer() {}
    public IndividualTaxpayer(String partyId, String firstName, String lastName, String email, String phoneNumber,
                              LocalDate dateOfBirth, String tin, LocalDate registrationDate, String taxpayerStatus,
                              String district, String sector, String taxOffice,
                              String nationalId, String occupation, String businessName,
                              LocalDate businessStartDate, BigDecimal annualTurnover, boolean resident) {
        super(partyId, firstName, lastName, email, phoneNumber, dateOfBirth,
              tin, registrationDate, taxpayerStatus, district, sector, taxOffice);
        this.nationalId = nationalId;
        this.occupation = occupation;
        this.businessName = businessName;
        this.businessStartDate = businessStartDate;
        this.annualTurnover = annualTurnover;
        this.resident = resident;
    }
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    public LocalDate getBusinessStartDate() { return businessStartDate; }
    public void setBusinessStartDate(LocalDate businessStartDate) { this.businessStartDate = businessStartDate; }
    public BigDecimal getAnnualTurnover() { return annualTurnover; }
    public void setAnnualTurnover(BigDecimal annualTurnover) { this.annualTurnover = annualTurnover; }
    public boolean isResident() { return resident; }
    public void setResident(boolean resident) { this.resident = resident; }
    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (nationalId == null || nationalId.length() != 16) throw new ValidationException("National ID must be exactly 16 digits.");
        if (occupation == null || occupation.isEmpty()) throw new ValidationException("Occupation is required.");
        if (businessName == null || businessName.isEmpty()) throw new ValidationException("Business name is required.");
        if (businessStartDate == null) throw new ValidationException("Business start date is required.");
        if (annualTurnover == null || annualTurnover.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Turnover cannot be negative.");
    }
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("National ID: " + nationalId + ", Occupation: " + occupation + ", Business: " + businessName + ", Turnover: " + annualTurnover + ", Resident: " + (resident ? "YES" : "NO"));
    }
    @Override
    public String getSummary() {
        return "[Individual] " + getTin() + " - " + getDisplayName() + " - " + occupation;
    }
}
