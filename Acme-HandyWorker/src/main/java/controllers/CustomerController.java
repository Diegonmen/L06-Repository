/*
 * CustomerController.java
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

import domain.Customer;
import services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService customerService;
	
	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("customer/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("customer/action-2");

		return result;
	}

	@RequestMapping("/viewProfile")
	public ModelAndView view() {
		ModelAndView result;

		result = new ModelAndView("customer/viewProfile");
		result.addObject("actor", customerService.findByPrincipal());

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.create();
		result = this.createEditModelAndView(customer);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		result = createEditModelAndView(customer);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Customer customer, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(customer);
			for(ObjectError e : binding.getAllErrors()) {
				System.out.println(e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
			}
		}else {
			try {
				customerService.save(customer);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(customer, "customer.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Customer customer) {
		ModelAndView result;
		
		result = createEditModelAndView(customer, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Customer customer, String messageCode) {
		ModelAndView result;
		
		if (customer.getId() > 0)
			result = new ModelAndView("customer/edit");
		else
			result = new ModelAndView("customer/register");
	
		result.addObject("actor", customer);
		result.addObject("message", messageCode);
		
		return result;
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {

		ModelAndView result;
		Customer actor = customerService.create();

		result = new ModelAndView("customer/register");
		result.addObject("actor", actor);

		return result;
	}

}

