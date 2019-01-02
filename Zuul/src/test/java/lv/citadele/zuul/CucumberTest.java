package lv.citadele.zuul;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lv.citadele.zuul.steps.LoanRequestSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "classpath:lv.citadele.zuul.steps")
public class CucumberTest {
}
