Feature: Crossover Job Portal Job Search Functionality

  Background: Open application in browser
    Given navigates to URL : "${defaultURL}" application
    Then application shows main home page

  @Sanity
  Scenario: Verify that search filters are working fine
    # Step 1,2
    When click "Available Jobs" link on the page
    And move to 1 tab in browser
    Then verify that default job page is opened
    # Step 3
    When focus on the "Job title" field
    Then verify that field "Job title" is currently focused
    # Step 4
    When type "Chief" in "Job title" field
    Then verify that field "Job title" has value "Chief" in form
    # Step 5
    When click on "SEARCH JOBS" button
    Then verify that results are shown with "Chief"
    # Step 6
    When click on "RESET" button
    Then verify that default job page is opened
    # Step 7,8
    When choose "Java" in "All Job Categories" combobox field
    Then verify that results are shown with "Java"
    # Step 9
    When click on "RESET" button
    Then verify that default job page is opened
    # Step 10
    When click on mobile menu button
    And click "About us" link on the page
    And click "Home" link on the page
    Then application shows main home page
    # Step 11
    When close the browser
    Then application is closed