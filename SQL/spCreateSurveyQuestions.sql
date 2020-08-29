DELIMITER $$
DROP PROCEDURE IF EXISTS spCreateSurveyQuestions $$

CREATE PROCEDURE spCreateSurveyQuestions (
	 IN surveyID BIGINT,
     IN questionID BIGINT
)
BEGIN
	INSERT INTO SurveyQuestions(surveyID,questionID)
    VALUES (surveyID,questionID);	
END $$

DELIMITER ;