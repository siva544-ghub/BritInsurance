Feature: Product API PATCH Endpoint
  As an API consumer
  I want to update product information
  So that I can maintain accurate product data

  Background:
    Given the API base URL is "https://api.restful-api.dev"

  @api
  Scenario Outline: Successfully update a product name
    When I update product with ID "<ID>" with name "<NAME>"
    Then the response status code should be 200
    And the response should contain product ID "ff808181932badb6019549a311c14554"
    And the response should contain product name "Apple MacBook Pro 16 (Updated Name)"
    And the response should contain year field "year" with value 2025
    And the response should contain price field "price" with value 1849.99
    And the response should contain CPU model field "CPU model" with value "Intel Core i9"
    And the response should contain Hard disk size field "Hard disk size" with value "5 TB"
    And the response should contain updatedAt timestamp
    Examples:
      | ID                               | NAME                                |
      | ff808181932badb6019549a311c14554 | Apple MacBook Pro 16 (Updated Name) |

  @api
  Scenario: Update a non-existent product
    When I update product with ID "999999" with name "Non-existent Product"
    Then the response status code should be 404
    And the response should contain error message

  @api
  Scenario: Verify product exists before updating
    Given product with ID "ff808181932badb6019549a311c14554" exists
    When I update product with ID "ff808181932badb6019549a311c14554" with name "Apple MacBook Pro 16 (Verified Update)"
    Then the response status code should be 200
    And the response should contain product ID "ff808181932badb6019549a311c14554"
    And the response should contain product name "Apple MacBook Pro 16 (Verified Update)"

  @api
  Scenario: Update with empty product name
    When I update product with ID "ff808181932badb6019549a311c14554" with name ""
    Then the response status code should be 200 or 400
    And if status code is 200 then name should be empty

  @api
  Scenario: Validate JSON schema of update response
    When I update product with ID "ff808181932badb6019549a311c14554" with name "Apple MacBook Pro 16 (Schema Test)"
    Then the response status code should be 200
    And the response should have valid JSON schema

  @api
  Scenario: Handle special characters in product name
    When I update product with ID "ff808181932badb6019549a311c14554" with name "MacBook Pro !@#$%^&*()_+"
    Then the response status code should be 200
    And the response should contain product name "MacBook Pro !@#$%^&*()_+"

  @api
  Scenario: Verify response time is acceptable
    When I update product with ID "ff808181932badb6019549a311c14554" with name "Apple MacBook Pro 16 (Response Time Test)"
    Then the response status code should be 200
    And the response time should be less than 2000 milliseconds

  @api
  Scenario Outline: Handle different name lengths
    When I update product with ID "ff808181932badb6019549a311c14554" with name "<product_name>"
    Then the response status code should be 200
    And the response should contain product name "<product_name>"

    Examples:
      | product_name                                                                                                                                                  |
      | MacBook Pro Short Name                                                                                                                                        |
      | MacBook Pro with a very long name that might exceed some character limits in some systems but should still be handled properly by a robust API implementation |
      | MacBook Pro 123456ff808181932badb6019549a311c1455489                                                                                                          |

  @api
  Scenario: Verify idempotency of PATCH request
    When I update product with ID "ff808181932badb6019549a311c14554" with name "Apple MacBook Pro 16 (Idempotency Test)"
    Then the response status code should be 200
    When I update product with ID "ff808181932badb6019549a311c14554" with name "Apple MacBook Pro 16 (Idempotency Test)" again
    Then the response status code should be 200
    And both responses should contain the same product name