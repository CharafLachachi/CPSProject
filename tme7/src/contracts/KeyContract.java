package contracts;

import decorators.RessourcesDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.EditMapService;
import services.EnvironnementService;
import services.KeyService;
import services.RessourcesService;

public class KeyContract extends RessourcesDecorator implements KeyService {

	public KeyContract(RessourcesService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	
	public void checkInvariants() {
		
		if(!getEnv().getCellNature(getCol(), getRow()).equals(Cell.EMP))
			throw new InvariantError("RessourcesService", "Invariant", "cell <> empty");
		
		
		if(!((EditMapService) getEnv()).isReachable(0,0,getRow(),getCol()))
			throw new InvariantError("RessourcesService", "Invariant", "key is not reachable");
			
		if (!(getEnv().getCellNature(getCol(), getRow()).equals(Cell.DWC)
				|| getEnv().getCellNature(getCol(), getRow()).equals(Cell.DNC)))
			throw new InvariantError("keyService", "checkInv", "key for something else then DOOR CLOSED");

	}

	
	@Override
	public void init(EnvironnementService env) {

		super.init(env);
		checkInvariants();
		
		if (!(getEnv().equals(env)))
			throw new PostconditionError("KeyService", "init", "getEnv() <> env");
	}

}
