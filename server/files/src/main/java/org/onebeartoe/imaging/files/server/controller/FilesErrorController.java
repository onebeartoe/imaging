package org.onebeartoe.imaging.files.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//TODO: rename this please
public class FilesErrorController implements ErrorController 
{
  @RequestMapping("/error")
  @ResponseBody
  public String handleError(HttpServletRequest request) 
  {
      Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

      Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

      String html = "<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                      + "<div>Exception Message: <b>%s</b></div><body></html>";
      
      return String.format(html, statusCode, exception==null? "N/A": exception.getMessage());
  }

  @Override
  public String getErrorPath() 
  {
      return "/error";
  }
}