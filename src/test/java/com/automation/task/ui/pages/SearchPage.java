package com.automation.task.ui.pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SearchPage extends BasePage {

    private static final By SEARCH_BUTTON = By.xpath("//button[@aria-label='Search button']");
    private static final By SEARCH_FIELD = By.xpath("//input[@type='search']");
    private static final By RESULT_LIST = By.xpath("//div[@class='header--search__results']//a");
    private static final By ACCEPT_COOKIE = By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
    private static final By INSURANCE_LINK = By.xpath("//div[@class='item']//a[normalize-space()='Insurance']");
    private static final By RESPONSIVE_HEADING = By.cssSelector("span.mask--element");
    Logger logger = LoggerFactory.getLogger(SearchPage.class);


    public void validateHomePage() {
        clickElement(ACCEPT_COOKIE);
        logger.info("Clicked on Accept Cookies button..");
        waitForElement(INSURANCE_LINK);
        logger.info("Home page loaded and validated..");
        waitForElement(SEARCH_BUTTON);
    }

    public void clickOnSearchButton() throws InterruptedException {
        logger.info("Wait for the responsive heading to load..");
        waitForRenderedElementsToBePresent(RESPONSIVE_HEADING);
        logger.info("Page content loaded successfully..");
        logger.info("Clicking on search button..");
        clickElement(SEARCH_BUTTON);
        logger.info("Clicked on search button..");
    }

    public void enterSearchText(String searchTerm) {
        logger.info("Entering text into search bar");
        typeInto(SEARCH_FIELD, searchTerm);
    }

    public void fetchResults() {
        waitForElement(RESULT_LIST, 20);
        List<WebElement> results = thenReturnElementList(RESULT_LIST);

        // Extract text from each result title
        List<String> resultTitles = new ArrayList<>();
        for (WebElement result : results) {
            String titleText = result.getText().trim();
            resultTitles.add(titleText);
        }
        Assertions.assertThat(results)
                .hasSize(5)
                .withFailMessage("Expected 5 titles, but found %d", results.size());

        // Assert that the titles contain expected keywords
        List<String> expectedTitles = List.of(
                "Interim results for the six months ended 30 June 2022",
                "Results for the year ended 31 December 2023",
                "Interim Report 2023",
                "Kirstin Simon",
                "Gavin Wilkinson"
        );

        if (resultTitles.size() == 5) {
            Assertions.assertThat(resultTitles).containsAll(expectedTitles);
        }

    }
}