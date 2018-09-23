package contracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import components.Entity;
import decorators.CowDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.CowService;
import services.Dir;
import services.EntityService;
import services.EnvironnementService;
import services.MobService;

public class CowContract extends CowDecorator{

	public CowContract(CowService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
	
	public void checkInvariants() {
		// inv getCol() < getEnv().getWidth()
		if (!(getCol() <= getEnv().getWidth())) {
			throw new InvariantError("CowService", "Invariant", "getCol() < getEnv().getWidth()");
		}
		// getRow() < getEnv().getHeight()
		if (!(getRow() <= getEnv().getHeight())) {
			throw new InvariantError("CowService", "Invariant", "getRow() < getEnv().getHeight()");
		}

		// getEnv().getCellNature(getCol(),getRow()) != {WLL,DNC,DWC}
		int c = getCol();
		int r = getRow();
		Cell inv = getEnv().getCellNature(getRow(), getCol());
		if (inv.equals(Cell.WLL) || inv.equals(Cell.DNC) || inv.equals(Cell.DWC)) {
			throw new InvariantError("CowService", "Invariant",
					"getEnv().getCellNature(getCol(),getRow()) != {WLL,DNC,DWC}");
		}
	}
	
	
	public void init(EnvironnementService env, int x, int y, Dir face, int h) {
		if(!(h>=3 && h <= 4))
			throw new PreconditionError("CowService", "init", "! 3 <= h <= 4");
		if (!((x >= 0 && x < env.getWidth()) && (y >= 0 && y < env.getHeight()))) {
			throw new PreconditionError("CowService", "init", "0<= x < env.getWidth() && 0<= x < env.getHeight()");
		}
		super.init(env, x, y, face, h);
		checkInvariants();
		
		// post getCol() == x
		/**
				if (!(getCol() == y)) {
					throw new PostconditionError("CowService", "init", " getCol() == x");
				}
				// post getRow() == y
				if (!(getRow() == x)) {
					throw new PostconditionError("CowService", "init", " getRow() == y");
				}
		*/		
				// post getFace() == d
				if (!(getFace() == face)) {
					throw new PostconditionError("CowService", "init", " getFace() == d");
				}
				// post getCol() == x
				if (!(getEnv() == env)) {
					throw new PostconditionError("CowService", "init", " getEnv() == env");
				}
		
		if(!(getHp() == h))
			throw new PostconditionError("Cow Service", "init", "getHp() <> h"); 
	}

	@Override
	public void forward() {

		// capture
		int row_atPre = getRow();
		int col_atPre = getCol();
		Dir face_atPre = getFace();
		boolean condForward = false;

		switch (face_atPre) {
		case N:
			condForward = row_atPre - 1 >= 0 &&
					(
				     getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.EMP)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DWO)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.OUT)) 
					
					&& getEnv().getCellContent(row_atPre - 1, col_atPre).equals(Optional.empty());
					
			break;

		case S:
			condForward = row_atPre + 1 < getEnv().getHeight() &&
			
					(getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DWO)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.OUT))
			&& getEnv().getCellContent(row_atPre + 1, col_atPre).equals(Optional.empty());
					

			break;

		case E:
			condForward = col_atPre + 1 < getEnv().getWidth()
			&& (
					getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.DNO)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.OUT))
					&& getEnv().getCellContent(row_atPre, col_atPre + 1).equals(Optional.empty());
			break;

		case W:

			condForward = col_atPre - 1 >= 0 && (	
					getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.EMP
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.DNO
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.OUT)
				    && getEnv().getCellContent( row_atPre, col_atPre - 1).equals(Optional.empty());

			break;

		}
		checkInvariants();
		super.forward();
		checkInvariants();

		if (condForward == true) {
			switch (face_atPre) {
			case S:
				if (!(getRow() == row_atPre + 1 && getCol() == col_atPre)) {
					throw new PostconditionError("CowService", "forward",
							"Face sud et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case N:

				if (!(getRow() == row_atPre - 1 && getCol() == col_atPre)) {
					throw new PostconditionError("CowService", "forward",
							"Face Nord et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;

			case E:

				if (!(getRow() == row_atPre && getCol() == col_atPre + 1)) {
					throw new PostconditionError("CowService", "forward",
							"Face est et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case W:

				if (!(getRow() == row_atPre && getCol() == col_atPre - 1)) {
					throw new PostconditionError("CowService", "forward",
							"Face West et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;
			}
		} else if (!(getRow() == row_atPre && getCol() == col_atPre)) {
			throw new PostconditionError("CowService", "forward",
					"le joueur a avance malgre l'insatifaction des conditions");
		}

	}

	@Override
	public void backward() {

		// capture
		int row_atPre = getRow();
		int col_atPre = getCol();
		Dir face_atPre = getFace();

		boolean condbackward = false;

		switch (face_atPre) {
		case S:
			condbackward = row_atPre - 1 >= 0 &&
					(getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.EMP)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DWO)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.OUT)) 
					
					&& getEnv().getCellContent(row_atPre - 1, col_atPre).equals(Optional.empty());
					
			break;

		case N:
			condbackward = row_atPre + 1 < getEnv().getHeight() &&
			(getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DWO)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.OUT))
			&& getEnv().getCellContent(row_atPre + 1, col_atPre).equals(Optional.empty());
					

			break;

		case W:
			condbackward = col_atPre + 1 < getEnv().getWidth()
			&& 
					(getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.DNO)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.OUT))
					&& getEnv().getCellContent(row_atPre, col_atPre + 1).equals(Optional.empty());
			break;

		case E:

			condbackward = col_atPre - 1 >= 0 && (	
				getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.EMP
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.DNO
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.OUT)
				    && getEnv().getCellContent( row_atPre, col_atPre - 1).equals(Optional.empty());

			break;

		}

		checkInvariants();
		super.backward();
		checkInvariants();

		if (condbackward == true) {
			switch (face_atPre) {
			case N:
				if (!(getRow() == row_atPre + 1 && getCol() == col_atPre)) {
					throw new PostconditionError("CowService", "forward",
							"Face sud et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case S:

				if (!(getRow() == row_atPre - 1 && getCol() == col_atPre)) {
					throw new PostconditionError("CowService", "forward",
							"Face Nord et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;

			case W:

				if (!(getRow() == row_atPre && getCol() == col_atPre + 1)) {
					throw new PostconditionError("CowService", "forward",
							"Face est et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case E:

				if (!(getRow() == row_atPre && getCol() == col_atPre - 1)) {
					throw new PostconditionError("CowService", "forward",
							"Face West et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;
			}
		} else if (!(getRow() == row_atPre && getCol() == col_atPre)) {
			throw new PostconditionError("CowService", "forward",
					"le joueur a avance malgre l'insatifaction des conditions");
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
			if (!(getFace().equals(Dir.W)))
				throw new PostconditionError("CowService", "turnL", "turnL N");
			break;

		case E:
			if (!(getFace().equals(Dir.N)))
				throw new PostconditionError("CowService", "turnL", "turnL E");
			break;

		case W:
			if (!(getFace().equals(Dir.S)))
				throw new PostconditionError("CowService", "turnL", "turnL W");
			break;

		case S:
			if (!(getFace().equals(Dir.E)))
				throw new PostconditionError("CowService", "turnL", "turnL S");
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
			if (!(getFace().equals(Dir.W)))
				throw new PostconditionError("CowService", "turnR", "turnL S");
			break;

		case W:
			if (!(getFace().equals(Dir.N)))
				throw new PostconditionError("CowService", "turnR", "turnL W");
			break;

		case E:
			if (!(getFace().equals(Dir.S)))
				throw new PostconditionError("CowService", "turnR", "turnL E");
			break;

		case N:
			if (!(getFace().equals(Dir.E)))
				throw new PostconditionError("CowService", "turnR", "turnL N");
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
				throw new PostconditionError("CowService", "strafR", "");
		}

		if (row_atPre + 1 < getEnv().getHeight() && (face.equals(Dir.E) || face.equals(Dir.W))
				&& (getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.EMP)
						|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DNO))
				&& getEnv().getCellContent(row_atPre + 1, col_atPre).equals(Optional.empty())) {
			if (!(getRow() == row_atPre + 1))
				throw new PostconditionError("CowService", "strafR", "");
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
				throw new PostconditionError("CowService", "strafL", "");
		}

		if (row_atPre - 1 < getEnv().getHeight() && (face.equals(Dir.E) || face.equals(Dir.W))
				&& (getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.EMP)
						|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DNO))
				&& getEnv().getCellContent(row_atPre - 1, col_atPre).equals(Optional.empty())) {
			if (!(getRow() == row_atPre - 1))
				throw new PostconditionError("CowService", "strafL", "");
		}
	}


	@Override
	public void attack() {
		
		checkInvariants();
		// capture
		List<MobService> mobs = new ArrayList<>(((EnvironnementService)getEnv()).getMobs());
		List<Integer> lifes = new ArrayList<>();
	
		int row = 0;
		int col = 0;
		
		
		switch(getFace()) {
		case N:
			if (getRow() - 1  > 0) {
				row = getRow() - 1;
				col = getCol();
			}
			break;
		case E:
			if (getCol() + 1 < getEnv().getWidth() ) {
				row = getRow();
				col = getCol()  + 1;
			}
			break;
		case S:
			if (getRow() + 1 < getEnv().getHeight()) {
				row = getRow() + 1;
				col = getCol();
			}
			break;
		case W:
			if (getCol() - 1 > 0) {
				row = getRow() - 1;
				col = getCol() - 1;
			}
			break;
		}
		Optional<MobService> cow = getEnv().getCellContent(row, col);
		if(row != 0 && col != 0 && !cow.equals(Optional.empty()))
		{
			int hp = ((EntityService) cow.get()).getHp();
			mobs.remove(cow.get());
			
			for (MobService mob : mobs)
				lifes.add(((EntityService) mob).getHp());
			
			super.attack();
			
			
			
			if(((Entity) cow.get()).getHp() != hp - 1)
				throw new PostconditionError("CowService", "attack", "cow is hit but life is same");
			
			for (int i = 0; i < mobs.size(); i++)
				if(((EntityService) mobs.get(i)).getHp() != lifes.get(i))
					throw new PostconditionError("CowService", "attack", "others is attacked");
		}
	}

	
	public void step() {
		
		//capture
		int col_atPre = getCol();
		int row_atPre = getRow();
		checkInvariants();
		super.step();
		if(!(getCol() >= col_atPre - 1 && getCol() <= col_atPre + 1))
			throw new PostconditionError("CowService", "step", "col");
		if(!(getRow() >= row_atPre - 1 && getRow() <= row_atPre + 1))
			throw new PostconditionError("CowService", "step", "row");
		
	}
}
