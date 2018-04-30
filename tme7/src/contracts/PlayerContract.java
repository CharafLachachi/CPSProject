package contracts;

import java.util.Optional;

import decorators.PlayerDecorator;
import exceptions.InvariantError;
import services.Cell;
import services.Command;
import services.Dir;
import services.EnvironnementService;
import services.MobService;
import services.PlayerService;

public class PlayerContract extends EntityContract implements PlayerService {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}

	public void checkInv() {
		// TODO : a verifier
		switch (getFace()) {
		case N:
			for(int u = 0; u < getRow(); u++)
			{
			if (!(content(u, 0) == getEnv().getCellContent(getRow() - u, getCol() )))
				throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
			if (!(nature(u, 0) == getEnv().getCellNature(getRow() - u, getCol() )))
				throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");
			}
			break;
		case S:
			for(int u = getEnv().getHeight();  u >= getRow();  u--)
			{
			if (!(content(u, 0) == getEnv().getCellContent(getRow() + u, getCol())))
				throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
			if (!(nature(u, 0) == getEnv().getCellNature(getRow() + u, getCol())))
				throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");
			}
			break;
		case E:
			for(int u = getCol(); u < getEnv().getWidth(); u++)
			{
			if (!(content(0, u) == getEnv().getCellContent(getRow(), getCol() + u)))
				throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
			if (!(nature(0, u) == getEnv().getCellNature(getRow(), getCol() + u)))
				throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");
			}
			break;
		case W:
			for(int u = 0 ;  u >= getCol();  u++)
			{
			if (!(content(0, u) == getEnv().getCellContent(getRow(), getCol() - u)))
				throw new InvariantError("playerService", "checkInv", "content diffirent of cell content");
			if (!(nature(0, u) == getEnv().getCellNature(getRow(), getCol() - u)))
				throw new InvariantError("playerService", "checkInv", "nature diffirent of cell nature");
			}
			break;
		}
		
		for (int u = -1; u <= 1; u++)
			for(int v = -1; v <= 1; v++)
			{
				if(!(viewable(u, v) == false))
					throw new InvariantError("PlayerService", "checkInv", "viewable");
			}
		
		if(viewable(-1, 2))
			if(!(nature(-1, 1).equals(Cell.WLL) || nature(-1, 1).equals(Cell.DWC) || nature(-1, 1).equals(Cell.DNC)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(-1,2)");
		
		if(viewable(0, 2))
			if(!(!nature(0, 1).equals(Cell.WLL) || !nature(0, 1).equals(Cell.DWC) || !nature(0, 1).equals(Cell.DNC)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(0,2)");
		
		if(viewable(1, 2))
			if(!(!nature(1, 1).equals(Cell.WLL) || !nature(1, 1).equals(Cell.DWC) || !nature(1, 1).equals(Cell.DNC)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(1,2)");
		
		if(viewable(-1, 3))
			if(!(!nature(-1, 2).equals(Cell.WLL) || !nature(-1, 2).equals(Cell.DWC) || !nature(-1, 2).equals(Cell.DNC) && viewable(-1, 2)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(-1,3)");
		
		if(viewable(0, 3))
			if(!(!nature(0, 2).equals(Cell.WLL) || !nature(0, 2).equals(Cell.DWC) || !nature(0, 2).equals(Cell.DNC) && viewable(0, 2)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(0,3)");
		
		if(viewable(1, 3))
			if(!(!nature(1, 2).equals(Cell.WLL) || !nature(1, 2).equals(Cell.DWC) || !nature(1, 2).equals(Cell.DNC) && viewable(1, 2)))
				throw new InvariantError("PlayerService", "checkInv", "viewable(1,3)");
		
	}



	@Override
	public void step() {
		switch(lastCom().get())
		{
		case FF :
			this.forward();
			break;
		case BB :
			this.backward();
			break;
		case LL :
			this.strafeL();
			break;
		case RR : 
			this.strafeR();
			break;
		case TL :
			this.turnL();
			break;
		case TR :
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

	

	

}
