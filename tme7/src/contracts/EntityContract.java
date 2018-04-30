package contracts;



import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Dir;
import services.EntityService;
import services.EnvironnementService;


public class EntityContract extends MobContract implements EntityService{

	public EntityContract(EntityService delegate) {
		super(delegate);
		
	}

	

	@Override
	public void init(EnvironnementService envi, int x, int y, Dir face, int h) {
		if(!(h>0))
			throw new PreconditionError("entity Service", "init", "h <= 0");
		
		((EntityService) getDelegate()).init(envi, x, y, face, h);
		checkInvariants();
		if(!(getHp() == h))
			throw new PostconditionError("entity Service", "init", "getHp() <> h"); 
		
	}

	@Override
	public void step() {
	}



	@Override
	public int getHp() {
		return ((EntityService) getDelegate()).getHp();
	}



}
