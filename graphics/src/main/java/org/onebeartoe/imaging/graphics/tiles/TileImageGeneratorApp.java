
package org.onebeartoe.imaging.graphics.tiles;

import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.green;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.onebeartoe.imaging.graphics.ImageWriterService;
import org.onebeartoe.imaging.graphics.tiles.mosaics.CreeperFaceTileImage;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class TileImageGeneratorApp 
{
    public static void main(String [] args) throws IOException
    {                
        TileImageProfile tileImage = new CreeperFaceTileImage();
        TileImageEncoder tig = new TileImageEncoder();
        BufferedImage bufferedImage = tig.encodeTiles(tileImage);
        Path outfile = Paths.get("creeper-face.png");
        ImageWriterService.writeImage(bufferedImage, outfile);
    }
    

    Color [][] creeperFaceSolution = 
    {
                        // top
        {green, green, green, green, green, green, green, green},
        {green, black, black, green, green, black, black, green},
        {green, black, black, green, green, black, black, green},
        {green, green, green, black, black, green, green, green},  // right
        {green, green, black, black, black, black, green, green},
        {green, green, black, black, black, black, green, green},
        {green, green, black, green, green, black, green, green},
        {green, green, green, green, green, green, green, green}
                        // bottom
    };        
    
}