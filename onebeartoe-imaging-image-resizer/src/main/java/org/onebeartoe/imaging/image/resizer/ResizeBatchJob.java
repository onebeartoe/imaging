
package org.onebeartoe.imaging.image.resizer;

import java.io.File;

public class ResizeBatchJob 
{
    File [] inputFiles;
    int width;
    int height;
    int percentage;

    public ResizeBatchJob(File [] files, int percent) 
    {
        inputFiles = files;
        percentage = percent;
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
}
