package services;

public interface EditMapService extends /*refine*/ MapService{

	/**
	 * Observateurs
	 * */
	
	//pre getCellNature(x1,y1) != WLL and getCellNature(x2,y2) != WLL
	public boolean isReachable(int x1,int y1, int x2, int y2);
	
	public boolean isReady();
	
	/**
	 * Operateurs
	 * */
	//pre  0 <= x < getWidth() and 0 <= y < getHeight()
	//post getCellNature(x,y) = Na
	//post \ForAll u,v in int² , {u != x or v != y} implies getCellNature(u,v) = getCellNature(u,v) 
	public void setNature(int x, int y, Cell Na);
	
	/**
	 * Invariants
	 * */
	// inv isReachable(x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
	//		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u-s)² + (v-t)² = 1
	//		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies getCellNature(u,v) != WLL
	// inv		isReady(M) = exists xi,yi,xo,yo in int�,
	//		getCellNature(xi,yi) = IN and getCellNature(xi,yi) = OUT
	//		and isReachable(M,xi,yi,xo,yo)
	//		and forall x,y in int², x != xi or y != yi implies getCellNature(x,y) != IN
	//		and forall x,y in int², x != xo or y != yo implies getCellNature(x,y) != OUT
	//			forall x,y in int, getCellNature(x,y) in {DNO, DNC} implies
	//			getCellNature(x+1,y) = getCellNature(x-1,y) = EMP and
	//			getCellNature(x,y-1) = getCellNature(x,y+1) = WLL
	//		forall x,y in int, getCellNature(x,y) in{ DWO, DWC} implies
	//			getCellNature(x+1,y) = getCellNature(x-1,y) = WLL and
	//			getCellNature(x,y-1) = getCellNature(x,y+1) = EMP
}
