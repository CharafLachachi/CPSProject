package application;

import java.util.Arrays;
import java.util.List;

import components.Cow;
import components.Engine;
import components.Environement;
import components.Key;
import components.Player;
import contracts.CowContract;
import contracts.EngineContract;
import contracts.EnvironnementContract;
import contracts.PlayerContract;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import services.Cell;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;
import services.KeyService;

public class MainController {

	@FXML
	private GridPane mapGrid;

	private double l, c, lc, w, w0, h, h0;

	private EngineService labyrinthe = new EngineContract(new Engine());
	private Polygon polygon;
	private Polygon polygonCow;
	private Image key;
	private Image playerImg;
	private ImageView playerView;
	private Image cowImg;
	private ImageView iv;
	private EntityService player;

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
		playerView = new ImageView();
		cowImg = new Image(getClass().getResource("images/monsterUP.png").toExternalForm());
	    iv = new ImageView(cowImg);
		labyrinthe.init(env);
		cow.init(env, 3, 3, Dir.S, 4);
		player.init(env, 0, 0, Dir.N);
		
		labyrinthe.addEntity(player);
		labyrinthe.addEntity(cow);
		cow.step();

		KeyService keyService = new Key();
		
		// les valeurs son misent dans init de Key
		keyService.init(env, 0, 0, 0, 0);

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
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start());
		// afficher une cle
		paintKey(keyService.getRow(), keyService.getRow());
	}

	public void paintCase(int col, int row) {
		Cell maCase = labyrinthe.getEnv().getCellNature(col, row);
		Rectangle rect = new Rectangle();
		rect.setHeight(57);
		rect.setWidth(57);

		if (maCase == Cell.WLL) {
//			rect.setFill(Color.BLACK);
//			GridPane.setColumnIndex(rect, row);
//			GridPane.setRowIndex(rect, col);
//			mapGrid.getChildren().addAll(rect);
			Image wall = new Image(getClass().getResource("images/wall1.png").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);

		}
		if (maCase == Cell.IN) {
			rect.setFill(Color.GRAY);
			GridPane.setColumnIndex(rect, row);
			GridPane.setRowIndex(rect, col);
			mapGrid.getChildren().addAll(rect);
		}
		if (maCase == Cell.OUT) {
//			rect.setFill(Color.GREEN);
//			GridPane.setColumnIndex(rect, row);
//			GridPane.setRowIndex(rect, col);
//			mapGrid.getChildren().addAll(rect);
			Image wall = new Image(getClass().getResource("images/finish.png").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.EMP) {
//			rect.setFill(Color.WHITE);
//			GridPane.setColumnIndex(rect, row);
//			GridPane.setRowIndex(rect, col);
//			mapGrid.getChildren().addAll(rect);
			
			Image wall = new Image(getClass().getResource("images/grass.png").toExternalForm());
		    ImageView iv = new ImageView(wall);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DNO) {
			Line door = new Line(0.0f,0.0f,57.0f,57.0f);
			door.setStroke(Color.BLACK);
			door.setStrokeWidth(6);
			GridPane.setColumnIndex(door, row);
			GridPane.setRowIndex(door, col);
			mapGrid.getChildren().addAll(door);
		}
		if (maCase == Cell.DWO) {
			  Image door = new Image(getClass().getResource("images/doorDWO.png").toExternalForm());
			    ImageView iv = new ImageView(door);
			    GridPane.setColumnIndex(iv, row);
				GridPane.setRowIndex(iv, col);
				mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DWC) {
//			Line door = new Line(0.0f,0.0f,25.0f,0.0f);
//			door.setStroke(Color.AQUA);
			
		    Image door = new Image(getClass().getResource("images/door.png").toExternalForm());
		    ImageView iv = new ImageView(door);
		    GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DNC) {
			Line door = new Line(0.0f,0.0f,0.0f,57.0f);
			door.setStroke(Color.BLACK);
			door.setStrokeWidth(6);
			GridPane.setColumnIndex(door, row);
			GridPane.setRowIndex(door, col);
			mapGrid.getChildren().addAll(door);
		}
		
	}

	// methode pour dessiner le joueur au debut et a l'appel des fonction de direction
	private void paintPlayer() {
		EntityService voyageur = labyrinthe.getEntity(0);
		int vl = voyageur.getRow();
		int vc = voyageur.getCol();
	//	System.out.println("row " + vl);

		if (voyageur.getFace() == Dir.N) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + 3 * lc / 4);
//			polygon.getPoints().clear();
//			polygon.getPoints().addAll(list);
			AnimatedImage ufo = new AnimatedImage();
			Image[] imageArray = new Image[3];
			for (int i = 0; i < 3; i++)
			    imageArray[i] = new Image(getClass().getResource("images/up" + i + ".png").toExternalForm());
			ufo.frames = imageArray;
			ufo.duration = 2000;
			playerView.setImage(ufo.getFrame(1));
			
//			playerView.setImage(new Image(getClass().getResource("images/up0.png").toExternalForm()));
//			playerView.setImage(new Image(getClass().getResource("images/up1.png").toExternalForm()));
//			playerView.setImage(new Image(getClass().getResource("images/up2.png").toExternalForm()));

		}
		if (voyageur.getFace() == Dir.W) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + 3 * lc / 4);
//			polygon.getPoints().clear();
//			polygon.getPoints().addAll(list);
			playerView.setImage(new Image(getClass().getResource("images/left1.png").toExternalForm()));
			playerView.setImage(new Image(getClass().getResource("images/left1.png").toExternalForm()));
			playerView.setImage(new Image(getClass().getResource("images/left2.png").toExternalForm()));
		}
		if (voyageur.getFace() == Dir.S) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + lc / 4);
//
//			polygon.getPoints().clear();
//			polygon.getPoints().addAll(list);
			playerView.setImage(new Image(getClass().getResource("images/down0.png").toExternalForm()));
			playerView.setImage(new Image(getClass().getResource("images/down1.png").toExternalForm()));
			playerView.setImage(new Image(getClass().getResource("images/down2.png").toExternalForm()));

		}
		if (voyageur.getFace() == Dir.E) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + 3 * lc / 4);
//			polygon.getPoints().clear();
//			polygon.getPoints().addAll(list);
			playerView.setImage(new Image(getClass().getResource("images/right0.png").toExternalForm()));
			playerView.setImage(new Image(getClass().getResource("images/right1.png").toExternalForm()));
			playerView.setImage(new Image(getClass().getResource("images/right2.png").toExternalForm()));

		}

//		GridPane.setColumnIndex(polygon, vc);
//		GridPane.setRowIndex(polygon, vl);
//		if (!mapGrid.getChildren().contains(polygon)) {
//			mapGrid.getChildren().addAll(polygon);
//		}
		GridPane.setColumnIndex(playerView, vc);
		GridPane.setRowIndex(playerView, vl);
		if (!mapGrid.getChildren().contains(playerView)) {
			mapGrid.getChildren().addAll(playerView);
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
//			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + 3 * lc / 4);
//			polygonCow.getPoints().clear();
//			polygonCow.getPoints().addAll(list);
				iv.setImage(new Image(getClass().getResource("images/monsterUP.png").toExternalForm()));
			}
			if (voyageur.getFace() == Dir.W) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + 3 * lc / 4);
//			polygonCow.getPoints().clear();
//			polygonCow.getPoints().addAll(list);
				iv.setImage(new Image(getClass().getResource("images/monsterLeft.png").toExternalForm()));

			}
			if (voyageur.getFace() == Dir.S) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
//					h0 + vl * lc + lc / 4);
//
//			polygonCow.getPoints().clear();
//			polygonCow.getPoints().addAll(list);
				iv.setImage(new Image(getClass().getResource("images/monsterDown.png").toExternalForm()));

			}
			if (voyageur.getFace() == Dir.E) {
//			List<Double> list = Arrays.asList(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
//					h0 + vl * lc + 3 * lc / 4);
//			polygonCow.getPoints().clear();
//			polygonCow.getPoints().addAll(list);
				iv.setImage(new Image(getClass().getResource("images/monsterRight.png").toExternalForm()));

			}

//		GridPane.setColumnIndex(polygonCow, vc);
//		GridPane.setRowIndex(polygonCow, vl);
//		if (!mapGrid.getChildren().contains(polygonCow)) {
//			mapGrid.getChildren().addAll(polygonCow);
//		}
			    GridPane.setColumnIndex(iv, vc);
				GridPane.setRowIndex(iv, vl);
				if (!mapGrid.getChildren().contains(iv)) {
				mapGrid.getChildren().addAll(iv);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Listner pour les direction sur le clavier permettant le deplcaement du joueur
	public void movePlayer(KeyEvent e) {
		if (e.getCode().equals(KeyCode.UP)) {
		//	System.out.println("je suis dans up");
			labyrinthe.getEntity(0).forward();
			paintPlayer();

		}
		if (e.getCode().equals(KeyCode.DOWN)) {
			labyrinthe.getEntity(0).backward();
			paintPlayer();

		}
		if (e.getCode().equals(KeyCode.LEFT)) {
			labyrinthe.getEntity(0).turnL();
			// polygon.setRotate(polygon.getRotate()-90);
			paintPlayer();

		}
		if (e.getCode().equals(KeyCode.RIGHT)) {
			labyrinthe.getEntity(0).turnR();
			// polygon.setRotate(polygon.getRotate()+90);
			paintPlayer();

		}

	}
	public void paintKey(int row, int col) {
		ImageView keyView = new ImageView(key);
		GridPane.setColumnIndex(keyView, row);
		GridPane.setRowIndex(keyView, col);
		mapGrid.getChildren().addAll(keyView);
	}

}
