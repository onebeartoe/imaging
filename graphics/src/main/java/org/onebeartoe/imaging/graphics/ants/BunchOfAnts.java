
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
 * This class generates an image of a grid of ants with random arm/leg positions.
 */
public class BunchOfAnts
{
    private void genereateSampleImage() throws IOException
    {
        int imageWidth = 1600;
        int imageHeight = 1600;
        
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.green);
          
        IntStream columnsRange = IntStream.range(0, 4);
                
        columnsRange.forEach( column -> 
        {
            IntStream.range(0, 7).forEach(row -> 
            {
                Circle c = locationToAnt(column, row);

                System.out.println("(" + c.x + ", " + c.y + ") r: " + c.radius);

                g2d.fillOval( (int)c.x, (int)c.y, (int)c.radius, (int)c.radius);                    
            });
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
        
        app.genereateSampleImage();
    }

    private Circle locationToAnt(int currentColumn, int row)
    {
        int radius = 100;
        
        int xSpacing = 180;
        
        int x = currentColumn * (radius + xSpacing);
        
        int ySpacing = 250;
        
        int y = row * ySpacing;

        Circle c = new Circle(x, y, radius);
        
        return c;
    }
}
