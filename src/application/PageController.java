package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


public class PageController extends Application{
	
	final static int CANVAS_WIDTH = 796;
    final static int CANVAS_HEIGHT = 427;
    
    ColorPicker colorPicker;
    
    @Override
    public void start(final Stage primaryStage) {
 
        final Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
         
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.setStroke(colorPicker.getValue());
                graphicsContext.stroke();
            }
        });

	 public void handle(ActionEvent t) {
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
                 canvas.snapshot(null, writableImage);
                 RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                 ImageIO.write(renderedImage, "png", file);
             } catch (IOException ex) {
                 Logger.getLogger(JavaFX_DrawOnCanvas.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
      
 });
	
}
