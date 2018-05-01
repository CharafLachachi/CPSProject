package components;

import services.EnvironnementService;
import services.KeyService;


public class Key extends Ressources implements KeyService{

	private int colDoor;
	private int rowDoor;
	
	@Override
	public int getColDoor() {
		return colDoor;
	}

	@Override
	public int getRowDoor() {
		// TODO Auto-generated method stub
		return rowDoor;
	}

	@Override
	public void init(EnvironnementService env, int x, int y, int xdoor, int ydoor) {
		super.init(env, xdoor, ydoor);
		colDoor = xdoor;
		rowDoor = ydoor;
		
	}

	

}
