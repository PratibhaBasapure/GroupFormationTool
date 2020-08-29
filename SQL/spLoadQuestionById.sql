DELIMITER $$

DROP procedure IF EXISTS spLoadQuestionById $$

CREATE PROCEDURE spLoadQuestionById (
	IN qid BIGINT
)
BEGIN
	SELECT id, instructorID, type_id, title, questionText, createdOn
	FROM Question
    WHERE id = qid;
END $$
DELIMITER ;