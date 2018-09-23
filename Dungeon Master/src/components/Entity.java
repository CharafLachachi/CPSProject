package components;


import services.Dir;
import services.EntityService;
import services.EnvironnementService;

public class Entity extends Mob implements EntityService{

	private int hp;
	

	@Override
	public int getHp() {
		return hp;
	}
	
	
	@Override
	public void init(EnvironnementService env, int x, int y, Dir d, int h) {
		super.init(env, x, y, d);
		this.hp = h;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	public void setHp(int hp) {
		this.hp = hp;
	}



}
