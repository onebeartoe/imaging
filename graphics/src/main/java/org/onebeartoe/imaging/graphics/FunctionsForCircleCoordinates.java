/*

 */
package org.onebeartoe.imaging.graphics;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class FunctionsForCircleCoordinates 
{
    public Circle twelveHundredCicles(int i)
    {
        double x = Math.cos( (14*3.14*i)/12000.0 ) * ( 1 - 3/4.0 * Math.pow( Math.cos( (32 * 3.14 * i) / 12000.0 ) , 2 ) );
                    
        double y = Math.sin( (14*3.14*i)/12000.0 ) * ( 1 - 3/4.0 * Math.pow( Math.cos( (32 * 3.14 * i) / 12000.0 ) , 2 ) );
                    
        double radius = 1/200.0 + 1/10.0 * Math.pow(  Math.sin( (56*3.14*i)/12000.0 )  ,6);
                
        Circle c = new Circle(x, y, radius);
        
        return c;
    }

    enum FunctionTypes
    {
        TWELVE_HUNDRED
    }
    
}
