package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToothbrushesCatalog
{
    private static WebElement _webElement;

    private static By _LowPrice = By.xpath("//div[contains(@data-auto, " +
            "'filter-range-glprice')]//span[contains(@data-auto, 'filter-range-min')]//input");
    private static By _HighPrice = By.xpath("//div[contains(@data-auto, " +
            "'filter-range-glprice')]//span[contains(@data-auto, 'filter-range-max')]//input");
    private static By _priceRange = By.cssSelector("[class=\"_3GNV1gy3cc\"]");
    private static By _showMoreButton = By.xpath("/html/body/div[1]/div[5]/div/div/div/div/div[3]" +
            "/div/div[6]/div/div/div/div/div/div[1]/button");
    private static By _neededToothbrush = By.xpath("//div[@class=\"_1uhsh_io8o\"]//div" +
            "[@class=\"_3rWYRsam78\"][last()]/div[last()]//div[@class=\"_1RjY7YIluf _1zYszmgEzn\"][last()-1]//span[@class=\"_2w0qPDYwej\"]");
    private static By _cartButton = By.className("_1LEwf9X1Gy");
    @Step("Выбор щётки по стоимости")
    public static void ChoseToothbrushPrice(ChromeDriver browser, int lowPrice, int highPrice){
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.elementToBeClickable(_LowPrice));
        _webElement = browser.findElement(_LowPrice);
        _webElement.click();
        _webElement.sendKeys(String.valueOf(lowPrice));

        wait.until(ExpectedConditions.elementToBeClickable(_HighPrice));
        _webElement = browser.findElement(_HighPrice);
        _webElement.click();
        _webElement.sendKeys(String.valueOf(highPrice));
    }
    @Step("Проверка диапазона")
    public static void CheckPriceRange(ChromeDriver browser, int lowPrice, int highPrice){
        WebDriverWait wait = new WebDriverWait(browser, 10);
        _webElement = browser.findElement(_priceRange);
        wait.until(ExpectedConditions.visibilityOf(_webElement));
        String priceRange = lowPrice + " — " + highPrice + " ₽";
        Assert.assertEquals(priceRange, _webElement.getText());
    }
    @Step("Показать больше шёток")
    public static void ClickShowMore(ChromeDriver browser){
        try {
            WebDriverWait wait = new WebDriverWait(browser, 10);
            wait.until(ExpectedConditions.visibilityOf((WebElement) _showMoreButton));
            wait.until(ExpectedConditions.elementToBeClickable(_showMoreButton));
            _webElement = browser.findElement(_showMoreButton);
            _webElement.click();
        }
        finally {}
    }
    @Step("Выбор препоследней")
    public static void SelectLast(ChromeDriver browser) {
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.elementToBeClickable(_neededToothbrush));
        _webElement = browser.findElement(_neededToothbrush);
        _webElement.click();
        //wait.until(ExpectedConditions.elementToBeClickable(_neededToothbrush));
    }
    @Step("Переход к корзине")
    public static void GoToCart(ChromeDriver browser){
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.elementToBeClickable(_cartButton));
        _webElement = browser.findElement(_cartButton);
        _webElement.click();
    }
}
