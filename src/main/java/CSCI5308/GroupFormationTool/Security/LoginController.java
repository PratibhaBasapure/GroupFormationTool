package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Logger.InfoLoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
	@GetMapping("/login")
	public String login(Model model)
	{
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("accessing /login",null, SystemConfig.instance().getLogDB());
		return "login.html";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model)
	{
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("accessing /login-error",null, SystemConfig.instance().getLogDB());
		return "login-error.html";
	}
}