package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.ArrayList;

public class Response {

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    private Question question;
    private String singleresponse;
    private ArrayList<String> response;

    public String getSingleresponse() {
        return singleresponse;
    }

    public void setSingleresponse(String singleresponse) {
        this.singleresponse = singleresponse;
    }


    public ArrayList<String> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<String> response) {
        this.response = response;
    }

    public boolean saveResponse(IResponsePersistence responseDB, ArrayList<Response> responses, int surveyID){
        return responseDB.saveResponse(responses,surveyID);
    }

    public boolean isResponseprovidedByStudent(IResponsePersistence responseDB, Long studentId, Long courseID){
        return responseDB.isResponseprovidedByStudent(studentId, courseID);
    }
}
