package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface IQuestionPersistence {
    public List<Question> loadAllQuestionsByInstructor(long instructorId);
    public Question loadQuestionById(long questionId);
    public boolean createQuestion(Question question);
    public boolean deleteQuestion(Question question);
    public List<Question> loadAllQuestionTypes();
}
