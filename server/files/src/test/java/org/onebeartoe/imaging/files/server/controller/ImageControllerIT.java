
package org.onebeartoe.imaging.files.server.controller;

import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.onebeartoe.imaging.files.server.JavaSpringRestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        ,
        classes = 
        {
            JavaSpringRestApplication.class
        }
)
public class ImageControllerIT 
{
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;    
    
    @BeforeEach
    public void setUp() throws Exception 
    {
        // a default testing image found under the src/test/recources/ directory
        base = new URL("http://localhost:" + port + "/file/square-1.png");
    }
    
    @Test
    public void randomImagePageIsPresent() throws Exception 
    {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        
        HttpStatus statusCode = response.getStatusCode();
        
        HttpStatus expectedStatus = HttpStatus.OK;

        assertEquals(expectedStatus, statusCode);
        
        MediaType contentType = response.getHeaders().getContentType();
        
        if(contentType == null)
        {
            String body = response.getBody();
            
            System.out.println("no content type body: " + body);
        }
        
        String expected = "image";
        
        assertEquals(expected, contentType.getType());        
    }
}
