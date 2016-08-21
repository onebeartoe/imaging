
package org.onebeartoe.imaging.image.resizer;

import java.awt.image.*;
import java.io.*;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

/**
 * This class loads an image, reduces the file size by compressing quality, and
 * saves the scaled image as a new JPEG file.
 *
 * @author Roberto Marquez
 */
public class ImageHelper 
{

    /**
     * @param infilePath
     * @param outfilePath
     * @param qualty
     * @return
     * @throws Exception
     */
    public static boolean generateThumbnail(String infilePath, String outfilePath, int qualty) throws Exception 
    {
        File infile = new File(infilePath);
        BufferedImage oldimage = ImageIO.read(infile);
        oldimage.flush();

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = (ImageWriter) iter.next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        int q = qualty;
        q = Math.max(0, Math.min(q, 100));
        float quality = (float) q / 100.0f;

        iwp.setCompressionQuality(quality);

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

        generateThumbnail(infile, outfile, quality);

        System.out.println("Done.");
        System.exit(0);
    }

}
