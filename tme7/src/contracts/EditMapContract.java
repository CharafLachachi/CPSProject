package contracts;

import services.Cell;
import services.EditMapService;

public class EditMapContract extends MapContract implements EditMapService {

	public EditMapContract(EditMapService delegate) {
		super(delegate);
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
		// TODO Auto-generated method stub
		super.init(w, h);
	}


	@Override
	public void openDoor(int x, int y) {
		// TODO Auto-generated method stub
		super.openDoor(x, y);
	}


	@Override
	public void closeDoor(int x, int y) {
		// TODO Auto-generated method stub
		super.closeDoor(x, y);
	}


	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNature(int x, int y, Cell Na) {
		// TODO Auto-generated method stub
		
	}

}
