package components;

import java.util.Optional;

import services.Cell;
import services.Dir;
import services.EntityService;
import services.EnvironnementService;
import services.KeyService;
import services.MobService;
import services.PlayerService;

public class Mob implements MobService {

	
	
	
	private EnvironnementService env;
	private int col;
	private int row;
	private Dir face;



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
	//	System.out.println("Forward "+face);
		switch (face) {
		case S:
			//System.err.println("EST "+env.getCellNature(row + 1, col));
			if (row + 1 < env.getHeight()
					&& (env.getCellNature(row + 1, col).equals(Cell.EMP)
							|| env.getCellNature(row + 1, col).equals(Cell.DWO) || env.getCellNature(row + 1, col).equals(Cell.OUT))
					&& env.getCellContent(row + 1, col).equals(Optional.empty())) {
				row += 1;
			}
			break;
		case N:
			
			if (row - 1 >= 0
					&& (env.getCellNature(row - 1, col).equals(Cell.EMP)
							|| env.getCellNature(row - 1, col).equals(Cell.DWO) || env.getCellNature(row - 1, col).equals(Cell.OUT))
					&& env.getCellContent(row - 1, col).equals(Optional.empty())) {
				row -= 1;
			}
			break;
		case E:
			
			if (col + 1 < env.getWidth()
					&& (env.getCellNature(row, col + 1).equals(Cell.EMP)
							|| env.getCellNature(row, col + 1).equals(Cell.DNO) || env.getCellNature(row , col + 1).equals(Cell.OUT))
					&& env.getCellContent(row, col + 1).equals(Optional.empty())) {

				col += 1;
			}
			break;
		case W:
			
			if (col - 1 >= 0
					&& (env.getCellNature(row ,col - 1).equals(Cell.EMP)
							|| env.getCellNature(row ,col - 1).equals(Cell.DNO) || env.getCellNature(row, col - 1).equals(Cell.OUT))
					&& env.getCellContent(row ,col - 1).equals(Optional.empty())) {
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
		case N:
			if (row + 1 < env.getHeight()
					&& (env.getCellNature(row + 1, col).equals(Cell.EMP)
							|| env.getCellNature(row + 1, col).equals(Cell.DWO) || env.getCellNature(row + 1, col).equals(Cell.OUT))
					&& env.getCellContent(row + 1, col).equals(Optional.empty())) {

				row += 1;
			}
			break;
		case S:
			if (row - 1 >= 0
					&& (env.getCellNature(row - 1, col).equals(Cell.EMP)
							|| env.getCellNature(row - 1, col).equals(Cell.DWO) || env.getCellNature(row - 1, col).equals(Cell.OUT))
					&& env.getCellContent(row - 1, col).equals(Optional.empty())) {
			//	System.out.println(face);
				row -= 1;
			}

			break;
		case W:
			if (col + 1 < env.getWidth()
					&& (env.getCellNature(row, col + 1).equals(Cell.EMP)
							|| env.getCellNature(row, col + 1).equals(Cell.DNO) || env.getCellNature(row , col + 1).equals(Cell.OUT))
					&& env.getCellContent(row, col + 1).equals(Optional.empty())) {
			//	System.out.println(face);
				col += 1;
			}
			break;
		case E:
			if (col - 1 >= 0
					&& (env.getCellNature(row, col - 1).equals(Cell.EMP)
							|| env.getCellNature(row ,col - 1).equals(Cell.DNO) || env.getCellNature(row , col - 1).equals(Cell.OUT))
					&& env.getCellContent(row ,col - 1).equals(Optional.empty())) {
			//	System.out.println(face);
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
				(env.getCellNature(row, col + 1).equals(Cell.EMP)
						|| env.getCellNature(row, col + 1).equals(Cell.DNO))
				&& env.getCellContent(row, col + 1).equals(Optional.empty()))
		{
			
			col += 1;
		}
		
		if( row + 1 < env.getHeight() && (face.equals(Dir.E) || face.equals(Dir.W)) 
				&& (env.getCellNature(row + 1, col).equals(Cell.EMP) || env.getCellNature(row + 1, col).equals(Cell.DNO))
		&& env.getCellContent(row + 1, col).equals(Optional.empty()))
		{
			row += 1;
		}
		
	}

	@Override
	public void strafeR() {
		if( col - 1 > 0 && (face.equals(Dir.N) || face.equals(Dir.S)) && 
				(env.getCellNature(row ,col - 1).equals(Cell.EMP)
						|| env.getCellNature(row ,col - 1).equals(Cell.DNO))
				&& env.getCellContent(row ,col - 1).equals(Optional.empty()))
		{
			
			col -= 1;
		}
		
		if( row - 1 > 0 && (face.equals(Dir.E) || face.equals(Dir.W)) 
				&& (env.getCellNature(row - 1, col).equals(Cell.EMP) || env.getCellNature(row - 1, col).equals(Cell.DNO))
		&& env.getCellContent(row - 1, col).equals(Optional.empty()))
		{
			row -= 1;
		}
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

	
	@Override
	public void attack() {
		switch(face) {
		case N:
			if (row - 1  > 0
					&& !env.getCellContent(row - 1, col).equals(Optional.empty())) {
				if (env.getCellContent(row - 1, col).get() instanceof EntityService) {
					EntityService entity = (EntityService) env.getCellContent(row - 1, col).get();
					((EntityService) entity).setHp(entity.getHp() - 1);
					System.out.println("point de vie reduit" + entity.getHp());
				}
				
			}
			break;
		case E:
			if (col + 1 < env.getWidth()
					&& !env.getCellContent(row , col + 1).equals(Optional.empty())) {
				if (env.getCellContent(row, col + 1).get() instanceof EntityService) {
					EntityService entity = (EntityService) env.getCellContent(row, col + 1).get();
					((EntityService) entity).setHp(entity.getHp() - 1);
					System.out.println("point de vie reduit" + entity.getHp());
				}
			}
			break;
		case S:
			if (row + 1 < env.getHeight()
					&& !env.getCellContent(row + 1, col).equals(Optional.empty())) {
				if (env.getCellContent(row + 1, col).get() instanceof EntityService) {
					EntityService entity = (EntityService) env.getCellContent(row + 1, col).get();
					((EntityService) entity).setHp(entity.getHp() - 1);
					System.out.println("point de vie reduit" + entity.getHp());
				}
			}
			break;
		case W:
			if (col - 1 >= 0
					&& !env.getCellContent(row , col - 1).equals(Optional.empty())) {
				if (env.getCellContent(row, col - 1).get() instanceof EntityService) {
					EntityService entity = (EntityService) env.getCellContent(row, col - 1).get();
					((EntityService) entity).setHp(entity.getHp() - 1);
					System.out.println("point de vie reduit" + entity.getHp());
				}
			}
			break;
		default:
			break;
		}
		
	}

}
