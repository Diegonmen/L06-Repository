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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrator;
import services.AdministratorService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	@Autowired
	private AdministratorService administratorservice;

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
		}else {
			try {
				administratorservice.save(administrator);
				result = new ModelAndView("redirect:list.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(administrator, "administrator.commit.error");
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
		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", messageCode);

		return result;
	}

}
