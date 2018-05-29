package test;

import components.Cow;
import contracts.CowContract;
import services.CowService;

public class TestCow extends AbstarctCowTest{

	@Override
	public void beforeTests() {
		CowService cow = new Cow();
		CowService contract = new CowContract(cow);
		setCow(contract);
	}

}
