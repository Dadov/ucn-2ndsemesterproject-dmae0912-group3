USE dmae0912_3;

SET DATEFORMAT dmy;

SELECT * FROM Activity WHERE activityType = 'TennisCourt' and activityID not in (
	SELECT activityID FROM ActivityBooking WHERE activityDate = '2013-10-10' and activityTime = '19:00');