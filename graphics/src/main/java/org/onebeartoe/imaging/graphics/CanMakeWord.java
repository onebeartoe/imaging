
package org.onebeartoe.imaging.graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author roberto
 */
public class CanMakeWord 
{
    public static void main(String[] args) 
    {
        CanMakeWord app = new CanMakeWord();
        
        Character[][] chars={{'R','A','B'},{'C','V'},{'T','L'},{'X'}};
        String word1="CALL"; //contains 3 distinct characters
        String word2="RAT"; //contains 3 distinct characters
        String word3="SAD";
        
        System.out.println(app.canMakeWords(chars,word1)  ); //true
        System.out.println(app.canMakeWords(chars,word2)  ); //true
        System.out.println(app.canMakeWords(chars,word3)  ); //false
    }
    
    public boolean canMakeWords(Character[][] chars, String word)
    {
        int foundCount = 0;
        
        int outerLenght = chars.length;
        
        boolean can = false;
        
        List<Character> rowList = null;
        
        for(int x = 0; x<outerLenght; x++)
        {
            Character[] row = chars[x];
            
            rowList = Arrays.asList(row);
            

        }
        

        List<Character> uniqRow = new  ArrayList();

        for(Character c : rowList)
        {
            String cStr = String.valueOf(c);

            if( word.contains(cStr) )
            {
                if( !uniqRow.contains(c) )
                {
                    uniqRow.add(c);
                }
            }
        }
        
        for(Character c : rowList)
        {
            String sStr = String.valueOf(c);
            
            if(uniqRow.contains(sStr))
            {
                foundCount++;
            }
        }
        
        if(foundCount == word.length())
        {
            can = true;
        }        
        
        return can;
    }    
}
