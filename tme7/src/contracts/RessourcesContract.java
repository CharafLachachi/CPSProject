package contracts;



import decorators.RessourcesDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.EditMapService;
import services.EnvironnementService;
import services.RessourcesService;

public class RessourcesContract extends RessourcesDecorator implements RessourcesService{

	public RessourcesContract(RessourcesService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void checkInvariants()
	{	
		if(!getEnv().getCellNature(getCol(), getRow()).equals(Cell.EMP))
			throw new InvariantError("RessourcesService", "Invariant", "cell <> empty");
		
		
		if(!((EditMapService) getEnv()).isReachable(0,0,getCol(),getRow()))
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
