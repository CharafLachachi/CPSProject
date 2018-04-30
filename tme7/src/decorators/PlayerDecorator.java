package decorators;

import java.util.Optional;

import services.Cell;
import services.Command;
import services.Dir;
import services.EnvironnementService;
import services.MobService;
import services.PlayerService;

public class PlayerDecorator implements PlayerService{

	private PlayerService delegate;

	

	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}
	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return delegate.getHp();
	}

	@Override
	public void init(EnvironnementService envi, int x, int y, Dir face, int h) {
		// TODO Auto-generated method stub
		delegate.init(envi, x, y, face, h);
		
	}

	@Override
	public EnvironnementService getEnv() {
		// TODO Auto-generated method stub
		return delegate.getEnv();
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return delegate.getCol();
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return delegate.getRow();
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return delegate.getFace();
	}

	@Override
	public void init(EnvironnementService env, int x, int y, Dir d) {
		// TODO Auto-generated method stub
		delegate.init(env, x, y, d);
	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub
		delegate.forward();
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		delegate.backward();
	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub
		delegate.turnL();
	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub
		delegate.turnR();
	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub
		delegate.strafeL();
	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub
		delegate.strafeR();
	}

	@Override
	public Optional<Command> lastCom() {
		// TODO Auto-generated method stub
		return delegate.lastCom();
	}

	@Override
	public Optional<MobService> content(int x, int y) {
		// TODO Auto-generated method stub
		return delegate.content(x, y);
	}

	@Override
	public Cell nature(int x, int y) {
		// TODO Auto-generated method stub
		return delegate.nature(x, y);
	}

	@Override
	public boolean viewable(int x, int y) {
		// TODO Auto-generated method stub
		return delegate.viewable(x, y);
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		delegate.step();
	}

}
