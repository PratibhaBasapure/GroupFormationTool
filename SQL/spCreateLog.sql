DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateLog $$

CREATE PROCEDURE spCreateLog (
	IN class VARCHAR(125),
    IN method VARCHAR(125),
    IN createTime date,
    IN level VARCHAR(75),
    IN message VARCHAR(200),
    IN possibleSolution VARCHAR(200),
    OUT id BIGINT
)
BEGIN
	INSERT INTO Log(class, method,createTime,level,message,possibleSolution)
    VALUES (class, method,createTime,level,message,possibleSolution);
	SET id = LAST_INSERT_ID();
END $$

DELIMITER ;