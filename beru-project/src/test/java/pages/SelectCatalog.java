package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectCatalog
{
    private static WebElement _webElement;
    private static By section = By.className("_19FPGVzRi9");
    private static By electricToothbrushes = By.cssSelector("a[href^='/catalog/elektricheskie-zubnye-shchetki']");

    @Step("Выбор отдела 'Красота и гигиена'")
    public static void BeautySectionSelect(ChromeDriver driver)
    {
        WebDriverWait waitTime = new WebDriverWait(driver, 10);
        waitTime.until(ExpectedConditions.elementToBeClickable(section));
        _webElement = driver.findElement(section);
        _webElement.click();
    }
    @Step("Открытие раздела с зубными щётками")
    public static void ElectricToothbrushesSelect(ChromeDriver browser){
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.elementToBeClickable(electricToothbrushes));
        _webElement = browser.findElement(electricToothbrushes);
        _webElement.click();
    }
}
