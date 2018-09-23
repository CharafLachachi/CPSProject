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
import exceptions.PreconditionError;
import services.Cell;
import services.CowService;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;
import services.MobService;
import services.PlayerService;

public abstract class AbstarctPlayerTest {
	private PlayerService player;
	
	protected AbstarctPlayerTest() {
		player = null;
	}

	protected PlayerService getPalyer() {
		return player;
	}

	protected final void setPlayer(PlayerService player) {
		this.player = player;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		player = null;
	}
	
	public void checkInvariants() {
		// todo
	}
	
	/*init*/
	
	@Test
	public void initTestPos() {
			Dir dir = Dir.N;
			int x = 0;
			int y = 0;
			int h = 10;
			EnvironnementService env = new EnvironnementContract(new Environement());
			env.init(15, 15);
			player.init(env, x, y, dir,h);
			assertTrue(true);
	}
	
	@Test
	public void initTestNeg() {
			try {
				Dir dir = Dir.N;
				int x = 16;
				int y = 0;
				int h = 10;
				EnvironnementService env = new EnvironnementContract(new Environement());
				env.init(15, 15);
				player.init(env, x, y, dir,h);
			} catch (PreconditionError e) {
				assertTrue(e.getMessage().equals("Precondition failed: message: 0<= x < env.getWidth() && 0<= x < env.getHeight() method init service PlayerService"));
			}
	}
	@Test
	public void forwardTestPost1Pos(){
		Dir dir = Dir.S;
		int x = 0;
		int y = 0;
		int h = 10;
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);
		player.init(env, x, y, dir,h);
		env.setNature(0, 1, Cell.EMP);
		env.setNature(1, 0, Cell.EMP);
		int row_atPre = player.getRow();
		int col_atPre = player.getCol();
		player.forward();
		assertTrue(player.getRow() == row_atPre + 1 && player.getCol() == col_atPre);	
	}
	@Test
	public void forwardTestPost2Pos(){
		Dir dir = Dir.E;
		int x = 0;
		int y = 0;
		int h = 10;
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);
		player.init(env, x, y, dir,h);
		env.setNature(0, 1, Cell.EMP);
		env.setNature(1, 0, Cell.EMP);
		int row_atPre = player.getRow();
		int col_atPre = player.getCol();
		player.forward();
		assertTrue(player.getRow() == row_atPre && player.getCol() == col_atPre + 1);	
	}
	
	@Test
	public void attackTestPost1Pos(){
		EngineService labyrinthe = new EngineContract(new Engine());
		Dir dir = Dir.E;
		int x = 0;
		int y = 0;
		int h = 10;
		CowService cow = new CowContract(new Cow());
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);
		labyrinthe.init(env);
		player.init(env, x, y, dir,h);
		labyrinthe.addEntity(player);
		int row = player.getRow();
		int col = player.getCol()  + 1;
		cow.init(env, row, col, Dir.W, 4);
		labyrinthe.addEntity(cow);
		Optional<MobService> coww = env.getCellContent(row, col);
		int hp = ((EntityService) coww.get()).getHp();
		player.attack();
		assertTrue(((EntityService) coww.get()).getHp() != hp - 1);	
	}
}
