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
		if (!(getEnv().getCellNature(getColDoor(), getRowDoor()).equals(Cell.DWC)
				|| getEnv().getCellNature(getColDoor(), getRowDoor()).equals(Cell.DNC)))
			throw new InvariantError("keyService", "checkInv", "key for something else then DOOR CLOSED");

	}

	@Override
	public int getColDoor() {
		// TODO Auto-generated method stub
		return ((KeyService) getDelegate()).getColDoor();
	}

	@Override
	public int getRowDoor() {
		// TODO Auto-generated method stub
		return ((KeyService) getDelegate()).getRowDoor();
	}

	@Override
	public void init(EnvironnementService env, int x, int y, int xdoor, int ydoor) {

		if (!(x >= 0 && y >= 0 && xdoor >= 0 && ydoor >=0))
			throw new PreconditionError("KeyService", "init", "x <= 0 || y <= 0 || xdoor <= 0 || ydoor <=0");
		((KeyService) getDelegate()).init(env, x, y, xdoor, ydoor);
		checkInvariants();
		if (!(getCol() == x))
			throw new PostconditionError("KeyService", "init", "getCol() <> col");
		if (!(getRow() == y))
			throw new PostconditionError("KeyService", "init", "getRow() <> row");

		if (!(getColDoor() == xdoor))
			throw new PostconditionError("KeyService", "init", "getColDoor() <> colDoor");
		if (!(getRowDoor() == ydoor))
			throw new PostconditionError("KeyService", "init", "getRowDoor() <> rowDoor");
		if (!(getEnv().equals(env)))
			throw new PostconditionError("KeyService", "init", "getEnv() <> env");
	}

}
