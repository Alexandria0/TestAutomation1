package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.time.Duration;

import static org.example.utils.Utils.explicitWait;
import static org.example.utils.Utils.fluentWait;

public class FormPom {

    private final WebDriver driver;
    public static JavascriptExecutor js;

    @FindBy(xpath = "//*[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    private WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    private WebElement userNumber;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    private WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//*[@id='state']")
    private WebElement stateDropdown;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement cityDropdown;

    public void setState(String stateParam) {
        stateDropdown.click();
        fluentWait(driver, By.xpath("//*[text()='" + stateParam + "']"), 10);
        WebElement state = driver.findElement(By.xpath("//*[text()='" + stateParam + "']"));
        state.click();
    }

    @Step("Set city")
    public void setCity(String cityParam) {
        cityDropdown.click();
        fluentWait(driver, By.xpath("//*[text()='" + cityParam + "']"), 10);
        WebElement city = driver.findElement(By.xpath("//*[text()='" + cityParam + "']"));
        city.click();
    }

    public void setHobbies(String hobbiesParam) {
        WebElement hobby = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='"+ hobbiesParam +"']/../input"));
        hobby.sendKeys(" ");
    }

    public void setSubjects(String subjectParam) {
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
    }

    public void setGender(String genderParam) {
        WebElement element = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        element.click();
    }

    public FormPom(WebDriver driver) {
        this.driver = driver;
        js =  (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void setLastName(String lastNameParam) {
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setFirstName(String firstNameParam) {
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setUserEmail(String userEmailParam) {
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
    }

    public void setUserNumber(String userNumberParam) {
        userNumber.clear();
        userNumber.sendKeys(userNumberParam);
    }



    public void setDateOfBirth(String dateOfBirthParam) {
        dateOfBirthInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateOfBirthInput.sendKeys(dateOfBirthParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);

    }

    public void submitForm() {
        // Așteaptă ca butonul să fie clicabil
        WebElement submitBtn = explicitWait(driver, ExpectedConditions.elementToBeClickable(By.id("submit")), 10);
        submitBtn.click();
    }

    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }

    public void scrollToEl() {
        scrollToElement(dateOfBirthInput);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
