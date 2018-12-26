package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Warranty;
import services.WarrantyService;

@Controller
@RequestMapping("/warranty")
public class WarrantyController extends AbstractController {
	
	@Autowired
	WarrantyService warrantyservice;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("warranty/list");
		model.addObject("list", warrantyservice.findAll());
		
		return model;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "warrantyId") int warrantyId, BindingResult binding) {
		ModelAndView result;
		try {
			warrantyservice.delete(warrantyId);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(warrantyservice.findOne(warrantyId), "warranty.commit.error");
		}
		
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Warranty warranty;
		
		warranty = this.warrantyservice.create();
		result = this.createEditModelAndView(warranty);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Warranty warranty) {
		ModelAndView result;
		result = createEditModelAndView(warranty, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Warranty warranty, String messageCode) {
		ModelAndView result;
		result = new ModelAndView("warranty/administrator/edit");
		result.addObject("warranty", warranty);
		result.addObject("message", messageCode);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Warranty warranty;
		
		warranty = this.warrantyservice.create();
		result = this.createEditModelAndView(warranty);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Warranty warranty) {
		ModelAndView result;
		result = createEditModelAndView(warranty, null);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Warranty warranty, String messageCode) {
		ModelAndView result;
		result = new ModelAndView("warranty/edit");
		result.addObject("warranty", warranty);
		result.addObject("message", messageCode);
		
		return result;
	}
}
