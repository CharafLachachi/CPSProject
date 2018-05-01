package services;

public interface RessourcesService {

	// Observators
	public int getCol();
	public int getRow();
	public EnvironnementService getEnv();
	// constructors:
	// pre : 0<x<EnvironnementService::Width(K,getEnv(K))
	// pre :  0<y<EnvironnementService::Height(K,getEnv(K))
	// post : getCol(init(e,x,y)) = x
	// post : getRow(init(e,x,y)) = y
	// post : getEnv(init(e,x,y)) = e
	public void init(EnvironnementService env, int x, int y);
	
	// Invariants:
	// inv : Environnement::CellNature(getEnv(K), getCol(K), getRow(K)) in {EMP}
	// inv : Environemment::CellNature(getEnv(K),u,v) in {IN} implies Environnement::isReachable(getEnv(K),u,v, getCol(K), getRow(K))
	
}
