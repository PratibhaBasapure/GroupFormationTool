DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateSurvey $$

CREATE PROCEDURE spCreateSurvey (
	IN courseID bigint,
    IN instructorID bigint  
)
BEGIN
	DECLARE sid bigint(11);
    DECLARE existingSurveID bigint(11);
    SET existingSurveID = (select S.id from Survey S WHERE S.courseID= courseID and S.instructorID=instructorID);
    
    IF(existingSurveID != 0)
    THEN 
    Delete from SurveyQuestions where surveyID=existingSurveID;
   Delete  from Survey where id=existingSurveID;
    END IF;
    
	INSERT INTO Survey(courseID,instructorID)
    VALUES (courseID,instructorID);
	SET sid = LAST_INSERT_ID();
    SELECT S.id from Survey S WHERE S.id = sid;
END $$

DELIMITER ;