package springmvc.buddyinfo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("<button type=\"button\" id=\"createAddy\">Create Address Book</button>");
    }

    @Test
    public void greetingShouldReturnMessage() throws Exception {
        System.out.println("Hey");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/home",
                String.class)).contains("<button type=\"submit\">Add new book</button>");
    }
}
