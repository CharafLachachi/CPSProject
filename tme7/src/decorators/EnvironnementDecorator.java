package decorators;

import java.util.List;
import java.util.Optional;

import services.Cell;
import services.EnvironnementService;
import services.MapService;
import services.MobService;
import services.RessourcesService;

public class EnvironnementDecorator extends MapDecorateur implements EnvironnementService {

	public EnvironnementDecorator(MapService delegate) {
		super(delegate);
		
	}

	public EnvironnementService getDelegate() {
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
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return getDelegate().isReachable(x1, y1, x2, y2);
	}
	
	@Override
	public List<MobService> getMobs() {
		
		return getDelegate().getMobs();
	}

	@Override
	public boolean isReady() {
		
		return getDelegate().isReady();
	}

	@Override
	public void setNature(int x, int y, Cell Na) {
		// TODO Auto-generated method stub
		getDelegate().setNature(x, y, Na);
	}

	
}
