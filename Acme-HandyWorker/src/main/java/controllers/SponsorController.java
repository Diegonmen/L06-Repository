package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Sponsor;
import services.SponsorService;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService sponsorService;
	
	public SponsorController() {
		super();
	}
	
	@RequestMapping("/viewProfile")
	public ModelAndView view() {
		ModelAndView result;

		result = new ModelAndView("sponsor/viewProfile");
		result.addObject("actor", sponsorService.findByPrincipal());

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.create();
		result = this.createEditModelAndView(sponsor);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Sponsor sponsor;
		
		sponsor = sponsorService.findByPrincipal();
		result = createEditModelAndView(sponsor);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Sponsor sponsor, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(sponsor);
		}else {
			try {
				sponsorService.save(sponsor);
				result = new ModelAndView("redirect:list.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(sponsor, "sponsor.commit.error");
			}
		}
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Sponsor sponsor) {
		ModelAndView result;
		
		result = createEditModelAndView(sponsor, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Sponsor sponsor, String messageCode) {
		ModelAndView result;
		
		if (sponsor.getId() > 0)
			result = new ModelAndView("sponsor/edit");
		else
			result = new ModelAndView("sponsor/create");
	
		result.addObject("sponsor", sponsor);
		result.addObject("message", messageCode);
		
		return result;
	}
		
	
}
