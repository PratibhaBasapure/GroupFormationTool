DELIMITER $$

DROP procedure IF EXISTS spLoadSurveyQuestionsByCourseId;


CREATE PROCEDURE spLoadSurveyQuestionsByCourseId (
IN id BIGINT
)
BEGIN
	select SurveyQuestions.questionID,QuestionType.questionTypeName,Question.title,Question.questionText,Question.createdOn,Survey.isPublished from Survey
join SurveyQuestions on Survey.id=SurveyQuestions.surveyID
join Question on SurveyQuestions.questionID=Question.id
join QuestionType on Question.type_id=QuestionType.id
    WHERE Survey.courseID = id;
END $$

DELIMITER ;

