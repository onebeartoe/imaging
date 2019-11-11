
package org.onebeartoe.imaging.image.resizer;

import java.io.File;
import java.util.TimerTask;

import javax.swing.JButton;

import org.onebeartoe.application.ui.swing.ScrollableTextArea;

/**
 * This class should be used in conjunction with a java.util.Timer object to actually schedule the task be executed. 
 * @author Roberto Marquez
 */
public class ResizeTask extends TimerTask 
{
    private File [] targetedFiles;
    private int newWidth;
    private int newHeight;
    private int newPercentage;
    private boolean overwrite;

    private JButton actionButton;
    private ScrollableTextArea outputArea;

    public ResizeTask(ResizeBatchJob job, JButton button, ScrollableTextArea testArea) 
    {
        targetedFiles = job.getInputFiles();
        newWidth = job.getWidth();
        newHeight = job.getHeight();
        newPercentage = job.getPercentage();
        overwrite = job.getOverwrite();
        
        actionButton = button;
        outputArea = testArea;
    }
    
    private String buildResizeName(String originalName)
    {
        int i = originalName.lastIndexOf('.');
        
        String baseName = originalName.substring(0, i);
        String fileSuffix = originalName.substring(i);
        
        String resizedName = baseName + "-resized" + fileSuffix;
                
        return resizedName;
    }
	
    @Override
    public void run() 
    {
        actionButton.setEnabled(false);
        StringBuffer status = new StringBuffer("Starting job...\n\n");
        outputArea.setText( status.toString() );

        int max = targetedFiles.length;

        for(int f=0; f<max && f<targetedFiles.length; f++) 
        {			
            String infile = targetedFiles[f].getPath();
            status.append(infile + "...............");				
            outputArea.setText( status.toString() );
            
            String resizedName = buildResizeName(targetedFiles[f].getName());
            String outfile = targetedFiles[f].getParentFile().getPath() + File.separator + resizedName;
            
            String result = null;
            try 
            {
                ImageService.reduceQuality(infile, outfile, newPercentage, overwrite);
                result = " done.";								
            }
            catch(Exception e) 
            {
                System.err.println();
                System.err.println(infile + " ->");
                
                e.printStackTrace();
                
                result = " problem encountered -> " + e.getMessage();
            }
            status.append(result);
            status.append("\n\n");
            outputArea.setText( status.toString() );			
        }	    

        status.append("Done.\n");
        outputArea.setText( status.toString() );
        actionButton.setEnabled(true); 
    }
}
