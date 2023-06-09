package StellarBurgersPOM;

import StellarBurgersAPI.BaseRestClient;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationPage {
    private WebDriver driver;


    //Локаторы
    private By headerBlock = By.xpath(".//header[contains(@class, 'AppHeader_header__X9aJA')]");

    private By regForm = By.xpath(".//form[contains(@class, 'Auth_form__3qKeq')]");
    private By regFormNameField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Имя')]/../input[@class='text input__textfield text_type_main-default']");
    private By regFormEmailField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Email')]/../input[@class='text input__textfield text_type_main-default']");
    private By regFormPasswordField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Пароль')]/../input[@class='text input__textfield text_type_main-default']");
    private By regFormSubmit = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Зарегистрироваться')]");

    private By alertIncorrectPass = By.xpath(".//p[text()='Некорректный пароль']");
    private By alertUserAlreadyExists = By.xpath(".//p[text()='Такой пользователь уже существует']");

    private By loginForm = By.xpath(".//div[@class = 'Auth_login__3hAey']/h2[text() = 'Вход']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход по урлу регистрации")
    public void openURL() {
        driver.get(BaseRestClient.REG_URL);
    }

    //Базовый метод проверки отображения элемента
    public void elementVisibilityWait(By waitElement) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementWait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
    }

    @Step("Проверка отображения хэдера страницы")
    public boolean isHeaderBlockDisplayed() {
        elementVisibilityWait(headerBlock);
        return driver.findElement(headerBlock).isDisplayed();
    }

    @Step("Проверка отображения формы регистрации")
    public boolean isRegFormDisplayed() {
        elementVisibilityWait(regForm);
        return driver.findElement(regForm).isDisplayed();
    }

    //Методы заполнения формы
    public void setName(String name) {
        driver.findElement(regFormNameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(regFormEmailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(regFormPasswordField).sendKeys(password);
    }

    public void submitRegForm() {
        driver.findElement(regFormSubmit).click();
    }

    @Step("Заполнение формы регистрации")
    public void completeRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        submitRegForm();
    }

    @Step("Проверка отображения формы логина после успешной регистрации")
    public boolean checkAfterRegister() {
        elementVisibilityWait(loginForm);
        return driver.findElement(loginForm).isDisplayed();

    }

    @Step("Проверка отображения ошибки некорректный пароль")
    public boolean getPasswordValidationAlert() {
        elementVisibilityWait(alertIncorrectPass);
        return driver.findElement(alertIncorrectPass).isDisplayed();
    }

    @Step("Проверка отображения ошибки что пользователь уже существует")
    public boolean getAlreadyExistsAlert() {
        elementVisibilityWait(alertUserAlreadyExists);
        return driver.findElement(alertUserAlreadyExists).isDisplayed();
    }
}
