package test;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import components.Engine;
import components.Environement;
import components.Player;
import contracts.EngineContract;
import contracts.EnvironnementContract;
import contracts.PlayerContract;
import exceptions.PreconditionError;
import services.Cell;
import services.CowService;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;
import services.MobService;
import services.PlayerService;

public abstract class AbstarctCowTest {
	private CowService cow;
	
	protected AbstarctCowTest() {
		cow = null;
	}

	protected CowService getCow() {
		return cow;
	}

	protected final void setCow(CowService cow) {
		this.cow = cow;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		cow = null;
	}
	
	public void checkInvariants() {
		// todo
	}
	
	/*init*/
	
	@Test
	public void initTestPos() {
			Dir dir = Dir.N;
			int x = 5;
			int y = 5;
			EnvironnementService env = new EnvironnementContract(new Environement());
			env.init(15, 15);
			cow.init(env, x, y, dir,4);
			assertTrue(true);
	}
//	
	@Test
	public void initTestNeg() {
			try {
				Dir dir = Dir.N;
				int x = 1;
				int y = 2;
				int h = 4;
				EnvironnementService env = new EnvironnementContract(new Environement());
				env.init(15, 15);
				cow.init(env, x, y, dir,1);
			} catch (PreconditionError e) {
				assertTrue(e.getMessage().equals("Precondition failed: message: ! 3 <= h <= 4 method init service CowService"));
			}
	}
	@Test
	public void forwardTestPost1Pos(){
		Dir dir = Dir.S;
		int x = 0;
		int y = 0;
		int h = 4;
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);
		cow.init(env, x, y, dir,h);
		env.setNature(0, 1, Cell.EMP);
		env.setNature(1, 0, Cell.EMP);
		int row_atPre = cow.getRow();
		int col_atPre = cow.getCol();
		cow.forward();
		assertTrue(cow.getRow() == row_atPre + 1 && cow.getCol() == col_atPre);	
	}
	@Test
	public void forwardTestPost2Pos(){
		Dir dir = Dir.E;
		int x = 0;
		int y = 0;
		int h = 4;
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);
		cow.init(env, x, y, dir,h);
		env.setNature(0, 1, Cell.EMP);
		env.setNature(1, 0, Cell.EMP);
		int row_atPre = cow.getRow();
		int col_atPre = cow.getCol();
		cow.forward();
		assertTrue(cow.getRow() == row_atPre && cow.getCol() == col_atPre + 1);	
	}
	

}
