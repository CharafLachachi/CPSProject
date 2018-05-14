package contracts;

import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.EnvironnementService;
import services.KeyService;
import services.RessourcesService;

public class KeyContract extends RessourcesContract implements KeyService {

	public KeyContract(RessourcesService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkInvariants() {
		super.checkInvariants();
		if (!(getEnv().getCellNature(getCol(), getRow()).equals(Cell.DWC)
				|| getEnv().getCellNature(getCol(), getRow()).equals(Cell.DNC)))
			throw new InvariantError("keyService", "checkInv", "key for something else then DOOR CLOSED");

	}

	
	@Override
	public void init(EnvironnementService env, int x, int y) {

		if (!(x >= 0 && y >= 0 ))
			throw new PreconditionError("KeyService", "init", "x <= 0 || y <= 0 || xdoor <= 0 || ydoor <=0");
		((KeyService) getDelegate()).init(env, x, y);
		checkInvariants();
		if (!(getCol() == x))
			throw new PostconditionError("KeyService", "init", "getCol() <> col");
		if (!(getRow() == y))
			throw new PostconditionError("KeyService", "init", "getRow() <> row");

		
		if (!(getEnv().equals(env)))
			throw new PostconditionError("KeyService", "init", "getEnv() <> env");
	}

}
