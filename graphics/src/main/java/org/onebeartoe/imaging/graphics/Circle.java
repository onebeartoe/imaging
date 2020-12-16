
/*

 */
package org.onebeartoe.imaging.graphics;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class Circle 
{
//TODO: make this a Java Record    
    public double x;
    public double y;
    public double radius;
    
    public Circle(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    @Override
    public String toString()
    {
        return "" + x + "\t" + y + "\t" + radius;
    }
}
