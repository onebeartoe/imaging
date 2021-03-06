
package org.onebeartoe.imaging.image.resizer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.onebeartoe.application.filesystem.FileSelectionMethods;
import org.onebeartoe.application.ui.GUITools;
import org.onebeartoe.application.ui.swing.FileSelectionPanel;
import org.onebeartoe.application.ui.swing.ScrollableTextArea;
import org.onebeartoe.filesystem.FileType;

public class ResizeImageGui extends JPanel implements ActionListener 
{
    final static long serialVersionUID = 1321324897498189498L;

    private final FileSelectionPanel fileSelectionPanel;

    private final JButton actionButton;

    private final ScrollableTextArea statusPanel;
    
    private final JTextField qualityTextField;

    private final JCheckBox overwriteCheckBox;
    
    public ResizeImageGui() 
    {
        // this panel gives the user a button to click to pick an input direcotyr,  it also shows which files will be worked on.
        boolean showRecursive = true;
        fileSelectionPanel = new FileSelectionPanel(FileType.IMAGE, FileSelectionMethods.SINGLE_DIRECTORY, showRecursive);
        Border border = GUITools.factoryLineBorder("Input Images");
        fileSelectionPanel.setBorder(border);

        // these panel holds the components for the new image's dementions and filesize reduction percentage.  
        Border border2 = GUITools.factoryLineBorder("% of Quality Kept");
        qualityTextField = new JTextField("30");
        qualityTextField.setBorder(border2);
        
        overwriteCheckBox = new JCheckBox();
        overwriteCheckBox.setText("Existing Files");
        Border qaopBorder = GUITools.factoryLineBorder("Overwrite");
        JPanel checkboxPanel = new JPanel( new FlowLayout(FlowLayout.LEFT) );
        checkboxPanel.add(overwriteCheckBox);
        checkboxPanel.setBorder(qaopBorder);
        
        Box qualityAndOverwritePanel = new Box(BoxLayout.Y_AXIS);        
        qualityAndOverwritePanel.add(qualityTextField);
        qualityAndOverwritePanel.add(checkboxPanel);
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(fileSelectionPanel, BorderLayout.CENTER);
        inputPanel.add(qualityAndOverwritePanel, BorderLayout.SOUTH);

        // this panel holds the  buttons that start the thumbnail generation and displays the status of the application.  Tthis is the text area the displays the status of the application
        statusPanel = new ScrollableTextArea("\n \n \n \n \n ");

        // this panel holds the  buttons that start the thumbnail generation				
        actionButton = new JButton("Resize");
        actionButton.addActionListener(this);

        // place the status and action components onto a panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(statusPanel, BorderLayout.CENTER);
        controlPanel.add(actionButton, BorderLayout.SOUTH);
        Border border3 = GUITools.factoryLineBorder("Control and Status");
        controlPanel.setBorder(border3);
        controlPanel.setMinimumSize( new Dimension(650, 1) );

        // define the JFrame content layout        
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, controlPanel);
        splitPane.setOneTouchExpandable(true);
        add(splitPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        Object eventSource = ae.getSource();
        if (eventSource == actionButton) 
        {
            // the "Resize" button has been clicked
            String text = qualityTextField.getText().trim();
            boolean valid = ! text.equals("");
            if(valid) 
            {
                ResizeBatchJob resizeJob = createBatchJob();
                ResizeTask task = new ResizeTask(resizeJob, actionButton, statusPanel);
                Date date = new Date();
                Timer timer = new Timer();
                timer.schedule(task, date);
            } 
            else 
            {
                statusPanel.setText("Ensure all Output Specification fields are populated.");
            }
        }
    }

    private ResizeBatchJob createBatchJob() 
    {
        File[] files = fileSelectionPanel.getTargetedFiles();

        String s = qualityTextField.getText();
        int percentage = Integer.valueOf(s);

        boolean overwrite = overwriteCheckBox.isSelected();
        
        ResizeBatchJob job = new ResizeBatchJob(files, percentage, overwrite);
        
        return job;
    }

}
