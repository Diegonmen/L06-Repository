package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Administrator;
import security.UserAccountService;
import services.ActorService;
import services.AdministratorService;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Supporting services ----------------------------------------------------

	@Autowired
	ActorService	actorService;
	@Autowired
	UserAccountService	 userAccountService;
	@Autowired
	AdministratorService administratorservice;


	// Constructors -----------------------------------------------------------

	public ActorController() {
		super();
	}

	// Login ------------------------------------------------------------------

	@RequestMapping("/register")
	public ModelAndView register() {

		ModelAndView result;
		Administrator actor = administratorservice.create();

		result = new ModelAndView("actor/register");
		result.addObject("actor", actor);

		return result;
	}
}
