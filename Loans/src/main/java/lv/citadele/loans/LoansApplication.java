package lv.citadele.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
public class LoansApplication {

    /**
     *  When running two instances use VM params:
     *  -Dserver.port=8095 -Dmicro-service.instance-name=LoansInstanceA
     */
    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}

