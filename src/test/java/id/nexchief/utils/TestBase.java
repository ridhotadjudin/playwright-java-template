package id.nexchief.utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;

public class TestBase {
    private static final Logger log = Logger.getLogger(TestBase.class);
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;

    public void initBrowser() throws IOException {
        playwright = Playwright.create();

        String browserName = TestDataContext.getBrowserName();
        boolean headless = TestDataContext.getHeadless();
        boolean recordingVideo = TestDataContext.getRecordingVideo();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new LaunchOptions().setHeadless(headless));
                break;
            case "edge":
                browser = playwright.webkit().launch(new LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new LaunchOptions().setHeadless(headless));
                break;
        }

        if (recordingVideo) {
            browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize((int)screenSize.width,(int)screenSize.height).setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(screenSize.width, screenSize.height));
        } else {
            browserContext = browser.newContext(new Browser.NewContextOptions());
        }

        // start tracing before creating / navigating a page
        browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));

        page = browserContext.newPage();
        page.setDefaultTimeout(Double.parseDouble(TestDataContext.getTimeout()));
    }

}
