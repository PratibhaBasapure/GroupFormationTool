USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spUsersBySurveyId`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spUsersBySurveyId`(
	IN sid BIGINT
)
BEGIN
	SELECT  distinct studentID
    FROM SurveyResponse
    WHERE SurveyResponse.surveyID = sid;
END$$

DELIMITER ;

