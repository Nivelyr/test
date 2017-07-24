package com.attempt1.app;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class ThirdTest {
    WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/Auto projects/Last version/attempt1/attempt1/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void thirdTest() {
        // зайти на сайт rozetka.com.ua
        driver.get("https://rozetka.com.ua/");

        // перейти в раздел "Телефоны, ТВ и электроника"
        driver.findElement(By.xpath("//*[@id=\"3361\"]/a")).click();

        // перейти в раздел "Телефоны"
        driver.findElement(By.xpath("//*[@id=\"content-inner-block\"]/div[2]/div/div/div[2]/div/" +
                "div[1]/div[1]/div/div[3]/div/div/div[1]/div/div[1]/div[1]/div/h3/a")).click();

        // перейти в раздел "Смартфоны"
        driver.findElement(By.xpath("//*[@id=\"menu_categories_left\"]/li[1]/div/a[1]")).click();

        // выбрать из первых трех страниц поисковой выдачи название и цены всех девайсов, обозначенных как “Топ продаж”
        for (int i = 2; i < 5; i++) {
            driver.findElements(By.xpath("//*[@class=\"g-tag " +
                    "g-tag-icon-middle-popularity sprite\"]"));
            List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"g-tag " +
                    "g-tag-icon-middle-popularity sprite\"]"));
            for (WebElement ele:elements) {
                driver.findElement(By.xpath(ele + "../../../div[4]/a"));
                WebElement itemName = ele.findElement(By.xpath("../../../div[4]/a"));
                String name = itemName.getAttribute("innerText"); // for getting productName of each element
                driver.findElement(By.xpath("../../../div[@name=\"" +
                        "prices_active_element_original\"]/div/div/div"));
                WebElement itemPrice = ele.findElement(By.xpath("../../../div[@name=\"" +
                        "prices_active_element_original\"]/div/div/div"));
                String price = itemPrice.getAttribute("innerText"); //for getting price of each element
                System.out.println(name);
                System.out.println(price);
            }
            driver.findElement(By.xpath("//*[@id=\"page" + i + "\"]/a"));
            System.out.println(elements);
            driver.findElement(By.xpath("//*[@id=\"page" + i + "\"]/a")).click();
        }
        WebElement min_enter = driver.findElement(By.xpath("//*[@id=\"price[min]\"]"));
        WebElement max_enter = driver.findElement(By.xpath("//*[@id=\"price[max]\"]"));
        Actions builder = new Actions(driver);
        Actions seriesOfActions1 = builder.moveToElement(min_enter).click().sendKeys(min_enter, "3000");
        seriesOfActions1.perform();
        Actions seriesOfActions2 = builder.moveToElement(max_enter).click().sendKeys(max_enter, "6000");
        seriesOfActions2.perform();
        driver.findElement(By.xpath("//*[@id=\"submitprice\"]")).click();

        for (int i = 1; i < 6; i++) {
            driver.findElements(By.xpath("//*[@class=\"g-i-tile-i-box-desc\"]"));
            List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"g-i-tile-i-box-desc\"]"));
            System.out.println(elements);
            for (WebElement ele:elements) {
                driver.findElement(By.xpath(ele + "div[4]/a"));
                WebElement itemName = ele.findElement(By.xpath("div[4]/a"));
                String name = itemName.getAttribute("innerText"); // for getting productName of each element
                driver.findElement(By.xpath("div[@name=\"prices_active_element_original\"]/div/div/div"));
                WebElement itemPrice = ele.findElement(By.xpath("div[@name=\"" +
                        "prices_active_element_original\"]/div/div/div"));
                String price = itemPrice.getAttribute("innerText"); //for getting price of each element
                System.out.println(name);
                System.out.println(price);
            }
            driver.findElement(By.xpath("//*[@id=\"page" + i + "\"]/a"));
            driver.findElement(By.xpath("//*[@id=\"page" + i + "\"]/a")).click();

        }
    }
}
