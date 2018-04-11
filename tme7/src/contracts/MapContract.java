package contracts;

import decorators.MapDecorateur;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.MapService;

public class MapContract extends MapDecorateur {

	public MapContract(MapService delegate) {
		super(delegate);
	}

	public void checkInvariants() {
		// pas d'invariants
	}

	@Override
	public void init(int w, int h) {
		// pre 0 < w and 0 < h
		if (!(w > 0 && h > 0)) {
			throw new PreconditionError("MapService", "init", "0 < w and 0 < h");
		}
		checkInvariants();
		super.init(w, h);
		checkInvariants();
		// post getHeight() = h
		if (!(getHeight() == h)) {
			throw new PostconditionError("MapService", "init", "getHeight() == h");
		}
		if (!(getWidth() == w)) {
			throw new PostconditionError("MapService", "init", "getWidth() == w");
		}
	}

	@Override
	public void openDoor(int x, int y) {
		// pre getCellNature(x,y) in {DNC, DWC}
		if (!(getCellNature(x, y) == Cell.DNC || getCellNature(x, y) == Cell.DWC)) {
			throw new PreconditionError("MapService", "openDoor", "getCellNature(x,y) in {DNC, DWC}");
		}
		// capture
		Cell cellNature_atPre = getCellNature(x, y);
		Cell[][] cells_atPre = new Cell[getWidth()][getHeight()];

		for (int i = 0; i < cells_atPre.length; i++)
			for (int j = 0; j < cells_atPre.length; j++)
				cells_atPre[i][j] = getCellNature(i, j);

		// Traitement et invariants

		checkInvariants();
		super.openDoor(x, y);
		checkInvariants();

		// post getCellNature(x,y)@pre = DWC \implies getCellNature(x,y) = DWO
		if (cellNature_atPre == Cell.DWC) {
			if (!(getCellNature(x, y) == Cell.DWO)) {
				throw new PostconditionError("MapService", "openDoor",
						"getCellNature(x,y)@pre = DWC \\implies getCellNature(x,y) = DWO");
			}
		}

		// post getCellNature(x,y)@pre = DNC \implies getCellNature(x,y) = DNO
		if (cellNature_atPre == Cell.DNC) {
			if (!(getCellNature(x, y) == Cell.DNO)) {
				throw new PostconditionError("MapService", "openDoor",
						"getCellNature(x,y)@pre = DNC \\implies getCellNature(x,y) = DNO");
			}
		}

		// post \Forall 0<= u <= (getWidth() -1) \Forall 0<= v <= (getHeight() -1)
		// {u != x || v != y} \implies getCellNature(u,v) = getCellNature(u,v)@pre
		for (int i = 0; i < cells_atPre.length; i++) {
			for (int j = 0; j < cells_atPre.length; j++) {
				if (x != i || y != j) {
					if (!(cells_atPre[i][j] == getCellNature(i, j))) {
						throw new PostconditionError("MapService", "openDoor", "La case a la positio" + i + " " + j
								+ " a change de nature malgre elle n'est pas concerne");
					}
				}
			}
		}

	}

	@Override
	public void closeDoor(int x, int y) {
		// pre getCellNature(x,y) in {DNO, DWO}
		if (!(getCellNature(x, y) == Cell.DNO || getCellNature(x, y) == Cell.DWO)) {
			throw new PreconditionError("MapService", "closeDoor", "getCellNature(x,y) in {DNO, DWO}");
		}
		// capture
		Cell cellNature_atPre = getCellNature(x, y);
		Cell[][] cells_atPre = new Cell[getWidth()][getHeight()];

		for (int i = 0; i < cells_atPre.length; i++)
			for (int j = 0; j < cells_atPre.length; j++)
				cells_atPre[i][j] = getCellNature(i, j);

		// Traitement et invariants

		checkInvariants();
		super.closeDoor(x, y);
		checkInvariants();

		// post getCellNature(x,y)@pre = DWO \implies getCellNature(x,y) = DWC
		if (cellNature_atPre == Cell.DWO) {
			if (!(getCellNature(x, y) == Cell.DWC)) {
				throw new PostconditionError("MapService", "closeDoor",
						"getCellNature(x,y)@pre = DWO \\implies getCellNature(x,y) = DWC");
			}
		}

		// post getCellNature(x,y)@pre = DNO \implies getCellNature(x,y) = DNC
		if (cellNature_atPre == Cell.DNO) {
			if (!(getCellNature(x, y) == Cell.DNC)) {
				throw new PostconditionError("MapService", "closeDoor",
						"getCellNature(x,y)@pre = DNO \\implies getCellNature(x,y) = DNC");
			}
		}

		// post \Forall 0<= u <= (getWidth() -1) \Forall 0<= v <= (getHeight() -1)
		// {u != x || v != y} \implies getCellNature(u,v) = getCellNature(u,v)@pre
		for (int i = 0; i < cells_atPre.length; i++) {
			for (int j = 0; j < cells_atPre.length; j++) {
				if (x != i || y != j) {
					if (!(cells_atPre[i][j] == getCellNature(i, j))) {
						throw new PostconditionError("MapService", "closeDoor", "La case a la positio" + i + " " + j
								+ " a change de nature malgre elle n'est pas concerne");
					}
				}
			}
		}

	}

}
