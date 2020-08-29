use CSCI5308_1_DEVINT;
CREATE TABLE Survey(  
   id BIGINT PRIMARY KEY AUTO_INCREMENT,   
   courseID BIGINT NOT NULL,   
   instructorID bigint NOT NULL,   
   isPublished bit default 0, 
   FOREIGN KEY(courseID) REFERENCES Course(id),   
   FOREIGN KEY(instructoreID) REFERENCES User(id)
   );

CREATE TABLE SurveyQuestions(  
  surveyID bigint,
  questionID bigint,
   FOREIGN KEY(surveyID) REFERENCES Survey(id)
   ON DELETE CASCADE,
   FOREIGN KEY(questionID) REFERENCES Question(id)
      ON DELETE CASCADE
   );
   
CREATE TABLE SurveyResponse(  
  id bigint PRIMARY KEY AUTO_INCREMENT,   
 surveyID bigint,
  questionID bigint,
  studentID bigint,
   FOREIGN KEY(surveyID) REFERENCES Survey(id)
   ON DELETE CASCADE,
   FOREIGN KEY(questionID) REFERENCES Question(id)
      ON DELETE CASCADE,
        FOREIGN KEY(studentID) REFERENCES User(id)
         ON DELETE CASCADE
   );

 
    CREATE TABLE SurveyResponseNumeric(  	
    id bigint PRIMARY KEY AUTO_INCREMENT,   
  responseID bigint,
  answer int,
  questionTypeID int,
   FOREIGN KEY(responseID) REFERENCES SurveyResponse(id)
   ON DELETE CASCADE,
   FOREIGN KEY(questionTypeID) REFERENCES QuestionType(id)
   ON DELETE CASCADE
   );
   
   CREATE TABLE SurveyResponseMultipleChoiceSingle(  	
    id bigint PRIMARY KEY AUTO_INCREMENT,   
  responseID bigint,
  answer int,
  questionTypeID int,
   FOREIGN KEY(responseID) REFERENCES SurveyResponse(id)
   ON DELETE CASCADE,
   FOREIGN KEY(questionTypeID) REFERENCES QuestionType(id)
   ON DELETE CASCADE
   );
   
   CREATE TABLE SurveyResponseMultipleChoiceMultiple(  	
    id bigint PRIMARY KEY AUTO_INCREMENT,   
  responseID bigint,
  answer int,
  questionTypeID int,
   FOREIGN KEY(responseID) REFERENCES SurveyResponse(id)
   ON DELETE CASCADE,
   FOREIGN KEY(questionTypeID) REFERENCES QuestionType(id)
   ON DELETE CASCADE
   );
   
   CREATE TABLE SurveyResponseFreeText(  	
    id bigint PRIMARY KEY AUTO_INCREMENT,   
  responseID bigint,
  answer varchar(2000),
  questionTypeID int,
   FOREIGN KEY(responseID) REFERENCES SurveyResponse(id)
   ON DELETE CASCADE,
   FOREIGN KEY(questionTypeID) REFERENCES QuestionType(id)
   ON DELETE CASCADE
   );