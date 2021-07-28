package com.Google.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GooglePF {

    private WebDriver chromeDriver;
    private WebDriverWait waitGoogle;

    @FindBy(how = How.XPATH, using = "//input[@id='text']")
    private WebElement elemSearchGoogle;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement elemFindGoogle;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='Organic-Subtitle organic__subtitle']//b"))
    private List<WebElement> elemWebResult;

    public GooglePF(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.waitGoogle = new WebDriverWait(chromeDriver, 30);
    }

    public void searchInGoogle(String text){
        elemSearchGoogle.sendKeys(text);
        elemFindGoogle.click();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public List<WebElement> getWebResult(){
        return elemWebResult;
    }
}
