package test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.EditMapService;

public abstract class AbstractEditMapTest {
	private EditMapService map;

	protected AbstractEditMapTest() {
		map = null;
	}

	protected EditMapService getMap() {
		return map;
	}

	protected final void setMap(EditMapService map) {
		this.map = map;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		map = null;
	}

	public void checkInvariants() {
		// todo
	}

	/* init () */
	@Test
	public void initTestPos() {
		
		try {
			int w = 5;
			int h = 10;
			map.init(w, h);
			assertTrue(true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Test
	public void initTestNeg() {
		try {
			int w = 5;
			int h = -3;
			map.init(w, h);
		} catch (PreconditionError e) {
			assertTrue(e.getMessage()
					.equals("Precondition failed: message: 0 < w and 0 < h method init service MapService"));
		}
	}
	/* openDoor*/

	@Test
	public void openDoorPos() {
		int w = 5;
		int h = 10;
		map.init(w, h);
		map.setNature(4, 4, Cell.DNC);
		map.openDoor(4, 4);
	}

	@Test
	public void openDoorNeg() {
		try {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DNO);
			map.openDoor(4, 4);
		} catch (PreconditionError e) {
			assertTrue(e.getMessage().equals(
					"Precondition failed: message: getCellNature(x,y) in {DNC, DWC} method openDoor service MapService"));
		}
	}
	
	@Test
	public void openDoorPost1() {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DNC);
			map.openDoor(4, 4);
			assertTrue(map.getCellNature(4, 4).equals(Cell.DNO));
	}
	
	@Test
	public void openDoorPost2() {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DWC);
			map.openDoor(4, 4);
			assertTrue(map.getCellNature(4, 4).equals(Cell.DWO));
	}
	
	@Test
	public void openDoorPost3() {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DWC);
			Cell[][] cells_atPre = new Cell[w][h];
			for (int i = 0; i < cells_atPre.length; i++)
				for (int j = 0; j < cells_atPre.length; j++)
					cells_atPre[i][j] = map.getCellNature(i, j);
			map.openDoor(4, 4);
			for (int i = 0; i < cells_atPre.length; i++)
				for (int j = 0; j < cells_atPre.length; j++)
					if (4 != i || 4 != j) 
					assertTrue(cells_atPre[i][j].equals(map.getCellNature(i, j)));
	}
	

	@Test
	public void closeDoorPos() {
		int w = 5;
		int h = 10;
		map.init(w, h);
		map.setNature(4, 4, Cell.DNO);
		map.closeDoor(4, 4);
	}

	@Test
	public void closeDoorNeg() {
		try {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DNC);
			map.closeDoor(4, 4);
		} catch (PreconditionError e) {
			assertTrue(e.getMessage().equals(
					"Precondition failed: message: getCellNature(x,y) in {DNO, DWO} method closeDoor service MapService"));
		}

	}
	
	@Test
	public void closeDoorPost1() {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DNO);
			map.closeDoor(4, 4);
			assertTrue(map.getCellNature(4, 4).equals(Cell.DNC));
	}
	
	@Test
	public void closeDoorPost2() {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DWO);
			map.closeDoor(4, 4);
			assertTrue(map.getCellNature(4, 4).equals(Cell.DWC));
	}
	
	@Test
	public void closeDoorPost3() {
			int w = 5;
			int h = 10;
			map.init(w, h);
			map.setNature(4, 4, Cell.DWO);
			Cell[][] cells_atPre = new Cell[w][h];
			for (int i = 0; i < cells_atPre.length; i++)
				for (int j = 0; j < cells_atPre.length; j++)
					cells_atPre[i][j] = map.getCellNature(i, j);
			map.closeDoor(4, 4);
			for (int i = 0; i < cells_atPre.length; i++)
				for (int j = 0; j < cells_atPre.length; j++)
					if (4 != i || 4 != j) 
					assertTrue(cells_atPre[i][j].equals(map.getCellNature(i, j)));
	}
}
