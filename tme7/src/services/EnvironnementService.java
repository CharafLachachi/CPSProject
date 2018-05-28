package services;

import java.util.List;
import java.util.Optional;

public interface EnvironnementService extends /*include*/ EditMapService{
	
	//Obeservators
	 
	public Optional<MobService> getCellContent(int x, int y);
	public Optional<RessourcesService> getCellRessources(int x, int y);
	public MobService getMob(int x, int y);
	public RessourcesService getRessource(int x, int y);
	public List<MobService> getMobs();
	
	
	
	//Operators
	/**
	 * pre getCellContent(M,x,y) = No
	 * @param x
	 * @param y
	 */
	public void closeDoor(int x, int y);
	
	/**
	 
	 * pre getCellNature(mob.getRow(),mob.getCol()) NOT IN {DNC,DWC,WLL}
	 * @param x
	 * @param y
	 */
	
	public void addMob(MobService mob);
	public void addRessource(RessourcesService ressource);
	
	public void removeMob(MobService mob);
	public void removeRessource(RessourcesService ressource);
	
}
