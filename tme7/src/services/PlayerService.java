package services;

import java.util.Optional;

public interface PlayerService extends /* include */ EntityService {

	// observators
	public void setCommand(Command c);
	public void setKey(KeyService key);
	public KeyService getKey();
	public RessourcesService getRessources();
	public void setRessources(RessourcesService gold);
	
	//pre getRessources() = TREASOR
	public boolean isWin();

	// pre getHp() <= 0
	public boolean isDead();
	
	
	

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
	 * 
	 * pre face = N and key <> null ==> and getEnv().getCellNature(getRow() - 1 ,getCol()) in {DWC}
	 * pre face = S and key <> null ==> and getEnv().getCellNature(getRow() + 1 ,getCol()) in {DWC}
	 * pre face = E and key <> null ==> and getEnv().getCellNature(getRow()  ,getCol() + 1) in {DNC}
	 * pre face = W and key <> null ==> and getEnv().getCellNature(getRow() ,getCol() - 1) in {DNC}
	
	 * post face@pre = N  ==> and getEnv().getCellNature(getRow() - 1 ,getCol()) in {DWO}
	 * post face@pre = S  ==> and getEnv().getCellNature(getRow() + 1 ,getCol()) in {DWO}
	 * post face@pre = E  ==> and getEnv().getCellNature(getRow()  ,getCol() + 1) in {DNO}
	 * post face@pre = W  ==> and getEnv().getCellNature(getRow() ,getCol() - 1) in {DNO}
	 * post key <> null
	 */
		public void openDoor();
		
	
		/**
		 * 
		 * pre face = N and key <> null ==> and getEnv().getCellNature(getRow() - 1 ,getCol()) in {DWO}
		 * pre face = S and key <> null ==> and getEnv().getCellNature(getRow() + 1 ,getCol()) in {DWO}
		 * pre face = E and key <> null ==> and getEnv().getCellNature(getRow()  ,getCol() + 1) in {DNO}
		 * pre face = W and key <> null ==> and getEnv().getCellNature(getRow() ,getCol() - 1) in {DNO}
		
		 * post face@pre = N  ==> and getEnv().getCellNature(getRow() - 1 ,getCol()) in {DWC}
		 * post face@pre = S  ==> and getEnv().getCellNature(getRow() + 1 ,getCol()) in {DWC}
		 * post face@pre = E  ==> and getEnv().getCellNature(getRow()  ,getCol() + 1) in {DNC}
		 * post face@pre = W  ==> and getEnv().getCellNature(getRow() ,getCol() - 1) in {DNC}
		 * key <> null
		 */	
		public void closeDoor();
		
	
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
