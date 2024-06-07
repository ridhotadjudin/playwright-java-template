package id.nexchief.staging.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false,
        tags = "@login_SANITY",
        features = "src/test/resources",
        glue = "id.nexchief.staging.stepDefinitions",
        plugin = "json:target/cucumber/result/json/debug.json"
)

public class DebugRunner {
}
