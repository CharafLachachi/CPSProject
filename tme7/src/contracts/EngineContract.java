package contracts;

import java.util.ArrayList;
import java.util.List;

import decorators.EngineDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;

public class EngineContract extends EngineDecorator implements EngineService {

	public EngineContract(EngineService delegate) {
		super(delegate);

	}

	public void checkInvariant() {
		for (int i = 0; i < getEntities().size(); i++)
			if (!(getEntity(i).getEnv().equals(getEnv())))
				throw new InvariantError("engine", "checkInvariant", "different Environement");

		for (int i = 0; i < getEntities().size(); i++)
		
			if (!(getEnv().getCellContent(getEntity(i).getRow(), getEntity(i).getCol()).get().equals(getEntity(i))))
			{
				throw new InvariantError("engine", "checkInvariant", "different entity");
				
			}
	}

	@Override
	public void init(EnvironnementService env) {
		// preCond
		if (!(!env.equals(null)))
			throw new PreconditionError("engine", "init", "Environement is null");
		// checkInvariant();
		// traitement
		super.init(env);
		checkInvariant();
		// postCond
		if (!getEnv().equals(env))
			throw new PostconditionError("engine", "init", "different Environement");

	}

	@Override
	public void removeEntity(int i) {

		// preCond
		if (!(i >= 0 && i < getEntities().size()))
			throw new PreconditionError("engine", "removeEntity", "wrong index");
		checkInvariant();
		// capture
		List<EntityService> entitesAtPre = new ArrayList<>(getEntities());
		super.removeEntity(i);
		checkInvariant();
		// postCond
		for (int k = 0; k < i; k++)
			if (!getEntity(k).equals(entitesAtPre.get(k)))
				throw new PostconditionError("engine", "removeEntity", "different entities before index");

		for (int k = i; k < getEntities().size() - 1; k++)
			if (!getEntity(k).equals(entitesAtPre.get(k + 1)))
				throw new PostconditionError("engine", "removeEntity", "different entities after index");

	}

	@Override
	public void addEntity(EntityService e) {

		// preCond
		if (!(!e.equals(null)))
			throw new PreconditionError("engine", "addEntity", "entity null");
		checkInvariant();
		// capture
		List<EntityService> entitesAtPre = new ArrayList<>(getEntities());
		super.addEntity(e);
		checkInvariant();

		// postCond
		for (int k = 0; k < entitesAtPre.size(); k++)
			if (!getEntity(k).equals(entitesAtPre.get(k)))
				throw new PostconditionError("engine", "addEntity", "different entities ");

		if (!getEntity(entitesAtPre.size()).equals(e))
			throw new PostconditionError("engine", "addEntity", "different entity ");

	}

}
