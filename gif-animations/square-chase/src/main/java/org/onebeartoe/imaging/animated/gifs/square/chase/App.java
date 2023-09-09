package org.onebeartoe.imaging.animated.gifs.square.chase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }


    @Override
    public void start(final Stage primaryStage) 
    {
        Date now = new Date();
        String time = now.toString();
        Label label = new Label(time);
        label.relocate(100, 100);
        Font font = new Font(48);
        label.setFont(font);
        
        Pane canvas = new Pane();
        final Scene scene = new Scene(canvas, 800, 600);

        primaryStage.setTitle("Time App");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
        
        Rectangle r = new Rectangle(20, 20);
        
        ConcentricSquareChase squareChase = new ConcentricSquareChase();

        canvas.getChildren().addAll(label, r, squareChase);

        // Thi Timeline object moves the position of the label every 50 milliseconds.
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() 
        {
            double deltaX = 6;
            double deltaY = 3;

            @Override
            public void handle(final ActionEvent t) 
            {
                label.setLayoutX(label.getLayoutX() + deltaX);
                label.setLayoutY(label.getLayoutY() + deltaY);

                final Bounds bounds = canvas.getBoundsInLocal();
                
                final boolean atRightBorder = label.getLayoutX() >= (bounds.getMaxX() - label.getBoundsInLocal().getMaxX());
                final boolean atLeftBorder = label.getLayoutX() <= (bounds.getMinX() + label.getBoundsInLocal().getMinX() );
                final boolean atBottomBorder = label.getLayoutY() >= (bounds.getMaxY() - label.getBoundsInLocal().getMaxY() );
                final boolean atTopBorder = label.getLayoutY() <= (bounds.getMinY() + label.getBoundsInLocal().getHeight() );

                if (atRightBorder || atLeftBorder) 
                {
                    deltaX *= -1;
                }
                
                if (atBottomBorder || atTopBorder) 
                {
                    deltaY *= -1;
                }
            }
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();        
        
        // this timeline object is responsible for updating the lable text with the current time every second.
        final Timeline timeTimeline = new Timeline( new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(final ActionEvent t) 
            {
                Date now = new Date();
                String currentTime = now.toString();
                label.setText(currentTime);
            }
        }));
        timeTimeline.setCycleCount(Timeline.INDEFINITE);
        timeTimeline.play();
    }
    
    
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}