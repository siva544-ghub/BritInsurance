Feature: Search and assert on results

  @ui
  Scenario Outline: Search and Assert on the results
    Given User opens the Brit insurance url
    And Clicks on the search icon
    When The user enters search term "<SEARCH>" in the search bar
    Then Fetch the results and assert the values retrieved during search

    Examples:
      | SEARCH  |
      | IFRS 17 |

