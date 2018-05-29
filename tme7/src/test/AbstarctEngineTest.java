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

public abstract class AbstarctEngineTest {
	private EngineService engine;
	
	protected AbstarctEngineTest() {
		engine = null;
	}

	protected EngineService getEngine() {
		return engine;
	}

	protected final void setEngine(EngineService engine) {
		this.engine = engine;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		engine = null;
	}
	
	public void checkInvariants() {
		// todo
	}
	
	/*init*/
	
	@Test
	public void initTestPos() {
			EnvironnementService env = new EnvironnementContract(new Environement());
			env.init(15, 15);
			engine.init(env);
			assertTrue(true);
	}

	@Test
	public void initTestNeg() {
			try {
				engine.init(null);
			} catch (PreconditionError e) {
				assertTrue(e.getMessage().equals("Precondition failed: message: Environement is null method init service engine"));
			}
	}
	@Test
	public void removeEntityPos(){
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);
		PlayerService player = new PlayerContract(new Player());
		player.init(env, 5, 5, Dir.N,5);
		engine.init(env);
		engine.addEntity(player);
		engine.removeEntity(0);
		
	}
	
	@Test
	public void removeEntityNeg(){
		try {
			EnvironnementService env = new EnvironnementContract(new Environement());
			env.init(15, 15);
			PlayerService player = new PlayerContract(new Player());
			player.init(env, 5, 5, Dir.N,5);
			engine.init(env);
			engine.addEntity(player);
			engine.removeEntity(2);
		} catch (PreconditionError e) {
			assertTrue(e.getMessage().equals("Precondition failed: message: wrong index method removeEntity service engine"));
		}
	}	
		@Test
		public void addEntityNeg(){
			try {
				EnvironnementService env = new EnvironnementContract(new Environement());
				engine.init(env);
				engine.addEntity(null);
				engine.removeEntity(2);
			} catch (PreconditionError e) {
			
				assertTrue(e.getMessage().equals("Precondition failed: message: entity null method addEntity service engine"));
			}
		}
//	@Test
//	public void forwardTestPost2Pos(){
//		Dir dir = Dir.E;
//		int x = 0;
//		int y = 0;
//		int h = 4;
//		EnvironnementService env = new EnvironnementContract(new Environement());
//		env.init(15, 15);
//		cow.init(env, x, y, dir,h);
//		env.setNature(0, 1, Cell.EMP);
//		env.setNature(1, 0, Cell.EMP);
//		int row_atPre = cow.getRow();
//		int col_atPre = cow.getCol();
//		cow.forward();
//		assertTrue(cow.getRow() == row_atPre && cow.getCol() == col_atPre + 1);	
//	}
	
//	@Test
//	public void attackTestPost1Pos(){
//		EngineService labyrinthe = new EngineContract(new Engine());
//		Dir dir = Dir.E;
//		int x = 5;
//		int y = 5;
//		int h = 4;
//		PlayerService player = new PlayerContract(new Player());
//		EnvironnementService env = new EnvironnementContract(new Environement());
//		env.init(15, 15);
//		labyrinthe.init(env);
//		cow.init(env, x, y, dir,h);
//		labyrinthe.addEntity(cow);
//		int row = cow.getRow();
//		int col = cow.getCol()  + 1;
//		player.init(env, row, col, Dir.W, 10);
//		labyrinthe.addEntity(player);
//		System.err.println(cow.getRow() +" " + cow.getCol());
//		Optional<MobService> coww = env.getCellContent(row, col);
//		System.out.println(coww);
//		int hp = ((EntityService) coww.get()).getHp();
//		cow.attack();
//		assertTrue(((EntityService) coww.get()).getHp() != hp - 1);	
//	}
}
