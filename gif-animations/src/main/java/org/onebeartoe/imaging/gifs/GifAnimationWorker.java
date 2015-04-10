
package org.onebeartoe.imaging.gifs;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import net.jmge.gif.Gif89Encoder;
import org.onebeartoe.ui.worker.AsynchIntermediateProgressEDTWorker;

/**
 * @author Roberto Marquez
 */
public class GifAnimationWorker extends AsynchIntermediateProgressEDTWorker
{

    private JFileChooser inputChooser;
    
    public GifAnimationWorker(Component guiParent, JTextArea outputArea, JFileChooser inputChooser) 
    {
        super(guiParent, outputArea);
        
        this.inputChooser = inputChooser;
    }
    
    @Override
    public String doInBackground() 
    {
        File [] images = loadImageFiles();
        
        if(images == null)
        {
            publish("No images were loaded.  Select an Input Directory, to continue.\n");
        }
        else
        {
            publish("Running tasks...\n");
            
            Image[] originalImages = new Image[images.length];

            String annotation = "some annotation";
            boolean looped = true;
            double frames_per_second = 2;

            Gif89Encoder gifenc = new Gif89Encoder();
            for (int i = 0; i < originalImages.length; ++i)
            {
                String errorMessage = "";
                try
                {
                    System.out.print("jmpe encoding image " + i + " of " + originalImages.length + " - " + images[i].getName() );
                    originalImages[i] = ImageIO.read(images[i]);

                    // use the origianl image to create a new 8 bit / 256 color image
                    int w = originalImages[i].getWidth(guiParent);
                    int h = originalImages[i].getHeight(guiParent);
                    int type = BufferedImage.TYPE_BYTE_INDEXED;            
                    BufferedImage bi = new BufferedImage(w, h, type);                    
                    Graphics2D g = bi.createGraphics();
                    g.drawImage(originalImages[i], 0,0, guiParent);
                    
                    // add the new 8 bit / 256 color image to the animation
                    gifenc.addFrame(bi);
                    
                    bi.flush();
                    System.out.println(" done.");                    
                }
                catch(Exception e)
                {
                    errorMessage = "But an error occured!";
                    e.printStackTrace();
                }

                String message = "task " + i + " is complete. " + errorMessage + "\n";
                publish(message);
    		setProgress(100 * i / originalImages.length);
            }
            gifenc.setComments(annotation);
            gifenc.setLoopCount(looped ? 0 : 1);
            gifenc.setUniformDelay((int) Math.round(100 / frames_per_second));

            try
            {
                publish("outputing amimation file...\n");
                File inputDirectory = inputChooser.getSelectedFile();
                String outpath = inputDirectory.getAbsolutePath() + File.separatorChar + "jmge-animation.gif";
                File outfile = new File(outpath);
                OutputStream out = new FileOutputStream(outfile);
                gifenc.encode(out);
                
                out.flush();
                out.close();
            }
            catch(Exception e)
            {
                publish("could not create output animation file");
                e.printStackTrace();
            }
            
            setProgress(100);
            publish("The last task is finished.");            
        }
		
        return "doing";
    }
    
    private File [] loadImageFiles()
    {
        File inputDirectory = inputChooser.getSelectedFile();
        File [] images;
        
        if(inputDirectory == null ||
                ! inputDirectory.exists() ||
                        ! inputDirectory.isDirectory())
        {
            images = null;
        }
        else
        {
            images = inputDirectory.listFiles( new FilenameFilter() 
            {
                public boolean accept(File dir, String name) 
                {
                    boolean accepted = false;
                    String n = name.toLowerCase();
                    if(n.endsWith("png") || n.endsWith("jpg"))
                    {                    
                        accepted = true;
                    }

                    return accepted;
                }
            });
        }                
        
        return images;
    }
    
}
