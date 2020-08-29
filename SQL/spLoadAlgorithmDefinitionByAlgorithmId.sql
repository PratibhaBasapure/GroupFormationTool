DELIMITER $$

DROP procedure IF EXISTS spLoadAlgorithmDefinitionByAlgorithmId;

CREATE PROCEDURE spLoadAlgorithmDefinitionByAlgorithmId (
    IN algorithmID BIGINT
)
BEGIN
    SELECT a.id, algorithmID,questionID, weight,isChoiceSimilarity, b.questionTypeName
    FROM
    (SELECT a.id, algorithmID,questionID, weight,isChoiceSimilarity, b.type_id
    FROM AlgorithmDefinition a join Question b on a.questionID = b.id
    WHERE  a.algorithmID = algorithmID) a join QuestionType b on a.type_id = b.id
    ORDER BY id DESC ;
END $$
DELIMITER ;