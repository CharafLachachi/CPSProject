package components;


import java.util.concurrent.ThreadLocalRandom;

import services.Cell;
import services.CowService;
import services.Dir;
import services.EnvironnementService;
import services.MobService;
import services.PlayerService;

public class Cow extends Entity implements CowService {

	private final int distanceChase = 4;

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d, int h) {
		if (h >= 3 && h <= 4) {
			setEnv(env);
			while (env.getCellNature(x, y).equals(Cell.WLL) || env.getCellNature(x, y).equals(Cell.DWC)
					|| env.getCellNature(x, y).equals(Cell.DNC)
				  ) {
				y = ThreadLocalRandom.current().nextInt(1, env.getHeight() - 1);
				x = ThreadLocalRandom.current().nextInt(1, env.getWidth() - 1);

			}
			
			setRow(x);
			setCol(y);
			setFace(d);
			setHp(h);

		}
	}

	@Override
	public void step() {

			int rand = ThreadLocalRandom.current().nextInt(1, 4);

			switch (rand) {
			case 1:
				forward();

				break;
			case 2:
				backward();

				break;
			case 3:
				turnL();
				break;
			case 4:
				turnR();
				break;

			}
		if(playerFound() == true)
			chase();

	}

	public boolean playerFound() {
		Integer[] pos = coordPlayer();
		int row = getRow();
		int col = getCol();
		int r = pos[0];
		int c = pos[1];
		int distance = (int) Math.sqrt(row * row - r * r + col * col - c * c);

		if (distance <= distanceChase)
			return true;
		return false;
	}

	public Integer[] coordPlayer() {
		Integer[] pos = new Integer[2];

		for (MobService pl : getEnv().getMobs())
			if (pl instanceof PlayerService) {
				pos[0] = pl.getRow();
				pos[1] = pl.getCol();
				return pos;
			}
		return pos;
	}

	@Override
	public void chase() {
		int col = getCol();
		int row = getRow();
		Integer[] pos = coordPlayer();
		int r = pos[0];
		int c = pos[1];

		int distance = (int) Math.sqrt(row * row - r * r + col * col - c * c);

			switch (getFace()) {
			case E:
				if (col - c < 0)
					forward();
				else {
					turnL();
					turnL();
					forward();
				}
				break;
			case N:
				if (row - r > 0)
					forward();
				else {
					turnL();
					turnL();
					forward();
				}
				break;
			case S:
				if (row - r < 0)
					forward();
				else {
					turnL();
					turnL();
					forward();
				}
				break;
			case W:
				if (col - c > 0)
					forward();
				else {
					turnL();
					turnL();
					forward();
				}
				break;
			default:
				break;

			}
			if(distance <= 1)
				attack();
	}

	

}
