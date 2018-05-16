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
		
		if(!(getEnv().getCellNature(getRow(), getCol()).equals(Cell.EMP) ||  
				getEnv().getCellNature(getRow(), getCol()).equals(Cell.DNO) ||
				getEnv().getCellNature(getRow(), getCol()).equals(Cell.DWO)))
			throw new InvariantError("keyService", "Invariant", "cell <> empty");
		
		
		if(!((getEnv().isReachable(0,0,getRow(),getCol())) == true))
			throw new InvariantError("KeyService", "Invariant", "key is not reachable");
			
		/**
		if (!(getEnv().getCellNature(getRow(), get()).equals(Cell.DWC)
				|| getEnv().getCellNature(getCol(), getRow()).equals(Cell.DNC)))
			throw new InvariantError("keyService", "checkInv", "key for something else then DOOR CLOSED");
*/
	}

	
	@Override
	public void init(EnvironnementService env) {

		super.init(env);
		checkInvariants();
		
		if (!(getEnv().equals(env)))
			throw new PostconditionError("KeyService", "init", "getEnv() <> env");
	}

}
