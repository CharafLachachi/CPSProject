package contracts;

import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.EnvironnementService;
import services.FoodService;

public class FoodContract extends RessourcesContract implements FoodService{

	public FoodContract(FoodService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public int getHealthRising() {
		// TODO Auto-generated method stub
		return ((FoodService) getDelegate()).getHealthRising();
	}

	@Override
	public void init(EnvironnementService env, int x, int y, int hp) {
		
		if(!(x>0 && y>0 && hp>0))
			throw new PreconditionError("FoodService", "init", "x <= 0 || y <= 0 || hp <= 0");
		((FoodService)getDelegate()).init(env, x, y, hp);
		checkInvariants();
		if(!(getCol() == x))
			throw new PostconditionError("FoodService", "init", "getCol() <> col");
		if(!(getRow() == y))
			throw new PostconditionError("FoodService", "init", "getRow() <> row");
		
		if(!(getEnv().equals(env)))
			throw new PostconditionError("FoodService", "init", "getEnv() <> env");
		
		if(!(getHealthRising() == hp))
			throw new PostconditionError("FoodService", "init", "healthRising() <> hp");
		
	}
	

}
