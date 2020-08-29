DELIMITER $$

DROP PROCEDURE IF EXISTS spFindQuestionsByUserID $$

CREATE PROCEDURE spFindQuestionsByUserID (
	IN instructorID BIGINT
)
BEGIN
	SELECT q.id, q.instructorID, qt.questionTypeName, q.title, q.questionText, q.createdOn
	FROM Question as q join QuestionType as qt on q.type_id = qt.id
    WHERE q.instructorID = instructorID;
END $$

DELIMITER ;