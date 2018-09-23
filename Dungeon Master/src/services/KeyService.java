package services;

public interface KeyService extends /*include*/ RessourcesService {

	
	
	//constructors:
	
	public void init(EnvironnementService env);
	
	// Observations:
	// [init] : Environnement::getCellNature(getEnv(K), Col(K), Row(C) in {EMP,DNC,DWC} 
	
}
