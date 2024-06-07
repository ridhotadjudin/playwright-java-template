package id.nexchief.utils;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TestContext extends TestBase {
    private static final int SCREENSHOT_TARGET_WIDTH = 850;
    private static Logger log = Logger.getLogger(TestContext.class);
    static GenObjGui genObjGui = new GenObjGui();
    private static Scenario scenario;

    public static void setScenario(Scenario scenario) {
        TestContext.scenario = scenario;
    }

    public static Scenario getScenario() {
        return TestContext.scenario;
    }

    public static void log(String message) {
        log.info(message);
        scenario.log(message);
    }

    public static void logWithScreenshot(String message) {
        try {
            byte[] imagePngBytes = page.screenshot();
            byte[] resizedPngBytes = get
        }
    }

    public static byte[] getResizedPngBytes(byte[] imagePngBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imagePngBytes);
        BufferedImage rawImage = ImageIO.read(bais);
        BufferedImage resizedImage = resizeImage
    }

    private static BufferedImage resizedImage(BufferedImage sourceImage, int targetWidth) {
        float ratio = (float) targetWidth / (float) sourceImage.getWidth();
        float calculatedHeight = ratio * (float) sourceImage.getHeight();
        int targetHeight = (int) calculatedHeight;
        BufferedImage resizedImage = Scalr.resize(sourceImage, targetWidth, targetHeight);
        return resizedImage;
    }
}
