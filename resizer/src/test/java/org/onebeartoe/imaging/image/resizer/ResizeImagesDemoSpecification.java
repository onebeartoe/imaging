/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onebeartoe.imaging.image.resizer;

import org.onebeartoe.application.ui.swing.SwingApplication;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author lando
 */
public class ResizeImagesDemoSpecification
{
    private ResizeImagesDemo implementation;
    
    @BeforeClass
    public void initialize() throws Exception 
    {
        implementation = new ResizeImagesDemo("some title");
    }    

    /**
     * Test of main method, of class ResizeImagesDemo.
     */
    @Test
    public void loadDefaultGuiConfig()
    {
        SwingApplication app = implementation.loadDefaultGuiConfig();
        
        assertNotNull(app);
    }    
}
