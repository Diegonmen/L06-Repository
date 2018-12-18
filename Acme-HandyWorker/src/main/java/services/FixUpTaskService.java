
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
import domain.FixUpTask;
import repositories.FixUpTaskRepository;

@Service
@Transactional
public class FixUpTaskService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FixUpTaskRepository fixUpTaskRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService customerService;

	// Simple CRUD methods ----------------------------------------------------

	public FixUpTask save(FixUpTask entity) {
		return fixUpTaskRepository.save(entity);
	}

	public List<FixUpTask> findAll() {
		return fixUpTaskRepository.findAll();
	}

	public FixUpTask findOne(Integer id) {
		return fixUpTaskRepository.findOne(id);
	}

	public void delete(FixUpTask entity) {
		fixUpTaskRepository.delete(entity);
	}

	public boolean exists(final Integer id) {
		return this.fixUpTaskRepository.exists(id);
	}

	// Other business methods

	public Collection<FixUpTask> findFixUpTasksByCustomer(final Customer customer) {
		Assert.notNull(customer);
		Assert.isTrue(this.customerService.exists(customer.getId()));

		Collection<FixUpTask> result;
		result = this.fixUpTaskRepository.findFixUpTasksByCustomer(customer.getId());

		return result;
	}

	public Collection<FixUpTask> findAllFixUpTaskWithAcceptedApplications() {
		Collection<FixUpTask> res;
		res = fixUpTaskRepository.findAllFixUpTaskWithAcceptedApplications();
		Assert.notEmpty(res);
		return res;
	}

	public Collection<Double> findAvgMinMaxStdDvtFixUpTasksPerUser() {
		Collection<Double> res = fixUpTaskRepository.findAvgMinMaxStrDvtFixUpTaskPerUser();
		return res;
	}
	
	public Collection<Double> findAvgMinMaxStrDvtPerFixUpTask() {
		Collection<Double> res = fixUpTaskRepository.findAvgMinMaxStrDvtPerFixUpTask();
		return res;
	}
	
	public Double ratioFixUpTasksWithComplaints() {
		Double res = fixUpTaskRepository.ratioFixUpTasksWithComplaints();
		Assert.notNull(res);
		return res;
	}
	
	public String generateAlphanumeric() {
		final Character[] letras = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		final Random rand = new Random();
		String alpha = "";
		for(int i = 0; i<6; i++) {
			alpha+=letras[rand.nextInt(letras.length-1)];
		}
		
		return alpha;
	}
	
	@SuppressWarnings("deprecation")
	public String tickerGenerator() {
		String str = "";
		Date date = new Date(System.currentTimeMillis());
		str += Integer.toString(date.getYear()).substring(Integer.toString(date.getYear()).length()-2);
		str += String.format("%02d", date.getMonth());
		str += String.format("%02d", date.getDay());
		String res = str + "-" + generateAlphanumeric() ;
		return res;
	}

}
