package com.automation.task.ui.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected WebElement waitForElement(By locator) {
        return waitForElement(locator, 10);
    }

    protected WebElement waitForElement(By locator, int timeoutInSeconds) {
        LOGGER.debug("Waiting for element: {}", locator);
        return withTimeoutOf(Duration.ofSeconds(timeoutInSeconds))
                .waitFor(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeClickable(By locator) {
        waitForElementToBeClickable(locator, 10);
    }

    protected void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        LOGGER.debug("Waiting for element to be clickable: {}", locator);
        withTimeoutOf(Duration.ofSeconds(timeoutInSeconds))
                .waitFor(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(By locator) {
        LOGGER.debug("Clicking element: {}", locator);
        waitForElementToBeClickable(locator);
        $(locator).click();
    }

    protected void typeInto(By locator, String text) {
        LOGGER.debug("Typing '{}' into element: {}", text, locator);
        waitForElement(locator);
        $(locator).clear();
        $(locator).sendKeys(text);
    }

    protected String getTextFrom(By locator) {
        LOGGER.debug("Getting text from element: {}", locator);
        waitForElement(locator);
        return $(locator).getText();
    }
}