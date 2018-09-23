package services;

public interface MobService {
	/**
	 * Observateurs
	 */
	public EnvironnementService getEnv();

	public int getCol();

	public int getRow();

	public Dir getFace();

	/**
	 * Ivariants
	 */
	// inv getCol() < getEnv().getWidth()
	// inv getRow() < getEnv().getHeight()
	// inv getEnv().getCellNature(getCol(),getRow()) != {WLL,DNC,DWC}

	/**
	 * Constructeurs
	 */
	// pre 0<= x < env.getWidth() && 0<= x < env.getHeight()
	// post getCol() == x;
	// post getRow() == y;
	// post getFace() == d;
	// post getEnv() == env;
	public void init(EnvironnementService env, int x, int y, Dir d);

	/**
	 * Operateurs
	 */
	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol(), getRow()+1) == Cell.EMP or Cell.DWO and
	// getRow() + 1 < getEnv().getHeigth() and
	// getEnv().getCellContent(getCol(), getRow() + 1) == Option.No then
	// getRow() = getRow()@pre + 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol(), getRow()+1) != Cell.EMP or Cell.DWO or
	// getRow() + 1 < getEnv().getHeight() or
	// getEnv().getCellContent(getCol(), getRow() + 1) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol()+1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() + 1 < getEnv().getWidth() and
	// getEnv().getCellContent(getCol() +1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre + 1

	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol() + 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() + 1 >= getEnv().getWidth() or
	// getEnv().getCellContent(getCol() + 1, getRow()) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol(), getRow() - 1) == Cell.EMP or Cell.DWO and
	// getRow() - 1 > 0 and
	// getEnv().getCellContent(getCol(), getRow() - 1) == Option.No then
	// getRow() = getRow()@pre - 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol(), getRow()+1) != Cell.EMP or Cell.DWO or
	// getRow() - 1 <= 0 or
	// getEnv().getCellContent(getCol(), getRow() - 1) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol()-1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() - 1 > 0 and
	// getEnv().getCellContent(getCol() - 1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre - 1

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol() - 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() - 1 <= 0 or
	// getEnv().getCellContent(getCol() - 1, getRow()) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre
	public void forward();

	// Cas nord et peut reculer
	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol(), getRow()-1) == Cell.EMP || Cell.DWO &&
	// getRow() - 1 >= 0 &&
	// getEnv().getCellContent(getCol(), getRow() - 1) == Option.No then
	// getRow() = getRow()@pre -1 && getCol() = getCol()@pre

	// Cas nord et ne peut pas reculer
	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol(), getRow()-1) != Cell.EMP || Cell.DWO ||
	// getRow() - 1 < 0 ||
	// getEnv().getCellContent(getCol(), getRow() - 1) == Option.Yes then
	// getRow() = getRow()@pre && getCol() = getCol()@pre

	// Cas est et peut reculer
	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol() - 1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() - 1 >= 0 and
	// getEnv().getCellContent(getCol() +1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre + 1

	// Cas est et ne peut pas reculer
	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol() - 1, getRow()) != Cell.EMP || Cell.DNO ||
	// getCol() - 1 < 0 and
	// getEnv().getCellContent(getCol() - 1 , getRow() ) == Option.Yes then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol(), getRow() + 1) == Cell.EMP or Cell.DWO and
	// getRow() + 1 <= getEnv().getHeight() and
	// getEnv().getCellContent(getCol(), getRow() + 1) == Option.No then
	// getRow() = getRow()@pre + 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol(), getRow() + 1) != Cell.EMP || Cell.DWO ||
	// getRow() + 1 > getEnv().getHeight() ||
	// getEnv().getCellContent(getCol(), getRow() + 1) == Option.Yes then
	// getRow() = getRow()@pre + and getCol() = getCol()@pre

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol()+1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() + 1 <= getEnv().getWidth() and
	// getEnv().getCellContent(getCol() + 1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre + 1

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol() + 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() + 1 > getEnv().getWidth() or
	// getEnv().getCellContent(getCol() + 1, getRow()) == Option.Yes then
	// getRow() = getRow()@pre and getCol() = getCol()@pre
	public void backward();

	/**
	 * post if(getFace() == Dir.N) then if(getEnv().getCellContent(getCol(),
	 * getRow()-1) = Option.YES) then getEnv().getCellContent(getCol(),
	 * getRow()-1).getHp() -= 1 for i <> getCol(),j<> getRow()-1 in N²,
	 * getEnv().getMob(i,j).getHp() = getEnv().getMob(i,j).getHp()@pre
	 */
	/**
	 * post if(getFace() == Dir.S) then if(getEnv().getCellContent(getCol(),
	 * getRow()+1) = Option.YES) then getEnv().getCellContent(getCol(),
	 * getRow()+1).getHp() -= 1 for i <> getCol(),j<> getRow()+1 in N²,
	 * getEnv().getMob(i,j).getHp() = getEnv().getMob(i,j).getHp()@pre
	 */
	/**
	 * post if(getFace() == Dir.E) then if(getEnv().getCellContent(getCol()+1,
	 * getRow()) = Option.YES) then getEnv().getCellContent(getCol()+1,
	 * getRow()).getHp() -= 1 for i <> getCol()+1,j<> getRow() in N²,
	 * getEnv().getMob(i,j).getHp() = getEnv().getMob(i,j).getHp()@pre
	 */
	/**
	 * post if(getFace() == Dir.W) then if(getEnv().getCellContent(getCol()-1,
	 * getRow()) = Option.YES) then getEnv().getCellContent(getCol()-1,
	 * getRow()).getHp() -= 1 for i <> getCol()-1,j<> getRow() in N²,
	 * getEnv().getMob(i,j).getHp() = getEnv().getMob(i,j).getHp()@pre
	 */

	public void attack();

	/**
	 * [TurnLeft]:
	 * 
	 * \post Face(M)=N implies Face(TurnLeft(M))=W
	 * 
	 * \post Face(M)=W implies Face(TurnLeft(M))=S
	 * 
	 * \post Face(M)=S implies Face(TurnLeft(M))=E
	 * 
	 * \post Face(M)=E implies Face(TurnLeft(M))=N
	 */
	public void turnL();

	/**
	 * \post Face(M)=S implies Face(TurnRight(M))=W
	 * 
	 * \post Face(M)=E implies Face(TurnRight(M))=S
	 * 
	 * \post Face(M)=N implies Face(TurnRight(M))=E
	 * 
	 */
	public void turnR();

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol(), getRow()+1) == Cell.EMP or Cell.DWO and
	// getRow() + 1 < getEnv().getHeigth() and
	// getEnv().getCellContent(getCol(), getRow() + 1) == Option.No then
	// getRow() = getRow()@pre + 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol(), getRow()+1) != Cell.EMP or Cell.DWO or
	// getRow() + 1 < getEnv().getHeight() or
	// getEnv().getCellContent(getCol(), getRow() + 1) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol()+1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() + 1 < getEnv().getWidth() and
	// getEnv().getCellContent(getCol() +1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre + 1

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol() + 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() + 1 >= getEnv().getWidth() or
	// getEnv().getCellContent(getCol() + 1, getRow()) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol(), getRow() - 1) == Cell.EMP or Cell.DWO and
	// getRow() - 1 > 0 and
	// getEnv().getCellContent(getCol(), getRow() - 1) == Option.No then
	// getRow() = getRow()@pre - 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol(), getRow()-1) != Cell.EMP or Cell.DWO or
	// getRow() - 1 <= 0 or
	// getEnv().getCellContent(getCol(), getRow() - 1) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol()-1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() - 1 > 0 and
	// getEnv().getCellContent(getCol() - 1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre - 1

	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol() - 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() - 1 <= 0 or
	// getEnv().getCellContent(getCol() - 1, getRow()) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre
	public void strafeL();

	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol(), getRow()+1) == Cell.EMP or Cell.DWO and
	// getRow() + 1 < getEnv().getHeigth() and
	// getEnv().getCellContent(getCol(), getRow() + 1) == Option.No then
	// getRow() = getRow()@pre + 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.E) then
	// if getEnv().getCellNature(getCol(), getRow()+1) != Cell.EMP or Cell.DWO or
	// getRow() + 1 < getEnv().getHeight() or
	// getEnv().getCellContent(getCol(), getRow() + 1) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol()+1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() + 1 < getEnv().getWidth() and
	// getEnv().getCellContent(getCol() +1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre + 1

	// post if(getFace() == Dir.N) then
	// if getEnv().getCellNature(getCol() + 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() + 1 >= getEnv().getWidth() or
	// getEnv().getCellContent(getCol() + 1, getRow()) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol(), getRow() - 1) == Cell.EMP or Cell.DWO and
	// getRow() - 1 > 0 and
	// getEnv().getCellContent(getCol(), getRow() - 1) == Option.No then
	// getRow() = getRow()@pre - 1 and getCol() = getCol()@pre

	// post if(getFace() == Dir.W) then
	// if getEnv().getCellNature(getCol(), getRow()-1) != Cell.EMP or Cell.DWO or
	// getRow() - 1 <= 0 or
	// getEnv().getCellContent(getCol(), getRow() - 1) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol()-1, getRow()) == Cell.EMP or Cell.DNO and
	// getCol() - 1 > 0 and
	// getEnv().getCellContent(getCol() - 1 , getRow() ) == Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre - 1

	// post if(getFace() == Dir.S) then
	// if getEnv().getCellNature(getCol() - 1, getRow()) != Cell.EMP or Cell.DNO or
	// getCol() - 1 <= 0 or
	// getEnv().getCellContent(getCol() - 1, getRow()) != Option.No then
	// getRow() = getRow()@pre and getCol() = getCol()@pre

	public void strafeR();
}
