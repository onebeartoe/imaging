
package org.onebeartoe.imaging.gifs;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import net.jmge.gif.Gif89Encoder;

/**
 * This service class provides methods for creating animated GIF files.
 */
public class AnimatedGifService
{
    public File encodeImagesJmge(File [] images, JFrame frame) throws IOException
    {
        Image[] originalImages = new Image[images.length];
        
        String annotation = "some annotation";

        double frames_per_second = 2;
       
        File parentDir = null;
        
        Gif89Encoder gifenc = new Gif89Encoder();
        
        for (int i = 0; i < originalImages.length; ++i)
        {
            System.out.print("jmpe encoding image " + i + " of " + originalImages.length + " - " + images[i].getName() );
            originalImages[i] = ImageIO.read(images[i]);
            
            // use the origianl image to create a new 8 bit / 256 color image
            int w = originalImages[i].getWidth(frame);
            int h = originalImages[i].getHeight(frame);
            int type = BufferedImage.TYPE_BYTE_INDEXED;

            BufferedImage bi = new BufferedImage(w, h, type);

            Graphics2D g = bi.createGraphics();
            g.drawImage(originalImages[i], 0,0, frame);
            
            // add the new 8 bit / 256 color image to the animation
            gifenc.addFrame(bi);
            
            if(parentDir == null)
            {
                parentDir = images[i].getParentFile();
            }
            
            System.out.println(" done.");            
        }
        gifenc.setComments(annotation);
        
        gifenc.setLoopCount(0);

        gifenc.setUniformDelay((int) Math.round(100 / frames_per_second));
        
        File outfile = null;
                
        if(parentDir != null)
        {
            String outpath = parentDir.getAbsolutePath() + File.separatorChar + "jmge-animation.gif";
            
            outfile = new File(outpath);
            
            OutputStream out = new FileOutputStream(outfile);

            gifenc.encode(out);            
        }
      
        return outfile;
    }

    public File [] loadImageFiles(File inputDirectory)
    {
        File [] images = inputDirectory.listFiles( new FilenameFilter() 
        {
            public boolean accept(File dir, String name) 
            {
                boolean accepted = false;
                String n = name.toLowerCase();
                if(n.endsWith("png") || n.endsWith("jpg") || n.endsWith("gif"))
                {                    
                    accepted = true;
                }
                
                return accepted;
            }
        });
        
        return images;
    }    
}
