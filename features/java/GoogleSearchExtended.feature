Feature: Google Search Feature
To verify Google Search works
I want to run a search.

@Firefox @Examples @Example1
Scenario: Firefox Scenario
Given Using The Browser Firefox
And Searching For Selenium
When The Form is Submitted
Then The Window Title Should Contain Selenium

@IE @Examples @Example2
Scenario: IE Scenario
Given Using The Browser IE
And Searching For Selenium
When The Form is Submitted
Then The Window Title Should Contain Selenium

@Safari @Examples @Example3
Scenario: Safari Scenario
Given Using The Browser Safari
And Searching For Selenium
When The Form is Submitted
Then The Window Title Should Contain Selenium

@Chrome @Examples @Example4
Scenario: Chrome Scenario
Given Using The Browser Chrome
And Searching For Selenium
When The Form is Submitted
Then The Window Title Should Contain Selenium