package lv.citadele.resource;

import lv.citadele.resource.security.TokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new TokenInterceptor());
        return template;
    }
}

