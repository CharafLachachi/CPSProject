package contracts;


import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.Cell;
import services.EditMapService;

public class EditMapContract extends MapContract implements EditMapService {

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	@Override
	protected EditMapService getDelegate() {
		return (EditMapService) super.getDelegate();
	}
	
	@Override
	public void checkInvariants() {
		super.checkInvariants();
		//inv isReachable(x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
		//		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u-s)² + (v-t)² = 1
		//		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies getCellNature(u,v) != WLL
		
	}


	@Override
	public void init(int w, int h) {
		checkInvariants();
		super.init(w, h);
		checkInvariants();
	}


	@Override
	public void openDoor(int x, int y) {
		checkInvariants();
		super.openDoor(x, y);
		checkInvariants();
	}


	@Override
	public void closeDoor(int x, int y) {
		checkInvariants();
		super.closeDoor(x, y);
		checkInvariants();
	}


	

	@Override
	public boolean isReady() {
		return getDelegate().isReady();
	}

	@Override
	public void setNature(int x, int y, Cell Na) {
		// pre 0 <= x < getWidth() and 0 <= y < getHeight()
		if(!((x>=0 && x < getWidth()) && (y>=0 && y <getHeight()))) {
			throw new PreconditionError("EditMapService", "setNature", "0 <= x < getWidth() and 0 <= y < getHeight()");
		}
		// capture
		Cell[][] cells_atPre = new Cell[getWidth()][getHeight()];

		for (int i = 0; i < cells_atPre.length; i++)
			for (int j = 0; j < cells_atPre.length; j++)
				cells_atPre[i][j] = getCellNature(i, j);
		
		checkInvariants();
		getDelegate().setNature(x, y, Na);
		checkInvariants();
		
		//post getCellNature(x,y) = Na
		if (!(getCellNature(x, y) == Na)) {
			throw new PostconditionError("EditMapService", "setNature", "getCellNature(x,y) = Na");
		}
		
		//post \ForAll u,v in int² , {u != x or v != y} implies getCellNature(u,v) = getCellNature(u,v) 
		for (int i = 0; i < cells_atPre.length; i++) {
			for (int j = 0; j < cells_atPre.length; j++) {
				if (x != i || y != j) {
					if (!(cells_atPre[i][j] == getCellNature(i, j))) {
						throw new PostconditionError("EditMapService", "setNature", "La case a la positio" + i + " " + j
								+ " a change de nature malgre elle n'est pas concerne");
					}
				}
			}
		}
		
	}

}
