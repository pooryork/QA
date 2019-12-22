package pages;

import io.qameta.allure.Step;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authorization
{
    private static WebElement _webElement;

    private static By  _loginInput = By.id("passp-field-login");
    private static By  _passwordInput = By.id("passp-field-passwd");
    private static By  _submitButton = By.cssSelector("button.control.button2.button2_view_classic.button2_size_l.button2_theme_action.button2_width_max.button2_type_submit.passp-form-button");
    @Step("Ввод логина и пароля")
    public static void Login(ChromeDriver browser, String login, String password)
    {
        _webElement = browser.findElement(_loginInput);
        _webElement.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        _webElement.sendKeys(login);
        _webElement = browser.findElement(_submitButton);
        _webElement.click();

        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.elementToBeClickable(_passwordInput));
        _webElement = browser.findElement(_passwordInput);
        _webElement.clear();
        _webElement.sendKeys(password);
        _webElement = browser.findElement(_submitButton);
        _webElement.click();
    }
}
