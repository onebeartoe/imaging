
package org.onebeartoe.imaging.graphics.ants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import org.onebeartoe.imaging.graphics.Circle;

/**
 * This class generates an image of a grid of ants with random arm/leg positions.
 */
public class BunchOfAnts
{
    private void drawAnt(Graphics2D graphics, Ant ant)
    {
        drawAntHead(graphics, ant);
        
        drawAntThorax(graphics, ant);
        
    }
    
    private BufferedImage genereateSampleImage() throws IOException
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
            IntStream.range(0, 4).forEach(row -> 
            {
                Ant c = locationToAnt(column, row);

                System.out.println("(" + c.thorax.x + ", " + c.thorax.y + ") r: " + c.thorax.radius);
                
                drawAnt(g2d, c);
            });
        });
        
        g2d.dispose();

        return bufferedImage;
    }

    private Ant locationToAnt(int currentColumn, int row)
    {
        int radius = 100;
        
        int xSpacing = 180;
        
        int x = currentColumn * (radius + xSpacing);
        
        int ySpacing = 250;
        
        int y = row * ySpacing;

        Circle c = new Circle(x, y, radius);
        
        Ant ant = new Ant();
        
        ant.thorax = c;
        
        return ant;
    }
    
    
    
    public static void main(String[] args) throws IOException 
    {
        BunchOfAnts app = new BunchOfAnts();
        
        BufferedImage graphics = app.genereateSampleImage();
        
        app.outputImages(graphics);
    }

    private void outputImages(BufferedImage bufferedImage) throws IOException 
    {
        File pwd = new File("target");

        File outfile = new File(pwd, "ants.png");

        System.out.println("outputing to: " + outfile.getCanonicalPath() );

        ImageIO.write(bufferedImage, "png", outfile);
        
        // save a copy
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String copyName = outfile.getCanonicalPath() + "." + timeInMillis + ".png";
        ImageIO.write(bufferedImage, "png", new File(copyName));
    }

    private void drawAntThorax(Graphics2D graphics, Ant ant)
    {
        graphics.setColor(Color.gray);

        graphics.fillOval((int)ant.thorax.x , (int)ant.thorax.y, 
                            (int)ant.thorax.radius, (int)ant.thorax.radius);        
    }

    private void drawAntHead(Graphics2D graphics, Ant ant) 
    {
        graphics.setColor(Color.green);
        
        int x = (int)ant.thorax.x;// - (int) (ant.thorax.radius / 2.0);
     
        int y = (int)ant.thorax.y + (int) ant.thorax.radius;
                
        graphics.fillOval(x, y, 
                          (int)ant.thorax.radius / 2, (int)ant.thorax.radius / 2);
    }
}
