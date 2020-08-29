USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spLoadFreeTextResponse`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spLoadFreeTextResponse`(
	IN rid BIGINT
)
BEGIN
	SELECT answer FROM
    SurveyResponseFreeText
    WHERE SurveyResponseFreeText.responseID = rid;
END$$

DELIMITER ;

