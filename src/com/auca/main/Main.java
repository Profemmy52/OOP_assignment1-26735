package com.auca.main;
import com.auca.abstracts.RegisteredTaxpayer;
import com.auca.abstracts.TaxParty;
import com.auca.individualtaxpayer.IndividualTaxpayer;
import com.auca.corporatetaxpayer.CorporateTaxpayer;
import com.auca.revenueofficer.RevenueOfficer;
import com.auca.taxtype.TaxType;
import com.auca.taxobligation.TaxObligation;
import com.auca.taxdeclaration.TaxDeclaration;
import com.auca.taxassessment.TaxAssessment;
import com.auca.taxpayment.TaxPayment;
import com.auca.penalty.Penalty;
import com.auca.taxrefund.TaxRefund;
import com.auca.taxclearance.TaxClearance;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Validatable;
import com.auca.exceptions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static IndividualTaxpayer[] indList = new IndividualTaxpayer[100];
    static int indCount = 0;
    static CorporateTaxpayer[] corpList = new CorporateTaxpayer[100];
    static int corpCount = 0;
    static RevenueOfficer[] officerList = new RevenueOfficer[50];
    static int officerCount = 0;
    static TaxType[] typeList = new TaxType[50];
    static int typeCount = 0;
    static TaxObligation[] oblList = new TaxObligation[200];
    static int oblCount = 0;
    static TaxDeclaration[] declList = new TaxDeclaration[200];
    static int declCount = 0;
    static TaxAssessment[] assessList = new TaxAssessment[200];
    static int assessCount = 0;
    static TaxPayment[] payList = new TaxPayment[300];
    static int payCount = 0;
    static Penalty[] penList = new Penalty[200];
    static int penCount = 0;
    static TaxRefund[] refundList = new TaxRefund[100];
    static int refundCount = 0;
    static TaxClearance[] clearList = new TaxClearance[100];
    static int clearCount = 0;
    public static void main(String[] args) {
        initSampleData();
        while (true) {
            System.out.println("\n================ TAX ADMINISTRATION MANAGEMENT ================");
            System.out.println(" 1 Register Individual Taxpayer    12 Record Tax Payment");
            System.out.println(" 2 Register Corporate Taxpayer     13 Calculate / Record Penalty");
            System.out.println(" 3 Register Revenue Officer        14 Request / Approve Refund");
            System.out.println(" 4 Register Tax Type               15 Request / Issue Tax Clearance");
            System.out.println(" 5 Create Tax Obligation           16 Search Record by ID / TIN");
            System.out.println(" 6 File Tax Declaration            17 Update Eligible Record");
            System.out.println(" 7 Assess Declaration              18 View Taxpayer Statement");
            System.out.println(" 8 Confirm Assessment              19 View Outstanding Balances");
            System.out.println(" 9 View All Records                20 View Tax Reports");
            System.out.println("10 View Declaration History        21 Demonstrate Polymorphism");
            System.out.println("11 View Assessment History          0 Exit");
            System.out.print("Select option: ");
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1: registerIndividual(); break;
                    case 2: registerCorporate(); break;
                    case 3: registerOfficer(); break;
                    case 4: registerTaxType(); break;
                    case 5: createObligation(); break;
                    case 6: fileDeclaration(); break;
                    case 7: assessDeclaration(); break;
                    case 8: confirmAssessment(); break;
                    case 9: viewAllRecords(); break;
                    case 10: viewDeclarationHistory(); break;
                    case 11: viewAssessmentHistory(); break;
                    case 12: recordPayment(); break;
                    case 13: calculatePenalty(); break;
                    case 14: handleRefund(); break;
                    case 15: handleClearance(); break;
                    case 16: searchRecord(); break;
                    case 17: updateRecord(); break;
                    case 18: viewStatement(); break;
                    case 19: viewOutstandingBalances(); break;
                    case 20: viewReports(); break;
                    case 21: demonstratePolymorphism(); break;
                    default: System.out.println("Invalid option! Please enter 0-21.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    static void initSampleData() {
        typeList[typeCount++] = new TaxType("TT-01", "VAT", "Value Added Tax", new BigDecimal("18.00"), "MONTHLY", true);
        typeList[typeCount++] = new TaxType("TT-02", "CIT", "Corporate Income Tax", new BigDecimal("30.00"), "ANNUAL", true);
        officerList[officerCount++] = new RevenueOfficer("P-OFF1", "Jean", "Kagabo", "jean@rra.gov.rw", "+250788111222",
                LocalDate.of(1985, 5, 12), "STF-101", "Audit", "Senior Inspector", LocalDate.of(2015, 1, 10), new BigDecimal("5000000.00"), true);
        IndividualTaxpayer ind = new IndividualTaxpayer("P-IND1", "Emmanuel", "Mugabo", "emmanuel@auca.ac.rw", "+250788555666",
                LocalDate.of(1998, 4, 14), "100234567", LocalDate.of(2021, 1, 15), "ACTIVE", "Gasabo", "Remera", "Kigali 1",
                "1199880012345678", "Software Engineer", "TechNova", LocalDate.of(2021, 2, 1), new BigDecimal("15000000.00"), true);
        indList[indCount++] = ind;
        corpList[corpCount++] = new CorporateTaxpayer("P-CORP1", "Diane", "Mutoni", "info@innovations.rw", "+250788777888",
                LocalDate.of(1990, 6, 10), "200345678", LocalDate.of(2020, 3, 10), "ACTIVE", "Nyarugenge", "Kiyovu", "LTO",
                "CPR-2020-9988", "Kigali Innovations Ltd", LocalDate.of(2019, 6, 10), "IT", "Diane Mutoni", new BigDecimal("85000000.00"));
        oblList[oblCount++] = new TaxObligation("OBL-001", ind, typeList[0], LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31), LocalDate.of(2026, 2, 15));
    }
    static RegisteredTaxpayer findTaxpayer(String tin) {
        for (int i = 0; i < indCount; i++) if (indList[i].getTin().equalsIgnoreCase(tin)) return indList[i];
        for (int i = 0; i < corpCount; i++) if (corpList[i].getTin().equalsIgnoreCase(tin)) return corpList[i];
        return null;
    }
    static TaxType findTaxType(String code) {
        for (int i = 0; i < typeCount; i++) {
            if (typeList[i].getTaxCode().equalsIgnoreCase(code) || typeList[i].getTaxTypeId().equalsIgnoreCase(code)) return typeList[i];
        }
        return null;
    }
    static TaxObligation findObligation(String id) {
        for (int i = 0; i < oblCount; i++) if (oblList[i].getObligationId().equalsIgnoreCase(id)) return oblList[i];
        return null;
    }
    static TaxAssessment findAssessment(String id) {
        for (int i = 0; i < assessCount; i++) if (assessList[i].getAssessmentId().equalsIgnoreCase(id)) return assessList[i];
        return null;
    }
    static RevenueOfficer findOfficer(String staff) {
        for (int i = 0; i < officerCount; i++) if (officerList[i].getStaffNumber().equalsIgnoreCase(staff)) return officerList[i];
        return null;
    }
    static void registerIndividual() throws Exception {
        System.out.println("\n--- Register Individual Taxpayer ---");
        System.out.print("Party ID: "); String pid = scanner.nextLine().trim();
        System.out.print("First Name: "); String fn = scanner.nextLine().trim();
        System.out.print("Last Name: "); String ln = scanner.nextLine().trim();
        System.out.print("Email: "); String em = scanner.nextLine().trim();
        System.out.print("Phone: "); String ph = scanner.nextLine().trim();
        System.out.print("Date of Birth (YYYY-MM-DD): "); LocalDate dob = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("TIN (9 digits): "); String tin = scanner.nextLine().trim();
        if (findTaxpayer(tin) != null) throw new DuplicateRecordException("TIN " + tin + " already exists!");
        System.out.print("District: "); String dst = scanner.nextLine().trim();
        System.out.print("Sector: "); String sct = scanner.nextLine().trim();
        System.out.print("Tax Office: "); String off = scanner.nextLine().trim();
        System.out.print("National ID (16 digits): "); String nid = scanner.nextLine().trim();
        System.out.print("Occupation: "); String occ = scanner.nextLine().trim();
        System.out.print("Business Name: "); String bn = scanner.nextLine().trim();
        System.out.print("Annual Turnover: "); BigDecimal to = new BigDecimal(scanner.nextLine().trim());
        IndividualTaxpayer it = new IndividualTaxpayer(pid, fn, ln, em, ph, dob, tin, LocalDate.now(), "ACTIVE",
                dst, sct, off, nid, occ, bn, LocalDate.now(), to, true);
        it.validate();
        indList[indCount++] = it;
        System.out.println(">> Registered Successfully: " + it.getSummary());
    }
    static void registerCorporate() throws Exception {
        System.out.println("\n--- Register Corporate Taxpayer ---");
        System.out.print("Party ID: "); String pid = scanner.nextLine().trim();
        System.out.print("Rep First Name: "); String fn = scanner.nextLine().trim();
        System.out.print("Rep Last Name: "); String ln = scanner.nextLine().trim();
        System.out.print("Company Email: "); String em = scanner.nextLine().trim();
        System.out.print("Company Phone: "); String ph = scanner.nextLine().trim();
        System.out.print("Contact DOB (YYYY-MM-DD): "); LocalDate dob = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("TIN (9 digits): "); String tin = scanner.nextLine().trim();
        if (findTaxpayer(tin) != null) throw new DuplicateRecordException("TIN " + tin + " already exists!");
        System.out.print("District: "); String dst = scanner.nextLine().trim();
        System.out.print("Sector: "); String sct = scanner.nextLine().trim();
        System.out.print("Tax Office: "); String off = scanner.nextLine().trim();
        System.out.print("Company Reg No: "); String regNo = scanner.nextLine().trim();
        System.out.print("Legal Name: "); String legalName = scanner.nextLine().trim();
        System.out.print("Business Sector: "); String sector = scanner.nextLine().trim();
        System.out.print("Annual Turnover: "); BigDecimal to = new BigDecimal(scanner.nextLine().trim());
        CorporateTaxpayer ct = new CorporateTaxpayer(pid, fn, ln, em, ph, dob, tin, LocalDate.now(), "ACTIVE",
                dst, sct, off, regNo, legalName, LocalDate.now(), sector, fn + " " + ln, to);
        ct.validate();
        corpList[corpCount++] = ct;
        System.out.println(">> Registered Successfully: " + ct.getSummary());
    }
    static void registerOfficer() throws Exception {
        System.out.println("\n--- Register Revenue Officer ---");
        System.out.print("Party ID: "); String pid = scanner.nextLine().trim();
        System.out.print("First Name: "); String fn = scanner.nextLine().trim();
        System.out.print("Last Name: "); String ln = scanner.nextLine().trim();
        System.out.print("Email: "); String em = scanner.nextLine().trim();
        System.out.print("Phone: "); String ph = scanner.nextLine().trim();
        System.out.print("DOB (YYYY-MM-DD): "); LocalDate dob = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Staff Number: "); String staff = scanner.nextLine().trim();
        System.out.print("Department: "); String dept = scanner.nextLine().trim();
        System.out.print("Role: "); String role = scanner.nextLine().trim();
        System.out.print("Approval Limit: "); BigDecimal lim = new BigDecimal(scanner.nextLine().trim());
        RevenueOfficer ro = new RevenueOfficer(pid, fn, ln, em, ph, dob, staff, dept, role, LocalDate.now(), lim, true);
        ro.validate();
        officerList[officerCount++] = ro;
        System.out.println(">> Registered Officer: " + ro.getSummary());
    }
    static void registerTaxType() throws Exception {
        System.out.println("\n--- Register Tax Type ---");
        System.out.print("Tax Type ID: "); String tid = scanner.nextLine().trim();
        System.out.print("Tax Code (e.g. PAYE): "); String code = scanner.nextLine().trim();
        System.out.print("Tax Name: "); String name = scanner.nextLine().trim();
        System.out.print("Rate %: "); BigDecimal rate = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Filing Frequency (MONTHLY/ANNUAL): "); String freq = scanner.nextLine().trim().toUpperCase();
        TaxType tt = new TaxType(tid, code, name, rate, freq, true);
        tt.validate();
        typeList[typeCount++] = tt;
        System.out.println(">> Registered Tax Type: " + tt.getSummary());
    }
    static void createObligation() throws Exception {
        System.out.println("\n--- Create Tax Obligation ---");
        System.out.print("Enter Taxpayer TIN: "); String tin = scanner.nextLine().trim();
        RegisteredTaxpayer tp = findTaxpayer(tin);
        if (tp == null) throw new RecordNotFoundException("Taxpayer TIN " + tin + " not found!");
        System.out.print("Enter Tax Code (e.g. VAT, CIT): "); String code = scanner.nextLine().trim();
        TaxType tt = findTaxType(code);
        if (tt == null) throw new RecordNotFoundException("Tax Type " + code + " not found!");
        System.out.print("Obligation ID: "); String oblId = scanner.nextLine().trim();
        System.out.print("Period Start (YYYY-MM-DD): "); LocalDate start = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Period End (YYYY-MM-DD): "); LocalDate end = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Due Date (YYYY-MM-DD): "); LocalDate due = LocalDate.parse(scanner.nextLine().trim());
        TaxObligation obl = new TaxObligation(oblId, tp, tt, start, end, due);
        obl.validate();
        oblList[oblCount++] = obl;
        System.out.println(">> Obligation Created: " + obl.getSummary());
    }
    static void fileDeclaration() throws Exception {
        System.out.println("\n--- File Tax Declaration ---");
        System.out.print("Enter Obligation ID: "); String oblId = scanner.nextLine().trim();
        TaxObligation obl = findObligation(oblId);
        if (obl == null) throw new RecordNotFoundException("Obligation " + oblId + " not found!");
        System.out.print("Declaration ID: "); String decId = scanner.nextLine().trim();
        System.out.print("Filing Date (YYYY-MM-DD): "); LocalDate fileDate = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Taxable Amount: "); BigDecimal taxable = new BigDecimal(scanner.nextLine().trim());
        BigDecimal rate = obl.getTaxType().getRate();
        BigDecimal expectedTax = taxable.multiply(rate).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        System.out.println("Expected Tax (" + rate + "%): " + expectedTax + " RWF");
        System.out.print("Declared Tax Amount: "); BigDecimal declared = new BigDecimal(scanner.nextLine().trim());
        String status = fileDate.isAfter(obl.getDueDate()) ? "LATE" : "PENDING";
        TaxDeclaration decl = new TaxDeclaration(decId, obl, fileDate, taxable, declared, status);
        decl.validate();
        declList[declCount++] = decl;
        System.out.println(">> Declaration Filed (" + status + "): " + decl.getSummary());
    }
    static void assessDeclaration() throws Exception {
        System.out.println("\n--- Assess Declaration ---");
        System.out.print("Enter Declaration ID: "); String decId = scanner.nextLine().trim();
        TaxDeclaration targetDecl = null;
        for (int i = 0; i < declCount; i++) if (declList[i].getDeclarationId().equalsIgnoreCase(decId)) targetDecl = declList[i];
        if (targetDecl == null) throw new RecordNotFoundException("Declaration " + decId + " not found!");
        System.out.print("Assessment ID: "); String assId = scanner.nextLine().trim();
        System.out.print("Adjustment Amount [e.g. 0.00]: "); BigDecimal adj = new BigDecimal(scanner.nextLine().trim());
        TaxAssessment ass = new TaxAssessment(assId, targetDecl, targetDecl.getDeclaredTax(), adj, LocalDate.now(), "DRAFT");
        ass.validate();
        assessList[assessCount++] = ass;
        System.out.println(">> Assessment Created (DRAFT): Total = " + ass.getTotalAmount() + " RWF");
    }
    static void confirmAssessment() throws Exception {
        System.out.println("\n--- Confirm Assessment ---");
        System.out.print("Enter Assessment ID: "); String assId = scanner.nextLine().trim();
        TaxAssessment ass = findAssessment(assId);
        if (ass == null) throw new RecordNotFoundException("Assessment " + assId + " not found!");
        ass.setStatus("CONFIRMED");
        System.out.println(">> Assessment " + assId + " is now CONFIRMED! Ready for payment.");
    }
    static void viewAllRecords() {
        System.out.println("\n--- ALL RECORDS IN SYSTEM ---");
        System.out.println("Taxpayers:");
        for (int i = 0; i < indCount; i++) System.out.println("  " + indList[i].getSummary());
        for (int i = 0; i < corpCount; i++) System.out.println("  " + corpList[i].getSummary());
        System.out.println("Revenue Officers:");
        for (int i = 0; i < officerCount; i++) System.out.println("  " + officerList[i].getSummary());
        System.out.println("Tax Types:");
        for (int i = 0; i < typeCount; i++) System.out.println("  " + typeList[i].getSummary());
        System.out.println("Obligations:");
        for (int i = 0; i < oblCount; i++) System.out.println("  " + oblList[i].getSummary());
        System.out.println("Declarations:");
        for (int i = 0; i < declCount; i++) System.out.println("  " + declList[i].getSummary());
        System.out.println("Assessments:");
        for (int i = 0; i < assessCount; i++) System.out.println("  " + assessList[i].getSummary());
        System.out.println("Payments:");
        for (int i = 0; i < payCount; i++) System.out.println("  " + payList[i].getSummary());
    }
    static void viewDeclarationHistory() {
        System.out.print("\nEnter Taxpayer TIN or Obligation ID: "); String q = scanner.nextLine().trim();
        for (int i = 0; i < declCount; i++) {
            TaxDeclaration d = declList[i];
            if (d.getObligation().getObligationId().equalsIgnoreCase(q) || d.getObligation().getTaxpayer().getTin().equalsIgnoreCase(q)) {
                d.printDetails();
            }
        }
    }
    static void viewAssessmentHistory() {
        System.out.print("\nEnter Taxpayer TIN or Declaration ID: "); String q = scanner.nextLine().trim();
        for (int i = 0; i < assessCount; i++) {
            TaxAssessment a = assessList[i];
            if (a.getDeclaration().getDeclarationId().equalsIgnoreCase(q) || a.getDeclaration().getObligation().getTaxpayer().getTin().equalsIgnoreCase(q)) {
                a.printDetails();
            }
        }
    }
    static void recordPayment() throws Exception {
        System.out.println("\n--- Record Tax Payment ---");
        System.out.print("Enter Assessment ID: "); String assId = scanner.nextLine().trim();
        TaxAssessment ass = findAssessment(assId);
        if (ass == null) throw new RecordNotFoundException("Assessment not found!");
        if (!"CONFIRMED".equalsIgnoreCase(ass.getStatus())) throw new InvalidStatusTransitionException("Assessment must be CONFIRMED before payment!");
        System.out.print("Payment ID: "); String pid = scanner.nextLine().trim();
        System.out.print("Payment Reference: "); String ref = scanner.nextLine().trim();
        System.out.print("Payment Amount: "); BigDecimal amt = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Payment Method (CASH/MOBILE_MONEY/BANK): "); String method = scanner.nextLine().trim();
        TaxPayment payment = new TaxPayment(pid, ass, ref, amt, LocalDate.now(), method);
        payment.validate();
        payList[payCount++] = payment;
        System.out.println(">> Payment recorded successfully: " + amt + " RWF");
    }
    static void calculatePenalty() throws Exception {
        System.out.println("\n--- Calculate Penalty ---");
        System.out.print("Enter Obligation ID: "); String oblId = scanner.nextLine().trim();
        TaxObligation obl = findObligation(oblId);
        if (obl == null) throw new RecordNotFoundException("Obligation not found!");
        System.out.print("Penalty ID: "); String penId = scanner.nextLine().trim();
        System.out.print("Base Tax Amount: "); BigDecimal base = new BigDecimal(scanner.nextLine().trim());
        BigDecimal rate = new BigDecimal("5.00"); 
        BigDecimal penAmt = base.multiply(rate).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        Penalty p = new Penalty(penId, obl, "LATE_FILING", base, rate, penAmt);
        p.validate();
        penList[penCount++] = p;
        System.out.println(">> Penalty Recorded: " + penAmt + " RWF (5% on " + base + " RWF)");
    }
    static void handleRefund() throws Exception {
        System.out.println("\n[1] Request Refund  [2] Approve Refund");
        System.out.print("Choice: "); String c = scanner.nextLine().trim();
        if (c.equals("1")) {
            System.out.print("Assessment ID: "); String assId = scanner.nextLine().trim();
            TaxAssessment ass = findAssessment(assId);
            if (ass == null) throw new RecordNotFoundException("Assessment not found!");
            System.out.print("Refund ID: "); String rid = scanner.nextLine().trim();
            System.out.print("Refund Amount: "); BigDecimal amt = new BigDecimal(scanner.nextLine().trim());
            TaxRefund ref = new TaxRefund(rid, ass.getDeclaration().getObligation().getTaxpayer(), ass, amt, LocalDate.now(), "PENDING");
            refundList[refundCount++] = ref;
            System.out.println(">> Refund Requested: " + ref.getSummary());
        } else if (c.equals("2")) {
            System.out.print("Enter Refund ID to Approve: "); String rid = scanner.nextLine().trim();
            System.out.print("Enter Officer Staff Number: "); String staff = scanner.nextLine().trim();
            RevenueOfficer ro = findOfficer(staff);
            if (ro == null || !ro.isActive()) throw new UnauthorizedApprovalException("Active officer required!");
            for (int i = 0; i < refundCount; i++) {
                if (refundList[i].getRefundId().equalsIgnoreCase(rid)) {
                    refundList[i].setStatus("APPROVED");
                    System.out.println(">> Refund " + rid + " APPROVED by " + ro.getDisplayName());
                    return;
                }
            }
            System.out.println("Refund ID not found.");
        }
    }
    static void handleClearance() throws Exception {
        System.out.println("\n[1] Request Clearance  [2] Issue Clearance");
        System.out.print("Choice: "); String c = scanner.nextLine().trim();
        if (c.equals("1")) {
            System.out.print("Taxpayer TIN: "); String tin = scanner.nextLine().trim();
            RegisteredTaxpayer tp = findTaxpayer(tin);
            if (tp == null) throw new RecordNotFoundException("Taxpayer not found!");
            System.out.print("Clearance ID: "); String cid = scanner.nextLine().trim();
            TaxClearance clr = new TaxClearance(cid, tp, LocalDate.now(), null, "PENDING", null);
            clearList[clearCount++] = clr;
            System.out.println(">> Clearance Requested: " + clr.getSummary());
        } else if (c.equals("2")) {
            System.out.print("Enter Clearance ID: "); String cid = scanner.nextLine().trim();
            System.out.print("Approving Officer Staff No: "); String staff = scanner.nextLine().trim();
            RevenueOfficer ro = findOfficer(staff);
            if (ro == null) throw new RecordNotFoundException("Officer not found!");
            for (int i = 0; i < clearCount; i++) {
                if (clearList[i].getClearanceId().equalsIgnoreCase(cid)) {
                    clearList[i].setStatus("APPROVED");
                    clearList[i].setApprovedBy(ro);
                    clearList[i].setExpiryDate(LocalDate.now().plusMonths(3));
                    System.out.println(">> Clearance Certificate APPROVED until " + clearList[i].getExpiryDate());
                    return;
                }
            }
            System.out.println("Clearance ID not found.");
        }
    }
    static void searchRecord() {
        System.out.print("\nEnter ID or TIN to search: "); String q = scanner.nextLine().trim();
        RegisteredTaxpayer tp = findTaxpayer(q);
        if (tp != null) { tp.printDetails(); return; }
        TaxType tt = findTaxType(q);
        if (tt != null) { tt.printDetails(); return; }
        TaxObligation obl = findObligation(q);
        if (obl != null) { obl.printDetails(); return; }
        TaxAssessment ass = findAssessment(q);
        if (ass != null) { ass.printDetails(); return; }
        RevenueOfficer ro = findOfficer(q);
        if (ro != null) { ro.printDetails(); return; }
        System.out.println("No matching record found.");
    }
    static void updateRecord() {
        System.out.print("\nEnter Taxpayer TIN: "); String tin = scanner.nextLine().trim();
        RegisteredTaxpayer tp = findTaxpayer(tin);
        if (tp == null) { System.out.println("Taxpayer not found."); return; }
        System.out.print("New Phone (or Enter to skip): "); String ph = scanner.nextLine().trim();
        if (!ph.isEmpty()) tp.setPhoneNumber(ph);
        System.out.print("New Email (or Enter to skip): "); String em = scanner.nextLine().trim();
        if (!em.isEmpty()) tp.setEmail(em);
        System.out.println(">> Updated: " + tp.getSummary());
    }
    static void viewStatement() {
        System.out.print("\nEnter Taxpayer TIN: "); String tin = scanner.nextLine().trim();
        RegisteredTaxpayer tp = findTaxpayer(tin);
        if (tp == null) { System.out.println("Taxpayer not found."); return; }
        System.out.println("\n=== STATEMENT FOR " + tp.getDisplayName() + " (TIN: " + tp.getTin() + ") ===");
        BigDecimal totalAssessed = BigDecimal.ZERO;
        BigDecimal totalPaid = BigDecimal.ZERO;
        for (int i = 0; i < assessCount; i++) {
            TaxAssessment a = assessList[i];
            if (a.getDeclaration().getObligation().getTaxpayer().getTin().equalsIgnoreCase(tin)) {
                totalAssessed = totalAssessed.add(a.getTotalAmount());
                System.out.println("Assessment " + a.getId() + ": " + a.getTotalAmount() + " RWF (" + a.getStatus() + ")");
            }
        }
        for (int i = 0; i < payCount; i++) {
            TaxPayment p = payList[i];
            if (p.getAssessment().getDeclaration().getObligation().getTaxpayer().getTin().equalsIgnoreCase(tin)) {
                totalPaid = totalPaid.add(p.getAmount());
                System.out.println("Payment " + p.getId() + ": -" + p.getAmount() + " RWF (" + p.getPaymentReference() + ")");
            }
        }
        System.out.println("Total Assessed : " + totalAssessed + " RWF");
        System.out.println("Total Paid     : " + totalPaid + " RWF");
        System.out.println("Net Balance    : " + totalAssessed.subtract(totalPaid) + " RWF");
    }
    static void viewOutstandingBalances() {
        System.out.println("\n--- OUTSTANDING BALANCES ---");
        for (int i = 0; i < assessCount; i++) {
            TaxAssessment a = assessList[i];
            BigDecimal paid = BigDecimal.ZERO;
            for (int j = 0; j < payCount; j++) {
                if (payList[j].getAssessment().getAssessmentId().equalsIgnoreCase(a.getAssessmentId())) {
                    paid = paid.add(payList[j].getAmount());
                }
            }
            BigDecimal bal = a.getTotalAmount().subtract(paid);
            if (bal.compareTo(BigDecimal.ZERO) > 0) {
                System.out.println("Assessment " + a.getId() + " (" + a.getDeclaration().getObligation().getTaxpayer().getTin() + "): Outstanding = " + bal + " RWF");
            }
        }
    }
    static void viewReports() {
        System.out.println("\n--- TAX REPORTS ---");
        BigDecimal totalCol = BigDecimal.ZERO;
        for (int i = 0; i < payCount; i++) totalCol = totalCol.add(payList[i].getAmount());
        System.out.println("Total Tax Collected: " + totalCol + " RWF");
        BigDecimal totalPen = BigDecimal.ZERO;
        for (int i = 0; i < penCount; i++) totalPen = totalPen.add(penList[i].getPenaltyAmount());
        System.out.println("Total Penalties Levied: " + totalPen + " RWF");
    }
    static void demonstratePolymorphism() {
        System.out.println("\n--- DEMONSTRATING RUNTIME POLYMORPHISM ---");
        System.out.println("1. Polymorphic call via TaxParty[] array:");
        TaxParty[] parties = new TaxParty[] { indList[0], corpList[0], officerList[0] };
        for (TaxParty p : parties) {
            System.out.println(" -> Class: " + p.getClass().getSimpleName() + " | ID: " + p.getId() + " | Display: " + p.getDisplayName() + " | Active: " + p.isActive());
        }
        System.out.println("\n2. Polymorphic call via Identifiable[] array:");
        Identifiable[] items = new Identifiable[] { typeList[0], oblList[0], indList[0], officerList[0] };
        for (Identifiable item : items) {
            System.out.println(" -> Type: " + item.getClass().getSimpleName() + " | ID: " + item.getId() + " | Active: " + item.isActive());
        }
    }
}
