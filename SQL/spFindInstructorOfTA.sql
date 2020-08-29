DELIMITER $$

DROP PROCEDURE IF EXISTS spFindInstructorOfTA $$

CREATE PROCEDURE spFindInstructorOfTA (	
	IN courseID BIGINT
)
BEGIN
	select userID from CourseRole where CourseRole.courseID = courseID and roleID=4 ;
END $$

DELIMITER ;