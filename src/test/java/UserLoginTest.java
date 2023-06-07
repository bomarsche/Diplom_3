import StellarBurgersAPI.User;
import StellarBurgersAPI.UserClient;
import StellarBurgersAPI.UserGenerator;
import StellarBurgersPOM.LoginPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static StellarBurgersAPI.BaseRestClient.BASE_URL;


public class UserLoginTest extends BaseTest {
    private LoginPage objLoginPage;
    private User user;
    private UserClient userClient;
    private String accessToken = null;

    @Before
    public void startUpPage() {
        objLoginPage = new LoginPage(driver);
        objLoginPage.openURL();

        user = UserGenerator.getRandomFullData();
        userClient = new UserClient();
        accessToken = userClient.createUser(user).extract().path("accessToken");
    }

    @Test
    @DisplayName("Авторизация пользователя с валидными данными через кнопку 'Личный кабинет'")
    public void checkLoginAccountTest() {
        objLoginPage.isHeaderBlockDisplayed();
        objLoginPage.isAccountLinkDisplayed();
        objLoginPage.openLoginFormFromAccountLink();
        objLoginPage.isLoginFormDisplayed();
        objLoginPage.completeLogin(user.getEmail(), user.getPassword());
        objLoginPage.isOrderButtonEnabled();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(BASE_URL, url);

    }

    @Test
    @DisplayName("Авторизация пользователя с валидными данными через кнопку 'Войти в аккаунт'")
    public void checkLoginEntranceTest() {
        objLoginPage.isHeaderBlockDisplayed();
        objLoginPage.isAccountLinkDisplayed();
        objLoginPage.openLoginFormFromEnterLink();
        objLoginPage.isLoginFormDisplayed();
        objLoginPage.completeLogin(user.getEmail(), user.getPassword());
        objLoginPage.isOrderButtonEnabled();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(BASE_URL, url);
    }

    @Test
    @DisplayName("Авторизация пользователя через форму регистрации")
    public void checkLoginRegistrationTest() {
        objLoginPage.openRegUrl();
        objLoginPage.isHeaderBlockDisplayed();
        objLoginPage.isAccountLinkDisplayed();
        objLoginPage.isRegLoginLinkDisplayed();
        objLoginPage.openLoginForm();
        objLoginPage.isLoginFormDisplayed();
        objLoginPage.completeLogin(user.getEmail(), user.getPassword());
        objLoginPage.isOrderButtonEnabled();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(BASE_URL, url);

    }

    @Test
    @DisplayName("Авторизация пользователя через форму восстановления пароля")
    public void checkLoginForgotPassTest() {
        objLoginPage.isHeaderBlockDisplayed();
        objLoginPage.isAccountLinkDisplayed();
        objLoginPage.openLoginFormFromAccountLink();
        objLoginPage.isLoginFormDisplayed();
        objLoginPage.openForgotPassForm();
        objLoginPage.isForgotPassLoginLinkDisplayed();
        objLoginPage.returnToLoginForm();
        objLoginPage.isLoginFormDisplayed();
        objLoginPage.completeLogin(user.getEmail(), user.getPassword());
        objLoginPage.isOrderButtonEnabled();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(BASE_URL, url);

    }

    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isBlank()) {
            System.out.println(accessToken);
            userClient.deleteUser(accessToken);
        }
    }
}
