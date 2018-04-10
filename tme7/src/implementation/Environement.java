package implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import services.EnvironnementService;
import services.MobService;

public class Environement extends EditMap implements EnvironnementService {

	private Map<String,MobService> mobs = new HashMap<>();
	
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
		return mobs.get(x+","+y);
	}

	@Override
	public void addMob(MobService mob) {
		mobs.put(mob.getRow()+","+mob.getCol(), mob);
		
	}

}
