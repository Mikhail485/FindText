package com.Google.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GoogleMainPage extends AbstractGooglePage {

    private String searchGoogle = "//input[@id='text']";
    private String findGoogle = "//button[@type='submit']";

    private WebElement elemSearchGoogle;
    private WebElement elemFindGoogle;



    public GoogleMainPage(WebDriver chromeDriver, Integer waitSecond) {
        this.chromeDriver = chromeDriver;
        this.waitGoogle = new WebDriverWait(chromeDriver, waitSecond);
        elemSearchGoogle = chromeDriver.findElement(By.xpath(searchGoogle));
        elemFindGoogle = chromeDriver.findElement(By.xpath(findGoogle));
    }

    public void searchInGoogle(String text){
        elemSearchGoogle.sendKeys(text);
        elemFindGoogle.click();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
