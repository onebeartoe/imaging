
package org.onebeartoe.imaging.files.server.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.onebeartoe.imaging.files.server.JavaSpringRestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * This class verifies US0-AC2 and US0-AC5.
 */
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

    @Autowired
    private TestRestTemplate template;    
 
    private static Logger logger;
    
    @BeforeAll
    public static void setUp() throws Exception 
    {
        logger = Logger.getLogger( "ImageControllerIT" );
    }
    
    private void assertImageIsPresent(String subpath) throws MalformedURLException
    {
        URL base = setupUrl(subpath);
        
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
    
    
    @Test
    public void knownImageIsPresent() throws Exception 
    {
        String subpath = "square-1.png";
        
        assertImageIsPresent(subpath);
    }

    @Test
    public void knownImageIsPresentInSubDirectory() throws Exception 
    {        
        String subpath = "digits/square-10.png";
        
        assertImageIsPresent(subpath);                
    }

    @Test
    public void knownImageWithSpacesIsPresent() throws Exception 
    {
        String subpath = "space dir/space image.png";

        assertImageIsPresent(subpath);
    }    

    private URL setupUrl(String subpath) throws MalformedURLException 
    {
        // a default testing image found under the src/test/recources/ directory
        
        String url = "http://localhost:" + port + "/file/" + subpath;
        
        logger.info("setupURL: " + url);
        
        URL base = new URL(url);

        return base;
    }
}
