package application;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.neet.DiamondHunter.MapViewer.TileInformation;
import com.sun.javafx.logging.Logger;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


public class PageController extends Application{
	
	Canvas c1;
	
	double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
	
	final static int CANVAS_WIDTH = 796;
    final static int CANVAS_HEIGHT = 427;
    
    ColorPicker colorPicker;
    
    @Override
    public void start(final Stage primaryStage) {
 
        Canvas canvas = c1;
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        //initDraw(graphicsContext);
         
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.setStroke(colorPicker.getValue());
                graphicsContext.stroke();
            }
        });
    
    @FXML
    public void saveFile(ActionEvent t) {
         FileChooser fileChooser = new FileChooser();
          
         //Set extension filter
         FileChooser.ExtensionFilter extFilter = 
                 new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
         fileChooser.getExtensionFilters().add(extFilter);
        
         //Show save file dialog
         File file = fileChooser.showSaveDialog(primaryStage);
          
         if(file != null){
             try {
                 WritableImage writableImage = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
                 c1.snapshot(null, writableImage);
                 RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                 ImageIO.write(renderedImage, "png", file);
             } catch (IOException ex) {
                 Logger.getLogger(JavaFX_DrawOnCanvas.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }

    }
 
 	 @FXML
 	public void drag(MouseEvent t) {
         double offsetX = t.getSceneX() - orgSceneX;
         double offsetY = t.getSceneY() - orgSceneY;
         double newTranslateX = orgTranslateX + offsetX;
         double newTranslateY = orgTranslateY + offsetY;
          
         ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
         ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
     }
 	 
 	 
	
}
