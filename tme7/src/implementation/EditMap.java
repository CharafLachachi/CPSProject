package implementation;

import services.Cell;
import services.EditMapService;

public class EditMap implements EditMapService {

	private int width;
	private int height;
	private Cell[][] cells;
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return cells[x][y];
	}

	@Override
	public void init(int w, int h) {
		this.width = w;
		this.height = h;
		this.cells = new Cell[w][h];
		
	}

	@Override
	public void openDoor(int x, int y) {
		if(cells[x][y].equals(Cell.DNC))
			cells[x][y] = Cell.DNO;
		
		if(cells[x][y].equals(Cell.DWC))
			cells[x][y] = Cell.DWO;
	}

	@Override
	public void closeDoor(int x, int y) {
		if(cells[x][y].equals(Cell.DNO))
			cells[x][y] = Cell.DNC;
		
		if(cells[x][y].equals(Cell.DWO))
			cells[x][y] = Cell.DWC;
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		TestRechabel tr = new TestRechabel(cells);
		return tr.isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setNature(int x, int y, Cell Na) {
		cells[x][y] = Na;
	}

}
