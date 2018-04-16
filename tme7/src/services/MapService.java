package services;

public interface MapService {
	/**
	 * Observateurs
	 * */
	
	public int getHeight();
	public int getWidth();
	public Cell[][] getCells();
	// pre 0 <= x < getWidth() and 0 <= y < getHeight(M)
	public Cell getCellNature(int x, int y);
	
	/**
	 * Constructeurs
	 * */
	// pre 0 < w and 0 < h
	// post getHeight() = h
	// post getWidth() = w
	public void  init(int w, int h);
	
	/**
	 * Operateurs
	 * */
	// pre getCellNature(x,y) in {DNC, DWC}
	// post getCellNature(x,y)@pre = DWC \implies getCellNature(x,y) = DWO
	// post getCellNature(x,y)@pre = DNC \implies getCellNature(x,y) = DNO
	// post \Forall 0<= u <= (getWidth() -1) \Forall 0<= v <= (getHeight() -1)
	// {u != x || v != y} \implies getCellNature(u,v) = getCellNature(u,v)@pre
	public void openDoor(int x, int y);
	
	// pre CellNature(x,y) in {DNO, DWO}
	// post getCellNature(x,y)@pre = DWO \implies getCellNature(x,y) = DWC
	// post getCellNature(x,y)@pre = DNO \implies getCellNature(x,y) = DNC
	// post \Forall 0<= u <= (getWidth() -1) \Forall 0<= v <= (getHeight() -1)
	// {u != x || v != y} \implies getCellNature(u,v) = getCellNature(u,v)@pre
	public void closeDoor(int x, int y);
	
	

	
}
