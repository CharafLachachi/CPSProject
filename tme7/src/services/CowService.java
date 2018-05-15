package services;

public interface CowService extends /*include*/ EntityService{

	//constructors:
	/**
	 * pre 4 >= h >= 3
	 */
	public void init(EnvironnementService envi, int x, int y, Dir face, int h);
	
	/**
	 * post getCol()@pre -1 <= getCol() <= getCol()@pre + 1
	 * post getRow()@pre -1 <= getRow() <= getRow()@pre + 1
	 *
	 */
	public void step();
	
	public void chase();
}
