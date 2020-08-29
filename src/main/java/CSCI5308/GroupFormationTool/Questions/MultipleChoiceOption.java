package CSCI5308.GroupFormationTool.Questions;

public class MultipleChoiceOption {
    private String displayText;
    private int optionNumber;

    public MultipleChoiceOption() {
        this.displayText = "";
        this.optionNumber = -1;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }
}
