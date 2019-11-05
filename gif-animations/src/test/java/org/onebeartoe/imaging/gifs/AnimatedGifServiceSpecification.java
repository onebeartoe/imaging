
package org.onebeartoe.imaging.gifs;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JFrame;
import org.onebeartoe.system.Filesystem;
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
        
        // cleanup
        String pwd = Filesystem.pwd();
        
        System.out.println("pwd = " + pwd);
        
        File moveDir = new File( pwd + "/target/" + outfile.getName() );
        
        Path source = outfile.toPath();
        
        Path target = moveDir.toPath();
        
        StandardCopyOption options = StandardCopyOption.ATOMIC_MOVE;
        
        Files.move(source, target, options);
    }    
}
