DELIMITER $$

DROP procedure IF EXISTS spLoadSurveyQuestionsBySurveyId;


CREATE PROCEDURE spLoadSurveyQuestionsBySurveyId (
IN id BIGINT
)
BEGIN
	SELECT SurveyQuestions.questionID
    FROM SurveyQuestions
    WHERE SurveyQuestions.surveyID = id;
END $$

DELIMITER ;

