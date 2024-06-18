package cz.kat.key_word;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class FileConverter {

    private WebDriver driver;

    //Metoda vleze na web, co převádí html do json, ale nějak jí to nejde
    public void convertFileAndSave(String url) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\catty\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.get("https://codebeautify.org/html-to-json-converter");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("sp_message_iframe_1117029")));

        wait.until(ExpectedConditions.elementToBeClickable(By.className("last-focusable-el"))).click();

        driver.switchTo().defaultContent();

        WebElement buttonForURL = driver.findElement(By.xpath("//button[@title='Load URL']"));
        buttonForURL.click();

        WebElement inputForURL = driver.findElement(By.id("urlText"));
        inputForURL.sendKeys(url);

        WebElement submitButton = driver.findElement(By.xpath("//button[@onclick='loadURLFromUI()']"));
        submitButton.click();

        Thread.sleep(5000);

        WebElement downloadFile = driver.findElement(By.xpath("//button[@title='Download JSON data']"));

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", downloadFile);

        downloadFile.click();

        Thread.sleep(5000);
        driver.close();
    }
}
