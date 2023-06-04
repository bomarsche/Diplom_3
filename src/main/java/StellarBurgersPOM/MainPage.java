package StellarBurgersPOM;
import StellarBurgersAPI.BaseRestClient;

import org.openqa.selenium.*;
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

    //Метод проверки отображения элементов хедера
    public boolean isHeaderBlockDisplayed() {
        elementVisibilityWait(headerBlock);
        return driver.findElement(headerBlock).isDisplayed();
    }

    public boolean isAccountLinkDisplayed() {
        elementVisibilityWait(accountLink);
        return driver.findElement(accountLink).isDisplayed();
    }

    //Метод проверки отображения блока конструктора
    public boolean isConstructorBlockDisplayed() {
        elementVisibilityWait(constructorBlock);
        return driver.findElement(constructorBlock).isDisplayed();
    }

    //Метод проверки активной вкладки "Булки"
    public boolean isBunTabActive(){
        elementVisibilityWait(bunTabActiveLink);
        return driver.findElement(bunTabActiveLink).isDisplayed();
    }


    //Переход к табу "Соусы"
    public void openSauceTab () {
        driver.findElement(sauceTabLink).click();
    }

    //Метод проверки активной вкладки "Соусы"
    public boolean isSauceTabActive(){
        elementVisibilityWait(sauceTabActiveLink);
        return driver.findElement(sauceTabActiveLink).isDisplayed();
    }


    //Переход к табу "Начинки"
    public void openFillingTab () {
        driver.findElement(fillingTabLink).click();
    }

    //Метод проверки активной вкладки "Начинки"
    public boolean isFillingTabActive(){
        elementVisibilityWait(fillingTabActiveLink);
        return driver.findElement(fillingTabActiveLink).isDisplayed();
    }

}
