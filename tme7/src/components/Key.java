package components;

import java.util.concurrent.ThreadLocalRandom;

import services.Cell;
import services.EnvironnementService;
import services.KeyService;

public class Key extends Ressources implements KeyService {

	private int colDoor;
	private int rowDoor;

	@Override
	public int getColDoor() {
		return colDoor;
	}

	@Override
	public int getRowDoor() {
		// TODO Auto-generated method stub
		return rowDoor;
	}

	@Override
	public void init(EnvironnementService env, int x, int y, int xdoor, int ydoor) {
		super.init(env, xdoor, ydoor);
		colDoor = xdoor;
		rowDoor = ydoor;
		findCellForKey();

	}

	public void findCellForKey() {
		Cell[][] cells = super.getEnv().getCells();
		outerloop: for (int i = 0; i < super.getEnv().getWidth(); i++) {
			for (int j = 0; j < super.getEnv().getHeight(); j++) {
				if (cells[i][j].equals(Cell.DNC) || cells[i][j].equals(Cell.DWC)) {
					rowDoor = i;
					colDoor = i;
					break outerloop;
				}
			}
		}
		boolean trouve = true;
		while (trouve) {
			int i = ThreadLocalRandom.current().nextInt(1, super.getEnv().getHeight() - 1);
			int j = ThreadLocalRandom.current().nextInt(1, super.getEnv().getWidth() - 1);
			TestRechabel tr = new TestRechabel(cells);
			if (tr.isReachable(0, 0, i, j)) {
				super.setRow(i);
				super.setCol(j);
				trouve = false;
			}
		}
	}

}
