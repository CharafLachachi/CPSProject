package Threads;

import java.util.Optional;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import services.EngineService;
import services.KeyService;
import services.PlayerService;
import services.RessourcesService;


public class RessourcesThread extends Task{

	@FXML
	private GridPane mapGrid;
	private EngineService labyrinthe;
	private RessourcesService ressource;
	private ImageView viewRes;
	
	
	public RessourcesThread(RessourcesService ressource, ImageView view, GridPane mapGrid, EngineService labyrinthe)
	{
		this.mapGrid = mapGrid;
		this.labyrinthe = labyrinthe;
		this.ressource = ressource;
		this.viewRes = view;
	}
	
	public void  init()
	{
		ressource.init(labyrinthe.getEnv());
		paintKey(ressource.getRow(), ressource.getCol());
		
	}
	
	public void paintKey(int row, int col) {

		GridPane.setColumnIndex(viewRes, col);
		GridPane.setRowIndex(viewRes, row);
		mapGrid.getChildren().addAll(viewRes);

	}
	
	public void removeRessourcefromMap(RessourcesService src, ImageView img)
	{
		
		if (labyrinthe.getEnv().getCellRessources(src.getRow(), src.getCol())
				.equals(Optional.empty())) {
			
			
		//	mapGrid.getChildren().removeAll(img);
			
			img.setVisible(false);
		//	img.setDisable(true);
			
		}
	}
	


	@Override
	protected Object call() throws Exception {
		PlayerService player = (PlayerService) labyrinthe.getEntity(0);
		
		
		if(ressource instanceof KeyService)
			while(player.isWin() == false && player.getKey() == null )
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else
			while(player.isWin() == false && player.getRessources() == null )
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		removeRessourcefromMap(ressource, viewRes);
		return null;
	}
}
