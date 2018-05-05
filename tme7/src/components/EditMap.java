package components;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import services.Cell;
import services.EditMapService;

public class EditMap implements EditMapService {

	private int width;
	private int height;
	private Cell[][] cells;
	private static Random h = new Random();

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
		this.cells = new Cell[h][w];
		initCells();
		while (!isReady()) {
			initCells();
		}
        initDoors();
		printCells();
	}

	@Override
	public void openDoor(int x, int y) {
		if (cells[x][y].equals(Cell.DNC))
			cells[x][y] = Cell.DNO;

		if (cells[x][y].equals(Cell.DWC))
			cells[x][y] = Cell.DWO;
	}

	@Override
	public void closeDoor(int x, int y) {
		if (cells[x][y].equals(Cell.DNO))
			cells[x][y] = Cell.DNC;

		if (cells[x][y].equals(Cell.DWO))
			cells[x][y] = Cell.DWC;
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		TestRechabel tr = new TestRechabel(cells);
		return tr.isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		TestRechabel tr = new TestRechabel(cells);
		int countIn = 0, xIn = 0, yIn = 0;
		int countOut = 0, xOut = 0, yOut = 0;
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				if (cells[i][j] == Cell.IN) {
					countIn++;
					xIn = i;
					yIn = j;
				}
				if (cells[i][j] == Cell.OUT) {
					countOut++;
					xOut = i;
					yOut = j;
				}
			}
		}
		// Test si plus d'une entre ou plus d'une sortie
		if (countIn > 1 || countOut > 1)
			return false;
		if (tr.isReachable(xIn, yIn, xOut, yOut) == false)
			return false;
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (cells[i][j] == Cell.DNC || cells[i][j] == Cell.DNO) {
					if (!(getCellNature(i + 1, j) == Cell.EMP && getCellNature(i - 1, j) == Cell.EMP))
						return false;
					if (!(getCellNature(i, j - 1) == Cell.WLL && getCellNature(i, j + 1) == Cell.WLL))
						return false;
				}
				if (cells[i][j] == Cell.DWC || cells[i][j] == Cell.DWO) {
					if (!(getCellNature(i + 1, j) == Cell.WLL && getCellNature(i - 1, j) == Cell.WLL))
						return false;
					if (!(getCellNature(i, j - 1) == Cell.EMP && getCellNature(i, j + 1) == Cell.EMP))
						return false;
				}
			}
		}

		return true;
	}

	@Override
	public void setNature(int x, int y, Cell Na) {
		cells[x][y] = Na;
	}

	@Override
	public Cell[][] getCells() {
		return cells;
	}

	public void initCells() {
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
				switch (randomNum) {
				case 1:
					cells[i][j] = Cell.WLL;
					break;
				case 2:
					cells[i][j] = Cell.EMP;
					break;
				}
			}
		}
		cells[0][0] = Cell.IN;
		cells[this.getWidth() - 1][this.getHeight() - 1] = Cell.OUT;
	}

	public void initDoors() {

		for (int x = 0; x < 5000; x++) {
			int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
			int i = ThreadLocalRandom.current().nextInt(2, getHeight() - 1);
			int j = ThreadLocalRandom.current().nextInt(2, getWidth() - 1);
			switch (randomNum) {
			case 1:
				if (j > 1 && j < getWidth() - 1 && cells[i][j - 1].equals(Cell.WLL)
						&& cells[i][j + 1].equals(Cell.WLL) && cells[i][j] == Cell.EMP) {
					
					if(isReachable(0,0 , i, j))
						// TODO mettre en DWO
					cells[i][j] = Cell.DWO;
				}
				break;

			case 2:
				if (i > 1 && i < getHeight() - 1 && cells[i - 1][j].equals(Cell.WLL)
						&& cells[i + 1][j].equals(Cell.WLL) && cells[i][j] == Cell.EMP) {
					
					if(isReachable(0,0 , i, j)) {
					cells[i][j] = Cell.DNO;
					}
				}
			case 3 :
				if (i > 1 && i < getHeight() - 1 && cells[i - 1][j].equals(Cell.WLL)
						&& cells[i + 1][j].equals(Cell.WLL) && cells[i][j] == Cell.EMP) {
					
					if(isReachable(0,0 , i, j))
					cells[i][j] = Cell.DNC;
				}
			case 4: 
				if (j > 1 && j < getWidth() - 1 && cells[i][j - 1].equals(Cell.WLL)
						&& cells[i][j + 1].equals(Cell.WLL) && cells[i][j] == Cell.EMP) {
					
					if(isReachable(0,0 , i, j))
					cells[i][j] = Cell.DWC;
				}
			default:
				break;
			}
		}
	}

	public void initCellsEmp() {
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {

				cells[i][j] = Cell.EMP;

			}
		}
	}

	public void printCells() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < this.getHeight(); i++) {
			string.append("\n");
			for (int j = 0; j < this.getWidth(); j++) {
				string.append("row " + i + " col " + j + " " + cells[i][j] + " | ");

			}
		}
		System.out.println(string.toString());
	}
}
