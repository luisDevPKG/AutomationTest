/*
 * Script desarrollado por Luis Fernando Mosquera como requisito en prueba tecnica de Automation Tester
 * */
package org.example;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.time.Duration;


public class Customer {

    WebDriver driver;

    By pageModules = By.xpath("//span[contains(.,'Northwind')]");
    By customerModule = By.xpath("(//span[contains(.,'Customers')])[1]");
    By newCustomer = By.xpath("(//span[@class='button-inner'])[1]");

    // selectors for customer general form
    By customerId = By.xpath("//input[contains(@id,'CustomerID')]");
    By companyName = By.xpath("//input[contains(@id,'CompanyName')]");

    // selectors for customer contact form
    By contactName = By.xpath("//input[contains(@id,'ContactName')]");
    By contactTitle = By.xpath("//input[contains(@id,'ContactTitle')]");
    By representativeList = By.xpath("//ul[@class='select2-choices'][contains(.,'Representatives')]");
    By representative = By.xpath("//div[@class='select2-result-label'][contains(.,'Andrew Fuller')]");

    // selectors for customer address form
    By address = By.xpath("//input[contains(@id,'Address')]");
    By countryList = By.xpath("(//span[@role='presentation'])[3]");
    By country = By.xpath("//div[@class='select2-result-label'][contains(.,'Argentina')]");
    By cityList = By.xpath("(//span[@role='presentation'])[4]");
    By city = By.xpath("//div[@class='select2-result-label'][contains(.,'Buenos Aires')]");
    By region = By.xpath("//input[contains(@id,'Region')]");
    By postalCode = By.xpath("//input[contains(@id,'PostalCode')]");
    By phone  = By.xpath("//input[contains(@id,'Phone')]");
    By fax = By.xpath("//input[contains(@id,'Fax')]");

    // selectors for customer details form
    By lastContactDate = By.xpath("(//i[@class='fa fa-calendar'])[1]");
    By date = By.xpath("//a[contains(.,'21')]");
    By lastContactedBy = By.xpath("(//span[@role='presentation'])[5]");
    By selectLastContactedBy = By.xpath("//div[@class='select2-result-label'][contains(.,'Janet Leverling')]");
    By email = By.xpath("//input[contains(@id,'Email')]");
    By selectBulletin = By.xpath("//input[contains(@id,'SendBulletin')]");

    // selector to save customer
    By saveCustomer = By.xpath("(//span[contains(@class,'button-inner')])[4]");

    // selector datatable
    By dataTable = By.xpath("//div[@class='title-text'][contains(.,'Customers')]");

    // selector to validate message required fields
    By alertMessage = By.xpath("//div[contains(@class,'toast-message')]");



    //Constructor
    public Customer(WebDriver driver){
        this.driver = driver;
    }

    // go to customer module
    public void moduleCustomer() throws InterruptedException {
        driver.findElement(pageModules).click();
        driver.findElement(customerModule).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.findElement(newCustomer).click();
        Thread.sleep(2000);
    }

    // fill new customer form
    public void fillGeneralInfo(String idCustomer, String nameCompany) {
        driver.findElement(customerId).sendKeys(idCustomer);
        driver.findElement(companyName).sendKeys(nameCompany);
    }
    public void fillContactInfo(String nameContact, String contTitle){
        driver.findElement(contactName).sendKeys(nameContact);
        driver.findElement(contactTitle).sendKeys(contTitle);
        driver.findElement(representativeList).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(representative).click();

    }
    public void fillAddressInfo(String addresInfo, String regionInfo, String codPostalInfo, String phoneInfo, String faxInfo){
        driver.findElement(address).sendKeys(addresInfo);
        driver.findElement(countryList).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(country).click();
        driver.findElement(cityList).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(city).click();
        driver.findElement(region).sendKeys(regionInfo);
        driver.findElement(postalCode).sendKeys(codPostalInfo);
        driver.findElement(phone).sendKeys(phoneInfo);
        driver.findElement(fax).sendKeys(faxInfo);
    }
    public void fillDetailsInfo(String emailInfo){
        driver.findElement(lastContactDate).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(date).click();
        driver.findElement(lastContactedBy).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(selectLastContactedBy).click();
        driver.findElement(email).sendKeys(emailInfo);
        driver.findElement(selectBulletin).click();
    }

    // clic Guardar button
    public void saveNewCustomer() {
        driver.findElement(saveCustomer).click();
    }

    // datatable
    public void visibleDatatable() {
        var validate = driver.findElement(dataTable).getText();
        Assert.assertEquals(validate,"Customers");
    }

    // validate required fields
    public void requiredFields(){
        var alert = driver.findElement(alertMessage).getText();
        Assert.assertTrue(true, alert);
    }

    public void formNewCustomer(String idCustomer, String nameCompany, String nameContact, String contTitle,
                                String addresInfo, String regionInfo, String codPostalInfo,
                                String phoneInfo, String faxInfo, String emailInfo) throws InterruptedException {
        this.moduleCustomer();
        this.fillGeneralInfo(idCustomer,nameCompany);
        this.fillContactInfo(nameContact, contTitle);
        this.fillAddressInfo(addresInfo, regionInfo, codPostalInfo, phoneInfo, faxInfo);
        this.fillDetailsInfo(emailInfo);
        Thread.sleep(3000);
        this.saveNewCustomer();

    }
    public void close(){
        driver.close();
    }
}
