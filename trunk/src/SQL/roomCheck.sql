USE Morocco;

SET DATEFORMAT dmy;

SELECT * FROM Room WHERE /* roomType = 'Family' and */ number not in (
	SELECT roomNumber FROM RoomsBooked WHERE roomBookingID in ( 
		SELECT bookingID FROM RoomBooking WHERE dateStart >= '13-08-2014' and dateEnd <= '24-08-2014'));