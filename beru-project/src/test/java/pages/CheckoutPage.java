package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutPage
{
    WebDriver _driver;
    WebDriverWait _wait;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this._driver = driver;
        _wait = new WebDriverWait(driver, 10);
    }

    @FindBy(css="[class=\"_1e2FY_93Ro\"]")
    private WebElement labelYourOrder;

    @FindBy(css="[class=\"_1oBlNqVHPq\"]")
    private WebElement labelTotal;

    @FindBy(xpath = "//div[@data-auto=\"DELIVERY\"]")
    private WebElement buttonCourierDelivery;

    @FindBy(xpath = "//div[@data-auto=\"total-items\"]/span[@data-auto=\"value\"]")
    private WebElement labelItemsPrice;

    @FindBy(xpath = "//div[@data-auto=\"total-delivery\"]//span[@data-auto=\"value\"]")
    private WebElement labelDeliveryPrice;

    @FindBy(xpath = "//span[@data-auto=\"change-link\"]")
    private WebElement buttonChangeOrder;

    private By locatorDiscount = By.xpath("//div[@data-auto=\"total-discount\"]//span[@data-auto=\"value\"]");
    private By locatorCartPage = By.className("wn9mZbgWbv");
    @Step("Проверка стоимости")
    public void CheckTotalCostCorrection() {
        String[] nums = labelItemsPrice.getText().split("\\D+");
        int itemsPrice = Integer.parseInt(nums[0]);
        if (nums.length > 1) {
            itemsPrice *= 1000;
            itemsPrice += Integer.parseInt(nums[1]);
        }

        buttonCourierDelivery.click();

        int discount = 0;
        if (_driver.findElements(locatorDiscount).size() != 0) {
            nums = _driver.findElement(locatorDiscount).getText().split("\\D+");
            discount = Integer.parseInt(nums[0]);
            if (nums.length > 1) {
                discount *= 1000;
                discount += Integer.parseInt(nums[1]);
            }
        }
        int deliveryPrice;
        if (labelDeliveryPrice.getText().equals("бесплатно")) {
            deliveryPrice = 0;
        } else {
            nums = labelDeliveryPrice.getText().split("\\D+");
            deliveryPrice = Integer.parseInt(nums[0]);
            if (nums.length > 1) {
                deliveryPrice *= 1000;
                deliveryPrice += Integer.parseInt(nums[1]);
            }
        }
        nums = labelTotal.getText().split("\\D+");
        int total = Integer.parseInt(nums[0]);
        if (nums.length > 1) {
            total *= 1000;
            total += Integer.parseInt(nums[1]);
        }

        Assert.assertEquals(deliveryPrice + itemsPrice - discount, total);
    }
    @Step("Открытие корзины")
    public void OpenCart() {
        buttonChangeOrder.click();
        _wait.until(ExpectedConditions.visibilityOfElementLocated(locatorCartPage));
    }
}