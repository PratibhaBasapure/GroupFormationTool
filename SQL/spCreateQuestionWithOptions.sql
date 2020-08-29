DELIMITER $$
DROP PROCEDURE IF EXISTS spCreateQuestionWithOptions $$

CREATE PROCEDURE spCreateQuestionWithOptions (
	IN questionID BIGINT,
    IN displayText VARCHAR(500),
    IN optionNumber int(11)    
)
BEGIN
	INSERT INTO QuestionOptions(questionID,displayText,optionNumber)
    VALUES (questionID,displayText,optionNumber);	
END $$

DELIMITER ;