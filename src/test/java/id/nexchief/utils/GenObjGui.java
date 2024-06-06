package id.nexchief.utils;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.log4j.Logger;

public class GenObjGui extends TestBase {
    private final Logger log = Logger.getLogger(GenObjGui.class);
    private final int DEFAULT_RETRY_COUNT = 30;
    private final int DEFAULT_TIMEOUT_SECONDS = 1;

    public void click(String selector) {
        page.click(selector);
    }

    public void clickByXpath(String xpath) {
        page.locator(xpath).click();
    }

    public void fill(String selector, String value) {
        page.fill(selector, value);
    }

    public void fillByXpath(String xpath, String value) {
        page.locator(xpath).fill(value);
    }

    public String getText(String selector) {
        return page.textContent(selector);
    }

    public ElementHandle getHandle(String selector) {
        return page.querySelector(selector);
    }

    public void scrollIntoView(String selector, int wheel) {
        int iteration = 0;
        do {
            page.mouse().wheel(0, wheel);
            iteration++;
        } while (iteration < 5 && page.locator(selector).count() == 0);
    }

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPresent(String selector) {
        if (page.locator(selector).count() <= 0) return false;
        return true;
    }

    public void reloadPage() {
        page.reload();
    }

    public void waitException(String selector, long millis) {
        try {
            page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(millis));
        } catch (TimeoutError e) {
            System.out.println("Timeout!");
        }
    }
}
