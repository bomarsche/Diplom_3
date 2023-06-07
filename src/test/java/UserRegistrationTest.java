import StellarBurgersAPI.User;
import StellarBurgersAPI.UserClient;
import StellarBurgersAPI.UserGenerator;
import StellarBurgersPOM.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static StellarBurgersAPI.BaseRestClient.LOGIN_URL;

public class UserRegistrationTest extends BaseTest {

    private RegistrationPage objRegistrationPage;
    private User user;
    private UserClient userClient;


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

}
