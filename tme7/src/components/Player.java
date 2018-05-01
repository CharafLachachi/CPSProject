package components;

import java.util.Optional;

import services.Cell;
import services.Command;
import services.MobService;
import services.PlayerService;

public class Player extends Entity implements PlayerService {

	private Command command;

	@Override
	public Optional<Command> lastCom() {
		return Optional.ofNullable(command);
	}

	@Override
	public Optional<MobService> content(int x, int y) {

		Optional<MobService> opt = Optional.empty();

		switch (getFace()) {
		case N:
			opt = getEnv().getCellContent(getRow() + x, getCol() + y);
			break;
		case S:
			opt = getEnv().getCellContent(getRow() - y, getCol()  - x);
			break;
		case E:
			opt = getEnv().getCellContent(getRow() - x, getCol()  + y );
			break;
		case W:
			opt = getEnv().getCellContent(getRow() + x, getCol() - y);
			break;

		}
		return opt;
	}

	@Override
	public Cell nature(int x, int y) {
		Cell cell = null;
		switch (getFace()) {
		case N:
			cell = getEnv().getCellNature(getRow() + y, getCol() + x);
			break;
		case S:
			cell = getEnv().getCellNature( getRow() - y, getCol() - x);
			break;
		case E:
			cell = getEnv().getCellNature(getRow() - x, getCol() + y);
			break;
		case W:
			cell = getEnv().getCellNature(getRow() + x, getCol() - y);
			break;

		}
		return cell;
	}
	
	

	@Override
	public boolean viewable(int x, int y) {
		if (x == -1 && y == 2 && !getEnv().getCellNature(getCol() - 1, getRow() + 1).equals(Cell.WLL)
				&& !getEnv().getCellNature(getCol() - 1, getRow() + 1).equals(Cell.DWC)
				&& !getEnv().getCellNature(getCol() - 1, getRow() + 1).equals(Cell.DNC)) {
			return true;
		} else if (x == 0 && y == 2 && !getEnv().getCellNature(getCol(), getRow() + 1).equals(Cell.WLL)
				&& !getEnv().getCellNature(getCol(), getRow() + 1).equals(Cell.DWC)
				&& !getEnv().getCellNature(getCol(), getRow() + 1).equals(Cell.DNC)) {
			return true;
		} else if (x == 1 && y == 2 && !getEnv().getCellNature(getCol() + 1, getRow() + 1).equals(Cell.WLL)
				&& !getEnv().getCellNature(getCol() + 1, getRow() + 1).equals(Cell.DWC)
				&& !getEnv().getCellNature(getCol() + 1, getRow() + 1).equals(Cell.DNC)) {
			return true;
		} else if (x == -1 && y == 3 && !getEnv().getCellNature(getCol() - 1, getRow() + 2).equals(Cell.WLL)
				&& !getEnv().getCellNature(getCol() - 1, getRow() + 2).equals(Cell.DWC)
				&& !getEnv().getCellNature(getCol() - 1, getRow() + 2).equals(Cell.DNC) && viewable(-1, 2))
			return true;
		else if (x == 0 && y == 3 && !getEnv().getCellNature(getCol(), getRow() + 2).equals(Cell.WLL)
				&& !getEnv().getCellNature(getCol(), getRow() + 2).equals(Cell.DWC)
				&& !getEnv().getCellNature(getCol(), getRow() + 2).equals(Cell.DNC) && viewable(0, 2))
			return true;
		else if (x == 1 && y == 3 && !getEnv().getCellNature(getCol() + 1, getRow() + 2).equals(Cell.WLL)
				&& !getEnv().getCellNature(getCol() + 1, getRow() + 2).equals(Cell.DWC)
				&& !getEnv().getCellNature(getCol() + 1, getRow() + 2).equals(Cell.DNC) && viewable(1, 2))
			return true;

		return false;
	}

	@Override
	public void step() {
		switch (lastCom().get()) {
		case FF:
			forward();
			break;
		case BB:
			backward();
			break;
		case LL:
			strafeL();
			break;
		case RR:
			strafeR();
			break;
		case TL:
			turnL();
			break;
		case TR:
			turnR();
			break;
		case C:
		//	battle();
			break;

		default:
			break;
		}
	}

}
