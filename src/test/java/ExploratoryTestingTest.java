import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class ExploratoryTestingTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put("productTitle", By.cssSelector(".product-page__title"));
        constants.put("buttonAdd", By.cssSelector(".product-page__order-buttons"));
        constants.put("popUp", By.cssSelector(".action-notification.show"));
        constants.put("popUpText", "Товар добавлен в корзину");
        constants.put("cartTitle", By.cssSelector(".basket-section__header.active"));
        constants.put("cartTitleText", "Корзина");
        constants.put("productTitleBasket", By.cssSelector(".good-info__good-name"));
        return constants;
    }

    @Test
    public void shouldAddProductToBasket() {
        driver.navigate().to("https://www.wildberries.ru/catalog/141357660/detail.aspx");

        Map<String, Object> constants = getConstants();

        By productTitle = (By) constants.get("productTitle");
        By buttonAdd = (By) constants.get("buttonAdd");
        By popUp = (By) constants.get("popUp");
        String popUpText = (String) constants.get("popUpText");
        By cartTitle = (By) constants.get("cartTitle");
        String cartTitleText = (String) constants.get("cartTitleText");
        By productTitleBasket = (By) constants.get("productTitleBasket");

        wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
        var productTitleText = driver.findElement(productTitle).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(buttonAdd));
        driver.findElement(buttonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(popUp));
        Assert.assertEquals("Неверный тест сообщения или сообщение отсутствует",
                popUpText, driver.findElement(popUp).getText());

        driver.findElement(buttonAdd).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(cartTitle));
        Assert.assertEquals("Неверный текст или элемент отсутствует",
                cartTitleText, driver.findElement(cartTitle).getText());

        Assert.assertEquals("Заголовок не совпадает",
                productTitleText, driver.findElement(productTitleBasket).getText());
    }
}
