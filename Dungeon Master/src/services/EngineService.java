package services;

import java.util.List;

public interface EngineService {

	//Observators
	public EnvironnementService getEnv();
	public List<EntityService> getEntities();
	public EntityService getEntity(int i);
	
	// Constructor:
	public void init(EnvironnementService env);
	
	//Operators:
	/**
	 * pre 0 =< i <= getEntities().size()
	 * post getEntities().size() = getEntities().size()@pre - 1
	 * post forAll k in [0 .. i-1]  { getEntity(k) = getEntity(k)@pre }
	 * post forAll k in [i .. getEntities().size()-1]  { getEntity(k) = getEntity(k)@pre }
	 * @param i : index in list
	 */
	public void removeEntity(int i);
	
	/**
	 * post getEntities().size() = getEntities().size()@pre + 1
	 * post forAll k in [0 .. getEntities().size()-1]  { getEntity(k) = getEntity(k)@pre }
	 * post getEntity(getEntities().size()) = e
	 * @param e
	 */
	public void addEntity(EntityService e);
	/**
	 * forAll i in [0..getEntites().size() - 1] {getEntity(i).getHp()>0} 
	 */
	public void step();
	
	// Observations:
	/** invariants :
	 * inv  forAll i in [0..getEntites().size() - 1] {getEntity(i).getEnv() = getEnv()}
	 * inv   forAll i in [0..getEntites().size() - 1] {getEnv().getCellContent(getEntity(i).getCol(), getEntity(i).getRow()) = getEntity(i)}
	 */

}
