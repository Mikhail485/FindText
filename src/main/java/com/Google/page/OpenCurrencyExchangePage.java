package com.Google.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OpenCurrencyExchangePage extends AbstractGooglePage {

    private String currencyExch = "//tr//span[@class='main-page-exchange__rate']";
    private double usdPurchase;
    private double usdSale;
    private double eurPurchase;
    private double eurSale;

    private List<WebElement> elemeCurrencyExch;

    public double getUsdPurchase() {
        return usdPurchase;
    }

    public double getUsdSale() {
        return usdSale;
    }

    public double getEurPurchase() {
        return eurPurchase;
    }

    public double getEurSale() {
        return eurSale;
    }

    public OpenCurrencyExchangePage(WebDriver chromeDriver, Integer waitSecond){
        this.chromeDriver = chromeDriver;
        this.waitGoogle = new WebDriverWait(chromeDriver, waitSecond);
        elemeCurrencyExch = chromeDriver.findElements(By.xpath(currencyExch));
        usdPurchase = Double.parseDouble(elemeCurrencyExch.get(0).getText().replace(",", "."));
        usdSale = Double.parseDouble(elemeCurrencyExch.get(1).getText().replace(",", "."));
        eurPurchase = Double.parseDouble(elemeCurrencyExch.get(2).getText().replace(",", "."));
        eurSale = Double.parseDouble(elemeCurrencyExch.get(3).getText().replace(",", "."));
    }

}
