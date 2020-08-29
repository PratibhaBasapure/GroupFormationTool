DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateAlgorithm $$

CREATE PROCEDURE spCreateAlgorithm (
	IN courseID BIGINT,
	IN createOn DATE,
	IN updateOn DATE,
	IN groupSize INT,
    OUT id BIGINT
)

BEGIN
    INSERT INTO Algorithm(courseID,createOn,updateOn,groupSize)
    VALUES (courseID,createOn,updateOn,groupSize);
    SET id = LAST_INSERT_ID();
END $$

DELIMITER ;