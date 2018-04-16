package decorators;

import services.Dir;
import services.EntityService;
import services.EnvironnementService;
import services.MobService;

public class EntityDecorator extends MobDecorateur implements EntityService{

	public EntityDecorator(MobService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	public EntityService getDelegate()
	{
		return (EntityService) super.getDelegate();
				
	}
	
	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return getDelegate().getHp();
	}

	@Override
	public void init(EnvironnementService envi, int x, int y, Dir face, int h) {
		getDelegate().init(envi, x, y, face,h);
		
	}

	@Override
	public void step() {
		getDelegate().step();
		
	}

	

}
