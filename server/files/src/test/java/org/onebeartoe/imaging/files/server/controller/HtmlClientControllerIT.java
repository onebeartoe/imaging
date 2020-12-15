
package org.onebeartoe.imaging.files.server.controller;

import java.net.URL;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.onebeartoe.imaging.files.server.JavaSpringRestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

/**
 * 
 * AC3 mabye????
 */
//TODO: change my name!!!
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        ,
        classes = 
        {
            JavaSpringRestApplication.class
        }
)
public class HtmlClientControllerIT
{
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;    
    
    @BeforeEach
    public void setUp() throws Exception 
    {
        this.base = new URL("http://localhost:" + port + "/random.jsp");
    }

    @Test
    public void randomImagePageIsPresent() throws Exception 
    {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        
        String body = response.getBody();
        
        assertThat(body).contains("Random Image");        // the title
        
        assertThat(body).contains("<img src=");        // an image tag
        
        assertThat(body).contains("http");        // an image URL
    }
    
}
