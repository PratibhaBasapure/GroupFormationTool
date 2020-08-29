package CSCI5308.GroupFormationTool.ResponseTest;
import static org.mockito.Mockito.mock;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.Response.Response;
import CSCI5308.GroupFormationTool.Response.ResponseDB;

import java.util.ArrayList;

public class ResponseDBMock implements IResponsePersistence{

    private final IResponsePersistence responseDB = mock(ResponseDB.class);

    @Override
    public boolean saveResponse(ArrayList<Response> responses, int surveyID) {

        for (Response eachResponse: responses) {
            if (surveyID == 1 && eachResponse.getSingleresponse().equals("singleResponse"))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isResponseprovidedByStudent(Long studentId, Long courseID) {

        if (studentId != null && courseID != null)
        {
            return true;
        }
        return false;
    }
}
