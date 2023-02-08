/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author herma
 */
public class AUTest1 {
    WebDriver driver = null;

    @BeforeTest
    public void setUp() {        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // mode headless
        driver = new ChromeDriver(options);
    }

    @Test
    public void firstTry() throws Exception {
        driver.get("http://localhost/index.html");
        driver.findElement(By.id("no")).click();
        driver.findElement(By.id("buttoncheck")).click();        
        String expectedResult = "Radio button is checked and it's value is No";
        String actualResult = driver.findElement(By.className("radiobutton")).getText();
        this.takeSnapShot(driver, "test.png") ;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }
    
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
