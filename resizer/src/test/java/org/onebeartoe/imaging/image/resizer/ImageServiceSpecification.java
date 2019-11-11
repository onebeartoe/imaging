/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onebeartoe.imaging.image.resizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import org.onebeartoe.system.Sleeper;
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

    /**
     * This version overwrites by default.
     * @param testImage
     * @return
     * @throws IOException 
     */
    private File reduceQuality(String testImage) throws IOException
    {
        boolean overwrite = true;
        
        File outfile = reduceQuality(testImage, overwrite);
        
        return outfile;
    }
    
    private File reduceQuality(String testImage, boolean overwrite) throws IOException
    {
        String infilePath = testImageDir + testImage;
        
        String outfilePath = outputDir + testImage;
        
        implementation.reduceQuality(infilePath, outfilePath, 20, overwrite);
        
        File infile = new File(infilePath);
        
        File outfile = new File(outfilePath);
        
        assertTrue( outfile.exists() );
        
        boolean fileSizeWasReduced = outfile.length() < infile.length();
        
        assertTrue(fileSizeWasReduced);

        return outfile;
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
    
    @Test
    public void reduceQuality_overwrite() throws IOException
    {
        String testImage = "overwrite.jpg";
        
        File outfile = reduceQuality(testImage);
        
        long initialLastModified = outfile.lastModified();

        System.out.println("sleepo");
        Sleeper.sleepo(1000);
        
        outfile = reduceQuality(testImage);
        
        long secondLastModified = outfile.lastModified();
        
        System.out.println("initialLastModified = " + initialLastModified + "\n secondLastModified = " + secondLastModified);
        
        assertTrue(initialLastModified < secondLastModified);
    }
    
    @Test(expectedExceptions = FileAlreadyExistsException.class)
    public void reduceQuality_fails_overwriteNotSet() throws IOException
    {
        String testImage = "overwrite-fails.jpg";
        
        reduceQuality(testImage);
        
        boolean overwrite = false;
        
        reduceQuality(testImage, overwrite);
    }
}
