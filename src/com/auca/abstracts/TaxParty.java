package com.auca.abstracts;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;
public abstract class TaxParty implements Identifiable, Validatable {
    private String partyId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    public TaxParty() {}
    public TaxParty(String partyId, String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.partyId = partyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
    public String getPartyId() { return partyId; }
    public void setPartyId(String partyId) { this.partyId = partyId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    @Override
    public String getId() { return partyId; }
    @Override
    public String getDisplayName() { return firstName + " " + lastName; }
    @Override
    public abstract boolean isActive();
    @Override
    public void validate() throws ValidationException {
        if (partyId == null || partyId.length() < 4) throw new ValidationException("Party ID must be at least 4 characters.");
        if (firstName == null || firstName.isEmpty()) throw new ValidationException("First name is required.");
        if (lastName == null || lastName.isEmpty()) throw new ValidationException("Last name is required.");
        if (email == null || !email.contains("@")) throw new ValidationException("Valid email is required.");
        if (phoneNumber == null || phoneNumber.length() < 10) throw new ValidationException("Phone number must have at least 10 digits.");
        if (dateOfBirth == null) throw new ValidationException("Date of birth is required.");
    }
    @Override
    public void printDetails() {
        System.out.println("Party ID: " + partyId + ", Name: " + getDisplayName() + ", Email: " + email + ", Phone: " + phoneNumber);
    }
    @Override
    public String getSummary() {
        return partyId + " - " + getDisplayName();
    }
}
