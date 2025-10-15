package org.example.test;

import org.example.pom.FormPom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormTest {

//    hyyf
    public static final Logger logger =  LogManager.getLogger(FormTest.class);

    static public WebDriver driver;
    static public String URL = "https://demoqa.com/automation-practice-form";
    static public String FIRST_NAME = "Alexandria";
    static public String LAST_NAME = "Țurcanu";
    static public String USER_EMAIL = "alexandriaturcanu@gmail.com";
    static public String GENDER = "Female";
    static public String NUMBER = "0672269811";
    static public String SUBJECTS1 = "Maths";
    static public String SUBJECTS2 = "English";
    static public String DATE_OF_BIRTH = "31 Jan 2007";
    static public String HOBBY1 = "Sports";
    static public String HOBBY2 = "Music";
    static public String STATE = "Haryana";
    static public String CITY = "Panipat";

    @BeforeClass
    public void beforeTest(){
        logger.info("Start beforeTest");
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void formTest() throws InterruptedException {
        logger.info("Start formTest");
        driver.get(URL);
        FormPom form = new FormPom(driver);
        form.closeAdvert();
        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.setUserEmail(USER_EMAIL);
        form.scrollToEl();
        form.setGender(GENDER);
        form.setUserNumber(NUMBER);
        form.scrollToEl();
        form.setDateOfBirth(DATE_OF_BIRTH);
        form.setSubjects(SUBJECTS1);
        form.setSubjects(SUBJECTS2);
        form.setHobbies(HOBBY1);
        form.setHobbies(HOBBY2);
        form.setState(STATE);
        form.setCity(CITY);
        form.submitForm();

        Thread.sleep(3000);

        WebElement actualName = driver.findElement(By.xpath("//table//*[text()='Student Name']/../td[2]"));
        Assert.assertEquals(actualName.getText(), FIRST_NAME + " " + LAST_NAME);

        WebElement actualEmail = driver.findElement(By.xpath("//table//*[text()='Student Email']/../td[2]"));
        Assert.assertEquals(actualEmail.getText(), USER_EMAIL);

        WebElement actualGender = driver.findElement(By.xpath("//table//*[text()='Gender']/../td[2]"));
        Assert.assertEquals(actualGender.getText(), GENDER);

        WebElement actualMobile = driver.findElement(By.xpath("//table//*[text()='Mobile']/../td[2]"));
        Assert.assertEquals(actualMobile.getText(), NUMBER);

        WebElement actualDateOfBirth = driver.findElement(By.xpath("//table//*[text()='Date of Birth']/../td[2]"));
        Assert.assertEquals(actualDateOfBirth.getText(), "31 January,2007", "Date of Birth nu corespunde!");

        WebElement actualSubjects = driver.findElement(By.xpath("//table//*[text()='Subjects']/../td[2]"));
        Assert.assertEquals(actualSubjects.getText(), SUBJECTS1 + ", " + SUBJECTS2);

        WebElement actualHobbies = driver.findElement(By.xpath("//table//*[text()='Hobbies']/../td[2]"));
        Assert.assertEquals(actualHobbies.getText(), HOBBY1 + ", " + HOBBY2);

        WebElement actualStateAndCity = driver.findElement(By.xpath("//table//*[text()='State and City']/../td[2]"));
        Assert.assertEquals(actualStateAndCity.getText(), STATE + " " + CITY, "State and City nu corespunde!");

        System.out.println();
        logger.info("Finish formTest");
    }
    @AfterClass
    public void afterTest(){

//      driver.quit();
        logger.info("Finish afterTest");

    }
}
