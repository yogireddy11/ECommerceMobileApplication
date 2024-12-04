package AlertsPack;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AlertClass {

    @FindBy(id = "demo") static WebElement getText;
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.navigate().to("https://demo.automationtesting.in/Alerts.html");
        WebElement okAlert = driver.findElement(By.xpath("//button[@onclick=\"alertbox()\"]"));
        okAlert.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        Thread.sleep(3000);
        WebElement okAndCancelAlert = driver.findElement(By.xpath("//a[@href=\"#CancelTab\"]"));
        okAndCancelAlert.click();
        WebElement alertBtn = driver.findElement(By.xpath("//button[@onclick=\"confirmbox()\"]"));
        alertBtn.click();
        System.out.println(alert.getText());
        alert.dismiss();
        Thread.sleep(3000);
        alertBtn.click();
        alert.accept();
        Thread.sleep(3000);
        driver.close();
    }
}
