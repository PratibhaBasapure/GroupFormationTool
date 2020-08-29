USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spIsResponseprovidedByStudent`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spIsResponseprovidedByStudent`(
	IN courseID int(20),
    IN userID int(20)
)
BEGIN
	SELECT * FROM Survey s INNER JOIN SurveyResponse SR ON s.id=SR.surveyID
    WHERE s.courseID = courseID AND SR.studentID = userID AND s.isPublished = 1;
END$$

DELIMITER ;

