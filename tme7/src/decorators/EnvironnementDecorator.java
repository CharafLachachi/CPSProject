package decorators;

import java.util.Optional;

import services.EnvironnementService;
import services.MapService;
import services.MobService;

public class EnvironnementDecorator extends /* include */ MapDecorateur implements EnvironnementService {

	public EnvironnementDecorator(MapService delegate) {
		super(delegate);
	}

	@Override
	protected EnvironnementService getDelegate() {
		return (EnvironnementService) super.getDelegate();
	}

	@Override
	public Optional<MobService> getCellContent(int x, int y) {
		return getDelegate().getCellContent(x, y);
	}

	@Override
	public MobService getMob(int x, int y) {
		return getDelegate().getMob(x, y);
	}

	@Override
	public void addMob(MobService mob) {
		getDelegate().addMob(mob);
	}

}
