/* 



#########################################
### Table Creation for Dentist Office ###
#########################################


Created by Luc McAuley G00388057


Dentist office database for Database module
GMIT h.dip April 2020

Database Dental Office project
*/

-- 
-- Crating tables
-- Note that Primary Keys are in this section due to the auto increment
-- 


-- 
-- Table structure for patient
-- 

CREATE TABLE patient (
    patientID INT(11) AUTO_INCREMENT PRIMARY KEY,
    fName CHAR(255) NOT NULL,
    lName CHAR(255) NOT NULL,
    DOB DATE NOT NULL,
    patientEirCode VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255)
);


-- 
-- Table structure for specialist
-- 

CREATE TABLE specialist (
    specialistID INT(11) AUTO_INCREMENT PRIMARY KEY,
    fName CHAR(255) NOT NULL,
    lName CHAR(255) NOT NULL,
    specialistEirCode VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL
);


-- 
-- Table sctructure for appointment
-- 

CREATE TABLE appointment (
    appointmentID INT(11) AUTO_INCREMENT PRIMARY KEY,
    appointmentDate DATETIME NOT NULL,
    patientID INT,
    dateCreated DATE DEFAULT CURDATE() NOT NULL,
    notes VARCHAR(255)
);


-- 
-- Table sctructure for bill
-- 

CREATE TABLE bill (
    billID INT(11) AUTO_INCREMENT PRIMARY KEY,
    patientID INT(11) NOT NULL,
    issued DATE NOT NULL
);  


-- 
-- Table structure for billBreakdown
-- 

CREATE TABLE billBreakdown (
    itemID INT(11) AUTO_INCREMENT PRIMARY KEY,
    billID INT(11) NOT NULL,
    treatmentID INT(11) NOT NULL
);


-- 
-- Table structure for payment
-- 

CREATE TABLE payment (
    paymentID INT(11) AUTO_INCREMENT PRIMARY KEY,
    installmentAmount INT(11) NOT NULL,
    billID INT(11) NOT NULL,
    datePaid DATE NOT NULL,
    notes VARCHAR(255) 
);


-- 
-- Table Structure for referral
-- 

CREATE TABLE referral (
    referralID INT(11) AUTO_INCREMENT PRIMARY KEY,
    appointmentID INT(11) NOT NULL,
    specialistID INT(11) NOT NULL,
    seenBySpecialistDate DATE
);


-- 
-- Table Structure for treatment
-- 

CREATE TABLE treatment (
    treatmentID INT(11) AUTO_INCREMENT PRIMARY KEY,
    treatmentName VARCHAR(255) NOT NULL,
    treatmentPrice INT(11) NOT NULL,
    dateChanged DATE 
);

    
-- 
-- Constraints on tables
-- 


-- Constraints for table appointment

ALTER TABLE appointment
    ADD CONSTRAINT appointment_ibfk_1 FOREIGN KEY (patientID) REFERENCES patient (patientID);


-- Constraints for table bill

ALTER TABLE bill
    ADD CONSTRAINT bill_ibfk_1 FOREIGN KEY (patientID) REFERENCES patient(patientID);
    
    
-- Constraints for table billBreakdown

ALTER TABLE billBreakdown
    ADD CONSTRAINT billBreakdown_ibfk_1 FOREIGN KEY (billID) REFERENCES bill(billID),
    ADD CONSTRAINT billBreakdown_ibfk_2 FOREIGN KEY (treatmentID) REFERENCES treatment(treatmentID);


-- Constraints for table payment

ALTER TABLE payment
    ADD CONSTRAINT payment_ibfk_1 FOREIGN KEY (billID) REFERENCES bill(billID);
  
  
-- Constraints for table referral

ALTER TABLE referral
   ADD CONSTRAINT referral_ibfk_1 FOREIGN KEY (appointmentID) REFERENCES appointment(appointmentID),
   ADD CONSTRAINT referral_ibfk_2 FOREIGN KEY (specialistID) REFERENCES specialist(specialistID);



/*

#######################################################################################
### Creating views for illustration of how the data might be collated and presented ###
#######################################################################################

*/


-- 
-- Creating Views
-- 

-- View structure for billDetails


CREATE VIEW billDetails
    AS SELECT bill.billID, patient.fname, patient.lname, treatment.treatmentName, treatment.treatmentPrice,  bill.issued
    FROM bill
    LEFT JOIN  billBreakdown
    ON bill.billID = billBreakdown.billID
    LEFT JOIN patient
    ON patient.patientID = bill.patientID
    LEFT JOIN treatment
    on treatment.treatmentID = billBreakdown.treatmentID
    ORDER BY bill.billID;
    
    
-- The following view was created for billsummary

CREATE VIEW sumPayments    
    AS SELECT billID, SUM(installmentAmount) AS paymentTotal
    FROM payment
    GROUP BY billID;
    
    
-- The following view was created for billsummary
CREATE VIEW sumBills
    AS SELECT billID, SUM(treatmentPrice) AS billTotal
    FROM billdetails
    GROUP BY billID;


-- View structure for billlsummmary

CREATE VIEW billSummary
    AS SELECT bill.billID, sumbills.billTotal, bill.issued, patient.patientID, patient.fName, patient.lName, sumpayments.paymentTotal
    FROM bill
    LEFT JOIN sumpayments
    ON bill.billID = sumPayments.billID
    LEFT JOIN sumbills
    ON sumBills.BILLID = bill.billID
    LEFT JOIN patient
    ON bill.patientID = patient.patientID
    GROUP BY bill.billID;
    
    
-- View structure for Patient's chart
-- NOTE: the values for totalOwed column are not appearing correctly and I'm not sure why it's not working, online forums discuss issues with InnoDB engine, the default implementation for MariaDB, with these kinds of queries
-- This remains for illustrative putposes of how the DB would be implemented

CREATE VIEW patientChart
    AS SELECT patient.patientID, patient.fname, patient.lname, patient.DOB, patient.patientEirCode, patient.phoneNumber, appointment.appointmentDate AS NextAppointment, 
    (SELECT SUM(billsummary.billtotal) - SUM(billsummary.paymenttotal)) as totalOwed, (SELECT billsummary.issued WHERE (SELECT SUM(billsummary.billtotal) - SUM(billsummary.paymenttotal))>0)
    FROM patient 
    LEFT JOIN appointment
    ON patient.patientID = appointment.patientID
    LEFT JOIN billsummary
    ON patient.patientID = billsummary.patientID
    GROUP BY patientID;


-- View for upcoming appointment

CREATE VIEW upcomingAppointment
    AS SELECT appointment.appointmentDate, patient.fName, patient.lName, appointment.notes
    FROM appointment
    LEFT JOIN patient
    ON patient.patientID = appointment.patientID
    WHERE appointmentDate between (SELECT CURDATE()) AND (SELECT DATE_ADD(CURDATE(), INTERVAL 1 WEEK));


