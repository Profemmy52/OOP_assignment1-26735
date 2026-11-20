package com.auca.taxclearance;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.revenueofficer.RevenueOfficer;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;
public class TaxClearance implements Identifiable, Validatable {
    private String clearanceId;
    private RegisteredTaxpayer taxpayer;
    private LocalDate requestDate;
    private LocalDate expiryDate;
    private String status;
    private RevenueOfficer approvedBy;
    public TaxClearance() {}
    public TaxClearance(String clearanceId, RegisteredTaxpayer taxpayer, LocalDate requestDate,
                        LocalDate expiryDate, String status, RevenueOfficer approvedBy) {
        this.clearanceId = clearanceId;
        this.taxpayer = taxpayer;
        this.requestDate = requestDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.approvedBy = approvedBy;
    }
    public String getClearanceId() { return clearanceId; }
    public void setClearanceId(String id) { this.clearanceId = id; }
    public RegisteredTaxpayer getTaxpayer() { return taxpayer; }
    public void setTaxpayer(RegisteredTaxpayer tp) { this.taxpayer = tp; }
    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate date) { this.requestDate = date; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate date) { this.expiryDate = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public RevenueOfficer getApprovedBy() { return approvedBy; }
    public void setApprovedBy(RevenueOfficer officer) { this.approvedBy = officer; }
    @Override
    public String getId() { return clearanceId; }
    @Override
    public String getDisplayName() { return clearanceId + " - Status: " + status; }
    @Override
    public boolean isActive() { return "APPROVED".equalsIgnoreCase(status); }
    @Override
    public void validate() throws ValidationException {
        if (clearanceId == null || clearanceId.isEmpty()) throw new ValidationException("Clearance ID is required.");
        if (taxpayer == null) throw new ValidationException("Taxpayer reference is required.");
        if (requestDate == null) throw new ValidationException("Request date is required.");
    }
    @Override
    public void printDetails() {
        System.out.println("Clearance: " + clearanceId + ", Taxpayer: " + (taxpayer != null ? taxpayer.getTin() : "N/A") + ", Status: " + status + ", ApprovedBy: " + (approvedBy != null ? approvedBy.getStaffNumber() : "Pending"));
    }
    @Override
    public String getSummary() {
        return clearanceId + " - " + status + " (" + (taxpayer != null ? taxpayer.getTin() : "N/A") + ")";
    }
}
