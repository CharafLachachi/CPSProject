package contracts;

import java.util.Optional;

import decorators.MobDecorateur;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.Dir;
import services.EnvironnementService;
import services.MobService;

public class MobContract extends MobDecorateur implements MobService {

	public MobContract(MobService delegate) {
		super(delegate);
	}

	public void checkInvariants() {
		// inv getCol() < getEnv().getWidth()
		if (!(getCol() < getEnv().getWidth())) {
			throw new InvariantError("MobService", "Invariant", "getCol() < getEnv().getWidth()");
		}
		// getRow() < getEnv().getHeight()
		if (!(getRow() < getEnv().getHeight())) {
			throw new InvariantError("MobService", "Invariant", "getRow() < getEnv().getHeight()");
		}

		// getEnv().getCellNature(getCol(),getRow()) != {WLL,DNC,DWC}
		Cell inv = getEnv().getCellNature(getCol(), getRow());
		if ((inv == Cell.WLL && inv != Cell.DNC && inv != Cell.DWC)) {
			throw new InvariantError("MobService", "Invariant",
					"getEnv().getCellNature(getCol(),getRow()) != {WLL,DNC,DWC}");
		}
	}

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d) {
		// pre 0<= x < env.getWidth() && 0<= x < env.getHeight()
		if (!((x >= 0 && x < env.getWidth()) && (y >= 0 && y > env.getHeight()))) {
			throw new PreconditionError("EditMapService", "init", "0<= x < env.getWidth() && 0<= x < env.getHeight()");
		}
		checkInvariants();
		super.init(env, x, y, d);
		checkInvariants();

		// post getCol() == x
		if (!(getCol() == x)) {
			throw new PostconditionError("EditMapService", "init", " getCol() == x");
		}
		// post getRow() == y
		if (!(getRow() == y)) {
			throw new PostconditionError("EditMapService", "init", " getRow() == y");
		}
		// post getFace() == d
		if (!(getFace() == d)) {
			throw new PostconditionError("EditMapService", "init", " getFace() == d");
		}
		// post getCol() == x
		if (!(getEnv() == env)) {
			throw new PostconditionError("EditMapService", "init", " getEnv() == env");
		}

	}

	@Override
	public void forward() {

		// capture
		int row_atPre = getRow();
		int col_atPre = getCol();
		Dir face_atPre = getFace();

		checkInvariants();
		super.forward();
		checkInvariants();

		switch (face_atPre) {
		case S:
			if ((getEnv().getCellNature(col_atPre, row_atPre + 1) == Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre + 1) == Cell.DWO)
					&& row_atPre + 1 < getEnv().getHeight()
					&& (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre + 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face sud et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre, row_atPre + 1) != Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre + 1) != Cell.DWO)
					|| row_atPre + 1 < getEnv().getHeight()
					|| (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face sud et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;

		case N:

			if ((getEnv().getCellNature(col_atPre, row_atPre - 1) == Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre - 1) == Cell.DWO) && row_atPre - 1 >= 0
					&& (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre - 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face Nord et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre, row_atPre - 1) != Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre - 1) != Cell.DWO) || row_atPre - 1 < 0
					|| (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre - 1)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face NORD et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;

		case E:

			if ((getEnv().getCellNature(col_atPre + 1, row_atPre) == Cell.DNO) && col_atPre + 1 < getEnv().getWidth()
					&& (Optional.ofNullable(getEnv().getMob(col_atPre + 1, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre + 1)) {
					throw new PostconditionError("MobService", "forward",
							"Face est et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre + 1, row_atPre) != Cell.EMP
					|| getEnv().getCellNature(col_atPre + 1, row_atPre) != Cell.DNO)
					|| col_atPre + 1 < getEnv().getWidth()
					|| (Optional.ofNullable(getEnv().getMob(col_atPre + 1, row_atPre)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face EST et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;

		case W:

			if ((getEnv().getCellNature(col_atPre - 1, row_atPre) == Cell.EMP
					|| getEnv().getCellNature(col_atPre - 1, row_atPre) == Cell.DNO) && col_atPre - 1 >= 0
					&& (Optional.ofNullable(getEnv().getMob(col_atPre - 1, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre - 1)) {
					throw new PostconditionError("MobService", "forward",
							"Face West et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre - 1, row_atPre) != Cell.EMP
					|| getEnv().getCellNature(col_atPre - 1, row_atPre) != Cell.DNO) || col_atPre - 1 < 0
					|| (Optional.ofNullable(getEnv().getMob(col_atPre - 1, row_atPre)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face WEST et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;
		}

	}

	@Override
	public void backward() {

		// capture
		int row_atPre = getRow();
		int col_atPre = getCol();
		Dir face_atPre = getFace();

		checkInvariants();
		super.backward();
		checkInvariants();

		switch (face_atPre) {
		case N:
			if ((getEnv().getCellNature(col_atPre, row_atPre + 1) == Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre + 1) == Cell.DWO)
					&& row_atPre + 1 < getEnv().getHeight()
					&& (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre + 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "BACKward",
							"Face NORD et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre, row_atPre + 1) != Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre + 1) != Cell.DWO)
					|| row_atPre + 1 < getEnv().getHeight()
					|| (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "BACKward",
							"Face NORD et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;

		case S:

			if ((getEnv().getCellNature(col_atPre, row_atPre - 1) == Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre - 1) == Cell.DWO) && row_atPre - 1 >= 0
					&& (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre - 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "backward",
							"Face SUD et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre, row_atPre - 1) != Cell.EMP
					|| getEnv().getCellNature(col_atPre, row_atPre - 1) != Cell.DWO) || row_atPre - 1 < 0
					|| (Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre - 1)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "backward",
							"Face SUD et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;

		case W:

			if ((getEnv().getCellNature(col_atPre + 1, row_atPre) == Cell.DNO) && col_atPre + 1 < getEnv().getWidth()
					&& (Optional.ofNullable(getEnv().getMob(col_atPre + 1, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre + 1)) {
					throw new PostconditionError("MobService", "backward",
							"Face West et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre + 1, row_atPre) != Cell.EMP
					|| getEnv().getCellNature(col_atPre + 1, row_atPre) != Cell.DNO)
					|| col_atPre + 1 < getEnv().getWidth()
					|| (Optional.ofNullable(getEnv().getMob(col_atPre + 1, row_atPre)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "backward",
							"Face WEST et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;

		case E:

			if ((getEnv().getCellNature(col_atPre - 1, row_atPre) == Cell.EMP
					|| getEnv().getCellNature(col_atPre - 1, row_atPre) == Cell.DNO) && col_atPre - 1 >= 0
					&& (Optional.ofNullable(getEnv().getMob(col_atPre - 1, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre - 1)) {
					throw new PostconditionError("MobService", "backward",
							"Face EST et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}

			if ((getEnv().getCellNature(col_atPre - 1, row_atPre) != Cell.EMP
					|| getEnv().getCellNature(col_atPre - 1, row_atPre) != Cell.DNO) || col_atPre - 1 < 0
					|| (Optional.ofNullable(getEnv().getMob(col_atPre - 1, row_atPre)).isPresent() == true)) {
				if (!(getRow() == row_atPre && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "backward",
							"Face EST et le joueur a avance malgre l'insatifaction des conditions");
				}
			}

			break;
		}

	}

	@Override
	public void turnL() {

		checkInvariants();
		// capture
		Dir face_atPre = getFace();
		super.turnL();
		switch (face_atPre) {
		case N:
			if (!(getFace().equals(Dir.E)))
				throw new PostconditionError("mobService", "turnL", "turnL N");
			break;

		case E:
			if (!(getFace().equals(Dir.S)))
				throw new PostconditionError("mobService", "turnL", "turnL E");
			break;

		case W:
			if (!(getFace().equals(Dir.N)))
				throw new PostconditionError("mobService", "turnL", "turnL W");
			break;

		case S:
			if (!(getFace().equals(Dir.W)))
				throw new PostconditionError("mobService", "turnL", "turnL S");
			break;
		}
	}

	@Override
	public void turnR() {
		checkInvariants();
		// capture
		Dir face_atPre = getFace();
		super.turnR();
		switch (face_atPre) {
		case S:
			if (!(getFace().equals(Dir.E)))
				throw new PostconditionError("mobService", "turnR", "turnL S");
			break;

		case W:
			if (!(getFace().equals(Dir.S)))
				throw new PostconditionError("mobService", "turnR", "turnL W");
			break;

		case E:
			if (!(getFace().equals(Dir.N)))
				throw new PostconditionError("mobService", "turnR", "turnL E");
			break;

		case N:
			if (!(getFace().equals(Dir.W)))
				throw new PostconditionError("mobService", "turnR", "turnL N");
			break;
		}
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		checkInvariants();
		// capture
		int col_atPre = getCol();
		int row_atPre = getRow();
		Dir face = getFace();
		super.strafeL();
		if (col_atPre + 1 < getEnv().getWidth() && (face.equals(Dir.N) || face.equals(Dir.S))
				&& (getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.EMP)
						|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.DNO))
				&& getEnv().getCellContent(row_atPre, col_atPre + 1).equals(Optional.empty())) {
			if (!(getCol() == col_atPre + 1))
				throw new PostconditionError("MobService", "strafR", "");
		}

		if (row_atPre + 1 < getEnv().getHeight() && (face.equals(Dir.E) || face.equals(Dir.W))
				&& (getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.EMP)
						|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DNO))
				&& getEnv().getCellContent(row_atPre + 1, col_atPre).equals(Optional.empty())) {
			if (!(getRow() == row_atPre + 1))
				throw new PostconditionError("MobService", "strafR", "");
		}

	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
				checkInvariants();
				// capture
				int col_atPre = getCol();
				int row_atPre = getRow();
				Dir face = getFace();
				super.strafeR();
				if (col_atPre - 1 < getEnv().getWidth() && (face.equals(Dir.N) || face.equals(Dir.S))
						&& (getEnv().getCellNature(row_atPre, col_atPre - 1).equals(Cell.EMP)
								|| getEnv().getCellNature(row_atPre, col_atPre - 1).equals(Cell.DNO))
						&& getEnv().getCellContent(row_atPre, col_atPre - 1).equals(Optional.empty())) {
					if (!(getCol() == col_atPre - 1))
						throw new PostconditionError("MobService", "strafL", "");
				}

				if (row_atPre - 1 < getEnv().getHeight() && (face.equals(Dir.E) || face.equals(Dir.W))
						&& (getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.EMP)
								|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DNO))
						&& getEnv().getCellContent(row_atPre - 1, col_atPre).equals(Optional.empty())) {
					if (!(getRow() == row_atPre - 1))
						throw new PostconditionError("MobService", "strafL", "");
				}
	}

}
