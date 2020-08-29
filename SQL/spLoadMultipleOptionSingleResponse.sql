USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spLoadMultipleOptionSingleResponse`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spLoadMultipleOptionSingleResponse`(
	IN rid BIGINT
)
BEGIN
	SELECT SurveyResponseMultipleChoiceSingle.answer from SurveyResponseMultipleChoiceSingle
    WHERE responseID = rid;
END$$

DELIMITER ;

