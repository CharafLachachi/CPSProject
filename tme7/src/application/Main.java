package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxml = new FXMLLoader(getClass().getResource("interface.fxml"));
			SplitPane root = fxml.load();
			MainController controller = fxml.getController();
			controller.init();
			
			Scene scene = new Scene(root,1024,768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					controller.movePlayer(event);
				}
				
			});
			
			 scene.setOnKeyReleased(event -> {
		            controller.stopPlayer();
		        });
		        
			 
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			 e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
