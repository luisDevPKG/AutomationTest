/*
* Script desarrollado por Luis Fernando Mosquera como requisito en prueba tecnica de Automation Tester
 */
package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.example.Login;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TestLogin {
    String driverPath = "/Users/lucho/Downloads/chromedriver";
    WebDriver driver;
    Login objLogin;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://serenity.is/demo/");
    }

    // realiza un login exitoso y valida que al ingresar el username y password correctos se visualice el dashboard
    @Test(priority = 1)
    public void test_login_successful() throws InterruptedException {
        objLogin = new Login(driver);
        objLogin.loginUser("admin", "serenity");
        Thread.sleep(5000);
        objLogin.getHomepageUrl();
        objLogin.close();
    }

    // realiza un login fallido y valida que al ingresar el username y password incorrectos se visualice la alerta Invalid username or password!
    @Test(priority = 2)
    public void test_login_failed() throws InterruptedException {
        setUp();
        objLogin = new Login(driver);
        objLogin.loginUser("admin", "admin");
        Thread.sleep(3000);
        objLogin.getTextAlertLoginFail();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
