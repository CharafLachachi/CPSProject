package components;

import java.util.HashMap;
import java.util.Map;

import services.Cell;

public class TestRechabel {

	private boolean reachable = false;
	private Map<String, Boolean> mem = new HashMap<>();
	private Cell[][] cells;

	public TestRechabel(Cell[][] c) {
		this.cells = c;
	}

	public boolean accessible(int x, int y) {

		if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length)
			return false;

		Cell c = cells[x][y];

		if (c.equals(Cell.WLL))
			return false;
		return true;
	}

	public boolean isReachable(int x1, int y1, int x2, int y2) {

		if (cells[x1][y1].equals(Cell.WLL) || cells[x2][y2].equals(Cell.WLL))
			return false;
		if (x1 == x2 && y1 == y2)
			return true;

		if (accessible(x2 - 1, y2 - 1) == true || accessible(x2 - 1, y2) == true || accessible(x2 - 1, y2 + 1) == true
				|| accessible(x2, y2 - 1) == true || accessible(x2, y2 + 1) == true
				|| accessible(x2 + 1, y2 - 1) == true || accessible(x2 + 1, y2) == true
				|| accessible(x2 + 1, y2 + 1) == true) {

			if (mem.containsKey(x2 + "," + y2))
				return mem.get(x2 + "," + y2);
			
			mem.put(x2 + "," + y2, reachable);
			
			if (accessible(x2 - 1, y2 - 1)) {
				reachable = reachable || isReachable(x1, y1, x2 - 1, y2 - 1);
			}  if (accessible(x2 - 1, y2)) {
				reachable = reachable || isReachable(x1, y1, x2 - 1, y2);
			}  if (accessible(x2 - 1, y2 + 1)) {
				reachable = reachable || isReachable(x1, y1, x2 - 1, y2 + 1);
			}  if (accessible(x2, y2 - 1)) {
				reachable = reachable || isReachable(x1, y1, x2, y2 - 1);
			} 

			if (accessible(x2, y2 + 1)) {
				reachable = reachable || isReachable(x1, y1, x2, y2 + 1);
			} 
			if (accessible(x2 + 1, y2 - 1)) {
				reachable = reachable || isReachable(x1, y1, x2 + 1, y2 - 1);
			} 
			if (accessible(x2 + 1, y2)) {
				reachable = reachable || isReachable(x1, y1, x2 + 1, y2);
			} 
			if (accessible(x2 + 1, y2 + 1)) {
				reachable = reachable || isReachable(x1, y1, x2 + 1, y2 + 1);
			} 
				
			
		} else
			reachable  = false;
		
		return reachable;

	}

	/**
	public static void main(String[] args) {
		Cell[][] cells = { { Cell.IN, Cell.EMP, Cell.WLL, Cell.EMP },
				{ Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP },
				{ Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP },
				{ Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL },
				{ Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP }, };
		TestRechabel tr = new TestRechabel(cells);
		System.out.println(tr.isReachable(3, 1, 4, 3));
	}
	 */
}
