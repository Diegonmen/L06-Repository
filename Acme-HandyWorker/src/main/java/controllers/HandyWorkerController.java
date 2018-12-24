package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.HandyWorker;
import services.HandyWorkerService;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService handyWorkerService;
	
	public HandyWorkerController() {
		super();
	}
	
	@RequestMapping("/viewProfile")
	public ModelAndView view() {
		ModelAndView result;

		result = new ModelAndView("handyWorker/viewProfile");
		result.addObject("actor", handyWorkerService.findByPrincipal());

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.create();
		result = this.createEditModelAndView(handyWorker);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		HandyWorker handyWorker;
		
		handyWorker = handyWorkerService.findByPrincipal();
		result = createEditModelAndView(handyWorker);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid HandyWorker handyWorker, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(handyWorker);
		}else {
			try {
				handyWorkerService.save(handyWorker);
				result = new ModelAndView("redirect:list.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(handyWorker, "handyWorker.commit.error");
			}
		}
		return result;
	}
		
	protected ModelAndView createEditModelAndView(HandyWorker handyWorker) {
		ModelAndView result;
		
		result = createEditModelAndView(handyWorker, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(HandyWorker handyWorker, String messageCode) {
		ModelAndView result;
		
		if (handyWorker.getId() > 0)
			result = new ModelAndView("handyWorker/edit");
		else
			result = new ModelAndView("handyWorker/create");
	
		result.addObject("handyWorker", handyWorker);
		result.addObject("message", messageCode);
		
		return result;
	}
	
}
