package decorators;

import java.util.Optional;

import services.Cell;
import services.Command;
import services.Dir;
import services.EnvironnementService;
import services.KeyService;
import services.MobService;
import services.PlayerService;
import services.RessourcesService;

public class PlayerDecorator implements PlayerService{

	private PlayerService delegate;

	

	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}
	@Override
	public int getHp() {
		
		return delegate.getHp();
	}

	@Override
	public void init(EnvironnementService envi, int x, int y, Dir face, int h) {
		
		delegate.init(envi, x, y, face, h);
		
	}

	@Override
	public EnvironnementService getEnv() {
		
		return delegate.getEnv();
	}

	@Override
	public int getCol() {
		
		return delegate.getCol();
	}

	@Override
	public int getRow() {
		
		return delegate.getRow();
	}

	@Override
	public Dir getFace() {
		
		return delegate.getFace();
	}

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d) {
		
		delegate.init(env, x, y, d);
	}

	@Override
	public void forward() {
		
		delegate.forward();
	}

	@Override
	public void backward() {
		
		delegate.backward();
	}

	@Override
	public void turnL() {
		
		delegate.turnL();
	}

	@Override
	public void turnR() {
		
		delegate.turnR();
	}

	@Override
	public void strafeL() {
		
		delegate.strafeL();
	}

	@Override
	public void strafeR() {
		
		delegate.strafeR();
	}

	@Override
	public Optional<Command> lastCom() {
		
		return delegate.lastCom();
	}

	@Override
	public Optional<MobService> content(int x, int y) {
		
		return delegate.content(x, y);
	}

	@Override
	public Cell nature(int x, int y) {
		
		return delegate.nature(x, y);
	}

	@Override
	public boolean viewable(int x, int y) {
		
		return delegate.viewable(x, y);
	}

	@Override
	public void step() {
		
		delegate.step();
	}
	@Override
	public void attack() {
		delegate.attack();
		
	}
	@Override
	public void setCommand(Command c) {
		delegate.setCommand(c);
		
	}
	@Override
	public void setKey(KeyService key) {
		delegate.setKey(key);
		
	}
	@Override
	public KeyService getKey() {
		// TODO Auto-generated method stub
		return delegate.getKey();
	}
	@Override
	public void openDoor() {
		delegate.openDoor();
		
	}
	@Override
	public void closeDoor() {
		// TODO Auto-generated method stub
		delegate.closeDoor();
	}
	@Override
	public void setHp(int hp) {
		delegate.setHp(hp);
		
	}
	@Override
	public RessourcesService getRessources() {
		return delegate.getRessources();
	}
	@Override
	public void setRessources(RessourcesService gold) {
		delegate.setRessources(gold);
	}
	@Override
	public boolean isWin() {
		return delegate.isWin();
	}
	@Override
	public boolean isDead() {
		return delegate.isDead();
	}

}
