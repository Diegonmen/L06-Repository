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

import domain.FixUpTask;
import domain.Phase;
import services.FixUpTaskService;
import services.PhaseService;

@Controller
@RequestMapping("/phase")
public class PhaseController {
	
	@Autowired
	PhaseService phaseService;
	@Autowired
	private FixUpTaskService fixuptaskservice;
	
	@ResponseBody
	@RequestMapping(value = "/handyworker/save-async", method = RequestMethod.POST)
	public String saveAsync(@Valid Phase phase, BindingResult binding, @RequestParam(value = "q") int fixupTaskId) {
		JsonObject result = new JsonObject();
		JsonArray errors = new JsonArray();
		
		if(binding.hasErrors()) {
			for(ObjectError e : binding.getAllErrors()) {
				errors.add(e.getDefaultMessage());
			}
		}else {
			try {
				Phase saved = phaseService.saveAndFlush(phase);
				FixUpTask task = fixuptaskservice.findOne(fixupTaskId);
				task.getPhases().add(saved);
				fixuptaskservice.saveAndFlush(task);
				
				result.addProperty("phase", saved.getId());
			}catch (Throwable oops) {
				errors.add("general.error");
			}
		}
		
		result.add("errors", errors);
		
		return result.toString();
	}

}
