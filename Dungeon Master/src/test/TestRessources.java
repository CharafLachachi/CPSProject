package test;

import components.Ressources;
import contracts.RessourcesContract;
import services.RessourcesService;

public class TestRessources extends AbstarctRessourcesTest{

	@Override
	public void beforeTests() {
		RessourcesService ressource = new Ressources();
		RessourcesService contract = new RessourcesContract(ressource);
		setRessources(contract);
	}

}
