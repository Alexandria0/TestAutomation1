package org.example.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Utils {
    public static WebElement fluentWait(WebDriver driver, By by, int timeout) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    public static <T> T explicitWait(WebDriver driver, ExpectedCondition<T> condition, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(condition);
    }
}
