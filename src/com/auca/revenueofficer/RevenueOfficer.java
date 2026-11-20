package com.auca.revenueofficer;
import com.auca.abstracts.TaxParty;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
public class RevenueOfficer extends TaxParty {
    private String staffNumber;
    private String department;
    private String role;
    private LocalDate hireDate;
    private BigDecimal approvalLimit;
    private boolean active;
    public RevenueOfficer() {}
    public RevenueOfficer(String partyId, String firstName, String lastName, String email, String phoneNumber,
                          LocalDate dateOfBirth, String staffNumber, String department, String role,
                          LocalDate hireDate, BigDecimal approvalLimit, boolean active) {
        super(partyId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.staffNumber = staffNumber;
        this.department = department;
        this.role = role;
        this.hireDate = hireDate;
        this.approvalLimit = approvalLimit;
        this.active = active;
    }
    public String getStaffNumber() { return staffNumber; }
    public void setStaffNumber(String staffNumber) { this.staffNumber = staffNumber; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public BigDecimal getApprovalLimit() { return approvalLimit; }
    public void setApprovalLimit(BigDecimal approvalLimit) { this.approvalLimit = approvalLimit; }
    public void setActive(boolean active) { this.active = active; }
    @Override
    public String getId() { return (staffNumber != null) ? staffNumber : getPartyId(); }
    @Override
    public boolean isActive() { return active; }
    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (staffNumber == null || staffNumber.isEmpty()) throw new ValidationException("Staff number is required.");
        if (department == null || department.isEmpty()) throw new ValidationException("Department is required.");
        if (role == null || role.isEmpty()) throw new ValidationException("Role is required.");
        if (hireDate == null) throw new ValidationException("Hire date is required.");
        if (approvalLimit == null || approvalLimit.compareTo(BigDecimal.ZERO) < 0) throw new ValidationException("Approval limit cannot be negative.");
    }
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Staff No: " + staffNumber + ", Dept: " + department + ", Role: " + role + ", Limit: " + approvalLimit + ", Active: " + (active ? "YES" : "NO"));
    }
    @Override
    public String getSummary() {
        return "[Officer] " + staffNumber + " - " + getDisplayName() + " (" + role + ")";
    }
}
