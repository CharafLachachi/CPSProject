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
		
		for(int x = 0; x < getEnv().getWidth(); x++)
			for(int y = 0; y < getEnv().getHeight(); y++)
				if(getEnv().getCellNature(x, y).equals(Cell.IN))
				{
					if(!((EditMapService) getEnv()).isReachable(x,y,getCol(),getRow()))
							throw new InvariantError("RessourcesService", "Invariant", "key is not reachable");
				}
		
		
	}

	public void init(EnvironnementService env, int x, int y)
	{
		if(!(x>0 && y>0))
			throw new PreconditionError("RessourcesService", "init", "x <= 0 || y <= 0");
		super.init(env, x, y);
		checkInvariants();
		if(!(getCol() == x))
			throw new PostconditionError("RessourcesService", "init", "getCol() <> col");
		if(!(getRow() == y))
			throw new PostconditionError("RessourcesService", "init", "getRow() <> row");
		if(!(getEnv().equals(env)))
			throw new PostconditionError("RessourcesService", "init", "getEnv() <> env");
			
	}
	
}
