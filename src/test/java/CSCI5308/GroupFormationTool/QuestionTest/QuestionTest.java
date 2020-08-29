package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {

    @Test
    public void testGetId() {
        Question question = new Question();
        question.setId(123);
        assertEquals(123, question.getId());
    }

    @Test
    public void testSetId() {
        Question question = new Question();
        question.setId(1234);
        assertEquals(1234,question.getId());
    }

    @Test
    public void testGetTitle() {
        Question question = new Question();
        question.setTitle("Question title getter test");
        assertEquals("Question title getter test", question.getTitle());
    }

    @Test
    public void testSetTitle() {
        Question question = new Question();
        question.setType("Question title setter test");
        assertEquals("Question title setter test",question.getType());
    }

    @Test
    public void testGetText() {
        Question question = new Question();
        question.setText("Question text getter test");
        assertEquals("Question text getter test", question.getText());
    }
    @Test
    public void testGettypeID(){
        Question question = new Question();
        question.setTypeID(123);
        assertEquals(123, question.getTypeID());
    }

    @Test
    public void testSetText() {
        Question question = new Question();
        question.setText("Question text setter test");
        assertEquals("Question text setter test", question.getText());
    }

    @Test
    public void testGetType() {
        Question question = new Question();
        question.setType("Question type getter test");
        assertEquals("Question type getter test", question.getType());
    }

    @Test
    public void testSetType() {
        Question question = new Question();
        question.setType("Question type setter test");
        assertEquals("Question type setter test",question.getType());
    }

    @Test
    public void testGetInstructor() {
        Question question = new Question();
        User u = new User();
        u.setId(1234);
        question.setInstructor(u);
        assertEquals(question.getInstructor().getId(), 1234);
    }

    @Test
    public void testSetInstructor() {
        Question question = new Question();
        User u = new User();
        u.setId(100);
        question.setInstructor(u);
        assertEquals(100, question.getInstructor().getId());
    }


    @Test
    public void testGetMultipleChoiceOption() {
        Question question = new Question();
        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();

        MultipleChoiceOption choiceQue1 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue2 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue3 = new MultipleChoiceOption();

        choiceQue1.setDisplayText("Option 1");
        choiceQue1.setOptionNumber(1);
        choices.add(choiceQue1);

        choiceQue2.setDisplayText("Option 2");
        choiceQue2.setOptionNumber(2);
        choices.add(choiceQue2);

        choiceQue3.setDisplayText("Option 3");
        choiceQue3.setOptionNumber(3);
        choices.add(choiceQue3);

        question.setMultipleChoiceOption(choices);
        assertEquals(choices, question.getMultipleChoiceOption());
    }

    @Test
    public void testSetMultipleChoiceOption() {
        Question question = new Question();
        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();

        MultipleChoiceOption choiceQue1 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue2 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue3 = new MultipleChoiceOption();

        choiceQue1.setDisplayText("Option 1");
        choiceQue1.setOptionNumber(1);
        choices.add(choiceQue1);

        choiceQue2.setDisplayText("Option 2");
        choiceQue2.setOptionNumber(2);
        choices.add(choiceQue2);

        choiceQue3.setDisplayText("Option 3");
        choiceQue3.setOptionNumber(3);
        choices.add(choiceQue3);

        question.setMultipleChoiceOption(choices);
        assertEquals(choices, question.getMultipleChoiceOption());
    }

    @Test
    public void testCreateQuestion() {
        IQuestionPersistence queDB = new QuestionDBMock();

        Question question = new Question();
        question.setTitle("Test Question");
        question.setText("How many credits you have taken?");
        question.setTypeID(1);

        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");
        question.setInstructor(user);

        queDB.createQuestion(question);
        Assert.isTrue(question.getId() == 0);
        Assert.isTrue(question.getTitle().equals("Test Question"));
        Assert.isTrue(question.getText().equals("How many credits you have taken?"));
        Assert.isTrue(question.getInstructor().getBannerID().equals("B00847415"));
        Assert.isTrue(question.getInstructor().getFirstName().equals("Pratz"));
        Assert.isTrue(question.getInstructor().getLastName().equals("B"));
        Assert.isTrue(question.getInstructor().getEmail().equals("pr676280@dal.ca"));
    }


    @Test
    public void testLoadAllQuestionTypes()
    {
        IQuestionPersistence queDB = new QuestionDBMock();
        List<Question> questionList  = queDB.loadAllQuestionTypes();
        for (Question questionin: questionList) {
            assertNotNull(questionin);
        }
    }

    @Test
    public void testLoadAllQuestionsByInstructor()
    {
        IQuestionPersistence queDB = new QuestionDBMock();
        List<Question> questions  = queDB.loadAllQuestionsByInstructor(1);
        for (Question questionin: questions) {
            assertNotNull(questionin);
        }
    }

    @Test
    public void testLoadQuestionById(){
        IQuestionPersistence queDB = new QuestionDBMock();

        Question question = queDB.loadQuestionById(1);
        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();

        assertEquals(1, question.getInstructor().getID());
        assertEquals("title",question.getTitle());
        assertEquals("text",question.getText());
        assertEquals(1, question.getId());
        assertEquals(2, question.getTypeID());
        choices = question.getMultipleChoiceOption();
        for (MultipleChoiceOption mul: choices) {
            assertNotNull(mul);
        }
    }
    
    @Test
	public void testDeleteQuestion() {
    	// Create a question
    	IQuestionPersistence queDB = new QuestionDBMock();

        Question question = new Question();
        question.setTitle("Test Question");
        question.setText("How many credits you have taken?");
        question.setTypeID(1);

        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");
        question.setInstructor(user);

        queDB.createQuestion(question);
        
        // Delete created question
		boolean status = queDB.deleteQuestion(question);

        // Test 1: Must set title to an empty string
        Assert.isTrue(question.getTitle().equals(""));
        
        // Test 2: Returned status must not be false
		Assert.isTrue(status);
	}
}
