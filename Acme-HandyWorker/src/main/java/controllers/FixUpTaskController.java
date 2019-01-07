package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Application;
import domain.Complaint;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.ApplicationService;
import services.CategoryService;
import services.ComplaintService;
import services.CustomerService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.PhaseService;
import services.WarrantyService;

@Controller
@RequestMapping("/fixuptask")
public class FixUpTaskController {
	
	@Autowired
	FixUpTaskService fixuptaskservice;
	@Autowired
	CustomerService customerservice;
	@Autowired
	CategoryService categoryService;
	@Autowired
	WarrantyService warrantyService;
	@Autowired
	ApplicationService applicationservice;
	@Autowired
	PhaseService phaseservice;
	@Autowired
	ComplaintService complaintservice;
	@Autowired
	HandyWorkerService handyworkerservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("fixuptask/list");
		model.addObject("list", fixuptaskservice.findAll());

		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/async/phases", method = RequestMethod.GET)
	public String asyncPhases(@RequestParam(required = true, value = "q", defaultValue = "-1") int id) {
		Collection<Phase> phases = fixuptaskservice.getPhasesOf(id);
		
		JsonArray array = new JsonArray();
		
		for(Phase e : phases) {
			JsonObject json = new JsonObject();
			json.addProperty("title", e.getTitle());
			json.addProperty("id", e.getId());
			
			array.add(json);
		}
		
		return array.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/async/complaints", method = RequestMethod.GET)
	public String asyncComplaints(@RequestParam(required = true, value = "q", defaultValue = "-1") int id) {
		Collection<Complaint> phases = fixuptaskservice.getComplaintsOf(id);
		
		JsonArray array = new JsonArray();
		
		for(Complaint e : phases) {
			JsonObject json = new JsonObject();
			json.addProperty("title", e.getTicker());
			json.addProperty("id", e.getId());
			
			array.add(json);
		}
		
		return array.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/async/aplications", method = RequestMethod.GET)
	public String asyncAplications(@RequestParam(required = true, value = "q", defaultValue = "-1") int id) {
		Collection<Application> aplications = fixuptaskservice.getApplicationsOf(id);
		
		JsonArray array = new JsonArray();
		
		for(Application e : aplications) {
			JsonObject json = new JsonObject();
			json.addProperty("title", e.getHandyWorker().getName() + " " + e.getHandyWorker().getSurname());
			json.addProperty("id", e.getId());
			
			array.add(json);
		}
		
		return array.toString();
	}
	
	@RequestMapping(value = "/customer/application-accept", method = RequestMethod.GET)
	public ModelAndView acceptApplication(@RequestParam(value = "q") int applicationId, @RequestParam(value = "f") int fixUpTaskId) {
		Application application = applicationservice.findOne(applicationId);
		application.setStatus("ACCEPTED");
		
		applicationservice.save(application);
		
		return edit(fixUpTaskId);
	}
	
	@RequestMapping(value = "/customer/application-reject", method = RequestMethod.GET)
	public ModelAndView rejectApplication(@RequestParam(value = "q") int applicationId, @RequestParam(value = "f") int fixUpTaskId) {
		Application application = applicationservice.findOne(applicationId);
		application.setStatus("REJECTED");
		
		applicationservice.save(application);
		
		return edit(fixUpTaskId);
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(FixUpTask fixuptask, BindingResult binding) {
		ModelAndView result;
		try {
			fixuptaskservice.delete(fixuptask);
			result = new ModelAndView("redirect:/fixuptask/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(fixuptask, "fixuptask.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		FixUpTask fixuptask;

		fixuptask = this.fixuptaskservice.create();
		result = this.createEditModelAndView(fixuptask);
		
		return result;
	}

	protected ModelAndView createEditModelAndView(FixUpTask fixuptask) {
		ModelAndView result;
		result = createEditModelAndView(fixuptask, null);
		result.addObject("fixuptasks", fixuptaskservice.findAll());
		
		return result;
	}

	protected ModelAndView createEditModelAndView(FixUpTask fixuptask, String messageCode) {
		ModelAndView result;
		
		UserAccount account = LoginService.getPrincipal();
		
		result = new ModelAndView(fixuptask.getId() < 1 ? "fixuptasks/customer/create" : "fixuptasks/customer/edit");
		result.addObject("fixuptask", fixuptask);
		result.addObject("categories", categoryService.findAll());
		result.addObject("warranties", warrantyService.findAll());
		
		boolean isHandyWorker = false;
		boolean isCustomer = false;
		boolean canAddApplication = true;
		boolean canAddPhase = false;
		
		for(Authority auth : account.getAuthorities()) {
			if(Authority.HANDYWORKER.equals(auth.getAuthority())) {
				isHandyWorker = true;
			}
			
			if(Authority.CUSTOMER.equals(auth.getAuthority())) {
				isCustomer = true;
			}
		}
		
		result.addObject("isHandyWorker", isHandyWorker);
		result.addObject("isCustomer", isCustomer);
		
		Collection<Complaint> allComplaints = complaintservice.findAll();
		
		// Ya tiene una aplicacion, no puede añadir más
		if(isHandyWorker) {
			HandyWorker worker = handyworkerservice.findByPrincipal();
			for(Application a : fixuptask.getApplications()) {
				if(a.getHandyWorker() != null && a.getHandyWorker().equals(worker)) {
					canAddApplication = false;
					canAddPhase = "ACCEPTED".equals(a.getStatus());
					break;
				}
			}
			
			result.addObject("acceptedApplication", applicationservice.findAcceptedHandyWorkerApplicationByFixUpTaskId(fixuptask.getId(), worker.getId()));
			result.addObject("workerId", worker.getId());
		} else if(isCustomer) {
			for(Application a : fixuptask.getApplications()) {
				if("ACCEPTED".equals(a.getStatus())) {
					canAddPhase = true;
					break;
				}
			}
		}
		
		result.addObject("canAddPhase", canAddPhase);
		result.addObject("canAddApplication", canAddApplication);
		result.addObject("allComplaints", allComplaints);
		
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int fixuptaskId) {
		ModelAndView result;
		FixUpTask fixuptask;

		fixuptask = fixuptaskservice.findOne(fixuptaskId);
		Assert.notNull(fixuptask);
		result = createEditModelAndView(fixuptask);

		return result;
	}
	
	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FixUpTask fixuptask, BindingResult binding) {
		ModelAndView result = new ModelAndView("redirect:/fixuptask/list.do");
		
		if(binding.hasErrors()) {
			result = createEditModelAndView(fixuptask);
		}else {
			try {
				fixuptaskservice.saveAndFlush(fixuptask);
			} catch(ObjectOptimisticLockingFailureException ex) {
				// This exception will can ignore
				return result;
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = createEditModelAndView(fixuptask, "fixuptask.commit.error");
			}
		}
		return result;
	}

}
