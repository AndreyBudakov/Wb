import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


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
    private static final String productTitle = ".product-page__title";
    private static final String buttonAdd = ".product-page__order-buttons";
    private static final String popUp = ".action-notification.show";
    private static final String cartTitle = ".basket-section__header.active";
    private static final String productTitleBasket = ".good-info__good-name";
    private static final String popUpText = "Товар добавлен в корзину";
    private static final String cartTitleText = "Корзина";
    private static final String productTitleText = "";

    private void findAndClick(String selector) {
        driver.findElement(By.cssSelector(selector)).click();
    }
    public String findAndGetText(String selector) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        return element.getText();
    }

    private void waitUntil(String selector) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }
    @Test
    public void shouldAddProductToBasket() {

        driver.navigate().to("https://www.wildberries.ru/catalog/141357660/detail.aspx");

        waitUntil(productTitle);
        String productTitleText = findAndGetText(productTitle);

        waitUntil(buttonAdd);
        findAndClick(buttonAdd);
        waitUntil(popUp);
        Assert.assertEquals("Неверный текст сообщения или сообщение отсутствует", popUpText, findAndGetText(popUp));

        findAndClick(buttonAdd);
        waitUntil(cartTitle);
        Assert.assertEquals("Неверный текст или элемент отсутствует", cartTitleText, findAndGetText(cartTitle));

        Assert.assertEquals("Заголовок товара в корзине не совпадает", productTitleText, findAndGetText(productTitleBasket));
    }
}
