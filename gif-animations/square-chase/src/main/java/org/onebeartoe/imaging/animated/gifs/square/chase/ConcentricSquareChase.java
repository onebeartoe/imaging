
package org.onebeartoe.imaging.animated.gifs.square.chase;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 */
public class ConcentricSquareChase extends Region
{
    int  horizontalNodeCount;
    
    int verticalNodeCount;
    
    public ConcentricSquareChase()
    {
        
        Rectangle r = new Rectangle(20, 20, Color.RED);
        r.setLayoutX(40);
        
        Rectangle r2 = new Rectangle(20, 20, Color.GREEN);
        r2.setLayoutX(60);
        
        Rectangle r3 = new Rectangle(20, 20);
        
        Rectangle r4 = new Rectangle(20, 20);
        
        SingleSquareChase c1 = new SingleSquareChase(null, null, -1);
        
        getChildren().addAll(r,r2, r3, r4, c1);
    }
}
