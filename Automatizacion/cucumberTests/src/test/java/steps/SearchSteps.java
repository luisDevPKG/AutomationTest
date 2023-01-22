package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchSteps {
    WebDriver driver;
    By userNameLogin = By.xpath("//input[contains(@id,'Username')]");
    By passwordLogin = By.xpath("//input[contains(@id,'Password')]");
    By btnLogin = By.xpath("//button[contains(@id,'LoginButton')]");

    @Given("as user of Serenity")
    public void as_user_of_serenity() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://serenity.is/demo/");
        Thread.sleep(4000);
    }
    @When("the user enter his username")
    public void the_user_enter_his_username() throws InterruptedException {
        driver.findElement(userNameLogin).clear();
        Thread.sleep(2000);
        driver.findElement(userNameLogin).sendKeys("admin");
    }
    @When("his password in the login form")
    public void his_password_in_the_login_form() throws InterruptedException {
        driver.findElement(passwordLogin).clear();
        Thread.sleep(2000);
        driver.findElement(passwordLogin).sendKeys("serenity");
    }
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() throws InterruptedException {
        driver.findElement(btnLogin).click();
        Thread.sleep(2000);
    }
    @Then("the user must enter the page and view the dashboard")
    public void the_user_must_enter_the_page_and_view_the_dashboard() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://demo.serenity.is/");
        driver.close();
        driver.quit();
    }
}

