USE dmae0912_3;

/* These SQL scripts use RIGHT JOIN on Person and Location tables.
 * The deal is that city value for a Person must be retrieved. There
 * are four options, in order to provide more flexibility in the
 * search of Person. They are placed altogther so you can get an
 * overview easily.
 * You can try them with no problem, 'cause Tomas Some One was used
 * as an example.
 */
SET DATEFORMAT dmy;

/* Makes a table with a single entry by personID */
SELECT * FROM Person RIGHT JOIN Location
ON Person.locationZIP=Location.ZIP
WHERE personID = 1;

/* Makes a table with a single entry by CPR */
SELECT * FROM Person RIGHT JOIN Location
ON Person.locationZIP=Location.ZIP
WHERE CPR = '123456-7890';

/* Makes a table with a single entry by fname & lname */
SELECT * FROM Person RIGHT JOIN Location
ON Person.locationZIP=Location.ZIP
WHERE fname = 'Some' and lname = 'One';

/* Makes a table with multiple entries */
SELECT * FROM Person RIGHT JOIN Location
ON Person.locationZIP=Location.ZIP;