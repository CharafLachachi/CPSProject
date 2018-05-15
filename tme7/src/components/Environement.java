package components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import services.EnvironnementService;
import services.MobService;
import services.RessourcesService;




public class Environement extends EditMap implements EnvironnementService {

	public List<MobService> mobs = new ArrayList<>();
	public List<RessourcesService> ressources = new ArrayList<>();
	
	@Override
	public Optional<MobService> getCellContent(int x, int y) {
		
		
		Optional<MobService> mob = Optional.ofNullable(getMob(x, y));
		if(mob.isPresent())
			return mob;
		
		return Optional.empty();
	}


	@Override
	public MobService getMob(int x, int y) {
		for (MobService mob : mobs)
		{
			if (mob.getRow() == x && mob.getCol() == y) {
				return mob;
			}
		}
		return null;
	}

	@Override
	public void addMob(MobService mob) {
		mobs.add(mob);
		
	}

	@Override
	public Optional<RessourcesService> getCellRessources(int x, int y) {
		Optional<RessourcesService> ressource = Optional.ofNullable(getRessource(x, y));
		if(ressource.isPresent())
			return ressource;
		
		return Optional.empty();
	}

	@Override
	public RessourcesService getRessource(int x, int y) {
		for (RessourcesService r : ressources)
		{
			if (r.getRow() == x && r.getCol() == y) {
				return r;
			}
		}
		return null;
	}

	@Override
	public void addRessource(RessourcesService ressource) {
		ressources.add(ressource);
		
	}

	@Override
	public void removeMob(MobService mob) {
		mobs.remove(mob);
		
	}

	@Override
	public void removeRessource(RessourcesService ressource) {
		ressources.remove(ressource);
		
	}


	@Override
	public List<MobService> getMobs() {
		return mobs;
	}

}
