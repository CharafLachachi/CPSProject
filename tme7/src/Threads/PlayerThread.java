package Threads;

import java.util.Optional;

import javax.swing.JOptionPane;

import application.AnimatedImage;
import components.Player;
import contracts.PlayerContract;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import services.Command;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.PlayerService;
import services.RessourcesService;

public class PlayerThread extends Task{

	private PlayerService player;
	private AnimatedImage playerImg;
	
	@FXML
	private GridPane mapGrid;
	private EngineService labyrinthe;
	
	public PlayerThread(GridPane mapGrid, EngineService engine) {
		this.player = new PlayerContract(new Player());
		this.labyrinthe = engine;
		this.mapGrid = mapGrid;
		playerImg = new AnimatedImage(new Image("/application/images/heros1.png"), 3, 3, 0, 48, 35, 48);
		
	//	System.out.println("Player Position : " + player.getRow() + ", " + player.getCol());
	}
	
	
	public void init()
	{
		player.init(labyrinthe.getEnv(), 0, 0, Dir.N, 100);
		labyrinthe.addEntity(player);
	//	paintPlayer();
	//	playerImg.play();
		
	}
	
	
	public void paintPlayer() {
		EntityService voyageur = labyrinthe.getEntity(0);
		int vl = voyageur.getRow();
		int vc = voyageur.getCol();
		// System.out.println("row " + vl + ", col " + vc);

		if (voyageur.getFace() == Dir.N) {
			playerImg.setOffsetY(0);
		}
		if (voyageur.getFace() == Dir.W) {
			playerImg.setOffsetY(48 * 3);

		}
		if (voyageur.getFace() == Dir.S) {
			playerImg.setOffsetY(48 * 2);

		}
		if (voyageur.getFace() == Dir.E) {
			playerImg.setOffsetY(48);
		}

		GridPane.setColumnIndex(playerImg.getImageView(), vc);
		GridPane.setRowIndex(playerImg.getImageView(), vl);
		if (!mapGrid.getChildren().contains(playerImg.getImageView())) {
			mapGrid.getChildren().addAll(playerImg.getImageView());
		}

	}
	
	// Listner pour les direction sur le clavier permettant le deplcaement du joueur
		public void movePlayer(KeyEvent e) {

			switch (e.getCode()) {
			case UP:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.FF);
				break;

			case DOWN:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.BB);
	
				break;
			case LEFT:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.TL);
				break;
			case RIGHT:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.TR);
				break;
			case Q:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.LL);
				break;
			case S:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.RR);
				break;
			case O:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.OPEN);
				break;
			case C:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.CLOSE);
				break;
			case A:
				((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.C);
				break;
			default:
				break;
			}

			player.step();


		}

	
		public void removeRessourcefromMap(RessourcesService src, ImageView img)
		{
			if (labyrinthe.getEnv().getCellRessources(src.getRow(), src.getCol())
					.equals(Optional.empty())) {
				mapGrid.getChildren().removeAll(img);
			}
		}

		public void stopPlayer() {
			playerImg.stop();
		}
		
		@Override
		protected Object call() throws Exception {
			
			while(player.isDead() == false && player.isWin() == false)
			{
				paintPlayer();
				playerImg.play();
				Thread.sleep(100);
			}
			paintPlayer();
			playerImg.play();
			
			if(player.isWin() == true)
				JOptionPane.showMessageDialog(null, "****** WINNER WINNER CHICKEN DINNER *****");
			if(player.isDead() == true)
				JOptionPane.showMessageDialog(null, "****** SORRY YOU'R DEAD :,(  *****");
			return true;
		}
	
}
