
package org.onebeartoe;

import java.util.List;

public class InterviewTest 
{
    public static void main(String[] args) 
    {
        InterviewTest test = new InterviewTest();
                
        test.printOnlyAfphas();
        
        test.firstCharacterIsUppercaes();
    }

    /**
     * Given a string with integers in it. please print only string but not digits. Ex: "A2B4G56GH2TQW1HL"
     */
    private void printOnlyAfphas() 
    {
        String input = "A2B4G56GH2TQW1HL";
        
        byte[] bytes = input.getBytes();
        
        for(byte b : bytes)
        {
            char c = (char) b;
                        
            if(Character.isAlphabetic(c) )
            {
                System.out.print(c);
            }
        }            
    }    

    /**
     * a) Print the sentance where first character of each word should start with upper case.
     */
    private void firstCharacterIsUppercaes() 
    {
        String input = "welcom to hcl interview process";
        
        String[] split = input.split("\\w");
        
        for(String s : split)
        {
            char charAt = s.charAt(0);
            
            String replace = String.valueOf(charAt).toUpperCase();
            
            String ending = s.substring(1);
            
            String output = 
            
            s.replaceFirst(replace, replace)
        });                
    }
}




/**

 who have a salary in empoly salary table 
 
 select * from employee_details ed
 join on employ_salary es
 where ed.id = es.id
 
 */


/*

from employ salary table retrieve second max salary

select min from
(
    select max(salary) as min
    from employee_salary
    limit 2
)

*/
