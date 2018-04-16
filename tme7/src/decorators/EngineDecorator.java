package decorators;

import java.util.List;

import services.EngineService;
import services.EntityService;
import services.EnvironnementService;

public class EngineDecorator implements EngineService {

	private EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
		
	public EngineService getDelegate() {
		return delegate;
	}

	
	@Override
	public EnvironnementService getEnv() {
		
		return delegate.getEnv();
	}

	@Override
	public List<EntityService> getEntities() {
		
		return delegate.getEntities();
	}

	@Override
	public EntityService getEntity(int i) {
		
		return delegate.getEntity(i);
	}

	@Override
	public void init(EnvironnementService env) {
		
		delegate.init(env);
	}

	@Override
	public void removeEntity(int i) {
		
		delegate.removeEntity(i);
	}

	@Override
	public void addEntity(EntityService e) {
		
		delegate.addEntity(e);
	}

	@Override
	public void step() {
		
		delegate.step();
	}

}
