package id.nexchief.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false,
        tags = "@login_SANITY",
        features = "src/test/scenarios/",
        glue = "id.nexchief.stepDefinitions",
        plugin = "json:target/cucumber/result/json/debug.json"
)

public class DebugRunner {
}
