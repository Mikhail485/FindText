package ru.page.yandex;

import com.Google.page.GoogleMainPage;
import com.Google.page.GooglePF;
import com.Google.page.OpenCurrencyExchangePage;
import com.Google.page.PageGoogle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.page.yandexMarket.*;
import steps.Steps;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Tests extends BaseTests {

    @Test
    public void testWithPO(){
       chromeDriver.get("https://yandex.ru/");
       GoogleMainPage googleMainPage = new GoogleMainPage(chromeDriver, 30);
       googleMainPage.searchInGoogle("Гладиолус");
       PageGoogle pageGoogle = new PageGoogle(chromeDriver, 30);
       List<WebElement> resultWeb = pageGoogle.getWebResult();
       List<String> resultString = new ArrayList<>();
       resultWeb.forEach(x -> resultString.add(x.getText()));
       resultString.forEach(x -> System.out.println(x));
       Assertions.assertTrue(resultString.contains("ru.wikipedia.org"));
    }

    @Test
    public void testWithPF(){
        chromeDriver.get("https://yandex.ru/");
        GooglePF googlePF = PageFactory.initElements(chromeDriver, GooglePF.class);
        googlePF.searchInGoogle("Гладиолус");
        List<WebElement> resultWeb = googlePF.getWebResult();
        List<String> resultString = new ArrayList<>();
        resultWeb.forEach(x -> resultString.add(x.getText()));
        resultString.forEach(x -> System.out.println(x));
        Assertions.assertTrue(resultString.contains("ru.wikipedia.org"));
    }

    @Test
    public void testWithPO2() {
        chromeDriver.get("https://yandex.ru/");
        GoogleMainPage googleMainPage = new GoogleMainPage(chromeDriver, 30);
        googleMainPage.searchInGoogle("Открытие");
        PageGoogle pageGoogle = new PageGoogle(chromeDriver, 30);
        List<WebElement> resultWeb = pageGoogle.getWebResult();
        for (WebElement element : resultWeb){
            if (element.getText().contains("Open.ru")) {
                element.click();
                break;
            }
        }
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(1));
        OpenCurrencyExchangePage openCurrencyExchangePage = new OpenCurrencyExchangePage(chromeDriver, 30);
        Assertions.assertTrue(openCurrencyExchangePage.getUsdSale() > openCurrencyExchangePage.getUsdPurchase());
        Assertions.assertTrue(openCurrencyExchangePage.getEurSale() > openCurrencyExchangePage.getEurPurchase());
    }

    @DisplayName("Проверка наименования товара с заполненым значением")
    @Test
    public void testYanMarketComputer(){
        chromeDriver.get("https://yandex.ru/");
        YandexMainPage yandexMain = new YandexMainPage(chromeDriver, 30);
        Steps.checkOpenService(yandexMain, "Маркет");

        YanMarketMainPage yanMarketMain = new YanMarketMainPage(chromeDriver, 30);
        Steps.checkOpenCatalog(yanMarketMain, "Компьютерная техника");

        YanMarketPageSection yanSection = new YanMarketPageSection(chromeDriver, 30);
        Steps.checkOpenSection(yanSection, "Ноутбуки");

        YanMarketPageResultsSearch yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 30);
        Steps.checkCriteriaPrice(yanResultSearch, "10000", "30000");
        Steps.checkManuf(yanResultSearch, "HP");
        Steps.checkManuf(yanResultSearch, "Lenovo");

        List<WebElement> listResults_1 = yanResultSearch.show12Items();
        Steps.checkShowItems(listResults_1);
        String nameFirstProduct = yanResultSearch.getNameProduct(listResults_1.get(0));

        List<WebElement> listResults_2 = yanResultSearch.searchProduct(nameFirstProduct);
        Steps.checkEqualsProducts(nameFirstProduct, yanResultSearch.getNameProduct(listResults_2.get(0)));
    }

}
