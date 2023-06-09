import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    public WebDriver driver;

    private WebDriver getDriver(String browserName) {
        if ("chrome".equals(browserName)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--disable-extensions", "start-maximized");
            return driver = new ChromeDriver(options);
        } else if ("yandex".equals(browserName)) {
            ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", "D:\\projects\\praktikum\\yandexdriver\\yandexdriver.exe");
            options.setBinary("C:\\Users\\bomar\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--remote-allow-origins=*");
            return driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Тестирование в данном браузере невозможно");
        }
    }

    @Before
    public void setUp() {
        driver = getDriver("yandex");
    }


    @After
    public void cleanUp() {
        driver.quit();
    }
}
