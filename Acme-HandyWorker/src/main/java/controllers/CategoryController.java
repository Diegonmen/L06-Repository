package controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Category;
import services.AdministratorService;
import services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController{
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	AdministratorService administratorService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("category/list");
		model.addObject("list", categoryservice.findAll());

		return model;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Category category, BindingResult binding) {
		ModelAndView result;
		try {
			categoryservice.delete(category.getId());
			result = new ModelAndView("redirect:/category/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(administratorService.findOneCategory(category.getId()), "category.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Category category;

		category = this.categoryservice.create();
		result = this.createEditModelAndView(category);
		return result;
	}

	protected ModelAndView createEditModelAndView(Category category) {
		ModelAndView result;
		result = createEditModelAndView(category, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Category category, String messageCode) {
		ModelAndView result;
		result = new ModelAndView("category/administrator/edit");
		result.addObject("category", category);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId) {
		ModelAndView result;
		Category category;

		category = administratorService.findOneCategory(categoryId);
		Assert.notNull(category);
		result = createEditModelAndView(category);

		return result;
	}
	
	@RequestMapping(value = "/administrator/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Category category, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(category);
			for(ObjectError e : binding.getAllErrors()) {
				System.out.println(e.getObjectName() + " error [" + e.getDefaultMessage() + "] " + Arrays.toString(e.getCodes()));
			}
		}else {
			try {
				administratorService.saveCategory(category);
				result = new ModelAndView("redirect:/category/list.do");
			}catch (Throwable oops) {
				result = createEditModelAndView(category, "category.commit.error");
			}
		}
		return result;
	}
}

