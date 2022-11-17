package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application  {
	private static Stage stg;
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			stg = primaryStage;
			primaryStage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("nameset.fxml"));											//starting window "nameset.fxml" of program to know user name
																															//nameset fxml is controlled by NamesetController.java
			Scene scene = new Scene(root,696,564);
			primaryStage.setTitle("TypR");
			primaryStage.getIcons().add(new Image("C:/Users/hrish/eclipse-workspace/TypR/src/logo/computer.png"));			//add location manually
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);																					//window of 696x564px is maintained throughout
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxml) throws IOException {																//method to swtich different windows when invoked
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
	
	
	public static void main(String[] args) {																				
		launch(args);
	}
	
}
