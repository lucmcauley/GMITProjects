/* 

Created by Luc McAuley G00388057


Dentist office database for Database module
GMIT h.dip April 2020

Database Dental Office project
*/

-- 
-- Dumping data for patient
-- 

INSERT INTO patient (fName, lName, DOB, patientEirCode, phoneNumber)
    VALUES 
    ('JOHN', 'DOE', '1995-01-30', 'AX3 FD48', NULL),
    ('ALAN', 'SMITH', '1987-12-25', 'WE3 F58D', '051420201'),
    ('THOMAS', 'DOYLE', '1984-08-13', 'R45 Q333', NULL),
    ('KEN', 'O\' DONOVAN', '1968-07-04', 'HH3 YU86', '0872599280'),
    ('DECLAN', 'O\' CONNELL', '1978-04-21', 'Q45 Y782', '0874107534'),
    ('PAT', 'WALSH', '1976-09-09', 'C89 3GHT', NULL),
    ('CONOR', 'ENGLISH', '1958-01-17', 'GU7 9578', '0895672394');   


-- 
-- Dumping data for specialist
-- 

INSERT INTO specialist (fName, lName, specialistEirCode, phoneNumber, specialty)
    VALUES
    ('GARETH', 'MORAHAN', 'RH6 86TH', '023889189', 'ORTHODONTICS'),
    ('ANDREW', 'MEEHAN', 'TH6 67GO', '02369283', 'PEDIATRICSS'),
    ('TERRY', 'MAGUIRE', 'YU5 89T1', '089334512', 'PERIODONTICS'),
    ('THOMAS', 'O\' SULLIVAN', 'JU67 AWI8', '023813989', 'SURGICAL');


-- 
-- Dumping data for appointment
-- 

INSERT INTO appointment (appointmentdate, patientID, dateCreated, notes)
    VALUES
    ('2020-07-25 11:45:00', 5, '2020-01-17', 'Appointment made by letter'),
    ('2020-08-22 18:00:00', 6, '2020-02-17', 'Appointment made by phone'),
    ('2020-09-28 10:00:00', 5, '2020-04-20', 'Follow-up appointment'),
    ('2020-11-09 09:00:00', 5, '2020-09-13', 'Follow-up appointment'),
    ('2020-05-12 14:30:00', 3, '2019-12-23', 'Follow-up appointment'),
    ('2020-10-09 09:45:00', 4, '2020-05-21', 'Appointment made in person'),
    ('2020-01-08 16:45:00', 6, '2020-01-05', 'Appointment made by letter'),
    ('2020-11-10 08:45:00', 7, '2020-06-21', 'Appointment made by phone'),
    ('2020-12-18 13:00:00', 6, '2020-10-29', 'Appointment made by letter'),
    ('2020-10-11 12:15:00', 5, '2020-06-02', 'Appointment made by letter'),
    ('2020-02-19 18:00:00', 6, '2020-01-20', 'Appointment made in person'),
    ('2020-07-14 10:15:00', 6, '2020-05-20', 'Appointment made by phone'),
    ('2020-07-28 10:45:00', 6, '2020-07-06', 'Follow-up appointment'),
    ('2020-12-20 17:15:00', 1, '2020-11-09', 'Appointment made by letter'),
    ('2020-08-22 17:00:00', 4, '2020-04-07', 'Appointment made in person'),
    ('2020-03-20 09:00:00', 3, '2020-03-12', 'Appointment made by phone'),
    ('2020-02-06 10:30:00', 7, '2019-12-14', 'Appointment made by phone'),
    ('2020-01-25 15:30:00', 3, '2020-01-20', 'Appointment made in person'),
    ('2020-08-05 18:45:00', 4, '2020-07-13', 'Appointment made in person'),
    ('2020-02-04 11:30:00', 7, '2020-01-14', 'Appointment made by letter'),
    ('2020-07-20 10:45:00', 6, '2020-02-23', 'Follow-up appointment'),
    ('2020-12-07 15:00:00', 2, '2020-08-24', 'Follow-up appointment'),
    ('2020-07-21 18:45:00', 7, '2019-12-04', 'Follow-up appointment'),
    ('2020-03-05 15:00:00', 3, '2019-12-20', 'Follow-up appointment'),
    ('2020-09-19 12:00:00', 6, '2020-02-02', 'Follow-up appointment'),
    ((SELECT DATE_ADD(CURDATE(), INTERVAL 3 DAY)), 6, '2020-02-02', 'Follow-up appointment'),
    ('2020-04-19 18:00:00', 6, '2020-04-19', 'Follow-up appointment');



-- 
-- Data dump for treatment
-- 

INSERT INTO treatment(treatmentName, treatmentPrice, dateChanged)
    VALUES
    ('Late Cancellation fee', 10, '2018-03-20'),
    ('Examination', 40, '2020-04-04'),
    ('X-RAYS', 35, '2019-09-01'),
    ('Scale and Polish', 60, '2019-09-01'),
    ('Prescription', 25, '2018-03-20'),
    ('Crowns', 600, '2020-04-04'),
    ('Veneers',	600, '2019-05-01'),
    ('Dentures', 400, '2019-05-01'),
    ('Extraction', 85, '2020-04-17');
  
-- 
-- Dumping data for bill
-- 

INSERT INTO bill(patientID, issued)
    VALUES 
    (6, '2020-01-14'),
    (6, '2020-02-25'),
    (3, '2020-03-24'),
    (7, '2020-02-11'),
    (3, '2020-01-28'),
    (7, '2020-02-11'),
    (3, '2020-03-10');



-- 
-- Data dumping for billBreakdown
-- 

INSERT INTO billBreakdown (billID, treatmentID)
    VALUES
    (7, 1),
    (1, 2),
    (3, 1),
    (2, 2),
    (4, 1),
    (6, 4),
    (6, 3),
    (5, 1),
    (3, 2),
    (4, 1),
    (4, 3),
    (5, 7);


-- 
-- Data dump for payment
-- 

INSERT INTO payment (installmentAmount, billID, datePaid, notes)
    VALUES
    (40, 1, '2020-04-17', "Paid by post"),
    (30, 5, '2020-03-17', "Paid with cash"),
    (30, 5, '2020-02-17', "Paid by card"),
    (15, 3, '2020-03-24', "Paid by card"),
    (55, 4, '2020-03-11', "Paid by post");


-- 
-- Data dump for referral
-- 

INSERT INTO referral (appointmentID, specialistID, seenBySpecialistDate)
    VALUES 
    (7, 4, '2020-02-01'),
    (20, 1, '2020-04-04'),
    (16, 1, NULL);



