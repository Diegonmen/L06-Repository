package TestGenerator;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.HandyWorker;
import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.HandyWorkerService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml", "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActorServiceTest extends AbstractTest {

	@Autowired
	private ActorService actorService;
	@Autowired
	private HandyWorkerService handyWorkerService;
	@Autowired
	private UserAccountService userAccountService;

	@Test
	public void saveActorTest() {
		Actor actor, saved;
		Collection<Actor> actors;
		actor = actorService.findAll().iterator().next();
		actor.setVersion(57);
		saved = actorService.save(actor);
		actors = actorService.findAll();
		Assert.isTrue(actors.contains(saved));
	}

	@Test
	public void findAllActorTest() {
		Collection<Actor> result;
		result = actorService.findAll();
		Assert.notNull(result);
	}

	@Test
	public void findOneActorTest() {
		Actor actor = actorService.findAll().iterator().next();
		int actorId = actor.getId();
		Assert.isTrue(actorId != 0);
		Actor result;
		result = actorService.findOne(actorId);
		Assert.notNull(result);
	}

	@Test
	public void deleteActorTest() {
		Actor actor = actorService.findAll().iterator().next();
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorService.exists(actor.getId()));
		this.actorService.delete(actor);
	}
	
	@Test
	public void isSuspiciousTest() {
		UserAccount userAccount = userAccountService.findUserAccountByUsername("useracount15");
		Assert.notNull(userAccount);
		HandyWorker handyWorker = handyWorkerService.findHandyWorkerByUserAccount(userAccount);
		Assert.notNull(handyWorker);
		boolean res = actorService.isSuspicious(handyWorker);
		Assert.isTrue(res==true);
		Assert.isTrue(handyWorker.isSuspicious()==true);
	}

}
