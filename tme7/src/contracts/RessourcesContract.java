package contracts;



import decorators.RessourcesDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import services.Cell;
import services.EnvironnementService;
import services.RessourcesService;

public class RessourcesContract extends RessourcesDecorator implements RessourcesService{

	public RessourcesContract(RessourcesService delegate) {
		super(delegate);
	}
	
	
	
	public void checkInvariants()
	{	
		if(!(getEnv().getCellNature(getRow(), getCol()).equals(Cell.EMP) ||  
				getEnv().getCellNature(getRow(), getCol()).equals(Cell.DNO) ||
				getEnv().getCellNature(getRow(), getCol()).equals(Cell.DWO)))
			throw new InvariantError("RessourcesService", "Invariant", "cell <> empty");
		
		
		if(! getEnv().isReachable(0,0,getRow(),getCol()))
			throw new InvariantError("RessourcesService", "Invariant", "key is not reachable");
				
		
		
	}

	public void init(EnvironnementService env)
	{
		
		super.init(env);
		checkInvariants();
		
		if(!(getEnv().equals(env)))
			throw new PostconditionError("RessourcesService", "init", "getEnv() <> env");
			
	}
	
}
