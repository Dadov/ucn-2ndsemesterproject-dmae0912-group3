USE Morocco;

/* setting date format for normal people, not americans */
SET DATEFORMAT dmy;

INSERT INTO Location(ZIP, country, city)
	VALUES('9000', 'Denmark', 'Aalborg');

INSERT INTO Person(CPR, fname, lname, address, locationZIP, country, email, password, personType)
	VALUES('123456-7890', 'Some', 'One', 'Street and number 3', '9000', 'Denmark', 'someone@somemail.dk', 'somepass', 'Customer');

INSERT INTO Customer(customerID, registrationDate, noOfStays)
	VALUES((SELECT IDENT_CURRENT('Person')), '30-12-2013', 0);

INSERT INTO RoomBooking(customerID, dateStart, dateEnd, dateBooked)
	VALUES((SELECT IDENT_CURRENT('Person')), '13-08-2014', '24-08-2014', '05-06-2014');

INSERT INTO RoomsBooked(roomBookingID, roomNumber)
	VALUES (1, 1);