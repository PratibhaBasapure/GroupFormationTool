DELIMITER $$

DROP PROCEDURE IF EXISTS spPublishSurvey $$

CREATE PROCEDURE spPublishSurvey (
	IN courseID bigint    
)
BEGIN	
	Update Survey set isPublished =1 where  courseID =courseID ;   
    SELECT S.id from Survey S WHERE courseID =courseID;
END $$

DELIMITER ;