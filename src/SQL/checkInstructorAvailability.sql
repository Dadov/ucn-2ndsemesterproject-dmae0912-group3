USE dmae0912_3;

SET DATEFORMAT dmy;

SELECT * FROM Staff WHERE staffID = 215 and staffID not in (
	SELECT instructorID FROM InstructorHire WHERE hireDate = '2013-10-10' and hireTime = '19:00');