USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spLoadMultipleOptionMultipleResponse`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spLoadMultipleOptionMultipleResponse`(
	IN rid BIGINT
)
BEGIN
	SELECT answer FROM
     SurveyResponseMultipleChoiceMultiple
    WHERE SurveyResponseMultipleChoiceMultiple.responseID = rid;
END$$

DELIMITER ;

