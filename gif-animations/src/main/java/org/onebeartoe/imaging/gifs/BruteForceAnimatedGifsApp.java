
package org.onebeartoe.imaging.gifs;

import com.fmsware.gif.AnimatedGifEncoder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import org.onebeartoe.application.JavaPreferencesService;
import org.onebeartoe.application.PreferencesService;
import org.onebeartoe.application.ui.swing.SwingApplication;

/**
 * @author Roberto Marquez
 */
public class BruteForceAnimatedGifsApp extends JFrame
{
    private JLabel statusLabel;
    
    public static final int DEFAULT_HEIGHT = 600;
    
    public static final int DEFAULT_WIDTH = 450;
    
    private PreferencesService preferenceService;
    
    private SwingApplication guiConfig;
    
    private final String applicationId;    
    
    private final Logger logger;

    String path = "C:\\Users\\user\\Desktop\\Workspace\\Sarah\\animated-gif-fabric\\moving-through-fabric\\ten\\";
    
    File inputDirectory = new File(path);
    
    private AnimatedGifService animatedGifService;
    
    public BruteForceAnimatedGifsApp()
    {        
	setLayout( new BorderLayout() );
        
        String className = getClass().getName();
	logger = Logger.getLogger(className);
        applicationId = className;
                
        guiConfig = loadDefaultGuiConfig();        
        guiConfig.restoreWindowProperties(this);        
        
        preferenceService = new JavaPreferencesService( getClass() );
        
        animatedGifService = new AnimatedGifService();
        
        JMenuBar menuBar = createMenuBar();
        
        String path = "/icons/apple_small.png";
	URL url = getClass().getResource(path);
        ImageIcon imagesTabIcon = new ImageIcon(url);
        
        String userIconPath = path;
	URL userUrl = getClass().getResource(userIconPath);
	ImageIcon userTabIcon = new ImageIcon(userUrl);	
        
        JPanel statusPanel = new JPanel();
	statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
	statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
	statusLabel = new JLabel("PIXEL Status: Searching...");
	statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
	statusPanel.add(statusLabel);
        
        JButton encodeButton = new JButton("Encode");
        encodeButton.addActionListener( new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                SwingUtilities.invokeLater( new Runnable() 
                {
                    public void run() 
                    {
                        try 
                        {
                            encodeImagesFmsware();              
                            
                            File [] images = animatedGifService.loadImageFiles(inputDirectory);
                            
                            animatedGifService.encodeImagesJmge(images, BruteForceAnimatedGifsApp.this);
                        } 
                        catch (IOException ex) 
                        {
                            Logger.getLogger(BruteForceAnimatedGifsApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
            }
        });
        JPanel encodePanel = new JPanel();
        encodePanel.add(encodeButton);
        
        JPanel decodePanel = new JPanel();
        
        JTabbedPane tabbedPane;
        tabbedPane = new JTabbedPane();
	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	tabbedPane.addTab("Images", imagesTabIcon, encodePanel, "Load built-in images.");
	tabbedPane.addTab("My Images", userTabIcon, decodePanel, "This panel displays images from your local hard drive.");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addWindowListener( new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                try 
                {                    
                    guiConfig.setCurrentConfiguration(BruteForceAnimatedGifsApp.this);
                    guiConfig.setApplicationId(applicationId);
                    guiConfig.persistWindowProperties();
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
            }
        });
    
                
	setJMenuBar(menuBar);
	add(tabbedPane, BorderLayout.CENTER);	
	add(statusPanel, BorderLayout.SOUTH);
		
	setVisible(true);
    }
    
    private JMenuBar createMenuBar()	    
    {
	JMenuItem menuItem;
	
	JMenu helpMenu = new JMenu("Help");
	helpMenu.setMnemonic(KeyEvent.VK_A);
	helpMenu.getAccessibleContext().setAccessibleDescription("update with accessible description");
	
	menuItem = new JMenuItem("Instructions");
	KeyStroke instructionsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK);
	menuItem.setAccelerator(instructionsKeyStroke);
	menuItem.getAccessibleContext().setAccessibleDescription("update with accessible description");
//	menuItem.addActionListener( new InstructionsListener() );
	helpMenu.add(menuItem);
	
	menuItem = new JMenuItem("About");
	KeyStroke aboutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK);
	menuItem.setAccelerator(aboutKeyStroke);
	menuItem.getAccessibleContext().setAccessibleDescription("update with accessible description");
//	menuItem.addActionListener( new AboutListener() );
	helpMenu.add(menuItem);
	
	menuItem = new JMenuItem("Exit");
	KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK);
	menuItem.setAccelerator(keyStroke);
//	menuItem.addActionListener( new QuitListener() );
	menuItem.getAccessibleContext().setAccessibleDescription("update with accessible description");
	helpMenu.add(menuItem);

	menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
	menuItem.setMnemonic(KeyEvent.VK_D);
	helpMenu.add(menuItem);	
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.add(helpMenu);
	
	
	return menuBar;
    }
    
    private void encodeImagesFmsware()
    {
        File [] images = animatedGifService.loadImageFiles(inputDirectory);
                
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        String outpath = inputDirectory.getAbsolutePath() + File.separatorChar + "fmsware-animation.gif";        
        encoder.start(outpath);
        encoder.setDelay(500);
        encoder.setRepeat(0);
        int i = 0;
        for(File image : images)
        {
            BufferedImage bi;
            try 
            {
                System.out.println("fmsware encoding image " + i + " of " + images.length + " - " + image.getName() );
                
                bi = ImageIO.read(image);
                encoder.addFrame(bi);                
            } 
            catch (IOException ex) 
            {
                String message = "could not load image: " + image.getAbsolutePath();
                logger.log(Level.SEVERE, message, ex);
            }
            
            i++;
        }
        encoder.finish();
        System.out.println("animation written to: " + outpath);
    }
    
    private SwingApplication loadDefaultGuiConfig()    
    {
        SwingApplication app = new SwingApplication(path) 
        {
            @Override
            public int defaultX() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public int defaultY() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public int defaultWidth() 
            {
                return DEFAULT_WIDTH;
            }
            
            @Override
            public int defaultHeight() 
            {
                return DEFAULT_HEIGHT;
            }
        };
        
        return app;
    }
    
    public static void main(String [] args)
    {
        BruteForceAnimatedGifsApp app = new BruteForceAnimatedGifsApp();
    }
}
