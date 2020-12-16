
package org.onebeartoe.imaging.shapes.controller;

import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.onebeartoe.imaging.shapes.ShapesApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        ,
        classes = 
        {
            ShapesApplication.class
        }
)
public class ManyCirclesControllerIT 
{
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;    
    
    @BeforeEach
    public void setUp() throws Exception 
    {
        this.base = new URL("http://localhost:" + port + "/circles/some.png");
    }
    
    @Test
    public void randomImagePageIsPresent() throws Exception 
    {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        
        MediaType contentType = response.getHeaders().getContentType();
        
        assertEquals(contentType.getType(), "image");        
    }
    
    @Test
    public void reqq()
    {
        assertTrue(true);
    }
}
