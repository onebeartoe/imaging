
package org.onebeartoe.imaging.image.resizer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.onebeartoe.application.logging.SysoutLoggerFactory;
import org.onebeartoe.application.ui.swing.SwingApplication;

public class ResizeImagesDemo extends JFrame 
{
    private static final long serialVersionUID = -2530681569390665572L;

    private transient SwingApplication guiConfig;

    private final String applicationId;
    
    private Logger logger;
    
    /**
     * @param title
     */
    public ResizeImagesDemo(String title) 
    {
        super(title);
        
        logger = SysoutLoggerFactory.getLogger( getClass().getName() );

        //	set up the input panel and its contents
        ResizeImageGui resizeImageGui = new ResizeImageGui();

        // define the JFrame's content's layout
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(resizeImageGui, BorderLayout.CENTER);

        // define the JFrame's window properties
        
        applicationId = getClass().getName();
        
        guiConfig = loadDefaultGuiConfig();
        
        guiConfig.restoreWindowProperties(this);

        addWindowListener( new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                try 
                {                    
                    guiConfig.setCurrentConfiguration(ResizeImagesDemo.this);
                    guiConfig.setApplicationId(applicationId);
                    guiConfig.persistWindowProperties();
                } 
                catch (IOException ex) 
                {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    SwingApplication loadDefaultGuiConfig()
    {
        return new SwingApplication(applicationId) 
        {
            @Override
            public int defaultX() 
            {
                return 85;
            }
            
            @Override
            public int defaultY() 
            {
                return 175;
            }
            
            @Override
            public int defaultWidth() 
            {
                return 700;
            }
            
            @Override
            public int defaultHeight() 
            {
                return 420;
            }
        };
    }
    
    /**
     * @param args - program doesn't make use of command line args
     */
    public static void main(String[] args) 
    {
        // setup and show the GUI
        
        String title = "ImageResizer by onebeartoe.com";
        
        ResizeImagesDemo app = new ResizeImagesDemo(title);
    }
}
