package components;

import java.util.concurrent.ThreadLocalRandom;

import services.CowService;
import services.Dir;
import services.EnvironnementService;

public class Cow extends Entity implements CowService {

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d, int h) {
		if (h >= 3 && h <= 4) {
			setEnv(env);
			setCol(y);
			setRow(x);
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

	}

}
