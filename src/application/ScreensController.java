package application;

import java.time.Duration;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {

	private HashMap<String,Node> screens = new HashMap<>();
	
	public void addScreen(String name, Node screen){
		screens.put(name, screen);
	}
	
	public boolean loadScreen(String name, String resource) { 
	     try { 
	       FXMLLoader sLoader = new FXMLLoader(getClass().getResource(resource)); 
	       Parent loadScreen = (Parent) sLoader.load(); 
	       ControlledScreen sControler = ((ControlledScreen) sLoader.getController()); 
	       sControler.setScreenParent(this); 
	       addScreen(name, loadScreen); 
	       return true; 
	     }catch(Exception e) { 
	       System.out.println(e.getMessage()); 
	       return false; 
	     } 
	   } 
	
	public boolean setScreen(final String name) { 

	     if(screens.get(name) != null) { 
	       //screen loaded
	       final DoubleProperty opacity = opacityProperty(); 
	       //Is there is more than one screen 
	       if(!getChildren().isEmpty()){ 
	         Timeline fade = 
	        		 new Timeline(
		        		 new KeyFrame(Duration.ZERO, new KeyValue(opacity,1.0)), 
		        		 new KeyFrame(
		        				 new Duration(1000, BOTTOM_INVALID),
		        				 e -> {
									 //remove displayed screen 
									 getChildren().remove(0); 
								     //add new screen 
								     getChildren().add(0, screens.get(name)); 
								     Timeline fadeIn = new Timeline(
								    		 new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
								    		 new KeyFrame(new Duration(800, BOTTOM_INVALID), new KeyValue(opacity, 1.0))
								     );
								     fadeIn.play();
								}, 
		        		 		new KeyValue(opacity, 0.0)
		        		)
	        		 ); 
	         fade.play(); 
	       }else{ 
	         //no one else been displayed, then just show 
	         setOpacity(0.0); 
	         getChildren().add(screens.get(name)); 
	         Timeline fadeIn = new Timeline(
	        		 new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), 
	        		 new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0))
	         ); 
	         fadeIn.play(); 
	       } 
	       return true; 
	     }else{ 
	         System.out.println("screen hasn't been loaded!\n"); 
	         return false; 
	   }
	   
	   public boolean unloadScreen(String name) { 
	       if(screens.remove(name) == null) { 
	         System.out.println("Screen didn't exist"); 
	         return false; 
	         }else{ 
	           return true; 
	        } 
	  } 
	
}
