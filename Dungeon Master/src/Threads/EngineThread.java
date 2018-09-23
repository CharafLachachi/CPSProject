package Threads;

import components.Environement;
import contracts.EnvironnementContract;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import services.Cell;
import services.EngineService;
import services.EnvironnementService;

public class EngineThread extends Task{

	
	@FXML
	private GridPane mapGrid;
	private EngineService labyrinthe;
	private PlayerThread player;
	private CowThread cow;
	
	private EnvironnementService env;
	
	private double l, c, lc, w, w0, h, h0;
	
	
	public EngineThread(GridPane mapGrid, EngineService labyrinthe, PlayerThread player, CowThread cow) {
		this.mapGrid = mapGrid;
		this.labyrinthe = labyrinthe;
		this.player = player;
		this.cow = cow;
		
		this.env = new EnvironnementContract(new Environement());
	}
	
	public void init()
	{
		
		env.init(15, 15);
		labyrinthe.init(env);
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
		
		player.paintPlayer();

	}
	
	public void paintCase(int col, int row) {
		Cell maCase = labyrinthe.getEnv().getCellNature(col, row);
		Rectangle rect = new Rectangle();
		rect.setHeight(61);
		rect.setWidth(61);

		if (maCase == Cell.WLL) {
			Image wall = new Image(getClass().getResource("/application/images/wall1.png").toExternalForm());
			ImageView iv = new ImageView(wall);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);

		}
		if (maCase == Cell.IN) {
			
			Image wall = new Image(getClass().getResource("/application/images/start.jpg").toExternalForm());
			ImageView iv = new ImageView(wall);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.OUT) {
			Image wall = new Image(getClass().getResource("/application/images/finish.png").toExternalForm());
			ImageView iv = new ImageView(wall);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.EMP) {
			Image wall = new Image(getClass().getResource("/application/images/grass.png").toExternalForm());
			ImageView iv = new ImageView(wall);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DNO) {
			System.out.println("DNO");

			Image door = new Image(getClass().getResource("/application/images/doorDNO.png").toExternalForm());
			ImageView iv = new ImageView(door);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DWO) {
			Image door = new Image(getClass().getResource("/application/images/doorDWO.png").toExternalForm());
			ImageView iv = new ImageView(door);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DWC) {
			Image door = new Image(getClass().getResource("/application/images/door.png").toExternalForm());
			ImageView iv = new ImageView(door);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		if (maCase == Cell.DNC) {
			Image door = new Image(getClass().getResource("/application/images/doorDNC.png").toExternalForm());
			ImageView iv = new ImageView(door);
			GridPane.setColumnIndex(iv, row);
			GridPane.setRowIndex(iv, col);
			mapGrid.getChildren().addAll(iv);
		}
		
		
	}
	
	
	@Override
	protected Object call() throws Exception {
		while(true)
		{
			paintAllCase();
			
			cow.paintCow();
			player.paintPlayer();
		}
		
	}

}
