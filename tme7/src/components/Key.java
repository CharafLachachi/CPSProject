package components;

import java.util.concurrent.ThreadLocalRandom;

import services.Cell;
import services.EnvironnementService;
import services.KeyService;

public class Key extends Ressources implements KeyService {

	

	@Override
	public void init(EnvironnementService env) {
		super.init(env);
		findCellForKey();
		
		System.out.println("key : row " + getRow() + "col " + getCol());
	//	env.addRessource(this);

	}

	public void findCellForKey() {
		Cell[][] cells = super.getEnv().getCells();
		boolean trouve = true;
		TestRechabel tr = new TestRechabel(cells);
		while (trouve) {
			int i = ThreadLocalRandom.current().nextInt(1, super.getEnv().getHeight() - 1);
			int j = ThreadLocalRandom.current().nextInt(1, super.getEnv().getWidth() - 1);
			
			if (tr.isReachableKey(0, 0, i, j)) {
				setRow(i);
				setCol(j);
				trouve = false;
			}
		}
	}

}
