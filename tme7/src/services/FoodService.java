package services;

public interface FoodService extends /*include*/ RessourcesService{

	// observator:
	public int getHealthRising();
	
	// constructors
	// pre : hp > 0
	// post : getHealthRising(init(env,x,y,hp)) = hp
	public void init(EnvironnementService env, int x, int y, int hp);
}
