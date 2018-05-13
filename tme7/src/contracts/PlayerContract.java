package contracts;

import java.util.Optional;

import exceptions.InvariantError;
import services.Cell;
import services.Command;
import services.MobService;
import services.PlayerService;

public class PlayerContract extends EntityContract implements PlayerService {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}

	public void checkInv() {
		// TODO : a verifier
		for (int u = -1; u < 1; u++)
			for (int v = -1; v < 3; u++) {
				switch (getFace()) {

				case N:
 
					if (!(content(u, v) == getEnv().getCellContent(getCol() + u, getRow() + v)))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v) == getEnv().getCellNature(getCol() + u, getRow() + v)))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				case S:

					if (!(content(u, v) == getEnv().getCellContent(getCol() - u, getRow() - v)))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v) == getEnv().getCellNature( getCol() - u, getRow() - v)))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				case E:

					if (!(content(u, v) == getEnv().getCellContent( getCol() + v, getRow() - u)))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v) == getEnv().getCellNature(getCol() + v, getRow() - u)))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				case W:

					if (!(content(u, v) == getEnv().getCellContent(getCol() - v, getRow() + u)))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v) == getEnv().getCellNature( getCol() - v, getRow() + u)))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				}
			}
		for (int u = -1; u <= 1; u++)
			for (int v = -1; v <= 1; v++) {
				if (!(viewable(u, v) == false))
					throw new InvariantError("PlayerService", "checkInv", "viewable");
			}

		if (viewable(-1, 2))
			if (!(nature(-1, 1).equals(Cell.WLL) || nature(-1, 1).equals(Cell.DWC) || nature(-1, 1).equals(Cell.DNC)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(-1,2)");

		if (viewable(0, 2))
			if (!(!nature(0, 1).equals(Cell.WLL) || !nature(0, 1).equals(Cell.DWC) || !nature(0, 1).equals(Cell.DNC)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(0,2)");

		if (viewable(1, 2))
			if (!(!nature(1, 1).equals(Cell.WLL) || !nature(1, 1).equals(Cell.DWC) || !nature(1, 1).equals(Cell.DNC)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(1,2)");

		if (viewable(-1, 3))
			if (!(!nature(-1, 2).equals(Cell.WLL) || !nature(-1, 2).equals(Cell.DWC)
					|| !nature(-1, 2).equals(Cell.DNC) && viewable(-1, 2)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(-1,3)");

		if (viewable(0, 3))
			if (!(!nature(0, 2).equals(Cell.WLL) || !nature(0, 2).equals(Cell.DWC)
					|| !nature(0, 2).equals(Cell.DNC) && viewable(0, 2)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(0,3)");

		if (viewable(1, 3))
			if (!(!nature(1, 2).equals(Cell.WLL) || !nature(1, 2).equals(Cell.DWC)
					|| !nature(1, 2).equals(Cell.DNC) && viewable(1, 2)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(1,3)");

	}

	@Override
	public void step() {
		switch (lastCom().get()) {
		case FF:
			this.forward();
			break;
		case BB:
			this.backward();
			break;
		case LL:
			this.strafeL();
			break;
		case RR:
			this.strafeR();
			break;
		case TL:
			this.turnL();
			break;
		case TR:
			this.turnR();
			break;
		}

	}

	@Override
	public Optional<Command> lastCom() {
		// TODO Auto-generated method stub
		return ((PlayerService) getDelegate()).lastCom();
	}

	@Override
	public Optional<MobService> content(int x, int y) {
		// TODO Auto-generated method stub
		return ((PlayerService) getDelegate()).content(x, y);
	}

	@Override
	public Cell nature(int x, int y) {
		// TODO Auto-generated method stub
		return ((PlayerService) getDelegate()).nature(x, y);
	}

	@Override
	public boolean viewable(int x, int y) {
		// TODO Auto-generated method stub
		return ((PlayerService) getDelegate()).viewable(x, y);
	}

	@Override
	public void setCommand(Command c) {
		
		((PlayerService) getDelegate()).setCommand(c);
	}

}
