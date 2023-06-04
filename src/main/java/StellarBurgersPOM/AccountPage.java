package StellarBurgersPOM;

import StellarBurgersAPI.BaseRestClient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static StellarBurgersAPI.BaseRestClient.BASE_URL;

public class AccountPage {

    private WebDriver driver;


    //Header
    private By headerBlock = By.xpath(".//header[contains(@class, 'AppHeader_header__X9aJA')]");
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    private By accountLink = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text() = 'Личный Кабинет']");
    private By constructorLink = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text()='Конструктор']");

    //Главная
    private By constructorBlock = By.className("BurgerIngredients_ingredients__1N8v2");
    private By orderButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Оформить заказ')]");


    //Форма логина
    private By loginForm = By.xpath(".//form[contains(@class, 'Auth_form__3qKeq')]");
    private By loginFormEmailField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Email')]/../input[@class='text input__textfield text_type_main-default']");
    private By loginFormPasswordField = By.xpath(".//label[contains(@class, 'input__placeholder') and contains(text(), 'Пароль')]/../input[@class='text input__textfield text_type_main-default']");
    private By loginFormSubmit = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Войти')]");

    //Страница ЛК
    private By accountNavBlock = By.className("Account_nav__Lgali");
    private By logoutButton = By.xpath(".//button[text()='Выход']");


    public AccountPage(WebDriver driver) {
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

    //Проверка отображения кнопки Оформить заказ
    public boolean isOrderButtonEnabled() {
        elementVisibilityWait(orderButton);
        return driver.findElement(orderButton).isDisplayed();
    }

    public void openAccountPage() {
        driver.findElement(accountLink).click();
    }

    //Личный кабинет
    public boolean isAccountNavDisplayed() {
        elementVisibilityWait(accountNavBlock);
        return driver.findElement(accountNavBlock).isDisplayed();
    }


    //Переходы на главную
    public void clickToConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    public void clickToLogo() {
        driver.findElement(logo).click();
    }

    public boolean isConstructorBlockDisplayed() {
        elementVisibilityWait(constructorBlock);
        return driver.findElement(constructorBlock).isDisplayed();
    }

    //Логаут
    public void logout() {
        driver.findElement(logoutButton).click();
    }

}
