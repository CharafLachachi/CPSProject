package application;

import components.Cow;
import components.Engine;
import components.Environement;
import components.Player;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
	private Circle p;
	private EntityService player;

	public void init() {
		p = new Circle(12.5);
		EntityService cow = new Cow();
		player = new Player();
		EnvironnementService env = new Environement();
		env.init(500, 500);

		// cow.init(env, 3, 3, Dir.N, 4);
		player.init(env, 0, 0, Dir.S);
		labyrinthe.init(env);
		labyrinthe.addEntity(player);
		// labyrinthe.addEntity(cow);
		// cow.step();

		l = 25;
		c = 25;
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
		// paintCow();
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

	private void paintPlayer() {
		EntityService voyageur = labyrinthe.getEntity(0);
		int vl = voyageur.getCol();
		int vc = voyageur.getRow();
		
		System.out.println("row "+vl);
	//	if (voyageur.getFace() == Dir.N) {

//			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4);
//			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
//			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
//			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
	//	}
//		if (voyageur.getFace() == Dir.W) {
//			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2);
//			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
//			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
//			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
//		}
//		if (voyageur.getFace() == Dir.S) {
//			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4);
//			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
//			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
//			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
//		}
//		if (voyageur.getFace() == Dir.E) {
//			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2);
//			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
//			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
//			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
//		}

		p.setFill(Color.BLUE);
		GridPane.setColumnIndex(p, vc);
		GridPane.setRowIndex(p, vl);
		if (!mapGrid.getChildren().contains(p)) {
			mapGrid.getChildren().addAll(p);
		}

	}

	private void paintCow() {
		EntityService voyageur = labyrinthe.getEntity(1);
		int vl = voyageur.getCol();
		int vc = voyageur.getRow();
		Polygon p = new Polygon();

		if (voyageur.getFace() == Dir.N) {

			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4);
			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		if (voyageur.getFace() == Dir.W) {
			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2);
			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		if (voyageur.getFace() == Dir.S) {
			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4);
			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
		}
		if (voyageur.getFace() == Dir.E) {
			p.getPoints().addAll(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2);
			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
			p.getPoints().addAll(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.getPoints().addAll(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		p.setFill(Color.RED);
		GridPane.setColumnIndex(p, 10);
		GridPane.setRowIndex(p, 10);
		mapGrid.getChildren().addAll(p);
		// g.setColor(PLAYER);
		// g.fillPolygon(p);
	}

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
			paintPlayer();

		}
		if (e.getCode().equals(KeyCode.RIGHT)) {
			labyrinthe.getEntity(0).turnR();
			paintPlayer();

		}

	}

}
