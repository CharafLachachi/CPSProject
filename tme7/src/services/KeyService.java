package services;

public interface KeyService extends /*include*/ RessourcesService {

	
	
	//constructors:
	// pre : xdoor > 0 && ydoor > 0
	
	public void init(EnvironnementService env,int x,int y);
	
	// invariant:
	// inv : Environnement::getCellNature(getEnv(K), x, y) in {EMP,DNC,DWC} 
	
}
