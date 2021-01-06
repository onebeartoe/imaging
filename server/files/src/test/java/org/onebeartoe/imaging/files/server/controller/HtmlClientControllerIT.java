
package org.onebeartoe.imaging.files.server.controller;

import java.net.URL;
import static org.assertj.core.api.Assertions.assertThat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.onebeartoe.imaging.files.server.JavaSpringRestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        ,
        classes = 
        {
            JavaSpringRestApplication.class
        }
)
//TODO: change my name!!!
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
        base = new URL("http://localhost:" + port + "/randomPage");
    }

    public String randomImagePageIsPresent() throws Exception 
    {        
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        
        String body = response.getBody();
        
        assertThat(body).contains("Random Image");        // the title
    
        // parse the URL
        
        Document doc = Jsoup.parse(body);
        
        Element imageElement = doc.getElementById("randomImage");
        
        String src = imageElement.attr("src");
        
        return src;
    }
    
    /**
     * This test handles user story US0-AC3.
     */    
    @Test 
    public void htmlClientImagesAreRandom() throws Exception
    {
        String image0 = randomImagePageIsPresent();      
        assertNotNull(image0);
        
        String image1 = randomImagePageIsPresent();      
        assertNotNull(image1);        
        assertNotEquals(image0, image1);
        
        String image2 = randomImagePageIsPresent();
        assertNotNull(image2);

        assertNotEquals(image1, image2);
    }
}
