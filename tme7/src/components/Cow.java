package components;

import java.util.concurrent.ThreadLocalRandom;



import services.CowService;
import services.Dir;
import services.EnvironnementService;
import services.PlayerService;

public class Cow extends Entity implements CowService {

	private final int distanceChase = 4;
	
	
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
		chase();
		attack();
	}

	public Integer[] findPlayer()
	{
		Integer[] pos = new Integer[2];
		
		for(int r = 0; r < getEnv().getWidth(); r++)
			for(int c = 0; c < getEnv().getHeight(); c++)
			{
				if(getEnv().getCellContent(r, c).isPresent() && getEnv().getCellContent(r, c).get() instanceof PlayerService)
				{
					pos[0] = r;
					pos[1] = c;
					return pos;
				}
			}
		
		return pos;
	}
	
	@Override
	public void chase() {
		int col = getCol();
		int row = getRow();
		Integer[] pos = findPlayer();
		int r = pos[0];
		int c = pos[1];
		int distance = (int) Math.sqrt( row * row - r * r + col * col - c * c);
			
		if(distance <= distanceChase)
			{
				
				System.out.println("enemy dans : row " + r + "," + " col " + c);
				switch(getFace())
				{
				case E:
					if(col - c < 0)
						forward();
					else
					{
						turnL();
						turnL();
						forward();
					}
					break;
				case N:
					if(row - r > 0)
						forward();
					else
					{
						turnL();
						turnL();
						forward();
					}	
					break;
				case S:
					if(row - r < 0)
						forward();
					else
					{
						turnL();
						turnL();
						forward();
					}
					break;
				case W:
					if(col - c > 0)
						forward();
					else
					{
						turnL();
						turnL();
						forward();
					}
					break;
				default:
					break;
				
				}
			}		
	}

}
