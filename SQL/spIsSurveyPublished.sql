DELIMITER $$

DROP procedure IF EXISTS spIsSurveyPublished $$

CREATE PROCEDURE spIsSurveyPublished (
    IN id BIGINT
)
BEGIN
	SELECT Survey.id
    FROM Survey
    WHERE Survey.courseID = id AND Survey.isPublished = 1;
END $$

DELIMITER ;

