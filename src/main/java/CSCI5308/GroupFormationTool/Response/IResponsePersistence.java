package CSCI5308.GroupFormationTool.Response;

import java.util.ArrayList;

public interface IResponsePersistence {
    public boolean saveResponse(ArrayList<Response> responses, int surveyID);
    public boolean isResponseprovidedByStudent(Long studentId, Long courseID);
}
