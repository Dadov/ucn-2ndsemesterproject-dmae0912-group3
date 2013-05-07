select number,count(*)
from ROOM
where roomType = 'type' and number in (select roomNumber
					from RoomsBooked
					where roomBookingID not in( select bookingID 
								   from RoomBooking
								   where dateStart <= DATE 'newEndDateJava' and dateEnd>= DATE 'newStartDateJava' or dateEnd >= DATE 'newStartDateJava' ));