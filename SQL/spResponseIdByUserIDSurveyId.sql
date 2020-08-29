USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spResponseIdByUserIDSurveyId`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spResponseIdByUserIDSurveyId`(
	IN sid BIGINT,
    IN uid BIGINT
)
BEGIN
	SELECT SurveyResponse.id as response_ID,
    SurveyResponse.questionID as question_ID
    FROM SurveyResponse
    WHERE SurveyResponse.surveyID = sid
    AND SurveyResponse.studentID = uid;
END$$

DELIMITER ;

