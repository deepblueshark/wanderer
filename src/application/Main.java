package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
//import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Main class of Wanderer application
 * 
 * **/

public class Main extends Application {
	
	public static final String MAIN_SCREEN = "page_master"; 
    public static final String SCREEN_FXML = "Interface.fxml"; 
    
    public static final String SCREEN1 = "page_1";
    public static final String SCREEN2 = "page_2";
    public static final String SCREEN3 = "page_3";
    public static final String SCREEN4 = "page_4";
    public static final String SCREEN5 = "page_5";
    public static final String SCREEN6 = "page_6";
    public static final String SCREEN7 = "page_7";
    public static final String SCREEN8 = "page_8";
    public static final String SCREEN9 = "page_9";
    
    public static String PREV_SCEEN = "";
    public static String NEXT_SCREEN = ""; 

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			ScreensController mainContainer = new ScreensController(); 
		    mainContainer.loadScreen(MAIN_SCREEN, SCREEN_FXML); 
		    mainContainer.loadScreen(SCREEN1, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN2, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN3, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN4, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN5, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN6, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN7, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN8, SCREEN_FXML);
		    mainContainer.loadScreen(SCREEN9, SCREEN_FXML);
		    mainContainer.setScreen(MAIN_SCREEN);
			
		    Group root = new Group();
			//Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));
			root.getChildren().addAll(mainContainer);
		    Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
