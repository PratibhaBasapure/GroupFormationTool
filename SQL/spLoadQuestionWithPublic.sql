DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadQuestionWithPublic $$

CREATE PROCEDURE spLoadQuestionWithPublic (
	IN userID BIGINT
)
BEGIN
	SELECT Q.id AS id,
		Q.owner_id AS owner_id,
		Q.type_id AS type_id,
        Q.title AS title,
        Q.context AS context
	FROM Question Q
    WHERE Q.owner_id = userID or Q.public = TRUE;
END $$

DELIMITER ;