package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GeneralPage
{
    WebDriver _driver;
    WebDriverWait _wait;

    public GeneralPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this._driver = driver;
        _wait = new WebDriverWait(driver, 30);
    }

    @FindBy(css = "[class=\"_1r1GkezLi0\"]")
    private WebElement _buttonBeru;
    @FindBy(css = "[class=\"_3odNv2Dw2n\"]")
    private WebElement _buttonAuth;
    @FindBy(xpath = "//span[contains(@data-auto,'region-form-opener')]//span[2]")
    private WebElement _buttonCity;
    @FindBy(css = "[class=\"_1U2ErCeoqP\"]")
    private WebElement _popupCity;
    @FindBy(xpath = "//div[contains(@data-auto,'region-popup')] //input[contains(@class,'_2JDvXzYsUI')]")
    private WebElement _fieldCity;
    @FindBy(id = "react-autowhatever-region")
    private WebElement _listboxCities;
    @FindBy(css = "[class=\"_4qhIn2-ESi Pjv3h3YbYr THqSbzx07u\"]")
    private WebElement _buttonOk;
    @FindBy(css = "[class=\"_1FEpprw_Km\"]")
    private WebElement _buttonMyProfile;
    @FindBy(css = "[href=\"/my/settings?track=menu\"]")
    private WebElement _buttonSettings;
    @FindBy(css = "[class=\"_3RM4_n5whA\"]")
    private WebElement _buttonCatalog;
    @FindBy(css = "[class=\"_3JUsAgML4w\"]")
    private WebElement _catalog;
    @FindBy(css = "a[title=\"Электрические зубные щетки\"]")
    private WebElement _buttonElectricalToothbrushes;
    @FindBy(css = "a[title=\"Красота и гигиена\"]")
    private WebElement _buttonBeautyAndHygiene;
    @FindBy(xpath = "//span[contains(@data-auto,'region')]//span[1]//span[1]")
    private WebElement _buttonYourCity;

    private By locatorButtonCity = By.xpath("//span[contains(@data-auto,'region-form-opener')]//span[2]");
    private By locatorFirstCityOfList = By.className("_229JDbp_Z8");
    private By locatorAuthPage = By.id("passp-field-login");
    private By locatorSettingsPage = By.className("_38iDpDiSsi");
    private By locatorBeautyAndHygienePage = By.cssSelector("a[title=\"Красота и гигиена\"]");
    private By locatorElectricalToothbrushesPage = By.className("ZsTILNLaud");

    @Step("Проверка города")
    public void CheckCity(String cityName) {
        _wait.until(ExpectedConditions.textToBe(locatorButtonCity, cityName));
        Assert.assertEquals(cityName, _buttonCity.getText());
    }
    @Step("Смена города")
    public void ChangeCity(String cityName) {
        _buttonCity.click();
        _wait.until(ExpectedConditions.visibilityOf(_popupCity));
        _fieldCity.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);

        for (int i = 0; i < cityName.length(); ++i) {
            _fieldCity.sendKeys(Character.toString(cityName.charAt(i)));
            _wait.withTimeout(50, TimeUnit.MILLISECONDS);
        }
        _wait = new WebDriverWait(_driver, 5);
        _wait.until(ExpectedConditions.visibilityOf(_listboxCities));
        WebElement firstCity = _listboxCities.findElement(locatorFirstCityOfList);
        _wait.until(ExpectedConditions.textToBePresentInElement(firstCity, cityName));
        Assert.assertEquals(cityName, firstCity.getText());
        firstCity.click();
        _wait.until(ExpectedConditions.visibilityOf(_buttonOk));
        _buttonOk.click();
        _wait.until(ExpectedConditions.textToBePresentInElement(_buttonCity, cityName));
    }
    @Step("Открытие настроект")
    public void OpenSettings()
    {
        _wait.until(ExpectedConditions.visibilityOf(_buttonAuth));
        _wait.until(ExpectedConditions.elementToBeClickable(_buttonAuth));
        _buttonAuth.click();
        _wait.until(ExpectedConditions.visibilityOf(_buttonSettings));
        _buttonSettings.click();
        _wait.until(ExpectedConditions.visibilityOfElementLocated(locatorSettingsPage));
    }
    @Step("Проверка города на совпадение с установленным")
    public void СheckCityMatches() {
        Assert.assertEquals(_buttonCity.getText(), _buttonYourCity.getText());
    }
}
