
/**
# these equations are from 
#
#       https://mathematics.culturalspot.org/home
#
#       https://blogs.scientificamerican.com/guest-blog/making-mathematical-art/
 */
package org.onebeartoe.imaging.graphics;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;

public class DrawJavaCircles 
{
    public static void main(String[] argv) throws Exception 
    {
        FunctionsForCircleCoordinates circleCoordinates = new TwelveHundredCicleCoordinates();
        
        int imageWidth = 1600;
        int imageHeight = 1600;
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.green);

        IntStream.range(1, 12000)
                 .forEach( i -> 
                 {
                    Circle c = circleCoordinates.iToCircle(i);
                    
                    c.x = c.x * 500.0 
                            + (imageWidth * 0.5);
                    

                    c.y = c.y * 500.0 
                            + (imageHeight * 0.5);
                    
                    c.radius = c.radius * 100.0;                    
                                        
                    System.out.println("(" + c.x + ", " + c.y + ") r: " + c.radius) ;
                    g2d.drawOval( (int)c.x, (int)c.y, (int)c.radius, (int)c.radius);
                 });

        g2d.dispose();

        File pwd = new File(".");
        File outfile = new File(pwd, "circles.png");
        System.out.println("outputing to: " + outfile.getCanonicalPath() );
        ImageIO.write(bufferedImage, "png", outfile);
    }
}