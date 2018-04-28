package components;

import java.util.ArrayList;
import java.util.List;

import services.EngineService;
import services.EntityService;
import services.EnvironnementService;
import services.PlayerService;

public class Engine implements EngineService{

	
	private EnvironnementService env;
	private List<EntityService> entities;
	
	@Override
	public EnvironnementService getEnv() {
		
		return env;
	}

	@Override
	public List<EntityService> getEntities() {
		
		return entities;
	}

	@Override
	public EntityService getEntity(int i) {
		
		return entities.get(i);
	}

	@Override
	public void init(EnvironnementService env) {
		this.env = env;
		this.entities = new ArrayList<>();
		
	}

	@Override
	public void removeEntity(int i) {
		
		entities.remove(i);
	}

	@Override
	public void addEntity(EntityService e) {
		
		entities.add(entities.size(), e);
		getEnv().addMob(e);
	}

	@Override
	public void step() {
		for (EntityService e : entities)
			if(!(e instanceof PlayerService))
				e.step();
		
	}

}
