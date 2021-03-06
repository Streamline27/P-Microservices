package lv.citadele.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableZuulProxy
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApplication {

    /**
     * http://localhost:8080/swagger-ui.html
     * Load balanced swagger for both services
     */
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}

