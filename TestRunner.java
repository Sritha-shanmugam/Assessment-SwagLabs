package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "D:/Assessment/SwagLab/src/main/features/Products.feature",glue = "stepDefinitions")
public class TestRunner {
}

