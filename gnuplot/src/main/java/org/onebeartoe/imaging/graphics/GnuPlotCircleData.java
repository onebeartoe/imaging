
package org.onebeartoe.imaging.graphics;

/**
 * The equations are from these sites:
#
#       https://mathematics.culturalspot.org/home
#
#       https://blogs.scientificamerican.com/guest-blog/making-mathematical-art/
 *
 * Java source code
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.onebeartoe.imaging.graphics.Circle;
import org.onebeartoe.imaging.graphics.FunctionsForCircleCoordinates;
import org.onebeartoe.io.TextFileWriter;

public class GnuPlotCircleData
{
    private String outpath(FunctionsForCircleCoordinates.FunctionTypes coordinateName, int limit)
    {
        String s = String.valueOf(limit);
        int length = s.length();
        String format = "%0" + length + "d";
        String number = String.format(format, limit);
        String outpath = "circles-" + number + ".dat";
        
        return outpath;
    }
    
    public static void main(String[] argv) throws Exception 
    {
        GnuPlotCircleData gpcd = new GnuPlotCircleData();
        gpcd.printData();
        
        System.exit(0);
    }
    
    /**
     * Write out each list of cicles to a file with the limit in as part of the file name.
     * 
     * @param circles 
     */
    private void outputList(FunctionsForCircleCoordinates.FunctionTypes coordinatesType, List<Circle> circles, int limit)
    {
        String outpath = outpath(coordinatesType, limit);
        File outfile = new File(outpath);
        
        StringBuilder sb = new StringBuilder();
        
        circles.stream()
                .forEach(c -> 
                        sb.append(c.toString() + System.lineSeparator() ));
        
        String circleData = sb.toString();
        
        TextFileWriter tfw = new TextFileWriter();
        tfw.writeText(outfile, circleData);
    }
    
    public void printData()
    {
        int xyMultiplier = 800;
        FunctionsForCircleCoordinates circleCoordinates = new FunctionsForCircleCoordinates();
        
        List<Circle> allCircles = IntStream.range(1, 12000)
                 .mapToObj( i -> 
                 {
                    Circle c = circleCoordinates.twelveHundredCicles(i);
                    c.x = c.x * xyMultiplier;
                    c.y = c.y * xyMultiplier;                    
                    c.radius = c.radius * 100.0;
                                        
                    System.out.println(i + ": " + c.toString() ) ;
                    
                    return c;
                 })
                .collect(Collectors.toList() );
        
        StringBuilder demSb = new StringBuilder();
                
        int limitIndex = 0;
        int [] limits = {2000, 4000 , 6000, 8000, 10000, allCircles.size()-1};
        List<Circle> currentList = new ArrayList();
                        
        System.out.println("outputing to files");
        
        for(int i=0; i<allCircles.size(); i++)
        {
            Circle c = allCircles.get(i);
            currentList.add(c);
            
            if(i == limits[limitIndex])
            {
                FunctionsForCircleCoordinates.FunctionTypes functionType = FunctionsForCircleCoordinates.FunctionTypes.TWELVE_HUNDRED;
               
                
                outputList(functionType, currentList, limits[limitIndex]);
               
               limitIndex++;
               
               currentList = new ArrayList();
            }
        };
        
//        create .dem file
    }   
}