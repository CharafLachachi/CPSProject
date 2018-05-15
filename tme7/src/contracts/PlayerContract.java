package contracts;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import components.Entity;
import components.Environement;
import decorators.PlayerDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.Command;
import services.Dir;
import services.EnvironnementService;
import services.KeyService;
import services.MobService;
import services.PlayerService;

public class PlayerContract extends PlayerDecorator  {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	

	public void checkInv() {
		// TODO : a verifier
		
		try
		{
		for (int u = -1; u < 1; u++)
			for (int v = -1; v < 3; v++) {
				switch (getFace()) {

				case N:
					if (!(content(u, v).equals(getEnv().getCellContent(getRow() + v, getCol() + u))))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v).equals(getEnv().getCellNature(getRow() + v, getCol() + u))))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				case S:

					if (!(content(u, v).equals(getEnv().getCellContent(getRow() - v, getCol() - u ))))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v).equals(getEnv().getCellNature( getRow() - v, getCol() - u ))))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				case E:

					if (!(content(u, v).equals(getEnv().getCellContent(getRow() - u, getCol() + v))))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v).equals(getEnv().getCellNature(getRow() - u, getCol() + v))))
						throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");

					break;
				case W:

					if (!(content(u, v).equals(getEnv().getCellContent(getRow() + u, getCol() - v))))
						throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
					if (!(nature(u, v).equals(getEnv().getCellNature( getRow() + u, getCol() - v))))
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
			if (!(!nature(-1, 1).equals(Cell.WLL) || !nature(-1, 1).equals(Cell.DWC) || !nature(-1, 1).equals(Cell.DNC)))
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
		
		}catch(ArrayIndexOutOfBoundsException e) {}
		

	}

	@Override
	public void step() {
		
		switch (lastCom().get()) {
		case FF:
			forward();
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
		default:
			break;
		}
		
		if(	!getEnv().getCellRessources(getRow(), getCol()).equals(Optional.empty()))
		{
			KeyService key = (KeyService) getEnv().getCellRessources(getRow(), getCol()).get();
			this.setKey(key);
			getEnv().removeRessource(key);
			if(key == null)
				System.out.println("Key NULL");
			else
				System.out.println("yep j'ai la clé");
			
		}

	//	super.step();
	}

	
	
	@Override
	public void init(EnvironnementService env, int x, int y, Dir face, int h) {
		
		// pre 0<= x < env.getWidth() && 0<= x < env.getHeight()
				if (!((x >= 0 && x < env.getWidth()) && (y >= 0 && y < env.getHeight()))) {
					throw new PreconditionError("EditMapService", "init", "0<= x < env.getWidth() && 0<= x < env.getHeight()");
				}
		if(!(h>0))
			throw new PreconditionError("entity Service", "init", "h <= 0");
		super.init(env, x, y, face, h);
		
		
		// post getCol() == x
				if (!(getCol() == x)) {
					throw new PostconditionError("EditMapService", "init", " getCol() == x");
				}
				// post getRow() == y
				if (!(getRow() == y)) {
					throw new PostconditionError("EditMapService", "init", " getRow() == y");
				}
				// post getFace() == d
				if (!(getFace() == face)) {
					throw new PostconditionError("EditMapService", "init", " getFace() == d");
				}
				// post getCol() == x
				if (!(getEnv() == env)) {
					throw new PostconditionError("EditMapService", "init", " getEnv() == env");
				}
		
		if(!(getHp() == h))
			throw new PostconditionError("entity Service", "init", "getHp() <> h"); 
		
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
				    ( getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.EMP)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DWO)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.OUT)) 
					||
					(getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DWC) && getKey() != null))
					
					&& getEnv().getCellContent(row_atPre - 1, col_atPre).equals(Optional.empty());
					
			break;

		case S:
			condForward = row_atPre + 1 < getEnv().getHeight() &&
			(
					(getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DWO)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.OUT))
			||
			(getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DWC) && getKey() != null))
			&& getEnv().getCellContent(row_atPre + 1, col_atPre).equals(Optional.empty());
					

			break;

		case E:
			condForward = col_atPre + 1 < getEnv().getWidth()
			&& (
					(getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.DNO)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.OUT))
					|| (getEnv().getCellNature(row_atPre , col_atPre + 1).equals(Cell.DNC) && getKey() != null))
					&& getEnv().getCellContent(row_atPre, col_atPre + 1).equals(Optional.empty());
			break;

		case W:

			condForward = col_atPre - 1 >= 0 && (	
					(getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.EMP
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.DNO
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.OUT)
				    || (getEnv().getCellNature(row_atPre , col_atPre - 1).equals(Cell.DNC) && getKey() != null))
					&& getEnv().getCellContent( row_atPre, col_atPre - 1).equals(Optional.empty());

			break;

		}
		checkInv();
		super.forward();
		checkInv();

		if (condForward == true) {
			switch (face_atPre) {
			case S:
				if (!(getRow() == row_atPre + 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face sud et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case N:

				if (!(getRow() == row_atPre - 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face Nord et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;

			case E:

				if (!(getRow() == row_atPre && getCol() == col_atPre + 1)) {
					throw new PostconditionError("MobService", "forward",
							"Face est et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case W:

				if (!(getRow() == row_atPre && getCol() == col_atPre - 1)) {
					throw new PostconditionError("MobService", "forward",
							"Face West et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;
			}
		} else if (!(getRow() == row_atPre && getCol() == col_atPre)) {
			throw new PostconditionError("MobService", "forward",
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
					(
				    ( getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.EMP)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DWO)
				    		|| getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.OUT)) 
					||
					(getEnv().getCellNature(row_atPre - 1, col_atPre).equals(Cell.DWC) && getKey() != null))
					
					&& getEnv().getCellContent(row_atPre - 1, col_atPre).equals(Optional.empty());
					
			break;

		case N:
			condbackward = row_atPre + 1 < getEnv().getHeight() &&
			(
					(getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DWO)
					|| getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.OUT))
			||
			(getEnv().getCellNature(row_atPre + 1, col_atPre).equals(Cell.DWC) && getKey() != null))
			&& getEnv().getCellContent(row_atPre + 1, col_atPre).equals(Optional.empty());
					

			break;

		case W:
			condbackward = col_atPre + 1 < getEnv().getWidth()
			&& (
					(getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.EMP)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.DNO)
					|| getEnv().getCellNature(row_atPre, col_atPre + 1).equals(Cell.OUT))
					|| (getEnv().getCellNature(row_atPre , col_atPre + 1).equals(Cell.DNC) && getKey() != null))
					&& getEnv().getCellContent(row_atPre, col_atPre + 1).equals(Optional.empty());
			break;

		case E:

			condbackward = col_atPre - 1 >= 0 && (	
					(getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.EMP
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.DNO
					|| getEnv().getCellNature(row_atPre, col_atPre - 1) == Cell.OUT)
				    || (getEnv().getCellNature(row_atPre , col_atPre - 1).equals(Cell.DNC) && getKey() != null))
					&& getEnv().getCellContent( row_atPre, col_atPre - 1).equals(Optional.empty());

			break;

		}

		checkInv();
		super.backward();
		checkInv();

		if (condbackward == true) {
			switch (face_atPre) {
			case N:
				if (!(getRow() == row_atPre + 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face sud et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case S:

				if (!(getRow() == row_atPre - 1 && getCol() == col_atPre)) {
					throw new PostconditionError("MobService", "forward",
							"Face Nord et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;

			case W:

				if (!(getRow() == row_atPre && getCol() == col_atPre + 1)) {
					throw new PostconditionError("MobService", "forward",
							"Face est et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}
				break;

			case E:

				if (!(getRow() == row_atPre && getCol() == col_atPre - 1)) {
					throw new PostconditionError("MobService", "forward",
							"Face West et le joueur n'a pas avance malgre la satifaction de toutes les conditions");
				}

				break;
			}
		} else if (!(getRow() == row_atPre && getCol() == col_atPre)) {
			throw new PostconditionError("MobService", "forward",
					"le joueur a avance malgre l'insatifaction des conditions");
		}
	}

	@Override
	public void turnL() {

		checkInv();
		// capture
		Dir face_atPre = getFace();
		super.turnL();
		switch (face_atPre) {
		case N:
			if (!(getFace().equals(Dir.W)))
				throw new PostconditionError("mobService", "turnL", "turnL N");
			break;

		case E:
			if (!(getFace().equals(Dir.N)))
				throw new PostconditionError("mobService", "turnL", "turnL E");
			break;

		case W:
			if (!(getFace().equals(Dir.S)))
				throw new PostconditionError("mobService", "turnL", "turnL W");
			break;

		case S:
			if (!(getFace().equals(Dir.E)))
				throw new PostconditionError("mobService", "turnL", "turnL S");
			break;
		}
	}

	@Override
	public void turnR() {
		checkInv();
		// capture
		Dir face_atPre = getFace();
		super.turnR();
		switch (face_atPre) {
		case S:
			if (!(getFace().equals(Dir.W)))
				throw new PostconditionError("mobService", "turnR", "turnL S");
			break;

		case W:
			if (!(getFace().equals(Dir.N)))
				throw new PostconditionError("mobService", "turnR", "turnL W");
			break;

		case E:
			if (!(getFace().equals(Dir.S)))
				throw new PostconditionError("mobService", "turnR", "turnL E");
			break;

		case N:
			if (!(getFace().equals(Dir.E)))
				throw new PostconditionError("mobService", "turnR", "turnL N");
			break;
		}
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		checkInv();
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
		checkInv();
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


	@Override
	public void attack() {
		
		checkInv();
		// capture
		List<MobService> mobs = new ArrayList<>(((Environement)getEnv()).mobs);
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
			int hp = ((Entity) cow.get()).getHp();
			mobs.remove(cow.get());
			
			for (MobService mob : mobs)
				lifes.add(((Entity) mob).getHp());
			
			super.attack();
			
			
			
			if(((Entity) cow.get()).getHp() != hp - 1)
				throw new PostconditionError("MobService", "attack", "cow is hit but life is same");
			
			for (int i = 0; i < mobs.size(); i++)
				if(((Entity) mobs.get(i)).getHp() != lifes.get(i))
					throw new PostconditionError("MobService", "attack", "others is attacked");
		}
		
		
		
		
	}
	




	




	
}
