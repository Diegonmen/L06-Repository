package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Application;
import domain.FixUpTask;
import services.ApplicationService;
import services.FixUpTaskService;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController{

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private FixUpTaskService fixuptaskservice;
	
	public ApplicationController() {
		super();
	}
	
	@ResponseBody
	@RequestMapping(value = "/handyworker/save-async", method = RequestMethod.POST)
	public String saveAsync(@Valid Application application, BindingResult binding, @RequestParam(value = "q") int fixupTaskId) {
		JsonObject result = new JsonObject();
		JsonArray errors = new JsonArray();
		
		if(binding.hasErrors()) {
			for(ObjectError e : binding.getAllErrors()) {
				errors.add(e.getDefaultMessage());
			}
		}else {
			try {
				Application saved = applicationService.save(application);
				FixUpTask task = fixuptaskservice.findOne(fixupTaskId);
				task.getApplications().add(saved);
				fixuptaskservice.saveAndFlush(task);
				
				result.addProperty("application", saved.getId());
			}catch (Throwable oops) {
				errors.add("general.error");
			}
		}
		
		result.add("errors", errors);
		
		return result.toString();
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
