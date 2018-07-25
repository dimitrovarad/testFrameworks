import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JoeColantonio {
    WebDriver driver;

    @BeforeClass
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.joecolantonio.com/SeleniumTestPage.html");

    }


    public void sendKeysWithJavaScript(String text, WebElement webElement) { //JavaScript sendkeys method
        JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
        try {
            myExecutor.executeScript("arguments[0].value='" + text + "';", webElement);
        } catch (Exception e) {
        }

    }

    @Test
    public void verifyBtn() {
        driver.findElement(By.id("waitButton")).click();
        WaitTool.waitForElement(driver, By.id("waitButton"), 5);
        boolean isDisplayed = driver.findElement(By.id("waitButton")).isDisplayed();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed);

        softAssert.assertAll();
    }

    @Test(dataProvider = "test1")
    public void sendFirstName(String name, String lastName) {
        WebElement inputFirstName = WaitTool.waitForElement(driver, By.cssSelector("#content-2-1 > form:nth-child(2) > input[type=\"text\"]:nth-child(2)"), 6);
        sendKeysWithJavaScript(name, inputFirstName);
    }

    //This method will provide data to any est method that declares its Data Provider
    // is named "test1"
    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][]
                {
                        {"Ivan", "Ivanov"},
                        {"Tozi", "Onzi"},
                        {"Todor", "Typoto"},
                };
    }
}
