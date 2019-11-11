
package org.onebeartoe.imaging.image.resizer;

import java.io.File;

public class ResizeBatchJob 
{
    File [] inputFiles;

    int width;

    int height;

    int percentage;

    boolean overwrite;

    public ResizeBatchJob(File [] files, int percent, boolean overwrite) 
    {
        inputFiles = files;
        
        percentage = percent;
        
        this.overwrite = overwrite;
    }

    public int getHeight() 
    {
        return height;
    }

    public int getPercentage() 
    {
        return percentage;
    }

    public int getWidth() 
    {
        return width;
    }

    public File[] getInputFiles() 
    {
        return inputFiles;
    }	

    boolean getOverwrite()
    {
        return overwrite;
    }
}
