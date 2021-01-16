//TODO: move to a .service package
package org.onebeartoe.imaging.files.server.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.onebeartoe.filesystem.FileHelper;
import org.onebeartoe.filesystem.FileSystemSearcher;
import org.onebeartoe.filesystem.FileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RandomImageService 
{
    /**
     * Get the value from application.properties where we define the location of the images.
     */
    @Value("${path.images}")
    private String pathImages;    

    private FileSystemSearcher searcher;
    
    private List<File> targetDirectories;
    
    private Random rando = new Random();
    
    private Logger logger = Logger.getLogger( getClass().getName() );
    
    public String randomImageUrl() 
    {
//System.out.printf("PathImages: %s\n", pathImages);
        if(searcher == null)
        {
            File directory = new File(pathImages);
            
            boolean recursive = true;
            
            List<FileType> targets = List.of(FileType.IMAGE);
            
            searcher = new FileSystemSearcher(directory, targets, recursive);
            
            targetDirectories  = searcher.findTargetDirectories();
        }
        
        int index = rando.nextInt( targetDirectories.size() );
        
        File directory = targetDirectories.get(index);
        
        FilenameFilter filter = (dir, name) -> 
        {
            return FileHelper.isImageFile(name);
        };
        
        File[] images = directory.listFiles(filter);
        
        int imageIndex = rando.nextInt(images.length);
        
        File image = images[imageIndex];

        // remove path for local testing
        String path = image.getPath().replaceFirst(pathImages + "/", "");

        // remove path for deployed testing
        path = image.getPath().replaceFirst(pathImages, "");
        
        logger.info("image service - pathImages -> " + pathImages);
        
        logger.info("image service - path       -> " + path);
        
        return path;
    }
}
