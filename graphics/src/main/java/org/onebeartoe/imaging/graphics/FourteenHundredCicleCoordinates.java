/*

 */
package org.onebeartoe.imaging.graphics;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class FourteenHundredCicleCoordinates implements FunctionsForCircleCoordinates
{
    public Circle iToCircle(int i)
    {
        double x = Math.cos( (10*3.14*i)/14000.0 ) * ( 1 - 1/2.0 * Math.pow( Math.cos( (16 * 3.14 * i) / 14000.0 ) , 2 ) );
                    
        double y = Math.sin( (10*3.14*i)/14000.0 ) * ( 1 - 1/2.0 * Math.pow( Math.cos( (16 * 3.14 * i) / 14000.0 ) , 2 ) );
                    
        double radius = 1/200.0 + 1/10.0 * Math.pow(  Math.sin( (52*3.14*i)/14000.0 )  , 4);
                
        Circle c = new Circle(x, y, radius);
        
        return c;
    }    
}