
package org.onebeartoe.imaging.gifs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.onebeartoe.application.DesktopApplication;
import org.onebeartoe.application.JavaPreferencesService;
import org.onebeartoe.application.PreferencesService;
import org.onebeartoe.application.ui.IntermediateProgressShowingWorkPanel;

/**
 * @author Roberto Marquez
 */
public class AnimatedGifsAppResponsive extends JFrame implements DesktopApplication
{
    
    public static final int DEFAULT_HEIGHT = 600;
    
    public static final int DEFAULT_WIDTH = 450;
    
    private PreferencesService preferenceService;
    
    private final Logger logger;
    
    private JFileChooser inputChooser;
    
    private JTextField textField;
    
    public AnimatedGifsAppResponsive()
    {
	setLayout( new BorderLayout() );
        
        String className = getClass().getName();
	logger = Logger.getLogger(className);
        
        preferenceService = new JavaPreferencesService(this);
        
        inputChooser = new JFileChooser();
        inputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        setTitle("onebeartoe GIF animator");	
        
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        
        JPanel animationPanel = createAnimationPanel();
        add(animationPanel, BorderLayout.CENTER);
        
        restoreSize();
        restoreLocation();
        restoreInputDirectory();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                String value = textField.getText();
                preferenceService.saveProperty(INPUT_PATH_KEY, value);
                
                preferenceService.saveWindowPreferences(AnimatedGifsAppResponsive.this);
            }
        });
        
	setVisible(true);
    }
    
    private JPanel createAnimationPanel()
    {
        textField = new JTextField();
        textField.setEditable(false);
        
        JButton chooserButton = new JButton("Input Directory");
        chooserButton.addActionListener( new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                int response = inputChooser.showOpenDialog(AnimatedGifsAppResponsive.this);
                
                if(response == JFileChooser.APPROVE_OPTION)
                {
                    String path = inputChooser.getSelectedFile().getAbsolutePath();
                    textField.setText(path);
                }
            }
        });
        JPanel directoryChooserPanel = new JPanel( new BorderLayout() );
        directoryChooserPanel.add(textField, BorderLayout.CENTER);
        directoryChooserPanel.add(chooserButton, BorderLayout.EAST);
        JProgressBar intermeidateProgressBar = new JProgressBar();
        JTextArea outputArea = new JTextArea();
        GifAnimationListener animationWorker = new GifAnimationListener(this, intermeidateProgressBar, outputArea, inputChooser);
        IntermediateProgressShowingWorkPanel workerPanel = new IntermediateProgressShowingWorkPanel(animationWorker, outputArea, intermeidateProgressBar);
        JPanel animationPanel = new JPanel( new BorderLayout() );
        animationPanel.add(directoryChooserPanel, BorderLayout.NORTH);
        animationPanel.add(workerPanel, BorderLayout.CENTER);
        
        return animationPanel;
    }
    
    private JMenuBar createMenuBar()	    
    {
	JMenuItem menuItem;
	
	JMenu applicationMenu = new JMenu("Application");
	applicationMenu.setMnemonic(KeyEvent.VK_A);
	applicationMenu.getAccessibleContext().setAccessibleDescription("update with accessible description");
	
	menuItem = new JMenuItem("Instructions");
	KeyStroke instructionsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK);
	menuItem.setAccelerator(instructionsKeyStroke);
	menuItem.getAccessibleContext().setAccessibleDescription("update with accessible description");
//	menuItem.addActionListener( new InstructionsListener() );
	applicationMenu.add(menuItem);
	
	menuItem = new JMenuItem("About");
	KeyStroke aboutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK);
	menuItem.setAccelerator(aboutKeyStroke);
	menuItem.getAccessibleContext().setAccessibleDescription("update with accessible description");
//	menuItem.addActionListener( new AboutListener() );
	applicationMenu.add(menuItem);
	
	menuItem = new JMenuItem("Exit");
	KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK);
	menuItem.setAccelerator(keyStroke);
//	menuItem.addActionListener( new QuitListener() );
	menuItem.getAccessibleContext().setAccessibleDescription("update with accessible description");
	applicationMenu.add(menuItem);

	menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
	menuItem.setMnemonic(KeyEvent.VK_D);
	applicationMenu.add(menuItem);	
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.add(applicationMenu);	
	
	return menuBar;
    }            
    
    public static void main(String [] args)
    {
        AnimatedGifsAppResponsive app = new AnimatedGifsAppResponsive();
    }
    
    private void restoreInputDirectory()
    {
        String path = 
                
        textField.setText(path);
    }
    
    private void restoreLocation()
    {
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
    }
    
    private void restoreSize()
    {        
	Dimension demension;
	try 
	{
	    demension = preferenceService.restoreWindowDimension();
	} 
	catch (Exception ex) 
	{
	    demension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
        
        setSize(demension);
    }
    
}
