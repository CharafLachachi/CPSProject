package contracts;

import decorators.MapDecorateur;
import services.Cell;
import services.MapService;

public class MapContract extends MapDecorateur {

	public MapContract(MapService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		// pas d'invariants
	}
	
	@Override
	public void init(int w, int h) {
		// TODO Auto-generated method stub
		super.init(w, h);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		// TODO Auto-generated method stub
		return super.getCellNature(x, y);
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
	protected MapService getDelegate() {
		// TODO Auto-generated method stub
		return super.getDelegate();
	}
	
}
