/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import domain.Referee;
import services.AdministratorService;
import services.RefereeService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	@Autowired
	private AdministratorService administratorservice;
	
	@Autowired
	private RefereeService refereeService;

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

	@RequestMapping("/viewProfile")
	public ModelAndView view() {
		ModelAndView result;

		result = new ModelAndView("administrator/viewProfile");
		result.addObject("actor", administratorservice.findByPrincipal());

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorservice.create();
		result = this.createEditModelAndView(administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator administrator;
		

		administrator = administratorservice.findByPrincipal();
		
		
		result = createEditModelAndView(administrator);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Administrator administrator, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(administrator);
			for(ObjectError e : binding.getAllErrors()) {
				System.out.println(e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
			}
		}else {
			try {
				administratorservice.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(administrator, "administrator.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/editReferee", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Referee referee, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(referee);
			for(ObjectError e : binding.getAllErrors()) {
				System.out.println(e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
			}
		}else {
			try {
				administratorservice.saveReferee(referee);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(referee, "referee.commit.error");
			}
		}
		return result;
	}
	

	protected ModelAndView createEditModelAndView(Administrator administrator) {
		ModelAndView result;
		
		result = createEditModelAndView(administrator, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Administrator administrator, String messageCode) {
		ModelAndView result;
		
		if (administrator.getId() > 0)
			result = new ModelAndView("administrator/edit");
		else
			result = new ModelAndView("administrator/registerAdministrator");
	
		result.addObject("actor", administrator);
		result.addObject("message", messageCode);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Referee referee) {
		ModelAndView result;
		
		result = createEditModelAndView(referee, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Referee referee, String messageCode) {
		ModelAndView result;
		
		result = new ModelAndView("administrator/registerReferee");
	
		result.addObject("actor", referee);
		result.addObject("message", messageCode);
		
		return result;
	}
	
	
	@RequestMapping("/registerAdministrator")
	public ModelAndView registerAdmin() {

		ModelAndView result;
		Administrator actor = administratorservice.create();

		result = new ModelAndView("administrator/registerAdministrator");
		result.addObject("actor", actor);

		return result;
	}
	
	@RequestMapping("/registerReferee")
	public ModelAndView registerReferee() {

		ModelAndView result;
		Referee actor = refereeService.create();

		result = new ModelAndView("administrator/registerReferee");
		result.addObject("actor", actor);

		return result;
	}

}
