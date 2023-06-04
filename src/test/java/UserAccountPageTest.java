import StellarBurgersAPI.User;
import StellarBurgersAPI.UserClient;
import StellarBurgersAPI.UserGenerator;
import StellarBurgersPOM.AccountPage;

import StellarBurgersPOM.LoginPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static StellarBurgersAPI.BaseRestClient.LOGIN_URL;


public class UserAccountPageTest {
    private WebDriver driver;
    private AccountPage objAccountPage;

    private User user;
    private UserClient userClient;
    private String accessToken = null;

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
        objAccountPage = new AccountPage(driver);
        objAccountPage.openURL();

        user = UserGenerator.getRandomFullData();
        userClient = new UserClient();
        accessToken = userClient.createUser(user).extract().path("accessToken");
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void accountPageEntranceTest() {
        objAccountPage.isHeaderBlockDisplayed();
        objAccountPage.isAccountLinkDisplayed();
        objAccountPage.openLoginFormFromAccountLink();
        objAccountPage.isLoginFormDisplayed();
        objAccountPage.completeLogin(user.getEmail(), user.getPassword());
        objAccountPage.isOrderButtonEnabled();
        objAccountPage.openAccountPage();
        objAccountPage.isAccountNavDisplayed();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void moveToConstructorFromAccountPageTest() {
        objAccountPage.isHeaderBlockDisplayed();
        objAccountPage.isAccountLinkDisplayed();
        objAccountPage.openLoginFormFromAccountLink();
        objAccountPage.isLoginFormDisplayed();
        objAccountPage.completeLogin(user.getEmail(), user.getPassword());
        objAccountPage.isOrderButtonEnabled();
        objAccountPage.openAccountPage();
        objAccountPage.isAccountNavDisplayed();
        objAccountPage.clickToConstructorLink();
        objAccountPage.isConstructorBlockDisplayed();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главноую по клику на логотип")
    public void moveToMainFromAccountPageTest() {
        objAccountPage.isHeaderBlockDisplayed();
        objAccountPage.isAccountLinkDisplayed();
        objAccountPage.openLoginFormFromAccountLink();
        objAccountPage.isLoginFormDisplayed();
        objAccountPage.completeLogin(user.getEmail(), user.getPassword());
        objAccountPage.isOrderButtonEnabled();
        objAccountPage.openAccountPage();
        objAccountPage.isAccountNavDisplayed();
        objAccountPage.clickToLogo();
        objAccountPage.isConstructorBlockDisplayed();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void accountExitTest() {
        objAccountPage.isHeaderBlockDisplayed();
        objAccountPage.isAccountLinkDisplayed();
        objAccountPage.openLoginFormFromAccountLink();
        objAccountPage.isLoginFormDisplayed();
        objAccountPage.completeLogin(user.getEmail(), user.getPassword());
        objAccountPage.isOrderButtonEnabled();
        objAccountPage.openAccountPage();
        objAccountPage.isAccountNavDisplayed();
        objAccountPage.logout();
        objAccountPage.isLoginFormDisplayed();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(LOGIN_URL, url);
    }

    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isBlank()) {
            System.out.println(accessToken);
            userClient.deleteUser(accessToken);
        }
        driver.quit();
    }

}
