
package org.onebeartoe.imaging.files.server.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 *
 */
@Controller
public class RandomImagePageController 
{
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private RandomImageService service;
    
    @RequestMapping("/random")
    public String next(Map<String, Object> model) 
    {
        model.put("message", "You are in new page, imager!!");

        model.put("currentPort: ", request.getServerPort() );
        
        String url = service.randomImageUrl();
        
//http://someimage-server:8080$        
        
        model.put("randomImageUrl", "/file/" + url);
        
        return "random";
    }

    @RequestMapping("/other")
    public String other(Map<String, Object> model) 
    {
        model.put("message", "You are in new page, imager!!");
    
        return "other/path";
    }    
}
