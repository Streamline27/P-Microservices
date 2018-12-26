package lv.citadele.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.HashMap;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/auth/**").uri("lb://auth-server"))
                .route(p -> p.path("/resource/**").uri("lb://resource-node"))
                .route(p -> p.path("/storage/**").uri("lb://storage-node"))
                .route(p -> p.path("/process/**").uri("lb://process-node"))
                .route(p -> p.path("/loans/**").uri("lb://loans-node"))
                .build();
    }

    @Bean
    public CorsConfiguration corsConfiguration(RoutePredicateHandlerMapping routePredicateHandlerMapping) {
        /*
            Workaround to enable proxy for Swagger UI without changing spring cloud MILESTONE
            https://github.com/spring-cloud/spring-cloud-gateway/issues/229
         */
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedOrigin("*");

        HashMap<String, CorsConfiguration> config = new HashMap<>();
        config.put("/**", corsConfiguration);
        routePredicateHandlerMapping.setCorsConfigurations(config);

        return corsConfiguration;
    }

}

