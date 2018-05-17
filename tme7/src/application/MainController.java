package application;



import java.util.concurrent.ExecutorService;

import Threads.CowThread;

import Threads.PlayerThread;
import Threads.RessourcesThread;
import components.Engine;
import components.Environement;
import components.Key;

import components.Ressources;
import contracts.EngineContract;
import contracts.EnvironnementContract;
import contracts.KeyContract;
import contracts.RessourcesContract;
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
import services.EngineService;
import services.EnvironnementService;
import services.KeyService;
import services.RessourcesService;

public class MainController {

	@FXML
	private GridPane mapGrid;

	private double l, c, lc, w, w0, h, h0;

	private EngineService labyrinthe = new EngineContract(new Engine());
	private Polygon polygon;
	private Polygon polygonCow;
	private Image key;
	private Image gold;

	private ImageView keyView;
	private ImageView goldView;
	private KeyService keyService;
	private RessourcesService goldService;
	
	private PlayerThread playerThread;	
	private CowThread cowThread;
//	private CowThread cowThread1;
	
	private RessourcesThread keyThread;
	private RessourcesThread goldThread;
	
	public void init() {
		polygon = new Polygon();
		polygon.setFill(Color.BLUE);
		polygonCow = new Polygon();
		polygonCow.setFill(Color.RED);

		
		EnvironnementService env = new EnvironnementContract(new Environement());
		env.init(15, 15);

		key = new Image(getClass().getResource("images/key.png").toExternalForm());
		gold = new Image(getClass().getResource("images/Gold_Ore.png").toExternalForm());
		
		// afficher une cle
		keyView = new ImageView(key);
		goldView = new ImageView(gold);
		
		labyrinthe.init(env);
		
		
		playerThread = new PlayerThread(mapGrid, labyrinthe);
		cowThread = new CowThread(mapGrid, labyrinthe);
	//	cowThread1 = new CowThread(mapGrid, labyrinthe);
		
		keyService = new KeyContract(new Key());
		goldService = new RessourcesContract(new Ressources());
			
		keyThread = new RessourcesThread(keyService, keyView, mapGrid, labyrinthe);
		goldThread = new RessourcesThread(goldService, goldView, mapGrid, labyrinthe);

	   	paintAllCase();
			
		keyThread.init();
		goldThread.init();
		playerThread.init();
		cowThread.init();
	//	cowThread1.init();
		
		
		
		new Thread(playerThread).start();
		
		new Thread(cowThread).start();
		
		
		new Thread(keyThread).start();
		new Thread(goldThread).start();
		
//		new Thread(cowThread1).start();
	}

	public void paintAllCase() {
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

	

	

	// Listner pour les direction sur le clavier permettant le deplcaement du joueur
	public void movePlayer(KeyEvent e) {

		playerThread.movePlayer(e);
		
	}

	
	
	
	public void stopPlayer() {
	  playerThread.stopPlayer();
	}

	
	
	public void paintGold(int row, int col)
	{
		GridPane.setColumnIndex(goldView, col);
		GridPane.setRowIndex(goldView, row);
		mapGrid.getChildren().addAll(goldView);
	}

}
