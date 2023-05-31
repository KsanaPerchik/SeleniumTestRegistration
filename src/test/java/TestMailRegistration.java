import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;

public class TestMailRegistration {
    private WebDriver driver;

    HashMap<String,By> locators = new HashMap<>();

    private HashMap<String,By> initLocators() {
        locators.put("buttonRegistrationLocator", By.cssSelector(".start__btn-wrapper"));
        locators.put("buttonCreateLocator", By.cssSelector("button.base-0-2-32:nth-child(23)"));
        locators.put("textErrorNameLocator", By.xpath("//*[text()='Укажите имя']"));
        locators.put("textErrorSurnameLocator", By.xpath("//*[text()='Укажите фамилию']"));
        locators.put("textErrorMailLocator", By.xpath("//*[text()='Укажите желаемое имя ящика']"));
        locators.put("textErrorPasswordLocator", By.xpath("//*[text()='Укажите пароль']"));
        locators.put("textNameLocator", By.cssSelector("[name=fname]"));
        locators.put("textSurnameLocator", By.cssSelector("[name=lname]"));
        locators.put("textNameMailLocator", By.cssSelector("[autocomplete=username]"));
        locators.put("textPasswordLocator", By.cssSelector("[autocomplete=new-password]"));
        locators.put("radioGenderLocator", By.cssSelector("label.label-0-2-145:nth-child(1) > div:nth-child(1) > div:nth-child(3)"));
        locators.put("dayBirthdayLocator", By.cssSelector("[data-test-id=birth-date__day]"));
        locators.put("dayOneBirthdayLocator", By.xpath("//*[text()='1']"));
        locators.put("monthBirthdayLocator", By.cssSelector("[data-test-id=birth-date__month]"));
        locators.put("monthJanuaryBirthdayLocator", By.xpath("//*[text()='Январь']"));
        locators.put("yearBirthdayLocator", By.cssSelector("[data-test-id=birth-date__year]"));
        locators.put("yearNumberBirthdayLocator", By.xpath("//*[text()='2000']"));

        return locators;
    }

    private void getDriverText(String locator) {
        try {
        System.out.println(driver.findElement(locators.get(locator)).getText());
        } catch (Exception e) {
            System.err.println("Locator not found: " + locator);
            this.driver.quit();
            System.exit(0);
        }
    }

    private void settDriverText(String locator, String setText) {
        try {
        driver.findElement(locators.get(locator)).sendKeys(setText);
        } catch (Exception e) {
            System.err.println("Locator not found: " + locator);
            this.driver.quit();
            System.exit(0);

        }
    }

    private void setDriverClick(String locator) {
        try {
        driver.findElement(locators.get(locator)).click();
        } catch (Exception e) {
            System.err.println("Locator not found: " + locator);
            this.driver.quit();
            System.exit(0);

        }
    }

    @Before
    public void setUp() throws InterruptedException {
        initLocators();
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.navigate().to("https://new.mail.ru/");
        setDriverClick("buttonRegistrationLocator");
        Thread.sleep(3000);
        setDriverClick("buttonCreateLocator");
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void createMailEmptyField() {
        getDriverText("textErrorNameLocator");
        getDriverText("textErrorSurnameLocator");
        getDriverText("textErrorMailLocator");
        getDriverText("textErrorPasswordLocator");
    }

    @Test
    public void createMailNameField() {

        settDriverText("textNameLocator", "Petr");

        getDriverText("textSurnameLocator");
        getDriverText("textErrorMailLocator");
        getDriverText("textErrorPasswordLocator");
    }

    @Test
    public void createMailSurnameField() {

        settDriverText("textSurnameLocator", "Petrov");

        getDriverText("textErrorNameLocator");
        getDriverText("textErrorMailLocator");
        getDriverText("textErrorPasswordLocator");
    }

    @Test
    public void createMailAllField() {

        settDriverText("textNameLocator", "Petr");
        settDriverText("textSurnameLocator", "Petrov");
        settDriverText("textNameMailLocator", "petrov12.23");
        setDriverClick("radioGenderLocator");
        setDriverClick("dayBirthdayLocator");
        setDriverClick("dayOneBirthdayLocator");
        setDriverClick("monthBirthdayLocator");
        setDriverClick("monthJanuaryBirthdayLocator");
        setDriverClick("yearBirthdayLocator");
        setDriverClick("yearNumberBirthdayLocator");
        settDriverText("textPasswordLocator", "oksanaSava");
    }
}
