package contracts;

import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.CowService;
import services.Dir;
import services.EntityService;
import services.EnvironnementService;

public class CowContract extends EntityContract implements CowService{

	public CowContract(EntityService delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public void init(EnvironnementService envi, int x, int y, Dir face, int h) {
		if(!(h>=3 && h <= 4))
			throw new PreconditionError("CowService", "init", "! 3 <= h <= 4");
		
		((CowService) getDelegate()).init(envi, x, y, face, h);
		checkInvariants();
		if(!(getHp() == h))
			throw new PostconditionError("Cow Service", "init", "getHp() <> h"); 
	}

	
	public void step() {
		
		//capture
		int col_atPre = getCol();
		int row_atPre = getRow();
		checkInvariants();
		((CowService) getDelegate()).step();
		if(!(getCol() >= col_atPre - 1 && getCol() <= col_atPre + 1))
			throw new PostconditionError("CowService", "step", "col");
		if(!(getRow() >= row_atPre - 1 && getRow() <= row_atPre + 1))
			throw new PostconditionError("CowService", "step", "row");
		
	}
}
