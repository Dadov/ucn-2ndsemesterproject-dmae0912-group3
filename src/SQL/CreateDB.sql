use something;

CREATE TABLE Location (
    ZIP varchar(255) not null,
    country carchar(255) not null,
    city varchar(255) not null,
    UNIQUE ( ZIP, country ),
    PRIMARY KEY ( ZIP, country )
);

CREATE TABLE Person (
    personID int not null,
    CPR varchar(11) not null,
    fname varchar(255) not null,
    lname varchar (255) not null,
    address varchar (255) not null,
    locationZIP varchar(255) not null,
    email varchar(255) not null,
    personType varchar (255) not null,
    UNIQUE ( personID ),
    PRIMARY KEY ( personID ),
    FOREIGN KEY ( locationZIP ) REFERENCES Location ( ZIP )
);

CREATE TABLE Staff (
    staffID int not null,
    rank varchar(255) not null,
    salary double not null,
    UNIQUE ( staffID ),
    PRIMARY KEY ( staffID ),
    FOREIGN KEY ( staffID ) REFERENCES Person ( personID )
);

CREATE TABLE Instructor (
    instructorID int not null,
    UNIQUE ( instructorID ),
    PRIMARY KEY ( instructorID ),
    FOREIGN KEY ( instructorID ) REFERENCES Person ( personID )
);

CREATE TABLE Customer (
    customerID int not null,
    registrationDate varchar(255),
    noOfStays int
    UNIQUE ( cutomerID ),
    PRIMARY KEY ( customerID ),
    FOREIGN KEY ( customerID ) REFERENCES Person ( personID )
);

CREATE TABLE Agency (
    name varchar(255) not null,
    discountLevel varchar(255),
    UNIQUE ( name ),
    PRIMARY KEY ( name )
);

CREATE TABLE ProvidedCustomers (
    agencyName varchar(255) not null,
    customerID int not null,
    UNIQUE ( agencyName, customerID ),
    PRIMARY KEY ( agencyName, customerID ),
    FOREIGN KEY ( agencyName ) REFERENCES Agency ( name ),
    FOREIGN KEY ( cusomerID ) REFERENCES Customer ( cusomerID )
);

CREATE TABLE Room (
    number int not null,
    roomType varchar(255) not null,
    price double not null,
    note varchar(255),
    UNIQUE ( number ),
    PRIMARY KEY ( number )
);

CREATE TABLE RoomBooking (
    bookingID int not null,
    customerID int not null,
    dateStart date not null,
    dateEnd date not null,
    dateBooked date not null,
    UNIQUE ( bookingID ),
    PRIMARY KEY ( bookingID ),
    FOREIGN KEY ( cusomerID ) REFERENCES Customer ( cusotmerID )
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
    activityID int not null,
    activityType varchar(255) not null,
    capacity int not null,
    instructorAvailability boolean not null,
    UNIQUE ( activityID ),
    PRIMARY KEY ( activityID )
);

CREATE TABLE ActivityBooking (
    activityBookingID int not null,
    activityID int not null,
    activityDate date not null,
    activityTime time not null,
    openActivity boolean not null,
    instructorHired boolean not null,
    UNIQUE ( activityBookingID ),
    PRIMARY KEY ( activityBookingID ),
    FOREIGN KEY ( activityID ) REFERENCES ACtivity ( activityID )
);

CREATE TABLE ActivityCustomers (
    activityID int not null,
    customerID int not null,
    UNIQUE ( activityID, customerID ),
    PRIMARY KEY ( activityID, customerID ),
    FOREIGN KEY ( activityID ) REFERENCES Activity ( activityID ),
    FOREIGN KEY ( customerID ) REFERENCES Customer ( customerID )
);

CREATE TABLE ActivityInstructors (
    activityID int not null,
    instructorID int not null,
    UNIQUE ( activityID, instructorID ),
    PRIMARY KEY ( activityID, instructorID ),
    FOREIGN KEY ( activityID ) REFERENCES Activity ( activityID ),
    FOREIGN KEY ( instructorID ) REFERENCES Instructor ( instructorID )
);

CREATE TABLE InstructorHire (
    instructorHireID int not null,
    customerID int not null,
    instructorID int not null,
    hireDate date not null,
    hireTime time not null,
    UNIQUE ( instructorHireID ),
    PRIMARY KEY ( instructorHireID ),
    FOREIGN KEY ( customerID ) REFERENCES Customer ( cusotmerID ),
    FOREIGN KEY ( instructorID ) REFERENCES Instructor ( instructorID )
);
    