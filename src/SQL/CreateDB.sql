USE Morocco;

/* setting date format for normal people, not americans */
SET DATEFORMAT dmy;

CREATE TABLE Location (
    ZIP varchar(255) not null,
    country varchar(255) not null,
    city varchar(255) not null,
    UNIQUE ( ZIP, country ),
    PRIMARY KEY ( ZIP, country )
);

CREATE TABLE Person (
    personID int not null identity,
    CPR varchar(11) not null,
    fname varchar(255) not null,
    lname varchar(255) not null,
    address varchar(255) not null,
    locationZIP varchar(255) not null,
	country varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    personType varchar(255) not null,
    UNIQUE ( personID ),
    PRIMARY KEY ( personID ),
    FOREIGN KEY ( locationZIP, country ) REFERENCES Location ( ZIP, country )
);

CREATE TABLE Staff (
    staffID int not null,
    staffRank varchar(255) not null,
    salary float,
    staffType varchar(255) not null,
    UNIQUE  ( staffID ),
    PRIMARY KEY ( staffID ),
    FOREIGN KEY ( staffID ) REFERENCES Person ( personID )
);

CREATE TABLE Customer (
    customerID int not null,
    registrationDate date,
    noOfStays int,
    UNIQUE ( customerID ),
    PRIMARY KEY ( customerID ),
    FOREIGN KEY ( customerID ) REFERENCES Person ( personID )
);

CREATE TABLE Agency (
    agencyID int not null identity,
    name varchar(255) not null,
    discountLevel int,
    UNIQUE ( agencyID ),
    PRIMARY KEY ( agencyID )
);

CREATE TABLE ProvidedCustomers (
    agencyID int not null,
    customerID int not null,
    UNIQUE ( agencyID, customerID ),
    PRIMARY KEY ( agencyID, customerID ),
    FOREIGN KEY ( agencyID ) REFERENCES Agency ( agencyID ),
    FOREIGN KEY ( customerID ) REFERENCES Customer ( customerID )
);

CREATE TABLE Room (
    number int not null identity,
    roomType varchar(255) not null,
    price float,
    note varchar(255),
    UNIQUE ( number ),
    PRIMARY KEY ( number )
);

CREATE TABLE RoomBooking (
    bookingID int not null identity,
    customerID int not null,
    dateStart date not null,
    dateEnd date not null,
    dateBooked date not null,
    UNIQUE ( bookingID ),
    PRIMARY KEY ( bookingID ),
    FOREIGN KEY ( customerID ) REFERENCES Customer ( customerID )
);

CREATE TABLE RoomsBooked (
    roomBookingID int not null,
    roomNumber int not null,
    UNIQUE ( roomBookingID, roomNumber ),
    PRIMARY KEY ( roomBookingID, roomNumber ),
    FOREIGN KEY ( roomBookingID ) REFERENCES RoomBooking ( bookingID ),
    FOREIGN KEY ( roomNumber ) REFERENCES Room ( number )
);

CREATE TABLE Activity (
    activityID int not null identity,
    activityType varchar(255) not null,
    capacity int not null,
    UNIQUE ( activityID ),
    PRIMARY KEY ( activityID )
);

CREATE TABLE ActivityBooking (
    activityBookingID int not null identity,
    activityID int not null,
    activityDate date not null,
    activityTime time not null,
    openActivity bit not null,
    instructorHired bit not null,
    UNIQUE ( activityBookingID ),
    PRIMARY KEY ( activityBookingID ),
    FOREIGN KEY ( activityID ) REFERENCES Activity ( activityID )
);

CREATE TABLE ActivityCustomers (
    activityBookingID int not null,
    customerID int not null,
    UNIQUE ( activityBookingID, customerID ),
    PRIMARY KEY ( activityBookingID, customerID ),
    FOREIGN KEY ( activityBookingID ) REFERENCES ActivityBooking ( activityBookingID ),
    FOREIGN KEY ( customerID ) REFERENCES Customer ( customerID )
);

CREATE TABLE ActivityInstructors (
    activityID int not null,
    instructorID int not null,
    UNIQUE ( activityID, instructorID ),
    PRIMARY KEY ( activityID, instructorID ),
    FOREIGN KEY ( activityID ) REFERENCES Activity ( activityID ),
    FOREIGN KEY ( instructorID ) REFERENCES Staff ( staffID )
);

CREATE TABLE InstructorHire (
    instructorHireID int not null identity,
    customerID int not null,
    instructorID int not null,
    activityBookingID int not null,
    hireDate date not null,
    hireTime time not null,
    UNIQUE ( instructorHireID ),
    PRIMARY KEY ( instructorHireID ),
    FOREIGN KEY ( customerID ) REFERENCES Customer ( customerID ),
    FOREIGN KEY ( instructorID ) REFERENCES Staff ( staffID ),
    FOREIGN KEY ( activityBookingID ) REFERENCES ActivityBooking ( activityBookingID )
);