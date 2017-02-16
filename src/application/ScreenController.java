package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ScreenController implements Initializable, ControlledScreen{
	
	ScreensController sController;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) { 
        // TODO 
   	}

	@Override
	public void setScreenParent(ScreensController screenPage) {
		sController = screenPage;
		
	} 
	
	@FXML
	private void goToMain(ActionEvent e){
		sController.setScreen(Main.MAIN_SCREEN);
	}
	
	
}
