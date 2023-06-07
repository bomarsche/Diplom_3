package StellarBurgersPOM;

import StellarBurgersAPI.BaseRestClient;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;


    //Header
    private By headerBlock = By.xpath(".//header[contains(@class, 'AppHeader_header__X9aJA')]");
    private By accountLink = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text() = 'Личный Кабинет']");

    //Войти в аккаунт
    private By signInButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Войти в аккаунт')]");
    private By orderButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Оформить заказ')]");

    //Форма логина
    private By loginForm = By.xpath(".//form[contains(@class, 'Auth_form__3qKeq')]");
    private By loginFormEmailField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Email')]/../input[@class='text input__textfield text_type_main-default']");
    private By loginFormPasswordField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Пароль')]/../input[@class='text input__textfield text_type_main-default']");
    private By loginFormSubmit = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Войти')]");
    private By loginFormForgotPassLink = By.xpath(".//a[@class='Auth_link__1fOlj' and contains(text(), 'Восстановить пароль')]");


    //Восстановить пароль
    private By forgotPassFormSignInLink = By.xpath(".//a[@class='Auth_link__1fOlj' and contains(text(), 'Войти')]");

    //Войти в регистрации
    private By regSignInButton = By.xpath(".//a[text()='Войти']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод перехода по урлу
    public void openURL() {
        driver.get(BaseRestClient.BASE_URL);
    }


    //Базовый метод проверки отображения элемента
    public void elementVisibilityWait(By waitElement) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        elementWait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
    }

    @Step("Проверка отображения хэдера страницы")
    public boolean isHeaderBlockDisplayed() {
        elementVisibilityWait(headerBlock);
        return driver.findElement(headerBlock).isDisplayed();
    }

    @Step("Проверка отображения кнопки Личный кабинет в хэдере")
    public boolean isAccountLinkDisplayed() {
        elementVisibilityWait(accountLink);
        return driver.findElement(accountLink).isDisplayed();
    }

    //Методы перехода к форме логина
    @Step("Клик по кнопке Личный кабинет в хэдере (без авторизации)")
    public void openLoginFormFromAccountLink() {
        driver.findElement(accountLink).click();
    }

    @Step("Клик по кнопке Войти в аккаунт (без авторизации)")
    public void openLoginFormFromEnterLink() {
        driver.findElement(signInButton).click();
    }

    @Step("Проверка отображения формы логина")
    public boolean isLoginFormDisplayed() {
        elementVisibilityWait(loginForm);
        return driver.findElement(loginForm).isDisplayed();
    }

    //Методы заполнения формы
    public void setEmail(String email) {
        driver.findElement(loginFormEmailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(loginFormPasswordField).sendKeys(password);
    }

    public void submitLoginForm() {
        driver.findElement(loginFormSubmit).click();
    }

    @Step("Заполнение формы логина")
    public void completeLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        submitLoginForm();
    }

    @Step("Переход по урлу регистрации")
    public void openRegUrl() {
        driver.get(BaseRestClient.REG_URL);
    }

    @Step("Проверка отображения кнопки Войти на странице регистрации")
    public boolean isRegLoginLinkDisplayed() {
        elementVisibilityWait(regSignInButton);
        return driver.findElement(regSignInButton).isDisplayed();
    }

    @Step("Проверка отображения формы логина")
    public void openLoginForm() {
        driver.findElement(regSignInButton).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void openForgotPassForm() {
        driver.findElement(loginFormForgotPassLink).click();
    }

    @Step("Проверка отображения кнопки Войти на странице восстановления пароля")
    public boolean isForgotPassLoginLinkDisplayed() {
        elementVisibilityWait(forgotPassFormSignInLink);
        return driver.findElement(forgotPassFormSignInLink).isDisplayed();
    }

    @Step("Клик по кнопке Войти на странице восстановления пароля")
    public void returnToLoginForm() {
        driver.findElement(forgotPassFormSignInLink).click();
    }

    @Step("Проверка отображения кнопки Оформить заказ")
    public boolean isOrderButtonEnabled() {
        elementVisibilityWait(orderButton);
        return driver.findElement(orderButton).isDisplayed();
    }
}
