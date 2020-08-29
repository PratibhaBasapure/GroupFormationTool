DROP procedure IF EXISTS spDeleteQuestion;

DELIMITER $$

CREATE spDeleteQuestion(
	IN id BIGINT
)
BEGIN
	DELETE FROM QuestionOptions
    WHERE QuestionOptions.questionID = id;

	DELETE FROM Question
    WHERE Question.id = id;
END$$

DELIMITER ;