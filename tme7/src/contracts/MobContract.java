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

	public MobContract(MobDecorateur delegate) {
		super(delegate);
	}

	public void chechInvariants() {
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
		chechInvariants();
		super.init(env, x, y, d);
		chechInvariants();

		// post getCol() == x
		if (!(getCol() == x)) {
			throw new PostconditionError("EditMapService", "init", " getCol() == x");
		}
		// post  getRow() == y
		if (!( getRow() == y)) {
			throw new PostconditionError("EditMapService", "init", " getRow() == y");
		}
		//post getFace() == d
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
		
		
		chechInvariants();
		super.forward();
		chechInvariants();
		// post if(getFace() == Dir.N) then 
		//	if getEnv().getCellNature(getCol(), getRow()+1) == Cell.EMP or Cell.DWO and 
		// getRow() + 1 < getEnv().getHeigth() and  
		// getEnv().getCellContent(getCol(), getRow() + 1) == Option.No then
		// getRow() = getRow()@pre + 1 and getCol() = getCol()@pre
		if (face_atPre == Dir.N) {
			if ((getEnv().getCellNature(col_atPre, row_atPre+1) == Cell.EMP ||
					getEnv().getCellNature(col_atPre, row_atPre+1) == Cell.DWO) &&
					row_atPre + 1 < getEnv().getHeight() &&
					(Optional.ofNullable(getEnv().getMob(col_atPre, row_atPre)).isPresent() == false)) {
				if (!(getRow() == row_atPre +1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward", "Face nord et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
			}
		}
		
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		super.backward();
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		super.turnL();
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		super.turnR();
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		super.strafeL();
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		super.strafeR();
	}

}
