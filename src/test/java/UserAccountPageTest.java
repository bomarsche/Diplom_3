import StellarBurgersAPI.User;
import StellarBurgersAPI.UserClient;
import StellarBurgersAPI.UserGenerator;
import StellarBurgersPOM.AccountPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static StellarBurgersAPI.BaseRestClient.LOGIN_URL;


public class UserAccountPageTest extends BaseTest {

    private AccountPage objAccountPage;

    private User user;
    private UserClient userClient;
    private String accessToken = null;

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
    }
}
