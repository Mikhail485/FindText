package com.Google.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageGoogle extends AbstractGooglePage {

    private String resultWebSearch = "//a[@target='_blank']//b";

    private List<WebElement> elemWebResult;

    public PageGoogle(WebDriver chromeDriver, Integer waitSecond) {
        this.chromeDriver = chromeDriver;
        this.waitGoogle = new WebDriverWait(chromeDriver, waitSecond);
        elemWebResult = chromeDriver.findElements(By.xpath(resultWebSearch));
    }

    public List<WebElement> getWebResult(){
        return elemWebResult;
    }
}
