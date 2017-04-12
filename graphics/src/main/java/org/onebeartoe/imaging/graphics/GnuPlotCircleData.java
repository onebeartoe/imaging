
/**
# these equations are from 
#
#       https://mathematics.culturalspot.org/home
#
#       https://blogs.scientificamerican.com/guest-blog/making-mathematical-art/
 */
package org.onebeartoe.imaging.graphics;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */

import java.util.stream.IntStream;

public class GnuPlotCircleData
{
    public static void main(String[] argv) throws Exception 
    {
        GnuPlotCircleData gpcd = new GnuPlotCircleData();
        
        gpcd.printData();
        
        System.exit(0);
    }
    
    public void printData()
    {
        
        int xyMultiplier = 600;
        FunctionsForCircleCoordinates circleCoordinates = new FunctionsForCircleCoordinates();
        
        IntStream.range(1, 12000)
                 .forEach( i -> 
                 {
                    Circle c = circleCoordinates.twelveHundredCicles(i);
                    c.x = c.x * xyMultiplier;
                    c.y = c.y * xyMultiplier;                    
                    c.radius = c.radius * 100.0;
                                        
                    System.out.println("" + c.x + "\t" + c.y + "\t" + c.radius) ;
                 });
    }
}