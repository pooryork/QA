package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage
{
    @FindBy(css = "[class=\"_2YHTmhZmt4\"]")
    private WebElement _labelLeftForFreeShipment;
    @FindBy(css="[class=\"_1oBlNqVHPq\"]")
    private WebElement _labelTotal;
    @FindBy(css="[class=\"_4qhIn2-ESi Pjv3h3YbYr THqSbzx07u _39B7yXQbvm _2W4X8tX6r0\"]")
    private WebElement _buttonCheckout;
    @FindBy(xpath = "//div[@class=\"_3MqS53YE3Q\"]//div[@class=\"_1u3j_pk1db\"]")
    private WebElement _labelToothbrushPrice;
    @FindBy(xpath = "//div[@data-auto=\"CartOffer\"]//input[@value]")
    private WebElement _fieldCount;
    @FindBy(css = "[class=\"_2TbI0lRCD8\"]")
    private WebElement _buttonDelete;
    @FindBy(css="[class=\"_2TFWzc3clT\"]")
    private WebElement _labelClear;

    By _locatorCheckoutPage = By.className("_1e2FY_93Ro");

    WebDriver _driver;
    WebDriverWait _wait;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this._driver = driver;
        _wait = new WebDriverWait(driver, 10);
    }
    @Step("Проверка доступной стоимости")
    public void CheckFreeShipment() {
        _wait.until(ExpectedConditions.visibilityOf(_labelLeftForFreeShipment));
        String[] priceStr = _labelLeftForFreeShipment.getText().split(" ");
        int leftForFree = Integer.parseInt(priceStr[0]);
        if (leftForFree < 10) {
            leftForFree *= 100;
            leftForFree += Integer.parseInt(priceStr[1]);
        }
        System.out.println(leftForFree);
    }
    @Step("Открытие для проверки")
    public void OpenCheckout() {
        _buttonCheckout.click();
        _wait.until(ExpectedConditions.visibilityOfElementLocated(_locatorCheckoutPage));
    }
    @Step("Дополнение корзины до максимума")
    public void IncreaseTotalTo(double price) {
        String[] nums = _labelToothbrushPrice.getText().split("\\D+");
        int toothbrushPrice = Integer.parseInt(nums[0]);
        if (nums.length > 1) {
            toothbrushPrice *= 1000;
            toothbrushPrice += Integer.parseInt(nums[1]);
        }
        int k = (int) Math.ceil(price / toothbrushPrice);
        _fieldCount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        _fieldCount.sendKeys(Integer.toString(k));
        _wait.until(ExpectedConditions.visibilityOf(_labelTotal));
        _wait.until(ExpectedConditions.elementToBeClickable(_buttonCheckout));
    }
}