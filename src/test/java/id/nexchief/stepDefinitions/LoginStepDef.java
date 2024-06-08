package id.nexchief.stepDefinitions;

import id.nexchief.pageobject.Login;
import io.cucumber.java.en.Given;
import org.apache.log4j.Logger;

public class LoginStepDef {
    private static final Logger log = Logger.getLogger(LoginStepDef.class);
    private final Login login = new Login();

    @Given("user open web and do login")
    public void verifyLoginPage() {
        login.verifyLoginPage();
    }


}
