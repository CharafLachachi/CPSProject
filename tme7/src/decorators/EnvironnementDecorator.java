package decorators;

import java.util.List;
import java.util.Optional;

import services.EnvironnementService;
import services.MapService;
import services.MobService;
import services.RessourcesService;

public class EnvironnementDecorator extends /* include */ MapDecorateur implements EnvironnementService {

	public EnvironnementDecorator(MapService delegate) {
		super(delegate);
	}

	@Override
	protected EnvironnementService getDelegate() {
		return (EnvironnementService) super.getDelegate();
	}

	@Override
	public Optional<MobService> getCellContent(int x, int y) {
		return getDelegate().getCellContent(x, y);
	}

	@Override
	public MobService getMob(int x, int y) {
		return getDelegate().getMob(x, y);
	}

	@Override
	public void addMob(MobService mob) {
		getDelegate().addMob(mob);
	}

	@Override
	public Optional<RessourcesService> getCellRessources(int x, int y) {
		return getDelegate().getCellRessources(x, y);
	}

	@Override
	public RessourcesService getRessource(int x, int y) {
		
		return getDelegate().getRessource(x, y);
	}

	@Override
	public void addRessource(RessourcesService ressource) {
		getDelegate().addRessource(ressource);
	}

	@Override
	public void removeMob(MobService mob) {
		getDelegate().removeMob(mob);
		
	}

	@Override
	public void removeRessource(RessourcesService ressource) {
		getDelegate().removeRessource(ressource);
		
	}

	@Override
	public List<MobService> getMobs() {
		
		return getDelegate().getMobs();
	}

}
