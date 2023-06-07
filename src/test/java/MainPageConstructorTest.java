import StellarBurgersPOM.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;


public class MainPageConstructorTest extends BaseTest {
    private MainPage objMainPage;

    @Before
    public void startUpPage() {
        objMainPage = new MainPage(driver);
        objMainPage.openURL();
    }

    @Test
    @DisplayName("Проверка конструктора: Таб Булки")
    public void checkConstructorTabsBunTest() {
        objMainPage.isHeaderBlockDisplayed();
        objMainPage.isAccountLinkDisplayed();
        objMainPage.isConstructorBlockDisplayed();
        objMainPage.isBunTabActive();
    }

    @Test
    @DisplayName("Проверка конструктора: Таб Соусы")
    public void checkConstructorTabsSauceTest() {
        objMainPage.isHeaderBlockDisplayed();
        objMainPage.isAccountLinkDisplayed();
        objMainPage.isConstructorBlockDisplayed();
        objMainPage.isBunTabActive();
        objMainPage.openSauceTab();
        objMainPage.isSauceTabActive();
    }

    @Test
    @DisplayName("Проверка конструктора: Таб Начинки")
    public void checkConstructorTabsFillingTest() {
        objMainPage.isHeaderBlockDisplayed();
        objMainPage.isAccountLinkDisplayed();
        objMainPage.isConstructorBlockDisplayed();
        objMainPage.isBunTabActive();
        objMainPage.openFillingTab();
        objMainPage.isFillingTabActive();
    }

}
