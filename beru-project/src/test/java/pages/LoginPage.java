package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage
{
    private static WebElement _webElement;
    private static By _loginButton = By.className("_1FEpprw_Km");
    private static By _loginText = By.className("pFhTbV17qj");
    //private static By _cityButton = By.className("_2LxmV3b641");
    private static By _cityButton = By.xpath("//span[contains(@data-auto,'region-form-opener')]//span[2]");
    private static By _citeCatalog = By.className("_3RM4_n5whA");
    private static By _cityUpRight = By.cssSelector("span[data-auto='region-form-opener']._2XJ6yiRp5w");
    @Step("Выбор страницы для авторизации")
    public static void LoginSelect(ChromeDriver browser)
    {
        WebDriverWait waitTime = new WebDriverWait(browser, 10);
        waitTime.until(ExpectedConditions.elementToBeClickable(_loginButton));
        _webElement = browser.findElement(_loginButton);
        _webElement.click();
    }
    @Step("Проверка залогинен ли пользователь")
    public static void CheckLogin(ChromeDriver browser)
    {
        WebDriverWait waitTime = new WebDriverWait(browser, 10);
        waitTime.until(ExpectedConditions.elementToBeClickable(_loginText));
        String text = browser.findElement(_loginText).getText();
        Assert.assertEquals(text, "Мой профиль");
    }
    @Step("Открытие каталога")
    public static void  CatalogClick(ChromeDriver browser){
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.elementToBeClickable(_citeCatalog));
        _webElement = browser.findElement(_citeCatalog);
        _webElement.click();
    }
}
