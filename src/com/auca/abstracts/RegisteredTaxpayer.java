package com.auca.abstracts;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;
public abstract class RegisteredTaxpayer extends TaxParty {
    private String tin;
    private LocalDate registrationDate;
    private String taxpayerStatus;
    private String district;
    private String sector;
    private String taxOffice;
    public RegisteredTaxpayer() {}
    public RegisteredTaxpayer(String partyId, String firstName, String lastName, String email, String phoneNumber,
                              LocalDate dateOfBirth, String tin, LocalDate registrationDate, String taxpayerStatus,
                              String district, String sector, String taxOffice) {
        super(partyId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.tin = tin;
        this.registrationDate = registrationDate;
        this.taxpayerStatus = taxpayerStatus;
        this.district = district;
        this.sector = sector;
        this.taxOffice = taxOffice;
    }
    public String getTin() { return tin; }
    public void setTin(String tin) { this.tin = tin; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public String getTaxpayerStatus() { return taxpayerStatus; }
    public void setTaxpayerStatus(String taxpayerStatus) { this.taxpayerStatus = taxpayerStatus; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    public String getTaxOffice() { return taxOffice; }
    public void setTaxOffice(String taxOffice) { this.taxOffice = taxOffice; }
    @Override
    public String getId() { return (tin != null) ? tin : getPartyId(); }
    @Override
    public boolean isActive() { return "ACTIVE".equalsIgnoreCase(taxpayerStatus); }
    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (tin == null || tin.length() != 9) throw new ValidationException("TIN must be exactly 9 digits.");
        if (registrationDate == null) throw new ValidationException("Registration date is required.");
        if (taxpayerStatus == null) throw new ValidationException("Taxpayer status is required.");
        if (district == null || district.isEmpty()) throw new ValidationException("District is required.");
        if (sector == null || sector.isEmpty()) throw new ValidationException("Sector is required.");
        if (taxOffice == null || taxOffice.isEmpty()) throw new ValidationException("Tax office is required.");
    }
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("TIN: " + tin + ", RegDate: " + registrationDate + ", Status: " + taxpayerStatus + ", Office: " + taxOffice);
    }
    @Override
    public String getSummary() {
        return "TIN: " + tin + " - " + getDisplayName() + " (" + taxpayerStatus + ")";
    }
}
