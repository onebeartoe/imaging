
package org.onebeartoe.imaging.image.resizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FilenameUtils;

/**
 * This class loads an image, reduces the file size by compressing quality, and
 * saves the scaled image as a new JPEG file.
 *
 * @author Roberto Marquez
 */
public class ImageService
{
    /**
     * @param infilePath
     * @param outfilePath
     * @param qualty - the amount of quality to keep; 1 - 100
     * @return
     * @throws Exception
     */
    public static boolean reduceQuality(String infilePath, String outfilePath, int qualty) throws IOException
    {
        File infile = new File(infilePath);
        BufferedImage oldimage = ImageIO.read(infile);
        oldimage.flush();

        String extention = FilenameUtils.getExtension( infile.getName() );
                
        Iterator iter = ImageIO.getImageWritersByFormatName(extention);
        ImageWriter writer = (ImageWriter) iter.next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        
        boolean isPng = extention.toLowerCase()
                                 .equals("png");
        
        if(isPng)
        {
            System.out.println("no compression mode allowed for PNG");
        }
        else
        {
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        }
        
        int q = qualty;
        q = Math.max(0, Math.min(q, 100));
        float quality = (float) q / 100.0f;
        
        boolean isGif = extention.toLowerCase()
                                 .equals("gif");
        
        if(isGif)
        {
            String[] compressionTypes = iwp.getCompressionTypes();
            List<String> list = Arrays.asList(compressionTypes);
            System.out.println("compression types: ");
            list.forEach(System.out::println);
            
            iwp.setCompressionType("LZW");
        }
        
        if( !isPng )
        {
            iwp.setCompressionQuality(quality);
        }

        File outfile = new File(outfilePath);
        FileImageOutputStream output = new FileImageOutputStream(outfile);
        writer.setOutput(output);

        IIOImage iioi = new IIOImage(oldimage, null, null);
        writer.write(null, iioi, iwp);
        writer.dispose();
        output.close();

        return true;
    }

    public static void main(String[] args) throws Exception 
    {
        if (args.length != 3) 
        {
            String message = "Usage: java ImageHelper INFILE OUTFILE QUALITY";
            System.err.println(message);
            System.exit(1);
        }

        String infile = args[0];
        String outfile = args[1];

        int quality = Integer.parseInt(args[2]);

        reduceQuality(infile, outfile, quality);

        System.out.println("Done.");
        System.exit(0);
    }

}
