
package org.onebeartoe.imaging.graphics;

//import corejava.*;

import java.io.File;
import org.onebeartoe.application.ui.GUITools;
import org.onebeartoe.io.TextFileWriter;

/**
 *  Program invocation: 
 * 
 *      java GeneratePoints > data1.dat
 * 
    File name: GeneratePoints.java
    Written by: Roberto Marquez
    @author <a href="mailto:onebeartoe@lycos.com">Roberto Marquez</a>
    Last Modified: 
    Created: 4/18/2004
    This application prints random points to standard output.
*/
public class GeneratePoints 
{
   static String [] content;
   static int arraysize = 28;

   public static void main( String [] args ) throws java.io.IOException
   {
      String input = GUITools.getString("How many points?");
      int count = Integer.parseInt(input);
      int point_type;

      input = GUITools.getString("2D or 3D points?");
      if( input.equalsIgnoreCase("2D") ) 
      {
         point_type = 2;
      }
      else 
      {
         point_type = 3;
      }

      int hi = 10;
      int low = 0;
      for(int x=0; x<count; x++) 
      {
         System.out.print(Standard.getRandomInt(low, hi) + " " + Standard.getRandomInt(low, hi));
         if( point_type == 3 ) 
         {
            System.out.print(" " + Standard.getRandomInt(low, hi) );
         }
         System.out.println("");
      }   

      // plot using corejava package
//      RandomIntGenerator r2 = new RandomIntGenerator(low, hi);
      input = GUITools.getString("How many points?");
      count = Integer.parseInt(input);
      File outfile = new File("data2.dat");
      StringBuffer buff =new StringBuffer();
      input = GUITools.getString("2D or 3D points?");
      if( input.equalsIgnoreCase("2D") ) 
      {
         point_type = 2;
      }
      else 
      {
         point_type = 3;
      }
      for(int x=0; x<count; x++) 
      {
         buff.append( Standard.getRandomInt(low, hi) );
         buff.append(" ");
         buff.append( Standard.getRandomInt(low, hi) );
         if( point_type == 3 ) 
         {
            buff.append(" ");
            buff.append( Standard.getRandomInt(low, hi) );
         }
         buff.append("\n");
      }
      
      TextFileWriter tfw = new TextFileWriter();

      tfw.writeText(outfile, buff.toString() );
   }

   static class Standard
   {
       	public static int getRandomInt(int low, int high) 
        {
		int value;
		// pick random integer between low and high
		value = low + ( int ) ( Math.random() * (high-low+1) );

		return value;
	}
   }
   
}
