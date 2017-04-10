/*
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
//import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;

public class DrawJavaCircles 
{
    public static void main(String[] argv) throws Exception 
    {
        Circle c;
        
        int imageWidth = 600;
        int imageHeight = 600;
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.green);

//        IntStream.range(1, 50100)
//        IntStream.range(1, 6000)
        IntStream.range(1, 12000)
                 .forEach( i -> 
                 {
//                    double x = ;
//                    double y = ;
//                    double r = ;
                    
        double x = Math.cos( (14*3.14*i)/12000.0 ) * ( 1 - 3/4.0 * Math.pow( Math.cos( (32 * 3.14 * i) / 12000.0 ) , 2 ) );
                    x = x * 500.0 
//                           
                            + (imageWidth * 0.5);
                    
                    double y = Math.sin( (14*3.14*i)/12000.0 ) * ( 1 - 3/4.0 * Math.pow( Math.cos( (32 * 3.14 * i) / 12000.0 ) , 2 ) );
                    y = y * 500.0 
//                           
                            + (imageHeight * 0.5);
                    
                    double radius = 1/200.0 + 1/10.0 * Math.pow(  Math.sin( (56*3.14*i)/12000.0 )  ,6);
                    radius = radius * 100.0;                    
                    
                    
                    System.out.println("(" + x + ", " + y + ") r: " + radius) ;
                    g2d.drawOval( (int)x, (int)y, (int)radius, (int)radius);
                 });
        
//        g2d.fillOval(10, 10, 50, 50);

        g2d.dispose();

        File pwd = new File(".");
        File outfile = new File(pwd, "circles.png");
        System.out.println("outputing to: " + outfile.getCanonicalPath() );
        ImageIO.write(bufferedImage, "png", outfile);
    }
}