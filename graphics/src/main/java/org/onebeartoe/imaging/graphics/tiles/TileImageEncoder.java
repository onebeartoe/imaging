/*

 */
package org.onebeartoe.imaging.graphics.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
class TileImageEncoder 
{
    public BufferedImage encodeTiles(TileImageProfile tileImage)
    {
        int imageWidth = tileImage.width;
        int imageHeight = tileImage.height;

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw the tiles on the the graphics object
        int rows = tileImage.colors.length;
        int columns = tileImage.colors[0].length;
        
        for(int r=0; r<rows; r++)
        {
            for(int c=0; c<columns; c++)
            {
                Color color = tileImage.colors[c][r];
                g2d.setPaint(color);
                
                int x = r * tileImage.tileSize;
                int y = c * tileImage.tileSize;
                
                int width = tileImage.tileSize;
                int height = width;
                
                g2d.fillRect(x,y, width,height);
            }
        }
        
        g2d.dispose();
        
        return bufferedImage;
    }
}
