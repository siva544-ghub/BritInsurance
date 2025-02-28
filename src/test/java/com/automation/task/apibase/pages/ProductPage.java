package com.automation.task.apibase.pages;

import com.automation.task.apibase.ApiEndpoints;
import com.automation.task.pojo.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.Map;

public class ProductPage {

    public ProductPojo createRequestSpecification(String id, String name) {
        ProductPojo product = new ProductPojo();
        product.setId(id);
        product.setName(name);
        product.setData(Map.of("year", 2025, "price", 1849.99, "CPU model", "Intel Core i9", "Hard disk size", "5 TB"));

        return product;
    }


    public Response updateProductName(ProductPojo product, String id) {

        RestAssured.baseURI = "https://api.restful-api.dev";
        Response response = RestAssured.given()
                .pathParam("id", product.getId())
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .patch(ApiEndpoints.OBJECT_BY_ID)
                .then()
                .extract().response();

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        return response;
    }


    public Response getProductById(String id) {
        return RestAssured.given()
                .pathParam("id", id)
                .when()
                .get(ApiEndpoints.OBJECT_BY_ID)
                .then()
                .extract()
                .response();
    }

    public void verifyJsonSchema(Response response) {
        response.then()
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.notNullValue())
                .body("data", Matchers.notNullValue())
                .body("updatedAt", Matchers.notNullValue());
    }

    public void verifyResponseTime(Response response, long maxTimeInMs) {
        response.then().time(Matchers.lessThan(maxTimeInMs));
    }


}
