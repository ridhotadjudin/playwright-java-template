package id.nexchief.stepDefinitions;

import id.nexchief.pageobject.Login;
import id.nexchief.utils.TestBase;
import id.nexchief.utils.TestContext;
import id.nexchief.utils.TestDataContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;

import java.io.IOException;

public class _Hooks extends TestBase {
    private static Logger log = Logger.getLogger(_Hooks.class);
    private Login login = new Login();

    @Before
    public void before(Scenario scenario) {
        try {
            TestContext.setScenario(scenario);
            TestContext.log("Executing scenario : " + scenario.getName());
            initBrowser();
            login();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void login() throws IOException {
        String environment = TestDataContext.getEnvironmentProfile();
        String url = TestDataContext.getEnvironmentUrl(environment);
        String username = TestDataContext.getUsername();
        String password = TestDataContext.getPassword();
        login.doLogin(url, username, password);
        playwright.selectors().setTestIdAttribute("data-id");
    }

    private void getFinalScreenshot() {
        try {
            TestContext.logWithScreenshot("Final Screenshot");
        } catch (Exception e) {
            log.debug("Failed to get the final screenshoot");
            e.printStackTrace();
        }

    }

}
