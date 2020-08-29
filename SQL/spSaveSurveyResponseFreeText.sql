USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spSaveSurveyResponseFreeText`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spSaveSurveyResponseFreeText`(
	IN responseID bigInt(20),
    IN answer Varchar(2000),
    IN questionTypeID int(20)
)
BEGIN
	INSERT INTO SurveyResponseFreeText(responseID,answer,questionTypeID)
    VALUES (responseID,answer,questionTypeID);
END$$

DELIMITER ;

