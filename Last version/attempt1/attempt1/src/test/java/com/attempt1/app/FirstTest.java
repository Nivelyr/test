package com.attempt1.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTest {

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
    public void firstTest(){
        driver.get("https://rozetka.com.ua/");
        driver.findElement(By.xpath("//*[@id=\"3361\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"content-inner-block\"]/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[3]/div/div/div[1]/div/div[1]/div[1]/div/h3/a")).click();

        driver.findElement(By.xpath("//*[@id=\"menu_categories_left\"]/li[1]/div/a[1]")).click();
        WebElement element1 = driver.findElement(By.xpath("/html/head/script[27]"));
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

    }

}
