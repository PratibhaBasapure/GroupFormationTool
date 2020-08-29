DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion(
	IN title VARCHAR(200),
    IN questionText VARCHAR(500),
    IN instructorID bigInt(20),
    IN type_id int(11)
)
BEGIN
	DECLARE qID bigint(11);
	INSERT INTO Question(instructorID,type_id,title,questionText,createdON)
    VALUES (instructorID,type_id,title,questionText,CURRENT_DATE);
	SET qid = LAST_INSERT_ID();
    SELECT Q.id from Question Q WHERE Q.id = qID;
END $$

DELIMITER ;