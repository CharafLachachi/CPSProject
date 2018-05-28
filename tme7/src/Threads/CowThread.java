package Threads;

import application.AnimatedImage;
import components.Cow;
import contracts.CowContract;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import services.CowService;
import services.Dir;
import services.EngineService;
import services.EntityService;

public class CowThread extends Task {

	private CowService cow;
	private AnimatedImage cowImg;
	@FXML
	private GridPane mapGrid;
	private EngineService labyrinthe;
	
	public CowThread(GridPane mapGrid, EngineService engine)
	{
		this.cow = new CowContract(new Cow());
		cowImg = new AnimatedImage(new Image("/application/images/test.png"), 3, 3, 12, 58, 55, 58);
		this.mapGrid = mapGrid;
		this.labyrinthe = engine;
		
	}
	
	public void init()
	{
		cow.init(labyrinthe.getEnv(), 3, 3, Dir.N, 4);
		labyrinthe.addEntity(cow);
		cowImg.play();

	}
	
	
	// methode pour dessiner le joueur au debut et a l'appel des fonction de
		// direction
	public void paintCow() {
			EntityService voyageur = labyrinthe.getEntity(1);
			int vl = voyageur.getRow();
			int vc = voyageur.getCol();
			// System.out.println("row " + vl);

			if (voyageur.getFace() == Dir.N) {
				cowImg.setOffsetY(58 * 3);
			}
			if (voyageur.getFace() == Dir.W) {
				cowImg.setOffsetY(58);

			}
			if (voyageur.getFace() == Dir.S) {
				cowImg.setOffsetY(0);

			}
			if (voyageur.getFace() == Dir.E) {
				cowImg.setOffsetY(58 * 2);

			}
			cowImg.play();

			GridPane.setColumnIndex(cowImg.getImageView(), vc);
			GridPane.setRowIndex(cowImg.getImageView(), vl);
			if (!mapGrid.getChildren().contains(cowImg.getImageView())) {
				mapGrid.getChildren().addAll(cowImg.getImageView());
			}

		}
	
	

	@Override
	protected Object call() throws Exception {
		
		while(cow.getHp() > 0)
		{
			cow.step();
			paintCow();
			cowImg.play();

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		labyrinthe.getEnv().removeMob(cow);
		System.out.println("cow life death : " + cow.getHp());
		
		cowImg.stop();
		cowImg.getImageView().setVisible(false);
		return true;
	}
}
