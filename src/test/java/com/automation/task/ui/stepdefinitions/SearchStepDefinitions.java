package com.automation.task.ui.stepdefinitions;

import com.automation.task.ui.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;


public class SearchStepDefinitions {

    @Steps
    private SearchPage searchPage;

    @Given("User opens the Brit insurance url")
    public void userOpensTheBritInsuranceUrl() {
        searchPage.open();
        searchPage.validateHomePage();
    }

    @And("Clicks on the search icon")
    public void clicksOnTheSearchIcon() throws InterruptedException {
        searchPage.clickOnSearchButton();
    }

    @When("The user enters search term {string} in the search bar")
    public void enterSearchTermInSearchBar(String searchTerm) throws InterruptedException {
        searchPage.enterSearchText(searchTerm);
    }

    @When("Fetch the results and assert the values retrieved during search")
    public void fetchTheSearchResultsAndAssert() {
        searchPage.fetchResults();
    }


}