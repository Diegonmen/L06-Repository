package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.BoxServices;

@Controller
@RequestMapping("/box")
public class BoxController extends AbstractController {

	@Autowired
	BoxServices boxService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("box/list");
		model.addObject("boxes", boxService.findBoxesByUserAccountId(LoginService.getPrincipal().getId()));

		return model;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam int boxId) {
		ModelAndView result;

		result = new ModelAndView("box/display");
		result.addObject("actor", boxService.findOne(boxId));

		return result;
	}
}
