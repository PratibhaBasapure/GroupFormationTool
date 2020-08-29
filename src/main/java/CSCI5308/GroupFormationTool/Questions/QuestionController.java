package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Logger.InfoLoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

	private static final String ID = "id";

	private static final int NUMERIC_TYPE_ID = 1;
	private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
	private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
	private static final int FREE_TEXT_TYPE_ID = 4;

	@RequestMapping("/question/questionmanager")
	public String createQuestion(Model model) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("accessing /question/questionmanager ",null, SystemConfig.instance().getLogDB());
	    IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
	    User user = CurrentUser.instance().getCurrentAuthenticatedUser();
	    Question q = new Question();
	    q.setInstructor(user);
	    List<Question> questionTypeList = q.getAllQuestionTypes(questionDB);
	    model.addAttribute("questionTypeList", questionTypeList);
	    model.addAttribute("question", new Question());
	    return "createquestion";
	}

	@RequestMapping(path = "/question/createQuestion", method = RequestMethod.POST)
	public String createQuestion(@ModelAttribute Question question, Model model, RedirectAttributes attr,
			@RequestParam(name = "displayText", required = false) ArrayList<String> displaytext,
			@RequestParam(name = "optionNumber", required = false) ArrayList<Integer> optionNumber) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("accessing /question/createQuestion with "+ question.toString(),null, SystemConfig.instance().getLogDB());
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		model.addAttribute("question", question);
		Question q = new Question();
		q.setTitle(question.getTitle());
		q.setText(question.getText());
		q.setTypeID(question.getTypeID());
		q.setInstructor(user);
		if (q.getTypeID() == 1 || q.getTypeID() == 4) {
			q.createQuestion(questionDB);
			return "redirect:/question/questionmanager";
		} else {
			if (optionNumber == null || displaytext == null) {
				attr.addFlashAttribute("question", question);
				model.addAttribute("question", question);
				return "redirect:/question/questionoptions";
			} else {
				return "redirect:/question/questionmanager";
			}
		}
	}

	@RequestMapping(value = "/question/questionoptions", method = RequestMethod.GET)
	public String displayQuestion(@ModelAttribute Question question, Model model, RedirectAttributes attr) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/question/questionoptions with "+ question.toString(),null, SystemConfig.instance().getLogDB());
		List<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
		attr.addFlashAttribute("question", question);
		MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
		model.addAttribute("multipleChoiceOption", multipleChoiceOption);
		model.addAttribute("multipleChoiceOptionList", multipleChoiceOptionList);
		return "questionswithoptions";
	}

	@RequestMapping(value = "/question/createQuestionmultiple", method = RequestMethod.POST)
	public String createQuestionmultiple(@ModelAttribute Question question, Model model, RedirectAttributes attr,
			@RequestParam(name = "questiontitle", required = false) String questiontitle,
			@RequestParam(name = "questiontypeid", required = false) int questiontypeid,
			@RequestParam(name = "questiontext", required = false) String questiontext,
			@RequestParam(name = "displayText", required = false) ArrayList<String> displaytext,
			@RequestParam(name = "optionNumber", required = false) ArrayList<Integer> optionNumber) {

		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		Question q = new Question();
		q.setTitle(questiontitle);
		q.setText(questiontext);
		q.setTypeID(questiontypeid);
		q.setInstructor(user);
		ArrayList<MultipleChoiceOption> multipleChoices = new ArrayList<MultipleChoiceOption>();
		for (int i = 0; i < displaytext.size(); i++) {
			if (optionNumber.get(i) != null && !displaytext.get(i).isEmpty()) {
				MultipleChoiceOption choice = new MultipleChoiceOption();
				choice.setDisplayText(displaytext.get(i));
				choice.setOptionNumber(optionNumber.get(i));
				multipleChoices.add(choice);
			}
		}
		q.setMultipleChoiceOption(multipleChoices);
		q.createQuestion(questionDB);
		infoLogger.logMessage("/question/createQuestionmultiple with "+question.toString(),null, SystemConfig.instance().getLogDB());
		return "redirect:/question/questionmanager";
	}

	@GetMapping("/question/questionmanagement")
	public String questionManagement(Model model) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/question/questionmanagement",null, SystemConfig.instance().getLogDB());
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		Question q = new Question();
		q.setInstructor(user);
		List<Question> questionList = q.loadAllQuestionsByInstructor(questionDB);
		model.addAttribute("questionlist", questionList);
		return "questionmanagement";
	}

	@GetMapping("/question/questiondelete/{questionId}")
	public String renderCourseAdminPage(@PathVariable("questionId") long questionId, Model model,
			RedirectAttributes redirectAttributes) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/question/questiondelete with questionID" + questionId,null, SystemConfig.instance().getLogDB());
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		Question q = new Question();
		q.setId(questionId);
		boolean success = q.deleteQuestion(questionDB);
		if (success) {
			redirectAttributes.addFlashAttribute("message", "Success");
		} else {
			redirectAttributes.addFlashAttribute("message", "Failed to delete");
		}

		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		q.setInstructor(user);
		List<Question> questionList = q.loadAllQuestionsByInstructor(questionDB);
		model.addAttribute("questionlist", questionList);
		return "questionmanagement";
	}

	@PostMapping("/question/questionsort")
	public String sortQuestions(Model model, @RequestBody String body) {
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		Question q = new Question();
		q.setInstructor(user);
		List<Question> questionList = q.loadAllQuestionsByInstructor(questionDB);
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/question/questionsort with body " + body+" user "+user.toString(),null, SystemConfig.instance().getLogDB());
		String sortOption = body.split("sort=")[1];
		ISortQuestions sq = new SortQuestions();

		List<Question> sortedQuestionList = questionList;

		if (sortOption.equals("titleAsc")) {
			sortedQuestionList = sq.sortAscendingByTitle(questionList);
		} else if (sortOption.equals("titleDes")) {
			sortedQuestionList = sq.sortDescendingByTitle(questionList);
		} else if (sortOption.equals("newToOld")) {
			sortedQuestionList = sq.sortNewestToOldest(questionList);
		} else if (sortOption.equals("oldToNew")) {
			sortedQuestionList = sq.sortOldestToNewest(questionList);
		}

		model.addAttribute("questionlist", sortedQuestionList);
		
		return "questionmanagement";
	}

    @GetMapping("/question/viewquestion")
    public ModelAndView viewCourse(@RequestParam(name = ID) long id, Model model)
    {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/question/viewquestion with questionID" + id,null, SystemConfig.instance().getLogDB());
        ModelAndView modelAndView = new ModelAndView("viewquestion");
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        Question question = questionDB.loadQuestionById(id);
        ArrayList<MultipleChoiceOption> choices = new ArrayList<>();
        if(question.getTypeID() == MULTI_CHOICE_MULTI_ONE_TYPE_ID || question.getTypeID() == MULTI_CHOICE_MULTI_MULTI_TYPE_ID){
            choices = question.getMultipleChoiceOption();
            for (MultipleChoiceOption option: choices) {
                modelAndView.addObject("choices", choices);
            }
        }
        boolean isNumeric = false;
        if(question.getTypeID() == NUMERIC_TYPE_ID){
            modelAndView.addObject("isNumeric", true);
        }
        boolean isFreeText = false;
        if(question.getTypeID() == FREE_TEXT_TYPE_ID){
            modelAndView.addObject("isFreeText",true);
        }

        modelAndView.addObject("questiontype", question.getTypeID());
        modelAndView.addObject("question", question);
        return modelAndView;
    }

}
