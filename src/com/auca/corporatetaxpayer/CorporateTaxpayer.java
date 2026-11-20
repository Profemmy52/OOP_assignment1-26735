package com.auca.corporatetaxpayer;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
public class CorporateTaxpayer extends RegisteredTaxpayer {
    private String companyRegistrationNo;
    private String legalName;
    private LocalDate incorporationDate;
    private String businessSector;
    private String authorizedRepresentative;
    private BigDecimal annualTurnover;
    public CorporateTaxpayer() {}
    public CorporateTaxpayer(String partyId, String firstName, String lastName, String email, String phoneNumber,
                             LocalDate dateOfBirth, String tin, LocalDate registrationDate, String taxpayerStatus,
                             String district, String sector, String taxOffice,
                             String companyRegistrationNo, String legalName, LocalDate incorporationDate,
                             String businessSector, String authorizedRepresentative, BigDecimal annualTurnover) {
        super(partyId, firstName, lastName, email, phoneNumber, dateOfBirth,
              tin, registrationDate, taxpayerStatus, district, sector, taxOffice);
        this.companyRegistrationNo = companyRegistrationNo;
        this.legalName = legalName;
        this.incorporationDate = incorporationDate;
        this.businessSector = businessSector;
        this.authorizedRepresentative = authorizedRepresentative;
        this.annualTurnover = annualTurnover;
    }
    public String getCompanyRegistrationNo() { return companyRegistrationNo; }
    public void setCompanyRegistrationNo(String compNo) { this.companyRegistrationNo = compNo; }
    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }
    public LocalDate getIncorporationDate() { return incorporationDate; }
    public void setIncorporationDate(LocalDate date) { this.incorporationDate = date; }
    public String getBusinessSector() { return businessSector; }
    public void setBusinessSector(String sector) { this.businessSector = sector; }
    public String getAuthorizedRepresentative() { return authorizedRepresentative; }
    public void setAuthorizedRepresentative(String rep) { this.authorizedRepresentative = rep; }
    public BigDecimal getAnnualTurnover() { return annualTurnover; }
    public void setAnnualTurnover(BigDecimal turnover) { this.annualTurnover = turnover; }
    @Override
    public String getDisplayName() { return (legalName != null) ? legalName : super.getDisplayName(); }
    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (companyRegistrationNo == null || companyRegistrationNo.isEmpty()) throw new ValidationException("Company reg no is required.");
        if (legalName == null || legalName.length() < 3) throw new ValidationException("Legal name must be at least 3 characters.");
        if (incorporationDate == null) throw new ValidationException("Incorporation date is required.");
        if (businessSector == null || businessSector.isEmpty()) throw new ValidationException("Business sector is required.");
        if (authorizedRepresentative == null || authorizedRepresentative.isEmpty()) throw new ValidationException("Representative is required.");
        if (annualTurnover == null || annualTurnover.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Turnover cannot be negative.");
    }
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Company Reg: " + companyRegistrationNo + ", Legal Name: " + legalName + ", IncDate: " + incorporationDate + ", Sector: " + businessSector + ", Turnover: " + annualTurnover);
    }
    @Override
    public String getSummary() {
        return "[Corporate] " + getTin() + " - " + legalName + " (" + companyRegistrationNo + ")";
    }
}
