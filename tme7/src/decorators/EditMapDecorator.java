package decorators;

import services.Cell;
import services.EditMapService;

public class EditMapDecorator extends MapDecorateur implements EditMapService {

	public EditMapDecorator(EditMapService delegate) {
		super(delegate);
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return getDelegate().isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		return getDelegate().isReady();
	}

	@Override
	public void setNature(int x, int y, Cell Na) {
		getDelegate().setNature(x, y, Na);
	}

	@Override
	protected EditMapService getDelegate() {
		return (EditMapService) super.getDelegate();
	}
}
