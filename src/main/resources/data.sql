INSERT INTO patient (name, gender, birth_date, email, blood_group)
VALUES
  ('Aarav Sharma', 'MALE', '1998-04-12', 'aarav.sharma@example.com', 'O_POSITIVE'),
  ('Neha Verma', 'FEMALE', '2000-09-25', 'neha.verma@example.com', 'A_POSITIVE'),
  ('Rohan Singh', 'MALE', '1996-01-08', 'rohan.singh@example.com', 'B_POSITIVE'),
  ('Priya Gupta', 'FEMALE', '1999-06-30', 'priya.gupta@example.com', 'AB_POSITIVE'),
  ('Kunal Mehta', 'MALE', '1997-11-15', 'kunal.mehta@example.com', 'O_NEGATIVE');

INSERT INTO doctor(name,specialization,email,created_at)
VALUES
    ('Dr. Rakesh Mehta','Cardiology','rakesh.mehta@example.com',CURRENT_TIMESTAMP),
    ('Dr. Sneha Kapoor','Dermatology','sneha.kapoor@example.com',CURRENT_TIMESTAMP),
    ('Dr. Arjun Nair','Orthopedics','arjun.nair@example.com',CURRENT_TIMESTAMP);

INSERT INTO appointment(appointment_time,reason,doctor_id,patient_id)
VALUES
    ('2025-07-01 10:30:00','General Checkup',1,2),
    ('2025-07-02 11:00:00','Skin Rash',2,2),
    ('2025-07-03 09:45:00','Knee Pain',3,3),
    ('2025-07-04 14:00:00','Follow up visit',1,1),
    ('2025-07-05 16:15:00','Consulation',1,4),
    ('2025-07-06 08:30:00','Allergy Treatment',2,5);
    