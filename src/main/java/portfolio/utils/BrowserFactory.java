package portfolio.utils;
import portfolio.config.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class BrowserFactory {
    private BrowserFactory() {}
    public static WebDriver create() {
        boolean headless = Boolean.parseBoolean(Settings.get("headless"));
        if (Settings.get("browser").equals("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (headless) options.addArguments("--headless=new");
            options.addArguments("--window-size=1280,900");
            return new EdgeDriver(options);
        }
        if (!Settings.get("browser").equals("chrome")) throw new IllegalArgumentException("browser must be chrome or edge");
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--window-size=1280,900");
        return new ChromeDriver(options);
    }
}
