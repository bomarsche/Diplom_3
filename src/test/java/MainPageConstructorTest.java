import StellarBurgersPOM.*;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainPageConstructorTest {

    private WebDriver driver;
    private MainPage objMainPage;

    @Before
    public void setUp(){
        //Проверяем соответствие версий вебдрайвера и браузера в системе
        WebDriverManager.chromedriver().setup();


        // Chromedriver init
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*", "--disable-extensions", "start-maximized");
        driver = new ChromeDriver(options);

        // Yandexdriver init
        /*ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\projects\\praktikum\\yandexdriver\\yandexdriver.exe");
        options.setBinary("C:\\Users\\bomar\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);*/
    }

    @Before
    public void startUpPage() {
        objMainPage = new MainPage(driver);
        objMainPage.openURL();
    }

    @Test
    @DisplayName("Проверка конструктора: Таб Булки")
    public void checkConstructorTabsBunTest() {
        objMainPage.isHeaderBlockDisplayed();
        objMainPage.isAccountLinkDisplayed();
        objMainPage.isConstructorBlockDisplayed();
        objMainPage.isBunTabActive();
    }

    @Test
    @DisplayName("Проверка конструктора: Таб Соусы")
    public void checkConstructorTabsSauceTest() {
        objMainPage.isHeaderBlockDisplayed();
        objMainPage.isAccountLinkDisplayed();
        objMainPage.isConstructorBlockDisplayed();
        objMainPage.isBunTabActive();
        objMainPage.openSauceTab();
        objMainPage.isSauceTabActive();
    }

    @Test
    @DisplayName("Проверка конструктора: Таб Начинки")
    public void checkConstructorTabsFillingTest() {
        objMainPage.isHeaderBlockDisplayed();
        objMainPage.isAccountLinkDisplayed();
        objMainPage.isConstructorBlockDisplayed();
        objMainPage.isBunTabActive();
        objMainPage.openFillingTab();
        objMainPage.isFillingTabActive();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
