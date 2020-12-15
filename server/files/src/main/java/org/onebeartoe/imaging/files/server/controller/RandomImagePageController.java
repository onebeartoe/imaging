
package org.onebeartoe.imaging.files.server.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 *
 */
@Controller
public class RandomImagePageController 
{
    @RequestMapping("/random")
    public String next(Map<String, Object> model) 
    {
        model.put("message", "You are in new page, imager!!");
    
        return "random";
    }

    @RequestMapping("/other")
    public String other(Map<String, Object> model) 
    {
        model.put("message", "You are in new page, imager!!");
    
        return "other/path";
    }    
}
