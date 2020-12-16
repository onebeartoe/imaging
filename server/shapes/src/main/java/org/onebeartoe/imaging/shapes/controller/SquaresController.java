
package org.onebeartoe.imaging.shapes.controller;

//THIS WAS RESTcONTROLER IN THE ORIGINAL iMAGEcONGTROLER

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import org.onebeartoe.imaging.graphics.Circle;
import org.onebeartoe.imaging.graphics.FunctionsForCircleCoordinates;
import org.onebeartoe.imaging.graphics.TwelveHundredCicleCoordinates;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SquaresController 
{
    @GetMapping(value = "/file/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable("filename") String fileName) 
    {
        // Initiate the headers we will use in the return
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());



        // Get the file as a byte array.
        byte[] media = null;
        try {//(InputStream in = new FileInputStream(file)) {
            media = getMedia();
        } catch (Exception ex) {
            // Oops something went wrong, return error 500.
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity(ex.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Check which type of file we are returning so we can correctly define
        // the header content type. By doing this, the browser can show 
        // the image inside the browser, otherwise it will do a download.
        if (fileName.toLowerCase().endsWith(".jpg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        } else if (fileName.toLowerCase().endsWith(".png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            headers.setContentType(MediaType.IMAGE_GIF);
        }

        // Everything OK, return the image.
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }    
    
    private byte [] getMedia( ) throws IOException
    {
        FunctionsForCircleCoordinates circleCoordinates = new TwelveHundredCicleCoordinates();
        
        int imageWidth = 1600;
        int imageHeight = 1600;
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.green);

        IntStream.range(1, 12000)
                 .forEach( i -> 
                 {
                    Circle c = circleCoordinates.iToCircle(i);
                    
                    c.x = c.x * 500.0 
                            + (imageWidth * 0.5);
                    

                    c.y = c.y * 500.0 
                            + (imageHeight * 0.5);
                    
                    c.radius = c.radius * 100.0;                    
                                        
                    System.out.println("(" + c.x + ", " + c.y + ") r: " + c.radius) ;
                    g2d.drawOval( (int)c.x, (int)c.y, (int)c.radius, (int)c.radius);
                 });

        g2d.dispose();

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
//        BufferedOutputStream bufferedOutstream = new BufferedOutputStream(outstream);
        
//        File pwd = new File(".");
//        File outfile = new File(pwd, "circles.png");
//        System.out.println("outputing to: " + outfile.getCanonicalPath() );
        boolean write = ImageIO.write(bufferedImage, "png", outstream);
        
        byte [] media = outstream.toByteArray();

        return media;
    }
}

