package test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		int w = 5;
		int h = 10;
		map.init(w, h);
		assertTrue(true);
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
}
