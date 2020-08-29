DELIMITER $$

DROP procedure IF EXISTS spLoadAlgorithmByCourseId;

CREATE PROCEDURE spLoadAlgorithmByCourseId (
    IN courseID BIGINT
)
BEGIN
    SELECT id, courseID,createOn, updateOn,groupSize
    FROM Algorithm
    WHERE Algorithm.courseID = courseID
    ORDER BY createOn DESC ;
END $$
DELIMITER ;