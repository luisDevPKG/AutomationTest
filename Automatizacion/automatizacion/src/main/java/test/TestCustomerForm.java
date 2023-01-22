/*
 * Script desarrollado por Luis Fernando Mosquera como requisito en prueba tecnica de Automation Tester
 */
package test;

import org.example.Login;
import org.example.Customer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.example.Login;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.TestRunner.PriorityWeight.priority;

public class TestCustomerForm {

    String driverPath = "/Users/lucho/Downloads/chromedriver";
    WebDriver driver;
    Login objLogin;
    Customer objCustomer;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://serenity.is/demo/");
    }

    // ingresa al modulo Customer y posteriormente a Nuevo Customer
    @Test(priority = 0)
    public void test_newCustomer() throws InterruptedException {
        objLogin = new Login(driver);
        objLogin.loginUser("admin", "serenity");
        Thread.sleep(5000);
        objLogin.getHomepageUrl();
        objCustomer = new Customer(driver);
        objCustomer.formNewCustomer("LFM97","TEST LTDA", "Luis Mosquera", "Luis",
                                    "CRA 2A-21", "Centro", "40020", "323421424",
                                    "5768394", "pruebas@gmail.com");
        Thread.sleep(3000);
        objCustomer.visibleDatatable();
        objLogin.close();
    }

    // Valida alerta a la hora de guardar un Nuevo customer sin llenar el formulario
    @Test(priority = 1)
    public void test_validate_required_fields() throws InterruptedException {
        setUp();
        objLogin = new Login(driver);
        objLogin.loginUser("admin", "serenity");
        Thread.sleep(5000);
        objLogin.getHomepageUrl();
        objCustomer = new Customer(driver);
        objCustomer.moduleCustomer();
        objCustomer.saveNewCustomer();
        Thread.sleep(3000);
        objCustomer.requiredFields();
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

