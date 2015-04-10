
package org.onebeartoe.imaging.gifs;

import com.fmsware.gif.AnimatedGifEncoder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
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
import net.jmge.gif.Gif89Encoder;
import org.onebeartoe.application.DesktopApplication;
import org.onebeartoe.application.JavaPreferencesService;
import org.onebeartoe.application.PreferencesService;

/**
 * @author Roberto Marquez
 */
public class BruteForceAnimatedGifsApp extends JFrame implements DesktopApplication
{
    private JLabel statusLabel;
    
    public static final int DEFAULT_HEIGHT = 600;
    
    public static final int DEFAULT_WIDTH = 450;
    
    private PreferencesService preferenceService;
    
    private final Logger logger;
    
//    String path = "C:\\Users\\urhm020\\Desktop\\Workspace\\Sarah\\animated-gif-fabric\\ten\\";
    
    String path = "C:\\Users\\urhm020\\Desktop\\Workspace\\Sarah\\animated-gif-fabric\\moving-through-fabric\\ten\\";
    
    File inputDirectory = new File(path);
    
    public BruteForceAnimatedGifsApp()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	setLayout( new BorderLayout() );
        
        String className = getClass().getName();
	logger = Logger.getLogger(className);
        
        preferenceService = new JavaPreferencesService(this);
        
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
                            encodeImagesJmge();
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
        
	Dimension demension;
	try 
	{
	    demension = preferenceService.restoreWindowDimension();
	} 
	catch (Exception ex) 
	{
	    demension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}        
                
	setJMenuBar(menuBar);
	add(tabbedPane, BorderLayout.CENTER);	
	add(statusPanel, BorderLayout.SOUTH);
	setSize(demension);		
	
        Point location = null;
	try 
	{
	    location = preferenceService.restoreWindowLocation();
	} 
	catch (Exception ex) 
	{
	    logger.log(Level.INFO, ex.getMessage(), ex);
	}
	if(location == null)
	{
	    // center it
	    setLocationRelativeTo(null); 		
	}
	else
	{
	    setLocation(location);
	}
		
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
        File [] images = loadImageFiles();
                
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
    
    void encodeImagesJmge() throws IOException
    {
        File [] images = loadImageFiles();
        Image[] originalImages = new Image[images.length];
        
        
        String annotation = "some annotation";
        boolean looped = true;
        double frames_per_second = 2;
       
        Gif89Encoder gifenc = new Gif89Encoder();
        for (int i = 0; i < originalImages.length; ++i)
        {
            System.out.print("jmpe encoding image " + i + " of " + originalImages.length + " - " + images[i].getName() );
            originalImages[i] = ImageIO.read(images[i]);
            
            // use the origianl image to create a new 8 bit / 256 color image
            int w = originalImages[i].getWidth(this);
            int h = originalImages[i].getHeight(this);
            int type = BufferedImage.TYPE_BYTE_INDEXED;            
            BufferedImage bi = new BufferedImage(w, h, type);                    
            Graphics2D g = bi.createGraphics();
            g.drawImage(originalImages[i], 0,0, this);
            
            // add the new 8 bit / 256 color image to the animation
            gifenc.addFrame(bi);
            System.out.println(" done.");
        }
        gifenc.setComments(annotation);
        gifenc.setLoopCount(looped ? 0 : 1);
        gifenc.setUniformDelay((int) Math.round(100 / frames_per_second));
        
        String outpath = inputDirectory.getAbsolutePath() + File.separatorChar + "jmge-animation.gif";
        File outfile = new File(outpath);
        OutputStream out = new FileOutputStream(outfile);
        
        gifenc.encode(out);
   }
    
    private File [] loadImageFiles()
    {
        
         
        File [] images = inputDirectory.listFiles( new FilenameFilter() 
        {
            public boolean accept(File dir, String name) 
            {
                boolean accepted = false;
                String n = name.toLowerCase();
                if(n.endsWith("png") || n.endsWith("jpg"))
                {                    
                    accepted = true;
                }
                
                return accepted;
            }
        });
        
        return images;
    }
    
    public static void main(String [] args)
    {
        BruteForceAnimatedGifsApp app = new BruteForceAnimatedGifsApp();
    }
    
}
