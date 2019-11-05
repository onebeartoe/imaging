
package org.onebeartoe.imaging.gifs;

import java.io.File;
import javax.swing.JFrame;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class tests the AnimatedGifService specification.
 */
public class AnimatedGifServiceSpecification
{
    AnimatedGifService implementation;

    @BeforeTest
    public void setUpMethod() throws Exception
    {
        implementation = new AnimatedGifService();
    }

    /**
     * Test of encodeImagesJmge method, of class AnimatedGifService.
     */
    @Test
    public void testEncodeImagesJmge() throws Exception 
    {
        String inpath = "src/test/resources/stills/";
        
        File inputDirectory = new File(inpath);
        
        File [] images = implementation.loadImageFiles(inputDirectory);
        
        JFrame frame = new JFrame();
        
        File outfile = implementation.encodeImagesJmge(images, frame);
        
        assertNotNull(outfile);
        
        assertTrue( outfile.exists() );
    }
    
}
