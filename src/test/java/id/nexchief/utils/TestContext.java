package id.nexchief.utils;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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
            byte[] resizedPngBytes = getResizedPngBytes(imagePngBytes);
            logWithScreenshot(message, resizedPngBytes);
        } catch (IOException e) {
            log.debug("Error on attaching image to scenario");
        }
    }

    public static void logWithScreenshot(String message, byte[] imagePngBytes) {
        try {
            byte[] resizedPngBytes = getResizedPngBytes(imagePngBytes);
            log.info(message);
            scenario.attach(resizedPngBytes, "image/png", message);
        } catch (Exception e) {
            log.debug("Error on attaching image to scenario", e);
        }
    }

    public static void logWithScreenshot(String message, List<byte[]> imagePngBytes) {
        try {
            byte[] compileScreenshotList = compileScreenshotList(imagePngBytes, false);
            log.info(message);
            scenario.attach(compileScreenshotList, "image/png", message);
        } catch (Exception e) {
            log.debug("Error on attaching image to scenario", e);
        }
    }

    public static byte[] getResizedPngBytes(byte[] imagePngBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imagePngBytes);
        BufferedImage rawImage = ImageIO.read(bais);
        BufferedImage resizedImage = resizedImage(rawImage,SCREENSHOT_TARGET_WIDTH);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "png", baos);
        byte[] resizedPngBytes = baos.toByteArray();
        return resizedPngBytes;
    }

    private static BufferedImage resizedImage(BufferedImage sourceImage, int targetWidth) {
        float ratio = (float) targetWidth / (float) sourceImage.getWidth();
        float calculatedHeight = ratio * (float) sourceImage.getHeight();
        int targetHeight = (int) calculatedHeight;
        BufferedImage resizedImage = Scalr.resize(sourceImage, targetWidth, targetHeight);
        return resizedImage;
    }

    private static byte[] compileScreenshotList(List<byte[]> imageByteList, boolean resize) throws IOException {
        int height = 0;
        int width = 0;
        for (byte[] imageByte : imageByteList) {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
            BufferedImage imageToDraw = ImageIO.read(bais);
            if (resize) {
                imageToDraw = resizedImage(imageToDraw, SCREENSHOT_TARGET_WIDTH);
            }
            width = imageToDraw.getWidth();
            height = height + imageToDraw.getHeight();
        }

        BufferedImage imageCanvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = imageCanvas.createGraphics();
        int heightIndex = 0;
        for (byte[] imageByte : imageByteList) {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
            BufferedImage imageToDraw = ImageIO.read(bais);
            graphics2D.drawImage(imageToDraw, 0, heightIndex, null);
            heightIndex = heightIndex + imageToDraw.getHeight();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(imageCanvas, "png", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
