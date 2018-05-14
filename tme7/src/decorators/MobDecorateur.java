package decorators;

import services.Dir;
import services.EnvironnementService;
import services.MobService;

public class MobDecorateur implements MobService {

	private MobService delegate;

	

	public MobDecorateur(MobService delegate) {
		this.delegate = delegate;
	}
	

	
	public MobService getDelegate() {
		return delegate;
	}
	
	@Override
	public EnvironnementService getEnv() {
		return delegate.getEnv();
	}

	@Override
	public int getCol() {
		return delegate.getCol();
	}

	@Override
	public int getRow() {
		return delegate.getRow();
	}

	@Override
	public Dir getFace() {
		return delegate.getFace();
	}

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d) {
		 delegate.init(env, x, y, d);
	}

	@Override
	public void forward() {
		delegate.forward();
	}

	@Override
	public void backward() {
		delegate.backward();
	}

	@Override
	public void turnL() {
		delegate.turnL();
	}

	@Override
	public void turnR() {
		delegate.turnR();
	}

	@Override
	public void strafeL() {
		delegate.strafeL();
	}

	@Override
	public void strafeR() {
		delegate.strafeR();
	}

	@Override
	public void attack() {
		delegate.attack();
		
	}

}
