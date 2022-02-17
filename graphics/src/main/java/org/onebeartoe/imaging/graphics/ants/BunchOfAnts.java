
package org.onebeartoe.imaging.graphics.ants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import org.onebeartoe.imaging.graphics.Circle;

/**
 *
 */
public class BunchOfAnts
{
    private void genereate() throws IOException
    {
        int imageWidth = 1600;
        int imageHeight = 1600;
        
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.green);
        
        IntStream.range(1, 5)
                 .forEach( i -> 
                 {
                    Circle c = iToCircle(i);
                                        
                    System.out.println("(" + c.x + ", " + c.y + ") r: " + c.radius);

                    g2d.fillOval( (int)c.x, (int)c.y, (int)c.radius, (int)c.radius);
                 });

        g2d.dispose();

        File pwd = new File("target");

        File outfile = new File(pwd, "ants.png");

        System.out.println("outputing to: " + outfile.getCanonicalPath() );

        ImageIO.write(bufferedImage, "png", outfile);
    }
    
    public static void main(String[] args) throws IOException 
    {
        BunchOfAnts app = new BunchOfAnts();
        
        app.genereate();
    }

    private Circle iToCircle(int currentColumn)
    {
        int radius = 100;
        
        int xSpacing = 80;
        
        int x = currentColumn * (radius + xSpacing);
        
        int y = 100;

        Circle c = new Circle(x, y, radius);
        
        return c;
    }
}
