package id.nexchief.utils;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;

public class TestContext extends TestBase {
    private static final int SCREENSHOT_TARGET_WIDTH = 850;
    private static Logger log = Logger.getLogger(TestContext.class);
    static GenObjGui genObjGui = new GenObjGui();
    private static Scenario scenario;
}
