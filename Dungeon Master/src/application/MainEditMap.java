package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;


public class MainEditMap extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxml = new FXMLLoader(getClass().getResource("EditMap.fxml"));
			SplitPane root = fxml.load();
			EditMapController controller = fxml.getController();
			controller.init();
			Scene scene = new Scene(root,1024,768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//				@Override
//				public void handle(KeyEvent event) {
//					controller.movePlayer(event);
//				}
//				
//			});
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
