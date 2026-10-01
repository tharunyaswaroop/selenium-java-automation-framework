package portfolio.tests;
import portfolio.pages.CheckoutPage;
import portfolio.utils.BrowserFactory;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;
import java.nio.file.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static org.testng.Assert.*;

public class CheckoutTest {
    private final ThreadLocal<WebDriver> browsers = new ThreadLocal<>();
    private static final System.Logger LOG = System.getLogger(CheckoutTest.class.getName());

    @BeforeMethod public void start() { browsers.set(BrowserFactory.create()); }
    @AfterMethod(alwaysRun=true) public void stop(ITestResult result) throws IOException {
        WebDriver driver = browsers.get();
        try {
            if (driver != null && !result.isSuccess()) {
                Path directory = Path.of("target", "screenshots");
                Files.createDirectories(directory);
                Path image = directory.resolve(result.getMethod().getMethodName() + "-" + UUID.randomUUID() + ".png");
                Files.write(image, ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                Reporter.log("Failure screenshot: " + image.toAbsolutePath());
                LOG.log(System.Logger.Level.ERROR, "Failed test: " + result.getName(), result.getThrowable());
            }
        } finally {
            if (driver != null) driver.quit();
            browsers.remove();
        }
    }
    private CheckoutPage page() {
        var fixture = getClass().getResource("/fixture/checkout.html");
        if (fixture == null) throw new IllegalStateException("Missing checkout fixture");
        return new CheckoutPage(browsers.get()).open(fixture.toExternalForm());
    }
    @DataProvider public Object[][] quantities() throws IOException {
        try (var stream = getClass().getResourceAsStream("/testdata/quantities.csv")) {
            if (stream == null) throw new IllegalStateException("Missing test data");
            return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines()
                .skip(1).filter(line -> !line.isBlank()).map(line -> line.split(",", 2)).toArray(Object[][]::new);
        }
    }
    @Test(groups={"smoke","regression"}) public void successfulCheckout() {
        assertEquals(page().submit().message(), "Approved: 1060 cents");
    }
    @Test(dataProvider="quantities",groups="regression") public void quantityBoundaries(String quantity, String expected) {
        assertEquals(page().quantity(quantity).submit().message(), expected);
    }
    @Test(groups="regression") public void discountBeforeTax() {
        assertEquals(page().quantity("2").discount("500").submit().message(), "Approved: 1590 cents");
    }
    @Test(groups="regression") public void invalidDiscount() {
        assertEquals(page().discount("1001").submit().message(), "Invalid discount");
    }
    @Test(groups="regression") public void declinedPayment() {
        assertEquals(page().payment("decline").submit().message(), "Payment declined; no receipt issued");
    }
    @Test(groups="regression") public void correctionAfterValidationError() {
        CheckoutPage checkout = page().quantity("0").submit();
        assertEquals(checkout.message(), "Quantity must be an integer from 1 to 99");
        assertEquals(checkout.quantity("2").submit().message(), "Approved: 2120 cents");
    }
}
