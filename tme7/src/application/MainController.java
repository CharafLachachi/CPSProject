package application;

import java.util.Arrays;
import java.util.List;

import components.Cow;
import components.Engine;
import components.Environement;
import components.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import services.Cell;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;

public class MainController {

	@FXML
	private GridPane mapGrid;

	private double l, c, lc, w, w0, h, h0;

	private EngineService labyrinthe = new Engine();
	private Polygon polygon;
	private Polygon polygonCow;

	private EntityService player;

	public void init() {
		polygon = new Polygon();
		polygon.setFill(Color.BLUE);

		polygonCow = new Polygon();
		polygonCow.setFill(Color.RED);

		EntityService cow = new Cow();
		player = new Player();
		EnvironnementService env = new Environement();
		env.init(25, 25);

		cow.init(env, 3, 3, Dir.S, 4);
		player.init(env, 0, 0, Dir.N);
		labyrinthe.init(env);
		labyrinthe.addEntity(player);
		labyrinthe.addEntity(cow);
		cow.step();

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
		for (int ll = 0; ll < 25; ll++)
			for (int cc = 0; cc < 25; cc++) {
				paintCase(ll, cc);
			}
		paintPlayer();
		paintCow();
		// lancer dans un thread le mouvement de la vache
//		Platform.runLater(() -> new Thread(() -> {
//			while (cow.getHp() > 0) {
//				cow.step();
//				paintCow();
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start());
	}

	public void paintCase(int col, int row) {
		Cell maCase = labyrinthe.getEnv().getCellNature(col, row);
		Rectangle rect = new Rectangle();
		rect.setHeight(25);
		rect.setWidth(25);

		if (maCase == Cell.WLL) {
			rect.setFill(Color.BLACK);

		}
		if (maCase == Cell.IN) {
			rect.setFill(Color.RED);
		}
		if (maCase == Cell.OUT) {
			rect.setFill(Color.GREEN);
		}
		if (maCase == Cell.EMP) {
			rect.setFill(Color.WHITE);
		}
		GridPane.setColumnIndex(rect, col);
		GridPane.setRowIndex(rect, row);
		mapGrid.getChildren().addAll(rect);
	}

	// methode pour dessiner le joueur au debut et a l'appel des fonction de direction
	private void paintPlayer() {
		EntityService voyageur = labyrinthe.getEntity(0);
		int vl = voyageur.getCol();
		int vc = voyageur.getRow();
		System.out.println("row " + vl);

		if (voyageur.getFace() == Dir.N) {
			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 4,
					h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + 3 * lc / 4);
			polygon.getPoints().clear();
			polygon.getPoints().addAll(list);
		}
		if (voyageur.getFace() == Dir.W) {
			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + 3 * lc / 4);
			polygon.getPoints().clear();
			polygon.getPoints().addAll(list);
		}
		if (voyageur.getFace() == Dir.S) {
			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 4,
					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + lc / 4);

			polygon.getPoints().clear();
			polygon.getPoints().addAll(list);
		}
		if (voyageur.getFace() == Dir.E) {
			List<Double> list = Arrays.asList(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
					h0 + vl * lc + 3 * lc / 4);
			polygon.getPoints().clear();
			polygon.getPoints().addAll(list);
		}

		GridPane.setColumnIndex(polygon, vc);
		GridPane.setRowIndex(polygon, vl);
		if (!mapGrid.getChildren().contains(polygon)) {
			mapGrid.getChildren().addAll(polygon);
		}

	}
	// methode pour dessiner le joueur au debut et a l'appel des fonction de direction
	private void paintCow() {
		EntityService voyageur = labyrinthe.getEntity(1);
		int vl = voyageur.getCol();
		int vc = voyageur.getRow();
		System.out.println("row " + vl);

		if (voyageur.getFace() == Dir.N) {
			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 4,
					h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + 3 * lc / 4);
			polygonCow.getPoints().clear();
			polygonCow.getPoints().addAll(list);
		}
		if (voyageur.getFace() == Dir.W) {
			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + 3 * lc / 4);
			polygonCow.getPoints().clear();
			polygonCow.getPoints().addAll(list);
		}
		if (voyageur.getFace() == Dir.S) {
			List<Double> list = Arrays.asList(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4, w0 + vc * lc + lc / 4,
					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + 3 * lc / 4,
					h0 + vl * lc + lc / 4);

			polygonCow.getPoints().clear();
			polygonCow.getPoints().addAll(list);
		}
		if (voyageur.getFace() == Dir.E) {
			List<Double> list = Arrays.asList(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
					h0 + vl * lc + lc / 4, w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2, w0 + vc * lc + lc / 4,
					h0 + vl * lc + 3 * lc / 4);
			polygonCow.getPoints().clear();
			polygonCow.getPoints().addAll(list);
		}

		GridPane.setColumnIndex(polygonCow, vc);
		GridPane.setRowIndex(polygonCow, vl);
		if (!mapGrid.getChildren().contains(polygonCow)) {
			mapGrid.getChildren().addAll(polygonCow);
		}
	}

	//Listner pour les direction sur le clavier permettant le deplcaement du joueur
	public void movePlayer(KeyEvent e) {
		if (e.getCode().equals(KeyCode.UP)) {
			System.out.println("je suis dans up");
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

}
