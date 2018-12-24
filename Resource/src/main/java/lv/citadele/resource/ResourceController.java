package lv.citadele.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResourceController {

    private final RestTemplate template;

    @Value("${message:MessageResourceA}")
    private String message;

    @Autowired
    public ResourceController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/hello")
    public String get() {
        return "Hello " + message;
    }

    @GetMapping("/storage")
    public String helloFromStorage() {
        return template.getForObject("http://storage-node/storage/hello", String.class);
    }
}
