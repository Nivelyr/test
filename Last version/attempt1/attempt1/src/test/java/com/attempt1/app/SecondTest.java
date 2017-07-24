package com.attempt1.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondTest {

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
    public void secondTest(){
        // зайти на сайт rozetka.com.ua
        driver.get("https://rozetka.com.ua/");

        //перейти в раздел "Товары для дома"
        driver.findElement(By.xpath("//*[@id=\"5300\"]/a")).click();

        // перейти в раздел "Бытовая химия"
        driver.findElement(By.xpath("//*[@id=\"menu_categories_left\"]/li[7]/div[1]/a[1]")).click();

        // перейти в раздел "Для стирки"
        driver.findElement(By.xpath("//*[@id=\"menu_categories_left\"]/li[2]/h4/a")).click();

        // перейти в раздел "Порошки для стирки"
        driver.findElement(By.xpath("//*[@id=\"content-inner-block\"]/div[2]/div/div/div[2]/div/div[1]/" +
                "div[1]/div/div[3]/div/div/div[1]/div[1]/div[1]/div[1]/div/h3/a")).click();

        // выбрать из первых пяти страниц поисковой выдачи название и цены всех товаров в диапазоне от 100 до 300 гривен
        WebElement min_enter = driver.findElement(By.xpath("//*[@id=\"price[min]\"]"));
        WebElement max_enter = driver.findElement(By.xpath("//*[@id=\"price[max]\"]"));
        Actions builder = new Actions(driver);
        Actions seriesOfActions1 = builder.moveToElement(min_enter).click().sendKeys(min_enter, "100");
        seriesOfActions1.perform();
        Actions seriesOfActions2 = builder.moveToElement(max_enter).click().sendKeys(max_enter, "300");
        seriesOfActions2.perform();
        driver.findElement(By.xpath("//*[@id=\"submitprice\"]")).click();

        driver.findElement(By.xpath("/html/head/script[30]"));
        WebElement element1 = driver.findElement(By.xpath("/html/head/script[30]"));
        String text1 = element1.getAttribute("innerText");
        Pattern p = Pattern.compile("(?<=productName.).*?(?=..productID)");
        Matcher m1 = p.matcher(text1);
        while(m1.find()) {
            System.out.println(text1.substring(m1.start()+1, m1.end()));
        }

        driver.findElement(By.xpath("//*[@id=\"page2\"]/a"));
        driver.findElement(By.xpath("//*[@id=\"page2\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"catalog_goods_block\"]/script[2]"));
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"catalog_goods_block\"]/script[2]"));
        String text2 = element2.getAttribute("innerText");
        Matcher m2 = p.matcher(text2);
        while(m2.find()) {
            System.out.println(text2.substring(m2.start()+1, m2.end()));
        }

        driver.findElement(By.xpath("//*[@id=\"page3\"]/a"));
        driver.findElement(By.xpath("//*[@id=\"page3\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"catalog_goods_block\"]/script[2]"));
        WebElement element3 = driver.findElement(By.xpath("//*[@id=\"catalog_goods_block\"]/script[2]"));
        String text3 = element3.getAttribute("innerText");
        Matcher m3 = p.matcher(text3);
        while(m3.find()) {
            System.out.println(text3.substring(m3.start()+1, m3.end()));
        }
        // Class.forName("com.mysql.jdbc.Driver");
        // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3036/powder",
        //        "root@localhost","1q2w3e!Q");
        // Statement stmt = con.createStatement();
        // stmt.executeQuery(select *  from employee;);
    }

}
