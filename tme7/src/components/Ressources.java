package components;

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
	public void init(EnvironnementService env, int x, int y) {
		this.env = env;
		this.col = x;
		this.row = y;	
	}
}
