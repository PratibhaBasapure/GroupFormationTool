USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spLoadNumericResponse`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spLoadNumericResponse`(
	IN rid BIGINT
)
BEGIN
	SELECT answer from SurveyResponseNumeric
    WHERE SurveyResponseNumeric.responseID = rid;
END$$

DELIMITER ;

