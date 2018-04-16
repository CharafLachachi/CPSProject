package components;

import java.util.Optional;

import services.Cell;
import services.Dir;
import services.EntityService;
import services.EnvironnementService;

public class Entity implements EntityService{

	private int hp;
	private EnvironnementService env;
	private int col;
	private int row;
	private Dir face;

	@Override
	public int getHp() {
		return hp;
	}

	@Override
	public EnvironnementService getEnv() {
		return env;
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public Dir getFace() {
		return face;
	}

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d) {
		this.env = env;
		this.col = x;
		this.row = y;
		this.face = d;
	}

	@Override
	public void forward() {

		switch (face) {
		case E:
			System.err.println(env.getCellNature(col, row + 1));
			if (row + 1 < env.getWidth()
					&& (env.getCellNature(col, row + 1).equals(Cell.EMP)
							|| env.getCellNature(col, row + 1).equals(Cell.DWO))
					&& env.getCellContent(col, row + 1).equals(Optional.empty())) {
				row += 1;
			}
			break;
		case W:
			System.err.println(env.getCellNature(col, row - 1));

			if (row - 1 >= 0
					&& (env.getCellNature(col, row - 1).equals(Cell.EMP)
							|| env.getCellNature(col, row - 1).equals(Cell.DWO))
					&& env.getCellContent(col, row - 1).equals(Optional.empty())) {

				row -= 1;
			}

			break;
		case S:
			System.out.println(env.getCellNature(col + 1, row));
			if (col + 1 < env.getHeight()
					&& (env.getCellNature(col + 1, row).equals(Cell.EMP)
							|| env.getCellNature(col + 1, row).equals(Cell.DNO))
					&& env.getCellContent(col + 1, row).equals(Optional.empty())) {

				col += 1;
			}
			break;
		case N:
			System.out.println(env.getCellNature(col - 1, row));
			if (col - 1 >= 0
					&& (env.getCellNature(col - 1, row).equals(Cell.EMP)
							|| env.getCellNature(col - 1, row).equals(Cell.DNO))
					&& env.getCellContent(col - 1, row).equals(Optional.empty())) {

				col -= 1;
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void backward() {
		switch (face) {
		case W:
			if (row + 1 < env.getHeight()
					&& (env.getCellNature(col, row + 1).equals(Cell.EMP)
							|| env.getCellNature(col, row + 1).equals(Cell.DWO))
					&& env.getCellContent(col, row + 1).equals(Optional.empty())) {

				row += 1;
			}
			break;
		case E:
			if (row - 1 >= 0
					&& (env.getCellNature(col, row - 1).equals(Cell.EMP)
							|| env.getCellNature(col, row - 1).equals(Cell.DWO))
					&& env.getCellContent(col, row - 1).equals(Optional.empty())) {

				row -= 1;
			}

			break;
		case N:
			if (col + 1 < env.getWidth()
					&& (env.getCellNature(col + 1, row).equals(Cell.EMP)
							|| env.getCellNature(col + 1, row).equals(Cell.DNO))
					&& env.getCellContent(col + 1, row).equals(Optional.empty())) {

				col += 1;
			}
			break;
		case S:
			if (col - 1 >= 0
					&& (env.getCellNature(col - 1, row).equals(Cell.EMP)
							|| env.getCellNature(col - 1, row).equals(Cell.DNO))
					&& env.getCellContent(col - 1, row).equals(Optional.empty())) {

				col -= 1;
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void turnL() {
		switch (face) {
		case N:
			face = Dir.W;
			break;
		case S:
			face = Dir.E;
			break;
		case E:
			face = Dir.N;
			break;
		case W:
			face = Dir.S;
			break;

		default:
			break;
		}
	}

	@Override
	public void turnR() {
		switch (face) {
		case S:
			face = Dir.W;
			break;
		case N:
			face = Dir.E;
			break;
		case W:
			face = Dir.N;
			break;
		case E:
			face = Dir.S;
			break;

		default:
			break;
		}
	}

	@Override
	public void strafeL() {
		if( col + 1 < env.getWidth() && (face.equals(Dir.N) || face.equals(Dir.S)) && 
				(env.getCellNature(col + 1, row).equals(Cell.EMP)
						|| env.getCellNature(col + 1, row).equals(Cell.DNO))
				&& env.getCellContent(col + 1, row).equals(Optional.empty()))
		{
			
			col += 1;
		}
		
		if( row + 1 < env.getHeight() && (face.equals(Dir.E) || face.equals(Dir.W)) 
				&& (env.getCellNature(col, row + 1).equals(Cell.EMP) || env.getCellNature(col, row + 1).equals(Cell.DNO))
		&& env.getCellContent(col, row + 1).equals(Optional.empty()))
		{
			row += 1;
		}
		
	}

	@Override
	public void strafeR() {
		if( col - 1 > 0 && (face.equals(Dir.N) || face.equals(Dir.S)) && 
				(env.getCellNature(col - 1, row).equals(Cell.EMP)
						|| env.getCellNature(col - 1, row).equals(Cell.DNO))
				&& env.getCellContent(col - 1, row).equals(Optional.empty()))
		{
			
			col -= 1;
		}
		
		if( row - 1 > 0 && (face.equals(Dir.E) || face.equals(Dir.W)) 
				&& (env.getCellNature(col, row - 1).equals(Cell.EMP) || env.getCellNature(col, row - 1).equals(Cell.DNO))
		&& env.getCellContent(col, row - 1).equals(Optional.empty()))
		{
			row -= 1;
		}
	}
	
	@Override
	public void init(EnvironnementService env, int x, int y, Dir d, int h) {
		this.env = env;
		this.col = x;
		this.row = y;
		this.face = d;
		this.hp = h;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setEnv(EnvironnementService env) {
		this.env = env;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setFace(Dir face) {
		this.face = face;
	}

}
