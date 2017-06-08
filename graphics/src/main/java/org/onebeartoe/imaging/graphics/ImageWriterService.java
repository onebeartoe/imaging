
package org.onebeartoe.imaging.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public interface ImageWriterService 
{
    static void writeImage(BufferedImage bufferedImage, Path outpath) throws IOException
    {
        File outfile = outpath.toFile();
        System.out.println("outputing to: " + outfile.getCanonicalPath() );
        ImageIO.write(bufferedImage, "png", outfile);                
    }
}
