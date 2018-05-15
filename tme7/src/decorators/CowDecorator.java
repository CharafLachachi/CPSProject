package decorators;

import services.CowService;
import services.Dir;
import services.EnvironnementService;

public class CowDecorator implements CowService{

	private CowService delegate;
	
	public CowDecorator(CowService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return delegate.getHp();
	}

	@Override
	public void setHp(int hp) {
		// TODO Auto-generated method stub
		delegate.setHp(hp);
	}

	@Override
	public EnvironnementService getEnv() {
		// TODO Auto-generated method stub
		return delegate.getEnv();
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return delegate.getCol();
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return delegate.getRow();
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return delegate.getFace();
	}

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d) {
		// TODO Auto-generated method stub
		delegate.init(env, x, y, d);
	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub
		delegate.forward();
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		delegate.backward();
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		delegate.attack();
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		delegate.turnL();
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		delegate.turnR();
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		delegate.strafeL();
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		delegate.strafeR();
	}

	@Override
	public void init(EnvironnementService envi, int x, int y, Dir face, int h) {
		delegate.init(envi, x, y, face, h);
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		delegate.step();
	}

	@Override
	public void chase() {
		 delegate.chase();
		
	}
	
	/** super */

}
