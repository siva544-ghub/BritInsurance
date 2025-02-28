package com.automation.task.apibase.stepdefinitions;

import com.automation.task.apibase.ApiEndpoints;
import com.automation.task.apibase.pages.ProductPage;
import com.automation.task.pojo.ProductPojo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class ProductStepDefinitions {

    @Steps
    private ProductPage productPage;

    @Steps
    private ApiEndpoints apiEndpoints;

    @Steps
    private ProductPojo product;

    private Response firstResponse;
    private Response secondResponse;

    @Given("the API base URL is {string}")
    public String getApiBaseUrl(String baseUrl) {
        baseUrl = apiEndpoints.BASE_URL;
        return baseUrl;
    }


    @When("I update product with ID \"([^\"]*)\" with name \"([^\"]*)\"$")
    public void updateProductWithDetails(String id, String name) {
        product = productPage.createRequestSpecification(id, name);
        firstResponse = productPage.updateProductName(product, id);
    }

    @When("I update product with ID {string} with name {string} again")
    public void iUpdateProductWithIDWithNameAgain(String id, String name) {
        product = productPage.createRequestSpecification(id, name);
        secondResponse = productPage.updateProductName(product, id);
    }


    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        firstResponse.then().statusCode(statusCode);
    }

    @And("the response should contain product ID {string}")
    public void theResponseShouldContainProductID(String id) {
        firstResponse.then().body("id", Matchers.equalTo(id));
    }

    @And("the response should contain product name {string}")
    public void theResponseShouldContainProductName(String name) {
        firstResponse.then().body("name", Matchers.equalTo(name));
    }

    @And("the response should contain year field {string} with value {int}")
    public void theResponseShouldContainDataFieldWithIntValue(String field, int value) {
        firstResponse.then().body("data." + field, Matchers.equalTo(value));
    }

    @And("the response should contain price field {string} with value {double}")
    public void theResponseShouldContainDataFieldWithDoubleValue(String field, double expectedPrice) {
        Double actualPrice = firstResponse.jsonPath().getDouble("data.price");
        Assertions.assertEquals(expectedPrice, actualPrice, "Price does not match!");
    }

    @And("the response should contain CPU model field {string} with value {string}")
    public void theResponseShouldContainCpumodelFieldWithStringValue(String field, String expectedCpuModel) {
        String actualCpuModel = firstResponse.jsonPath().getString("data.'CPU model'");
        Assertions.assertEquals(expectedCpuModel, actualCpuModel, "CPU model does not match!");
    }

    @And("the response should contain Hard disk size field {string} with value {string}")
    public void theResponseShouldContainDataFieldWithStringValue(String field, String expectedDiskSize) {
        String actualDiskSize = firstResponse.jsonPath().getString("data.'Hard disk size'");
        Assertions.assertEquals(expectedDiskSize, actualDiskSize, "Hard disk size does not match!");
    }

    @And("the response should contain updatedAt timestamp")
    public void theResponseShouldContainUpdatedAtTimestamp() {
        firstResponse.then().body("updatedAt", Matchers.notNullValue());
    }

    @And("the response should contain error message")
    public void theResponseShouldContainErrorMessage() {
        firstResponse.then().body("error", Matchers.notNullValue());
    }

    @Given("product with ID {string} exists")
    public void productWithIDExists(String id) {
        Response response = productPage.getProductById(id);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("the response status code should be {int} or {int}")
    public void theResponseStatusCodeShouldBeOr(int statusCode1, int statusCode2) {
        int actualStatusCode = firstResponse.getStatusCode();
        Assert.assertTrue(
                "Status code should be either " + statusCode1 + " or " + statusCode2,
                actualStatusCode == statusCode1 || actualStatusCode == statusCode2
        );
    }


    @And("if status code is {int} then name should be empty")
    public void ifStatusCodeIsThenNameShouldBeEmpty(int statusCode) {
        if (firstResponse.getStatusCode() == statusCode) {
            firstResponse.then().body("name", Matchers.equalTo(""));
        }
    }

    @And("the response should have valid JSON schema")
    public void theResponseShouldHaveValidJSONSchema() {
        productPage.verifyJsonSchema(firstResponse);
    }

    @And("the response time should be less than {int} milliseconds")
    public void theResponseTimeShouldBeLessThanMilliseconds(int maxTimeInMs) {
        productPage.verifyResponseTime(firstResponse, maxTimeInMs);
    }

    @And("both responses should contain the same product name")
    public void bothResponsesShouldContainTheSameProductName() {
        String firstName = firstResponse.jsonPath().getString("name");
        String secondName = secondResponse.jsonPath().getString("name");
        Assert.assertEquals("Product names should be the same in both responses", firstName, secondName);
    }


}

