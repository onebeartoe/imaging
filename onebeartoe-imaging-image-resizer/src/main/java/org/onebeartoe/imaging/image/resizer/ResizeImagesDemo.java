
package org.onebeartoe.imaging.image.resizer;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class ResizeImagesDemo extends JFrame 
{

    private static final long serialVersionUID = -2530681569390665572L;

    public static String title = "onebeartoe ImageResizer";

    /**
     * @param title
     */
    public ResizeImagesDemo(String title) 
    {
        super(title);

        //	set up the input panel and its contents
        ResizeImageGui resizeImageGui = new ResizeImageGui();

        // define the JFrame's content's layout
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(resizeImageGui, BorderLayout.CENTER);

        // define the JFrame's window properties
        setLocation(85, 175);
        setSize(700, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @param args - program doesn't make use of command line args
     */
    public static void main(String[] args) 
    {
        // setup and show the GUI
        ResizeImagesDemo app = new ResizeImagesDemo(title);
    }

}
