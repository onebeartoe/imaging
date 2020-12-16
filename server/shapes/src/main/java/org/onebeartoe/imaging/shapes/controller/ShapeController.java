
package org.onebeartoe.imaging.shapes.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 *
 */
public abstract class ShapeController 
{
    private Random rando = new Random();
    
    abstract byte [] getMedia( ) throws IOException;

    protected ResponseEntity<byte[]> serveImage(String fileName) 
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



    protected Color randomColor() 
    {
        float r = rando.nextFloat();
        float g = rando.nextFloat();
        float b = rando.nextFloat();
        

        Color randomColor = new Color(r, g, b);

        return randomColor;
    }    
}
