# Automation Framework

A comprehensive test automation framework using Selenium, Java, Cucumber, RestAssured, and Serenity BDD.

## Features

- UI testing with Selenium WebDriver 4
- API testing with RestAssured
- BDD approach with Cucumber
- Detailed reporting with Serenity BDD
- Parallel test execution
- Cross-browser testing support
- Environment-specific configuration
- Maven as build tool

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- IntelliJ IDEA (recommended)

## Setup

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Import as Maven project
4. Wait for dependencies to download


### How to Run Tests from Command Line?

# Run all tests

mvn clean verify

# Run with specific tags

mvn clean verify -Dcucumber.filter.tags="@ui" -->#Runs only UI tests
mvn clean verify -Dcucumber.filter.tags="@api" -->Runs only API tests

# Run with specific environment

mvn clean verify -Denvironment=dev -->Runs tests on dev environment

# Run with specific tags with specific browser

mvn clean verify -Dcucumber.filter.tags="@ui" -D"webdriver.driver=edge"     -->Runs only UI tests on edge
mvn clean verify -Dcucumber.filter.tags="@api" -D"webdriver.driver=chrome"    -->Runs only API tests on chrome

## Reports

After test execution, Serenity generates detailed reports in the `target/site/serenity` directory. Open `index.html` to view the report.

## Configuration

Environment-specific configuration is in `src/test/resources/serenity.conf`. You can override these settings using system properties(serenity.properties)



################################ **API TESTING High-Level approach** ################################

**High-Level Approach for API Testing:**

1. Review the API documentation
2. Identify the endpoints to be tested
3. Identify the request and response payloads, request parameters, headers, and authentication
4. Identify input fields, data types, and response structures.
5. Write test scenarios for each endpoint
    i) Positive scenarios
    ii) Negative scenarios
    iii) Edge cases
    iv) Response times
    v) Verify integration with other services if any
    vi) Cover security related scenarios as well
6. Write test steps and test cases for each scenario
7. Prepare test data for each test case
8. Execute the testcases using Manual or Automation(using postman, RestAssured, etc)
9. Use mock servers to simulate the API responses where ever we have dependency on other services
10. Validate the response and compare it with the expected results
11. Integrate the API tests with the CI/CD pipeline
12. Monitor the test results and analyze the test reports
13. Update the test cases based on the changes in the API


Exploratory test scenarios:

1. Verify the optional fields (test requests with various combinations of optional fields)
2. Empty payloads and invalid payloads and also invalid and malformed urls
3. Edge cases related to few fields especially amounts(min, max, boundary value, -ve values)
4. Unauthorized access to the API
5. Error scenarios like 404, 401, 403 and so on 
6. Test the rate limits
7. Test the API with different headers and content types
8. Data modification, deletion, creation and retrieval