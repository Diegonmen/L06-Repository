package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Application;
import domain.HandyWorker;
import services.ApplicationService;
import services.HandyWorkerService;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController{

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private HandyWorkerService handyWorkerService;
	
	public ApplicationController() {
		super();
	}
	
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//		ModelAndView result;
//		Application application;
//		
//		application = this.applicationService.create();
//		result = this.createEditModelAndView(application);
//		
//		return result;
//	}
	
//	protected ModelAndView createEditModelAndView(Application application) {
//		ModelAndView result;
//		
//		result = createEditModelAndView(application, null);
//		return result;
//	}
//	
//	protected ModelAndView createEditModelAndView(Application application, String messageCode) {
//		ModelAndView result;
//		
//		
//		if (application.getId() > 0)
//			result = new ModelAndView("application/edit");
//		else
//			result = new ModelAndView("application/create");
//	
//		result.addObject("application", application);
//		result.addObject("message", messageCode);
//		
//		return result;
//	}
	
}
