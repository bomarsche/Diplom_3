package StellarBurgersPOM;

import StellarBurgersAPI.BaseRestClient;
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

    //Метод проверки отображения элементов хедера
    public boolean isHeaderBlockDisplayed() {
        elementVisibilityWait(headerBlock);
        return driver.findElement(headerBlock).isDisplayed();
    }

    public boolean isAccountLinkDisplayed() {
        elementVisibilityWait(accountLink);
        return driver.findElement(accountLink).isDisplayed();
    }

    //Методы перехода к форме логина
    public void openLoginFormFromAccountLink() {
        driver.findElement(accountLink).click();
    }

    public void openLoginFormFromEnterLink() {
        driver.findElement(signInButton).click();
    }

    //Метод проверки отображения формы логина
    public boolean isLoginFormDisplayed() {
        elementVisibilityWait(loginForm);
        return driver.findElement(loginForm).isDisplayed();
    }

    //Методы заполнения формы
    public void setEmail(String email){
        driver.findElement(loginFormEmailField).sendKeys(email);
    }

    public void setPassword(String password){
        driver.findElement(loginFormPasswordField).sendKeys(password);
    }

    public void submitLoginForm(){
        driver.findElement(loginFormSubmit).click();
    }

    public void completeLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        submitLoginForm();
    }

    //Вход через регистрацию
    public void openRegUrl() {
        driver.get(BaseRestClient.REG_URL);
    }

    public boolean isRegLoginLinkDisplayed() {
        elementVisibilityWait(regSignInButton);
        return driver.findElement(regSignInButton).isDisplayed();
    }

    public void openLoginForm(){
        driver.findElement(regSignInButton).click();
    }

    //Вход через восстановление пароля
    public void openForgotPassForm () {
        driver.findElement(loginFormForgotPassLink).click();
    }

    public boolean isForgotPassLoginLinkDisplayed() {
        elementVisibilityWait(forgotPassFormSignInLink);
        return driver.findElement(forgotPassFormSignInLink).isDisplayed();
    }

    public void returnToLoginForm() {
        driver.findElement(forgotPassFormSignInLink).click();
    }

    //Проверка отображения кнопки Оформить заказ
    public boolean isOrderButtonEnabled() {
        elementVisibilityWait(orderButton);
        return driver.findElement(orderButton).isDisplayed();
    }
}
