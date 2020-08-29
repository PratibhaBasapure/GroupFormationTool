DELIMITER $$

DROP procedure IF EXISTS spLoadQuestionOptionsById $$

CREATE PROCEDURE spLoadQuestionOptionsById (
	IN qid BIGINT
)
BEGIN
	SELECT id, questionID, displayText, optionNumber
	FROM QuestionOptions
    WHERE questionID = qid;
END $$

DELIMITER ;
