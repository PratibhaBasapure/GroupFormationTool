DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateAlgorithmDefinition $$

CREATE PROCEDURE spCreateAlgorithmDefinition (
	IN algorithmID BIGINT,
	IN questionID BIGINT,
	IN weight DOUBLE,
	IN isChoiceSimilarity TINYINT,
    OUT id BIGINT
)
BEGIN
    INSERT INTO AlgorithmDefinition(algorithmID,questionID,weight,isChoiceSimilarity)
    VALUES (algorithmID,questionID,weight,isChoiceSimilarity);
    SET id = LAST_INSERT_ID();
END $$

DELIMITER ;