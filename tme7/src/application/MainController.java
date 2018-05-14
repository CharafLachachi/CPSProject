package application;


import java.util.Optional;

import components.Cow;
import components.Engine;
import components.Environement;
import components.Key;
import components.Player;
import contracts.EngineContract;
import contracts.EnvironnementContract;
import contracts.PlayerContract;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import services.Cell;
import services.Command;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;
import services.KeyService;
import services.PlayerService;


public class MainController {

	@FXML
	private GridPane mapGrid;

	private double l, c, lc, w, w0, h, h0;

	private EngineService labyrinthe = new EngineContract(new Engine());
	private Polygon polygon;
	private Polygon polygonCow;
	private Image key;
	private EntityService player;
	private AnimatedImage comImg;
	private AnimatedImage playerImg;
	
	
    
	public void init() {
		polygon = new Polygon();
		polygon.setFill(Color.BLUE);

		polygonCow = new Polygon();
		polygonCow.setFill(Color.RED);

		//EntityService cow = new CowContract(new Cow());
		EntityService cow = new Cow();
		player = new PlayerContract(new Player());
		EnvironnementService env = new EnvironnementContract(new  Environement());
		env.init(15, 15);
		
		key = new Image(getClass().getResource("images/key.png").toExternalForm());
		labyrinthe.init(env);
		cow.init(env, 3, 3, Dir.S, 4);
		player.init(env, 0, 0, Dir.N);
		
		labyrinthe.addEntity(player);
		labyrinthe.addEntity(cow);
		cow.step();
		
		playerImg = new AnimatedImage( new Image("/application/images/heros1.png"), 3, 3, 0, 48, 35, 48);
		comImg = new AnimatedImage(new Image("/application/images/test.png"), 3, 3, 12, 58, 55, 58);
		KeyService keyService = new Key();
		
		// les valeurs son misent dans init de Key
		keyService.init(env, 0, 0);

		l = 1;
		c = 1;
		Insets in = mapGrid.getInsets();
		w = labyrinthe.getEnv().getWidth();
		h = labyrinthe.getEnv().getHeight();
		double lw = (w - in.getLeft() - in.getRight() - 6) / c;
		double lh = (h - in.getTop() - in.getBottom() - 6) / l;
		lc = (lw < lh ? lw : lh);
		w0 = (w - c * lc) / 2;
		h0 = (h - l * lc) / 2;
		for (int ll = 0; ll < 15; ll++)
			for (int cc = 0; cc < 15; cc++) {
				paintCase(ll, cc);
			}
		paintPlayer();
		paintCow();
		// lancer dans un thread le mouvement de la vache
		Platform.runLater(() -> new Thread(() -> {
			while (cow.getHp() > 0) {
				cow.step();
				paintCow();
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start());
		// afficher une cle
		paintKey(keyService.getRow(), keyService.getCol());
	}

	public void paintCase(int col, int row) {
		Cell maCase = labyrinthe.getEnv().getCellNature(col, row);
		Rectangle rect = new Rectangle();
		rect.setHeight(61);
		rect.setWidth(61);

		if (maCase == Cell.WLL) {
			Image wall = new Image(getClass().getResource("images/wall1.png").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);

		}
		if (maCase == Cell.IN) {
//			rect.setFill(Color.GRAY);
//			GridPane.setColumnIndex(rect, row);
//			GridPane.setRowIndex(rect, col);
//			mapGrid.getChildren().addAll(rect);
			Image wall = new Image(getClass().getResource("images/start.jpg").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.OUT) {
			Image wall = new Image(getClass().getResource("images/finish.png").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.EMP) {
			Image wall = new Image(getClass().getResource("images/grass.png").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DNO) {
			System.out.println("DNO");

			Image door = new Image(getClass().getResource("images/doorDNO.png").toExternalForm());
		    ImageView iv = new ImageView(door);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DWO) {
				Image door = new Image(getClass().getResource("images/doorDWO.png").toExternalForm());
			    ImageView iv = new ImageView(door);
			    GridPane.setColumnIndex(iv, row);
				GridPane.setRowIndex(iv, col);
				mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DWC) {
		    Image door = new Image(getClass().getResource("images/door.png").toExternalForm());
		    ImageView iv = new ImageView(door);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DNC) {
			Image door = new Image(getClass().getResource("images/doorDNC.png").toExternalForm());
		    ImageView iv = new ImageView(door);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		
	}

	// methode pour dessiner le joueur au debut et a l'appel des fonction de direction
	private void paintPlayer() {
		EntityService voyageur = labyrinthe.getEntity(0);
		int vl = voyageur.getRow();
		int vc = voyageur.getCol();
	//	System.out.println("row " + vl);

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
	// methode pour dessiner le joueur au debut et a l'appel des fonction de direction
	private void paintCow() {
		EntityService voyageur = labyrinthe.getEntity(1);
		int vl = voyageur.getRow();
		int vc = voyageur.getCol();
	//	System.out.println("row " + vl);

		try {
			if (voyageur.getFace() == Dir.N) {
                comImg.play();

			}
			if (voyageur.getFace() == Dir.W) {
                comImg.play();

			}
			if (voyageur.getFace() == Dir.S) {
                comImg.play();

			}
			if (voyageur.getFace() == Dir.E) {
                comImg.setOffsetY(58 * 2);
                comImg.play();

			}

				GridPane.setRowIndex(comImg.getImageView(), vl);
				if (!mapGrid.getChildren().contains(comImg.getImageView())) {
				mapGrid.getChildren().addAll(comImg.getImageView());
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Listner pour les direction sur le clavier permettant le deplcaement du joueur
	public void movePlayer(KeyEvent e) {
		
		switch(e.getCode())
		{
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
		case A : 
			((PlayerService) labyrinthe.getEntity(0)).setCommand(Command.C);
		default:
			break;
		}
		
		player.step();
		paintPlayer();
		playerImg.play();
		
		/**
		if (e.getCode().equals(KeyCode.UP)) {
		//	System.out.println("je suis dans up");
			
			labyrinthe.getEntity(0).forward();
			paintPlayer();
			playerImg.play();

		}
		if (e.getCode().equals(KeyCode.DOWN)) {
			
			labyrinthe.getEntity(0).backward();
			paintPlayer();
			playerImg.play();

		}
		if (e.getCode().equals(KeyCode.LEFT)) {
			
			labyrinthe.getEntity(0).turnL();
			// polygon.setRotate(polygon.getRotate()-90);
			paintPlayer();
			playerImg.play();

		}
		if (e.getCode().equals(KeyCode.RIGHT)) {
			
			 labyrinthe.getEntity(0).turnR();
			// polygon.setRotate(polygon.getRotate()+90);
			 paintPlayer();
			 playerImg.play();
		}
		*/
		
	}
	
	public void stopPlayer() {
		playerImg.stop();
	}
	public void paintKey(int row, int col) {
		ImageView keyView = new ImageView(key);
		if(!labyrinthe.getEnv().getCellRessources(row, col).equals(Optional.empty()))
		{
			GridPane.setColumnIndex(keyView, col);
			GridPane.setRowIndex(keyView, row);
			mapGrid.getChildren().addAll(keyView);
		}
		else
		{	
			mapGrid.getChildren().removeAll(keyView);
		}
		
	}

}
