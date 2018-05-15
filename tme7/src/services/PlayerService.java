package services;

import java.util.Optional;

public interface PlayerService extends /* include */ EntityService {

	public void setCommand(Command c);
	public void setKey(KeyService key);
	public KeyService getKey();
	// observators

	public Optional<Command> lastCom();

	/**
	 * @param x
	 * @param y
	 * @return pre -1 <= x <= 1 and -1 <= y <= 3
	 */
	public Optional<MobService> content(int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @return pre -1 <= x <= 1 and -1 <= y <= 3
	 */

	public Cell nature(int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @return pre -1 <= x <= 1 and -1 <= y <= 3
	 */

	public boolean viewable(int x, int y);

	/**
	 * post LastCom(P)=FF implies step(P) = Forward(P)
	 * post LastCom(P)=BB implies step(P) = Backward(P) 
	 * post LastCom(P)=LL implies step(P) = StrafeLeft(P)
	 * post LastCom(P)=RR implies step(P) = StrafeRight(P) 
	 * post LastCom(P)=TL implies step(P) = TurnLeft(P)
	 * post LastCom(P)=TR implies step(P) = TurnRight(P)
	 * post LastCom(P)=C implies step(P) = Battle(P)
	 */

	public void step();
	
/**
 * [Invariant]
 * 	        getFace() = N implies content(u,v) = getEnv().CellContent(Envi(P),getCol()+u,getRow()+v)
 *			getFace() = N implies nature(u,v) = getEnv().getCellNature(getCol()+u,getRow()+v)
 *			getFace() = S implies content(u,v) = getEnv().CellContent(Envi(P),getCol()-u,getRow()-v)
 *			getFace() = S implies nature(u,v) = getEnv().getCellNature(getCol()-u,getRow()-v)
 *			getFace() = E implies content(u,v) = getEnv().CellContent(Envi(P),getCol()+v,getRow()-u)
 *			getFace() = E implies nature(u,v) = getEnv().getCellNature(getCol()+v,getRow()-u)
 *			getFace() = W implies content(u,v) = getEnv().CellContent(Envi(P),getCol()-v,getRow()+u)
 *			getFace() = W implies nature(u,v) = getEnv().getCellNature(getCol()-v,getRow()+u)
 *			forall u,v in [-1,1] * [-1,1], not viewable(u,v)
 *			viewable(,-1,2) = nature(-1,1) in {WALL, DWC, DNC}
 *			viewable(,0,2) = nature(0,1) in {WALL, DWC, DNC}
 *			viewable(,1,2) = nature(1,1) in {WALL, DWC, DNC}
 * 			viewable(,-1,3) = nature(-1,2) in {WALL, DWC, DNC} and viewable(-1,2)
 *			viewable(,0,3) = nature(0,2) in {WALL, DWC, DNC} and viewable(0,2)
 *			viewable(,1,3) = nature(1,2) in {WALL, DWC, DNC} and viewable(1,2)
	*/
}
