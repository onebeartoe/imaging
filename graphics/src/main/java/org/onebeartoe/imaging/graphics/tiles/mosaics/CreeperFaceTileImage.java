
package org.onebeartoe.imaging.graphics.tiles.mosaics;

import java.awt.Color;
import static java.awt.Color.*;
import org.onebeartoe.imaging.graphics.tiles.TileImageProfile;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * @author 
 */
public class CreeperFaceTileImage extends TileImageProfile
{    
    Color [][] creeperFace = 
    {
                        // top
        {black, black, black, black, black, black, black, black},
        {black, green, green, black, black, green, green, black},
        {black, green, green, black, black, green, green, black},
        {black, black, black, green, green, black, black, black},  // right
        {black, black, green, green, green, green, black, black},
        {black, black, green, green, green, green, black, black},
        {black, black, green, black, black, green, black, black},
        {black, black, black, black, black, black, black, black}
                        // bottom
    };
    
    public CreeperFaceTileImage()
    {
        width = 800;
        height = 800;
        
        // the Creeper face is an 8x8 tile design
        tileSize = width / 8;
        
        colors = creeperFace;
    }
}
