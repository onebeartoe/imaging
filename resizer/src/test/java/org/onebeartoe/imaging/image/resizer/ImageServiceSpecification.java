/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onebeartoe.imaging.image.resizer;

import java.io.File;
import java.io.IOException;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author lando
 */
public class ImageServiceSpecification
{
    private ImageService implementation;
    
    private final String testImageDir = "src/test/resources/images/";
    
    private final String outputDir = "target/";
    
    @BeforeClass
    public void setUpMethod()
    {
        implementation = new ImageService();
    }

    private void reduceQuality(String testImage) throws IOException
    {
        String infilePath = testImageDir + testImage;
        
        String outfilePath = outputDir + testImage;
        
        implementation.reduceQuality(infilePath, outfilePath, 20);
        
        File infile = new File(infilePath);
        
        File outfile = new File(outfilePath);
        
        assertTrue( outfile.exists() );
        
        boolean fileSizeWasReduced = outfile.length() < infile.length();
        
        assertTrue(fileSizeWasReduced);        
    }

    /**
     * Test of reduceQuality method, of class ImageService for the GIF format.
     */
    @Test
    public void reduceQuality_gif() throws IOException
    {
        String testImage = "screen-shot-hangman.gif";
        
        reduceQuality(testImage);
    }    

    /**
     * Test of reduceQuality method, of class ImageService for the JPEG format.
     */
    @Test
    public void reduceQuality_jpg() throws IOException
    {
        String testImage = "featured.jpg";
        
        reduceQuality(testImage);
    }
    
    /**
     * Test of reduceQuality method, of class ImageService for the PNG format.
     */
    @Test
    public void reduceQuality_png() throws IOException
    {
        String testImage = "valohai-screen-shot-2019-11-04.png";
        
        reduceQuality(testImage);
    }
}
