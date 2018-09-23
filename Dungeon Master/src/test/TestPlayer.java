package test;

import components.Player;
import contracts.PlayerContract;
import services.PlayerService;

public class TestPlayer extends AbstarctPlayerTest{

	@Override
	public void beforeTests() {
		PlayerService playerService = new Player();
		PlayerService playerContarct = new PlayerContract(playerService);
		setPlayer(playerContarct);
		
	}

}
