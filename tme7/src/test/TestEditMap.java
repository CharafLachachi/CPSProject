package test;

import components.EditMap;
import contracts.EditMapContract;
import services.EditMapService;

public class TestEditMap extends AbstractEditMapTest{

	
	@Override
	public void beforeTests() {
		EditMapService map = new EditMap();
		EditMapService mapContrat = new EditMapContract(map);
		setMap(mapContrat);	
	}

}
