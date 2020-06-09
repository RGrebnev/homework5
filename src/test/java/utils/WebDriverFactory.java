package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);
    // create driver without options
    public static WebDriver createNewDriver(String name) {
        switch (getBrowserName(name)) {
            case FIREFOX:
                return getFirefoxDriver();
            case CHROME:
                return getChromeDriver();
            default:
                logger.warn("Option not configured. Default browser selected: chrome");
                return null;
        }
    }

    //create driver with options
    public static WebDriver createNewDriver(String name, MutableCapabilities capabilities) {
        switch (getBrowserName(name)) {
            case FIREFOX:
                if (capabilities instanceof FirefoxOptions) {
                    return getFirefoxDriver((FirefoxOptions) capabilities);
                } else {
                    logger.warn("Invalid browser options! No options selected.");
                    return getFirefoxDriver();
                }
            case CHROME:
                if (capabilities instanceof ChromeOptions) {
                    return getChromeDriver((ChromeOptions) capabilities);
                } else {
                    logger.warn("Invalid browser options! No options selected.");
                    return getChromeDriver();
                }
            default:
                logger.warn("Option not configured. Default browser selected: chrome without capabilities");
                return null;
        }
    }

    private static BrowserNames getBrowserName(String name) {
        try {
            return BrowserNames.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException ex) {
            logger.warn("Invalid argument. Default browser selected: Chrome");
            return BrowserNames.CHROME;
        }
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getChromeDriver(ChromeOptions options) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver getFirefoxDriver(FirefoxOptions options) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }
}
