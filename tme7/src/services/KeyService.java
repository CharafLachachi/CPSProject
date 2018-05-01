package services;

public interface KeyService extends /*include*/ RessourcesService {

	//Observator
	public int getColDoor();
	public int getRowDoor();
	
	//constructors:
	// pre : xdoor > 0 && ydoor > 0
	// post : getColDoor(init(...)) = xdoor
	// post : getRowDoor(init(...)) = ydoor
	public void init(EnvironnementService env,int x,int y,int xdoor,int ydoor);
	
	// invariant:
	// inv : Environnement::getCellNature(getEnv(K), xdoor, ydoor) in {DNC,DWC} 
	
}
