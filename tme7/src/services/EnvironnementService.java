package services;

import java.util.Optional;

public interface EnvironnementService extends /*include*/ MapService{
	
	//Obeservators
	 
	public Optional<MobService> getCellContent(int x, int y);
	public MobService getMob(int x, int y);
	
	
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
	
}
