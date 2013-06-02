USE Morocco;

/* setting date format for normal people, not americans */
SET DATEFORMAT dmy;

-- all gonna come from Aalborg
INSERT INTO Location(ZIP, country, city)
	VALUES('9000', 'Denmark', 'Aalborg');

-- some staff memebers
INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('987654-3210','Mr.','Manager','Boulevarden 55','9000','Denmark','noone@nomail.dk', 'pass', 'Manager');
INSERT INTO Staff(staffID, salary, staffType)
	VALUES((SELECT IDENT_CURRENT('Person')), 15000, 'Manager');

INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('987654-3210','Grumpy','Instructor','Boulevarden 55','9000','Denmark','noone@nomail.dk', 'pass', 'Instructor');
INSERT INTO Staff(staffID, salary, staffType)
	VALUES((SELECT IDENT_CURRENT('Person')), 15000, 'Instructor');

INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('987654-3210','Dear','Secrectary','Boulevarden 55','9000','Denmark','noone@nomail.dk', 'pass', 'Secretary');
INSERT INTO Staff(staffID, salary, staffType)
	VALUES((SELECT IDENT_CURRENT('Person')), 15000, 'Secretary');

INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('987654-3210','Pretty','Receptionist','Boulevarden 55','9000','Denmark','noone@nomail.dk', 'pass', 'Receptionist');
INSERT INTO Staff(staffID, salary, staffType)
	VALUES((SELECT IDENT_CURRENT('Person')), 15000, 'Receptionist');

-- only two customers, whith their room bookings
INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('123456-7890', 'Some', 'One', 'Street and number 3', '9000', 'Denmark', 'someone@somemail.dk', 'somepass', 'Customer');
INSERT INTO Customer(customerID, registrationDate, noOfStays)
	VALUES((SELECT IDENT_CURRENT('Person')), '30-12-2013', 0);

INSERT INTO RoomBooking(customerID, dateStart, dateEnd, dateBooked)
	VALUES((SELECT IDENT_CURRENT('Person')), '13-08-2014', '24-08-2014', '05-06-2014');

INSERT INTO RoomsBooked(roomBookingID, roomNumber)
	VALUES (1, 1);

INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('123456-7890', 'No', 'One', 'Street and number 3', '9000', 'Denmark', 'someone@somemail.dk', 'somepass', 'Customer');
INSERT INTO Customer(customerID, registrationDate, noOfStays)
	VALUES((SELECT IDENT_CURRENT('Person')), '30-12-2013', 0);

INSERT INTO RoomBooking(customerID, dateStart, dateEnd, dateBooked)
	VALUES((SELECT IDENT_CURRENT('Person')), '13-09-2014', '24-09-2014', '05-06-2014');

INSERT INTO RoomsBooked(roomBookingID, roomNumber)
	VALUES (2, 1);

INSERT INTO RoomsBooked(roomBookingID, roomNumber)
	VALUES (2, 2);

INSERT INTO RoomsBooked(roomBookingID, roomNumber)
	VALUES (2, 3);

