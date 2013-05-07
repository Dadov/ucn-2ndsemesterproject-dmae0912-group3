SELECT * FROM Room WHERE roomType = 'type' and number in (
	SELECT roomNumber FROM RoomsBooked WHERE roomBookingID not in ( 
		SELECT bookingID FROM RoomBooking WHERE dateStart <= DATE 'newEndDateJava' and dateEnd>= DATE 'newStartDateJava' or dateEnd >= DATE 'newStartDateJava' ));