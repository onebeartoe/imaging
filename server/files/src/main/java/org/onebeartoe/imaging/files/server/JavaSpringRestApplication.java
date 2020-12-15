
package org.onebeartoe.imaging.files.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JavaSpringRestApplication extends SpringBootServletInitializer
{
    @Override 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(JavaSpringRestApplication.class);
    }
    
    public static void main(String[] args) 
    {
        SpringApplication.run(JavaSpringRestApplication.class, args);
    }
}
