USE dmae0912_3;

/*
* PopulateDB, used to populate tables with mostly fixed data:
*	Room
*	Activity
*	Agency
*	Staff
*/

SET DATEFORMAT dmy;

/* Inserting 64 Single rooms and 40 Family rooms */
INSERT INTO Room(roomType, price, note) VALUES('Single', 60, '');
/* 'GO' for repeating the command given number of times,
	it's not sql keyword, only specific to MSSQL management */
GO 64

INSERT INTO Room(roomType, price, note) VALUES('Family', 100, '');
GO 40

/* Activities/Facilities as described in case */
INSERT INTO Activity(activityType, capacity, instructorPrice) VALUES('TennisCourt', 4, 30);
GO 6

INSERT INTO Activity(activityType, capacity, instructorPrice) VALUES('BadmintonCourt', 4, 30);
GO 4

INSERT INTO Activity(activityType, capacity) VALUES('VolleyballCourt', 12);
GO 2

INSERT INTO Activity(activityType, capacity) VALUES('HandballCourt', 14);
GO 2

INSERT INTO Activity(activityType, capacity) VALUES('FitnessCenter', 20);

INSERT INTO Activity(activityType, capacity, instructorPrice) VALUES('Golf', 100000, 40);

INSERT INTO Activity(activityType, capacity, instructorPrice) VALUES('Swimming', 100000, 40);

/* Some trustworthy travel agencies */
INSERT INTO Agency(name, discountLevel)
	VALUES ('YOLO Travel', 0),
		   ('SWAG around the world', 0),
		   ('Never more', 0),
		   ('There is no return', 0),
		   ('Go to HELL!', 0);


/*
*INSERT INTO Person() VALUES();
*INSERT INTO Staff() VALUES()
*/