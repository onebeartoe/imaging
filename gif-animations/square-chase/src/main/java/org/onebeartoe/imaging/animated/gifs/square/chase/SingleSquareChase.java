
package org.onebeartoe.imaging.animated.gifs.square.chase;

import java.util.List;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 */
public class SingleSquareChase extends Region
{
    Dimension size;
    
    Point centerPoint;
    
    List<Point> tileLocations;
    
    /**
     * This is the distance of the tile from the center point.
     */
    int radius;
    
    public SingleSquareChase(Dimension size, Point centerPoint, int radius)
    {
        this.size = size;
        
        this.centerPoint = centerPoint;
        
        this.radius = radius;
        
        Dimension d; 

        Point p = new Point();
        
        Rectangle r2 = new Rectangle(20, 20, Color.PURPLE);
        r2.setLayoutX(80);
        
        getChildren().addAll(r2);
    }
    
}
