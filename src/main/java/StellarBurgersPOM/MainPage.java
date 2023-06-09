package StellarBurgersPOM;

import StellarBurgersAPI.BaseRestClient;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private WebDriver driver;


    // Хэдер
    private By headerBlock = By.className("AppHeader_header__X9aJA");
    private By accountLink = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text() = 'Личный Кабинет']");


    // Конструктор
    private By constructorBlock = By.className("BurgerIngredients_ingredients__1N8v2");
    private By bunTabActiveLink = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc')]/span[@class='text text_type_main-default' and contains(text(), 'Булки')]");
    private By sauceTabLink = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG')]/span[@class='text text_type_main-default' and contains(text(), 'Соусы')]");
    private By sauceTabActiveLink = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc')]/span[@class='text text_type_main-default' and contains(text(), 'Соусы')]");
    private By fillingTabLink = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG')]/span[@class='text text_type_main-default' and contains(text(), 'Начинки')]");
    private By fillingTabActiveLink = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc')]/span[@class='text text_type_main-default' and contains(text(), 'Начинки')]");


    public MainPage(WebDriver driver) {
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

    @Step("Проверка отображения конструктора Соберите бургер")
    public boolean isConstructorBlockDisplayed() {
        elementVisibilityWait(constructorBlock);
        return driver.findElement(constructorBlock).isDisplayed();
    }

    @Step("Проверка отображения активной вкладки Булки")
    public boolean isBunTabActive() {
        elementVisibilityWait(bunTabActiveLink);
        return driver.findElement(bunTabActiveLink).isDisplayed();
    }


    @Step("Клик по табу Соусы")
    public void openSauceTab() {
        driver.findElement(sauceTabLink).click();
    }

    @Step("Проверка отображения активной вкладки Соусы")
    public boolean isSauceTabActive() {
        elementVisibilityWait(sauceTabActiveLink);
        return driver.findElement(sauceTabActiveLink).isDisplayed();
    }


    @Step("Клик по табу Начинки")
    public void openFillingTab() {
        driver.findElement(fillingTabLink).click();
    }

    @Step("Проверка отображения активной вкладки Начинки")
    public boolean isFillingTabActive() {
        elementVisibilityWait(fillingTabActiveLink);
        return driver.findElement(fillingTabActiveLink).isDisplayed();
    }

}
