package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestionPersistence {
    @Override
    public List<Question> loadAllQuestionsByInstructor(long instructorId) {

        List<Question> questionList = new ArrayList<>();
        User user = new User();
        user.setID(123);

        Question question = new Question();
        question.setText("text");
        question.setTitle("title");
        question.setTypeID(1);
        question.setId(1);
        question.setInstructor(user);
        questionList.add(question);

        User user1 = new User();
        user.setID(1234);
        Question question1 = new Question();
        question.setText("text1");
        question.setTitle("title1");
        question.setTypeID(2);
        question.setId(2);
        question.setInstructor(user1);
        questionList.add(question1);

        return questionList;
    }

    @Override
    public Question loadQuestionById(long questionId) {
        User user = new User();
        user.setID(1);
        Question question = new Question();
        question.setTitle("title");
        question.setId(1);
        question.setText("text");
        question.setTypeID(2);
        question.setInstructor(user);

        question.setMultipleChoiceOption(loadMultipleChoiceOptions(1));

        return question;
    }

    private ArrayList<MultipleChoiceOption> loadMultipleChoiceOptions(long questionId) {

        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();
        MultipleChoiceOption choiceQue1 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue2 = new MultipleChoiceOption();
        choiceQue1.setDisplayText("Option 1");
        choiceQue1.setOptionNumber(1);
        choices.add(choiceQue1);
        choiceQue2.setDisplayText("Option 2");
        choiceQue2.setOptionNumber(2);
        choices.add(choiceQue2);

        return choices;
    }

    @Override
    public boolean createQuestion(Question question) {
        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");

        question.setId(0);
        question.setTypeID(1);
        question.setInstructor(user);
        question.setTitle("Test Question");
        question.setText("How many credits you have taken?");

        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();
        MultipleChoiceOption choiceQue1 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue2 = new MultipleChoiceOption();
        choiceQue1.setDisplayText("Option 1");
        choiceQue1.setOptionNumber(1);
        choices.add(choiceQue1);
        choiceQue2.setDisplayText("Option 1");
        choiceQue2.setOptionNumber(1);
        choices.add(choiceQue2);
        createMultipleQuestionOptions(1,choices);

        return true;
    }

    private boolean createMultipleQuestionOptions(long id, ArrayList<MultipleChoiceOption> options){
        return true;
    }
    
    @Override
    public boolean deleteQuestion(Question question) {
    	question.setTitle("");
    	question.setText("");
        return true;
    }

    @Override
    public List<Question> loadAllQuestionTypes() {
        List<Question> questions = new ArrayList<Question>();

        Question question = new Question();
        question.setType("numeric");
        question.setTypeID(1);
        questions.add(question);

        Question question1 = new Question();
        question1.setType("multiplechoicechooseone");
        question1.setTypeID(2);
        questions.add(question1);

        Question question2 = new Question();
        question2.setType("multiplechoicechoosemultiple");
        question2.setTypeID(3);
        questions.add(question2);

        Question question3 = new Question();
        question3.setType("freetext");
        question3.setTypeID(4);
        questions.add(question3);

        return questions;
    }
}
