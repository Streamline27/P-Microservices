package lv.citadele.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Value("${message:MessageA}")
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return "storage: message is " + message;
    }
}
