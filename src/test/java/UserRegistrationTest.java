import StellarBurgersAPI.User;
import StellarBurgersAPI.UserClient;
import StellarBurgersAPI.UserGenerator;
import StellarBurgersPOM.*;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


import static StellarBurgersAPI.BaseRestClient.LOGIN_URL;

public class UserRegistrationTest {

    private WebDriver driver;
    private RegistrationPage objRegistrationPage;

    private User user;
    private UserClient userClient;

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
        objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.openURL();

        user = UserGenerator.getRandomFullData();
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    public void checkRegistrationUserTest() {
        objRegistrationPage.isHeaderBlockDisplayed();
        objRegistrationPage.isRegFormDisplayed();
        objRegistrationPage.completeRegistration(user.getName(), user.getEmail(), user.getPassword());
        objRegistrationPage.checkAfterRegister();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(LOGIN_URL, url);
        objRegistrationPage.checkAfterRegister();
    }

    @Test
    @DisplayName("Регистрация пользователя с невалидными данными")
    public void checkRegistrationUserWithWrongPassTest() {
        objRegistrationPage.isHeaderBlockDisplayed();
        objRegistrationPage.isRegFormDisplayed();
        objRegistrationPage.completeRegistration(user.getName(), user.getEmail(), "123");
        objRegistrationPage.getPasswordValidationAlert();
    }

    @Test
    @DisplayName("Регистрация пользователя с уже зарегистрированным email")
    public void checkRegistrationUserRecurringEmailTest() {
        objRegistrationPage.isHeaderBlockDisplayed();
        objRegistrationPage.isRegFormDisplayed();
        userClient.createUser(user);
        objRegistrationPage.completeRegistration(user.getName(), user.getEmail(), user.getPassword());
        objRegistrationPage.getAlreadyExistsAlert();
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
