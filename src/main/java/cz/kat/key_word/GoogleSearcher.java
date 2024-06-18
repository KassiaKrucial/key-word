package cz.kat.key_word;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


@Service
public class GoogleSearcher {

    private WebDriver driver;
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public String searchGoogleForKeyWord(String text) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\catty\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.get("https://www.google.com/");

        keyWord = text;
        WebElement cookie = driver.findElement(By.id("W0wltc"));
        cookie.click();

        WebElement searchBar = driver.findElement(By.id("APjFqb"));
        searchBar.sendKeys(keyWord);

        WebElement submitButton = driver.findElement(By.name("btnK"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement div = driver.findElement(By.className("yuRUbf"));
        wait.until(ExpectedConditions.elementToBeClickable(div)).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        String urlOfFirstAddress = driver.getCurrentUrl();
        driver.close();
        System.out.println(urlOfFirstAddress);
        return urlOfFirstAddress;
    }
}
