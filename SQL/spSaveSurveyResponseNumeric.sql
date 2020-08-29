USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spSaveSurveyResponseNumeric`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spSaveSurveyResponseNumeric`(
	IN responseID bigInt(20),
    IN answer int(11),
    IN questionTypeID int(20)
)
BEGIN
	INSERT INTO SurveyResponseNumeric(responseID,answer,questionTypeID)
    VALUES (responseID,answer,questionTypeID);
END$$

DELIMITER ;

