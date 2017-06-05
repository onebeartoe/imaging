
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
import org.onebeartoe.io.TextFileWriter;

public class GnuPlotCircleData
{
    private String outpath(FunctionsForCircleCoordinates.FunctionTypes coordinateName, int limit)
    {
        String s = String.valueOf(limit);
        int length = s.length();
        String format = "%0" + length + "d";
        String number = String.format(format, limit);
        
        String parent = "samples/" + coordinateName.name();
        
        String outpath = parent + "/" + "circles-" + number + ".dat";
        
        
        
        return outpath;
    }
    
    public static void main(String[] argv) throws Exception 
    {
//        IntToCircle itc = new TwelveHundredIntToCircle();
        IntToCircle itc = new FourteenHundredIntToCircle();
        
//        FunctionsForCircleCoordinates circleCoordinates = new TwelveHundredCicleCoordinates();
        
        GnuPlotCircleData gpcd = new GnuPlotCircleData();
        gpcd.printData(itc);//, circleCoordinates);
        
        System.exit(0);
    }
    
    /**
     * Write out each list of cicles to a file with the limit in as part of the file name.
     * 
     * @param circles 
     */
    private void outputList(FunctionsForCircleCoordinates.FunctionTypes coordinatesType, List<Circle> circles, int limit)
    {
        StringBuilder sb = new StringBuilder();
        
        circles.stream()
                .forEach(c -> 
                        sb.append(c.toString() + System.lineSeparator() ) );
        
        String circleData = sb.toString();

        String outpath = outpath(coordinatesType, limit);
        
        System.out.println("output: " + outpath);
        
        File outfile = new File(outpath);
        File parent = outfile.getParentFile();
        if( !parent.exists())
        {
            parent.mkdirs();
        }
        
        TextFileWriter tfw = new TextFileWriter();
        tfw.writeText(outfile, circleData);
    }
    
    public void printData(IntToCircle itc)//, FunctionsForCircleCoordinates circleCoordinatessssss)
    {
        List<Circle> allCircles = IntStream.range(1, 12000)
                 .mapToObj( i -> 
                 {
                    Circle c = itc.convert(i);
                                   
                    System.out.println(i + ": " + c.toString() ) ;
                    
                    return c;
                 })
                .collect(Collectors.toList() );
                
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
    }   
    
    interface IntToCircle
    {              
        Circle convert(int i);//,FunctionsForCircleCoordinates circleCoordinates);
    }
    
    public static class IntToCirlceWithMultipliers implements IntToCircle
    {
        protected FunctionsForCircleCoordinates circleCoordinates;
        
//        public IntToCirlceWithMultipliers(FunctionsForCircleCoordinates circleCoordinates)
//        {
//            this.circleCoordinates = circleCoordinates;
//        }
//        q
        
        @Override
        public Circle convert(int i)
        {
            int xyMultiplier = 800;
            Circle c = circleCoordinates.iToCircle(i);
            c.x = c.x * xyMultiplier;
            c.y = c.y * xyMultiplier;                    
            c.radius = c.radius * 100.0;
            
            return c;
        }        
    }
    
    public static class TwelveHundredIntToCircle extends IntToCirlceWithMultipliers
    {
        public TwelveHundredIntToCircle()
        {
//            s
            circleCoordinates = new TwelveHundredCicleCoordinates();
        }
    }
    
    public static class FourteenHundredIntToCircle extends IntToCirlceWithMultipliers
    {
        public FourteenHundredIntToCircle()
        {
            circleCoordinates = new FourteenHundredCicleCoordinates();
        }
    }
}