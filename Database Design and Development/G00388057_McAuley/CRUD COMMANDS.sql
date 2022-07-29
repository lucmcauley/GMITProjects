/*

CRUD queries for final Database project
Luc McAuley G00388057
GMIT higher Diploma

*/

-- 
-- SELECT QUERY
-- 

SELECT bill.billID, patient.fname, patient.lname, treatment.treatmentName, treatment.treatmentPrice, bill.issued
    FROM bill
    LEFT JOIN  billBreakdown
    ON bill.billID = billBreakdown.billID
    LEFT JOIN patient
    ON patient.patientID = bill.patientID
    LEFT JOIN treatment
    on treatment.treatmentID = billBreakdown.treatmentID
    ORDER BY bill.billID;
  
  
-- 
-- INSERT QUERY
-- 

INSERT INTO appointment (appointmentdate, patientID, dateCreated, notes)
    VALUES
    (CURDATE(), 5, '2020-01-17', 'Appointment made by letter');


-- 
-- UPDATE QUERY
-- 

UPDATE specialist
SET specialistEircode = 'D08 GH5R',
    phoneNumber = '01111111'
WHERE lName = 'MORAHAN';


-- 
-- DELETE QUERY
-- 

DELETE FROM payment WHERE billID = 5;


-- 
-- CREATE QUERY
-- 

CREATE TABLE appointmentExample (
    appointmentID INT(11) AUTO_INCREMENT PRIMARY KEY,
    appointmentDate DATETIME NOT NULL,
    patientID INT,
    dateCreated DATE DEFAULT CURDATE(),
    notes VARCHAR(255)
);
