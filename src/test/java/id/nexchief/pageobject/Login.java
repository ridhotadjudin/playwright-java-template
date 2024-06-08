package id.nexchief.pageobject;

import com.microsoft.playwright.Locator;
import id.nexchief.utils.GenObjGui;

import static id.nexchief.utils.TestBase.page;

public class Login extends BasePageObject {
    private final GenObjGui genObjGui = new GenObjGui();

    public void doLogin(String url, String username, String password) {
        try {
            page.navigate(url);
            page.locator("//input[@id='username']").isVisible(new Locator.IsVisibleOptions().setTimeout(15000));
            page.locator("//input[@id='username']").fill(username);
            page.locator("//input[@id='password']").fill(password);
            page.locator("//button[@type='submit']").click();
        } catch (Exception e) {
            page.locator("").click();
        }
    }
}
