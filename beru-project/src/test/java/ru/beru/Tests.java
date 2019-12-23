package ru.beru;

import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import org.junit.Test;

public class Tests
{
    private ChromeDriver _browser;
    private String _login;
    private String _password;
    private String _city;
    private String _defaultCity;
    private int _lowPrice;
    private int _highPrice;
    private int _finalPrice;


    @Before
    public void Init()
    {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        _browser = new ChromeDriver();
        _browser.get("https://beru.ru");
        _login = "mathinf";
        _password = "a123456789";
        _city = "Хвалынск";
        _lowPrice = 999;
        _highPrice = 1999;
        _finalPrice = 2999;
        _defaultCity = "Саратов";
    }

    @Test
    public void Authorization()
    {
        LoginPage.LoginSelect(_browser);
        Authorization.Login(_browser, _login, _password);
        LoginPage.CheckLogin(_browser);
        _browser.quit();
    }
    @Test
    public void ChangeCity()
    {
        GeneralPage generalPage = new GeneralPage(_browser);
        generalPage.CheckCity(_defaultCity);
        generalPage.ChangeCity(_city);
        generalPage.CheckCity(_city);
        LoginPage.LoginSelect(_browser);
        Authorization.Login(_browser, _login, _password);
        generalPage.OpenSettings();
        generalPage.СheckCityMatches();
        _browser.quit();
    }

    @Test
    public void SelectToothbrushes()
    {
        LoginPage.LoginSelect(_browser);
        Authorization.Login(_browser, _login, _password);
        LoginPage.CatalogClick(_browser);
        SelectCatalog.BeautySectionSelect(_browser);
        SelectCatalog.ElectricToothbrushesSelect(_browser);
        ToothbrushesCatalog.ChoseToothbrushPrice(_browser, _lowPrice, _highPrice);
        ToothbrushesCatalog.CheckPriceRange(_browser, _lowPrice, _highPrice);
        //ToothbrushesCatalog.ClickShowMore(_browser);
        ToothbrushesCatalog.SelectLast(_browser);
        ToothbrushesCatalog.GoToCart(_browser);
        CartPage cartPage = new CartPage(_browser);
        cartPage.CheckFreeShipment();
        cartPage.OpenCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(_browser);
        checkoutPage.CheckTotalCostCorrection();
        checkoutPage.OpenCart();
        cartPage.IncreaseTotalTo(_finalPrice);
        cartPage.OpenCheckout();
        checkoutPage.CheckTotalCostCorrection();
        _browser.quit();
    }

}
