package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Response.Response;

public interface IMatchMatrixGeneration {
	LinkedHashMap<List<User>, Double> generateMatchMatrix(GroupFormationAlgorithm algorithm,
			LinkedHashMap<User, List<Response>> studentsResponses);
}
