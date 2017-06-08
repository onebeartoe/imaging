
package org.onebeartoe.imaging.graphics.tiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class TileImageReader 
{
    public TileImageProfile readTileImage() throws IOException
    {
        // read all lines of the file
        Path inpath = Paths.get("C:\\home\\owner\\versioning\\github\\3D-Modeling\\target-files.text");
        
        List<String> removeCommands;
        try (BufferedReader reader = Files.newBufferedReader(inpath)) 
        {           
            removeCommands = reader.lines()                                       
                               .map( l -> l.trim() )
                               .filter( p -> p.endsWith("BOTTOM-baseline.png") )
                               .map(p -> "rm " + p)
                               .collect( Collectors.toList() );

            removeCommands.forEach(System.out::println);
            System.out.println("Number of paths = " + removeCommands.size() );
        }
        
        return null;
    }
}