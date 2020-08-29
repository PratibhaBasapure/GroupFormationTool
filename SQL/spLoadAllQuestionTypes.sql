DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllCourses $$

CREATE PROCEDURE spLoadAllQuestoinTypes()
BEGIN
	SELECT id, questionTypeName
    FROM QuestionType
    ORDER BY id ASC;
END $$
DELIMITER ;