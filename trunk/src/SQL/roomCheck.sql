USE dmae0912_3;

SET DATEFORMAT dmy;

SELECT * FROM Room WHERE /* roomType = 'Family' and */ number not in (
	SELECT roomNumber FROM RoomsBooked WHERE roomBookingID in ( 
		SELECT bookingID FROM RoomBooking WHERE dateEnd >= '13-08-2014' and dateStart <= '24-08-2014'));