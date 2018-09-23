package services;

public interface EntityService extends /*include*/ MobService {

	//observators:
	public int getHp();
	//constructors:
	/**
	 * 
	 * @param envi : Environement
	 * @param x : row
	 * @param y : col
	 * @param face : direction
	 * @param h
	 * pre h > 0
	 * post getHp() = h;
	 */
	public void init(EnvironnementService envi, int x, int y, Dir face, int h);
	
	//operators:
	public void step();
	public void setHp(int hp);
	
}
