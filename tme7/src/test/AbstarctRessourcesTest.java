package test;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import components.Cow;
import components.Engine;
import components.Environement;
import contracts.CowContract;
import contracts.EngineContract;
import contracts.EnvironnementContract;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.CowService;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;
import services.MobService;
import services.PlayerService;
import services.RessourcesService;

public abstract class AbstarctRessourcesTest {
	private RessourcesService ressource;
	
	protected AbstarctRessourcesTest() {
		ressource = null;
	}

	protected RessourcesService getRessources() {
		return ressource;
	}

	protected final void setRessources(RessourcesService ressource) {
		this.ressource = ressource;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		ressource = null;
	}
	
	public void checkInvariants() {
		// todo
	}
	

	@Test
	public void initTestNeg() {
				Dir dir = Dir.N;
				int x = 16;
				int y = 0;
				int h = 10;
				EnvironnementService env = new EnvironnementContract(new Environement());
				env.init(15, 15);
				ressource.init(env);
				EnvironnementService env2 = ressource.getEnv();
				assertTrue(env.equals(env2));
	}
	
}
