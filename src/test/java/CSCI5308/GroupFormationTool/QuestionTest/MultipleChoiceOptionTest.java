package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceOptionTest {

    @Test
    public void testGetDisplayText() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        assertTrue(multipleChoiceOption.getDisplayText().isEmpty());
        multipleChoiceOption.setDisplayText("test");
        assertTrue(multipleChoiceOption.getDisplayText().equals("test"));
    }

    @Test
    public void testSetDisplayText() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setDisplayText("Display text");
        assertTrue(multipleChoiceOption.getDisplayText().equals("Display text"));
    }

    @Test
    public void testGetOptionNumber() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setOptionNumber(1000);
        assertEquals(1000, multipleChoiceOption.getOptionNumber());
    }

    @Test
    public void testSetOptionNumber() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setOptionNumber(1234);
        assertEquals(1234, multipleChoiceOption.getOptionNumber());
    }
}
