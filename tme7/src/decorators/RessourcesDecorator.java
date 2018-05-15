package decorators;

import services.EnvironnementService;
import services.RessourcesService;

public class RessourcesDecorator implements RessourcesService{

	private RessourcesService delegate;
	
	public RessourcesDecorator(RessourcesService delegate) {
		this.delegate = delegate;
	}
	
	public RessourcesService getDelegate()
	{
		return delegate;
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
	public EnvironnementService getEnv() {
		// TODO Auto-generated method stub
		return delegate.getEnv();
	}

	@Override
	public void init(EnvironnementService env) {
		// TODO Auto-generated method stub
		delegate.init(env);
	}

}
