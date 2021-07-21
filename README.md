# SiteTracker

Hello!!

Here you can find the automation test of the assignment

Project is made basically using:
  - Java 8
  - Cucumber
  - Junit
  - Selenium
  - Maven

There is one feature:
  - Salesforce.feature : It is testing the required functionalities for the challenge.

There is a file called Configuration.properties in the resource directory. In this file it can be configured:
  - Web browser for the test
  - Default time out time
  - Test environment (currently only one called PROD is configured)
  - URL for each environment
  
There is another file called defaultTestData.properties in the resource directory. In this file it can be 
configured some default test data to be used in the test when it is not needed on the feature file.

Depending on the IDE and the plugins the project can be run either:
- Running directly the features or scenarios (will run features or scenarios by demand)
- Running the test runner class (RunCucumberTest, will run sequentially the features)
- Using maven (will run test in parallel and create a more friendly report):
  * mvn clean compile (to clean and compile the project)
  * mvn verify (to actually run the test)

Regards.


