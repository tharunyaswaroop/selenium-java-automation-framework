package portfolio.pages;
import portfolio.config.Settings;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public final class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Settings.get("wait.seconds"))));
    }
    public CheckoutPage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
        return this;
    }
    public CheckoutPage quantity(String value) {
        WebElement input = driver.findElement(By.id("quantity"));
        input.clear(); input.sendKeys(value); return this;
    }
    public CheckoutPage discount(String value) {
        WebElement input = driver.findElement(By.id("discount"));
        input.clear(); input.sendKeys(value); return this;
    }
    public CheckoutPage payment(String value) {
        new Select(driver.findElement(By.id("payment"))).selectByValue(value); return this;
    }
    public CheckoutPage submit() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click(); return this;
    }
    public String message() {
        return wait.until(d -> {
            String text = d.findElement(By.id("result")).getText();
            return text.isBlank() ? null : text;
        });
    }
}
