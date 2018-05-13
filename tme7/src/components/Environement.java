package components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import services.EnvironnementService;
import services.MobService;

public class Environement extends EditMap implements EnvironnementService {

	public List<MobService> mobs = new ArrayList<>();
	
	@Override
	public Optional<MobService> getCellContent(int x, int y) {
		
		
		Optional<MobService> mob = Optional.ofNullable(getMob(x, y));
		if(mob.isPresent())
			return mob;
		
		return Optional.empty();
	}

	@Override
	public void closeDoor(int x, int y) {
		if(!getCellContent(x, y).isPresent())
			closeDoor(x, y);
		
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

}
