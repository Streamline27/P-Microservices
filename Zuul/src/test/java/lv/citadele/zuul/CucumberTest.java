package lv.citadele.zuul;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "classpath:lv.citadele.zuul.steps")
public class CucumberTest {
}
