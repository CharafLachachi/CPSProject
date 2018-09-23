package components;

import java.util.concurrent.ThreadLocalRandom;

import services.Cell;
import services.EnvironnementService;
import services.RessourcesService;

public class Ressources implements RessourcesService{

	private int col;
	private int row;
	private EnvironnementService env;
	
	@Override
	public int getCol() {
		return col;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public EnvironnementService getEnv() {
		return env;
	}

	@Override
	public void init(EnvironnementService env) {
		this.env = env;
		
		Cell[][] cells = env.getCells();
		boolean trouve = true;
		TestRechabel tr = new TestRechabel(cells);
		while (trouve) {
			int i = ThreadLocalRandom.current().nextInt(1, env.getHeight() - 1);
			int j = ThreadLocalRandom.current().nextInt(1, env.getWidth() - 1);
			
			if (tr.isReachable(env.getWidth() - 1 , env.getHeight() - 1, i, j)) {
				setRow(i);
				setCol(j);
				trouve = false;
			}
		}
		env.addRessource(this);
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
}
