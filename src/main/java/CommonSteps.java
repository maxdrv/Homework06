import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CommonSteps {

    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public String BASE_URL;

    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    public void endTest() {
        driver.quit();
    }

    public void waitForVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        findByLocator(locator).click();
    }

    public void click(String xpath) {
        findByXpath(xpath).click();
    }

    public void clickJS(By locator) {

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", locator);
    }

    public void clickJS(String xpath) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", findByXpath(xpath));
    }

    public void selectByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public WebElement findByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement findByLocator(By locator) {
        return driver.findElement(locator);
    }



    public void checkElementText(WebElement element, String expectedText) {
        Assert.assertEquals("Значения заголовка не соотвествует  ожидаемому", expectedText, element.getText());
    }

    public void scrollToElement(By by) {
        Locatable element = (Locatable) driver.findElement(by);
        Point p = element.getCoordinates().onPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + p.getX() + "," + (p.getY()+150) + ");");
    }

    public void scrollToElement(String xpath) {
        Locatable element = (Locatable) driver.findElement(By.xpath(xpath));
        Point p = element.getCoordinates().onPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + p.getX() + "," + (p.getY()+150) + ");");
    }

    public void typeText(String xpath, String text) {
        findByXpath(xpath).sendKeys(text);
    }
}
