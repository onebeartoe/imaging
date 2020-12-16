
package org.onebeartoe.imaging.shapes.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.onebeartoe.imaging.graphics.FunctionsForCircleCoordinates;
import org.onebeartoe.imaging.graphics.TwelveHundredCicleCoordinates;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 */
@Controller
public class RhoController extends ShapeController
{
    @GetMapping(value = "/rho/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable("filename") String fileName) 
    {
        return serveImage(fileName);
    }    

    @Override
    byte [] getMedia( ) throws IOException
    {
        FunctionsForCircleCoordinates circleCoordinates = new TwelveHundredCicleCoordinates();
        
        int imageWidth = 600;
        int imageHeight = 600;
        
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.green);

        int sideDecrement = 5;
        
        int iteration = 0;
        
        for(int side = imageWidth; side > 0; side -= sideDecrement)
        {
            int x = iteration * sideDecrement;
            
            int y = x;
        
            Color randomColor = randomColor();
            
            g2d.setPaint(randomColor);
            
            g2d.drawRect(x,y, side,side);
            
            iteration++;
        }

        g2d.dispose();

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();

        boolean write = ImageIO.write(bufferedImage, "png", outstream);
        
        byte [] media = outstream.toByteArray();

        return media;
    }    
}
