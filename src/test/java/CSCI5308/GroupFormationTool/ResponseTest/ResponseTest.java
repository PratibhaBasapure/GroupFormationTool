package CSCI5308.GroupFormationTool.ResponseTest;

import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.Response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SuppressWarnings("deprecation")
public class ResponseTest {

    @Test
    public void testGetQuestion()
    {
        Response response = new Response();
        Question question = new Question();
        question.setId(1);
        response.setQuestion(question);
        assertEquals(1, response.getQuestion().getId());
    }
    @Test
    public void testSetQuestion()
    {
        Response response = new Response();
        Question question = new Question();
        question.setId(123);
        response.setQuestion(question);
        assertEquals(123, response.getQuestion().getId());
    }

    @Test
    public void testGetSingleResponse()
    {
        Response response = new Response();
        response.setSingleresponse("test");
        assertEquals("test", response.getSingleresponse());
    }
    @Test
    public void testSetSingleResponse()
    {
        Response response = new Response();
        response.setSingleresponse("sample");
        assertEquals("sample", response.getSingleresponse());
    }

    @Test
    public void testGetResponse()
    {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("test");
        Response response = new Response();
        response.setResponse(responses);
        assertNotNull(response.getResponse());
    }

    @Test
    public void testSetResponse()
    {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("sample");
        Response response = new Response();
        response.setResponse(responses);
        assertNotNull(response.getResponse());
    }

    @Test
    public void testSaveResponse(){
        ArrayList<Response> responses = new ArrayList<>();
        Response response = new Response();
        response.setSingleresponse("singleResponse");
        responses.add(response);
        IResponsePersistence responseDBMock = new ResponseDBMock();
        assertTrue(responseDBMock.saveResponse(responses,1));
    }

    @Test
    public void testIsResponseprovidedByStudent(){
        IResponsePersistence responseDBMock = new ResponseDBMock();
        assertTrue(responseDBMock.isResponseprovidedByStudent(Long.valueOf(3),Long.valueOf(2)));
    }

}
