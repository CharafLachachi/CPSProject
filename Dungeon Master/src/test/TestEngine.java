package test;

import components.Engine;
import contracts.EngineContract;
import services.EngineService;

public class TestEngine extends AbstarctEngineTest{

	@Override
	public void beforeTests() {
		EngineService cow = new Engine();
		EngineService contract = new EngineContract(cow);
		setEngine(contract);
	}

}
