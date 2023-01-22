/*
* Script desarrollado por Luis Fernando Mosquera como requisito en prueba tecnica de Automation Tester 
 */
package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;

public class Login {

    WebDriver driver;
    By userNameLogin = By.xpath("//input[contains(@id,'Username')]");
    By passwordLogin = By.xpath("//input[contains(@id,'Password')]");
    By btnLogin = By.xpath("//button[contains(@id,'LoginButton')]");
    By alertMessage = By.xpath("//div[contains(@class,'toast-message')]");


    // constructor
    public Login(WebDriver driver){
        this.driver = driver;
    }

    // set username un textbox
    public void setUserName(String userName) throws InterruptedException {
        driver.findElement(userNameLogin).clear();
        Thread.sleep(2000);
        driver.findElement(userNameLogin).sendKeys(userName);
    }

    // set password un textbox
    public void setUserPassword(String userPassword) throws InterruptedException {
        driver.findElement(passwordLogin).clear();
        Thread.sleep(2000);
        driver.findElement(passwordLogin).sendKeys(userPassword);
    }

    // Clic login button
    public void clickLoginButton(){
        driver.findElement(btnLogin).click();
    }

    // Get URL of de homepage
    public void getHomepageUrl(){
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://demo.serenity.is/");
    }

    // Get text alert login fail
    public void getTextAlertLoginFail(){
        var alert = driver.findElement(alertMessage).getText();
        Assert.assertTrue(true, alert);
    }

    public void loginUser(String userName, String userPassword) throws InterruptedException {
        // send user name
        this.setUserName(userName);

        // send password
        this.setUserPassword(userPassword);

        // clic login button
        this.clickLoginButton();
    }
    public void close(){
        driver.close();
    }
}
