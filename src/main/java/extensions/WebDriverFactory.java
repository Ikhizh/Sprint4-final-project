package extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static config.AppConfig.URL;
import static config.WebDriverConfig.BROWSER;
import static config.WebDriverConfig.WAIT_SECONDS_TIME_OUT;

public class WebDriverFactory {
    public static WebDriver get() {
        WebDriver driver;
        switch (BROWSER) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser " + BROWSER + " not exist");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_SECONDS_TIME_OUT, TimeUnit.SECONDS);
        driver.navigate().to(URL);
        return driver;
    }
}
