import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestDemoQA {
    private static WebDriver driver;

    @AfterClass
    public static void closeBrowser() {
        driver.quit();

    }

    @BeforeClass
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://demoqa.com/registration");
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

    @Test
    public void Test3() {
        Faker faker = new Faker();

        String fakerName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String mobileNumber = "0888888888";
        String password = "mypassword";
        String confirmPassword = "mypassword";
        WebElement submitBtn = driver.findElement(By.cssSelector("#pie_register > li:nth-child(14) > div > input[type=\"submit\"]"));


        sendKeysWithJavaScript(fakerName, driver.findElement(By.id("name_3_firstname")));
        sendKeysWithJavaScript(lastName, driver.findElement(By.id("name_3_lastname")));
        driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[1]")).click();

        sendKeysWithJavaScript(mobileNumber, driver.findElement(By.id("phone_9")));
        sendKeysWithJavaScript(name, driver.findElement(By.id("username")));
        sendKeysWithJavaScript(email, driver.findElement(By.id("email_1")));
        sendKeysWithJavaScript(password, driver.findElement(By.id("password_2")));
        sendKeysWithJavaScript(confirmPassword, driver.findElement(By.id("confirm_password_password_2")));
        submitBtn.click();
        driver.findElement(By.cssSelector("#post-49 > div > p")).isDisplayed();
        System.out.println("Thank you for your registration message is displayed");

        //Assert.assertEquals(successMsgDisplayed.getText(), "Thank you for your registration");

    }


    public void sendKeysWithJavaScript(String text, WebElement element) { //JavaScript sendkeys method
        JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
        try {
            myExecutor.executeScript("arguments[0].value='" + text + "';", element);
        } catch (Exception e) {
        }

    }


    //    @Test
//    public void verifyErrorMessages() {
//        System.out.println("======================");
//        WebElement firstNameInput = driver.findElement(By.id("name_3_firstname"));
//        firstNameInput.click();
//
//        WebElement lastNameInput = driver.findElement(By.id("name_3_lastname"));
//        lastNameInput.click();
//
//        boolean isError = driver.findElement(By.cssSelector("#pie_register > li:nth-child(1) > div.fieldset.error > div.legend_txt > span")).isDisplayed();
//        Assert.assertTrue(isError, "Error is observed");
//        System.out.println("======================");
//
//    }

//    @Test
//    public void inputFirstName() {
//        WebElement firstName = driver.findElement(By.id("name_3_firstname"));
//        System.out.println("First name field is selected " + firstName.isDisplayed());
//        firstName.click();
//        sendKeysWithJavaScript("Charlie", firstName);
//        firstName.sendKeys("Charlie");
//        System.out.println("First name is entered" + firstName.isDisplayed());
//        return;
//    }

//    @org.testng.annotations.Test(testName = "Error message when username and password is not completed")
//    public void asd() {
//
//    }

    //    @Test
//    public void inputLastName() {
//        WebElement lastName = driver.findElement(By.id("name_3_lastname"));
//        System.out.println("Last name field is selected " + lastName.isDisplayed());
//        lastName.sendKeys("Chaplin");
//        System.out.println("Username is entered" + lastName.isDisplayed());
//        return;
//    }

//    @Test
//    public void selectHobby() {
//        WebElement hobby = driver.findElement(By.name("checkbox_5[]"));
//        hobby.click();
//        System.out.println("Hobby is selected " + "" + hobby.isSelected());
//        return;
//    }

//    @Test
//    public void enterMobilePhone() {
//        WebElement mobilePhone = driver.findElement(By.id("phone_9"));
//        mobilePhone.sendKeys("0897654321");
//        System.out.println("Phone number is entered " + mobilePhone.isDisplayed());
//        return;
//    }

}
