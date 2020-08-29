DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateGroup $$

CREATE PROCEDURE spCreateGroup (
	IN courseID BIGINT,
	IN createOn DATE,
	IN updateOn DATE,
    OUT id BIGINT
)

BEGIN
    INSERT INTO FormedGroup(courseID,createOn,updateOn)
    VALUES (courseID,createOn,updateOn);
    SET id = LAST_INSERT_ID();
END $$

DELIMITER ;