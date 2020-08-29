DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateHistoryPassword $$

CREATE PROCEDURE spCreateHistoryPassword (
	IN userID BIGINT,
    IN password VARCHAR(76),
    IN modify_date DATE,
    OUT id BIGINT
)
BEGIN
	INSERT INTO History_password(userID, password,modify_date)
    VALUES (userID, password,modify_date);
	SET id = LAST_INSERT_ID();
END $$

DELIMITER ;