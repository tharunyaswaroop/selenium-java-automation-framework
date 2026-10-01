# Selenium Java Automation Framework

Independent portfolio demonstration using fictional data. Not an employer system or a production service.

## Project Overview

A compact framework demonstrating maintainable browser automation and failure evidence.

## Business Scenario

A fictional retailer validates checkout quantity, discount-before-tax calculations, and declined-payment messaging.

## Technologies Used

Java 17+, Selenium WebDriver, TestNG, Maven, Page Object Model, GitHub Actions, Jenkins recipe.

## Architecture

Settings → BrowserFactory → CheckoutPage → independent TestNG assertions. Each test gets its own browser and local fixture.

## Framework Structure

```text
src/main/java/portfolio/config/
src/main/java/portfolio/pages/
src/main/java/portfolio/utils/
src/main/resources/config.properties
src/test/java/portfolio/tests/
src/test/resources/testdata/
src/test/resources/fixture/
target/surefire-reports/ (generated)
target/screenshots/ (generated)
```

## Test Scenarios

Positive checkout, negative quantity, minimum/maximum quantity, discount limits, declined payment, error correction, smoke/regression groups.

## Prerequisites

JDK 17+, Maven 3.9+, installed Chrome or Edge. Initial dependency and driver acquisition need internet.

## Installation

```sh
git clone https://github.com/tharunyaswaroop/selenium-java-automation-framework.git
cd selenium-java-automation-framework
```

## How to Run Tests

```sh
mvn -B verify
mvn -B test -Dgroups=smoke
mvn -B test -Dbrowser=edge
mvn -B test -Dheadless=false
```

## Configuration

Defaults: Chrome, headless, 10-second explicit wait. Override with -Dbrowser=edge or QA_BROWSER=edge; -Dheadless=false or QA_HEADLESS=false. No credentials required.

## Reporting

TestNG HTML and Surefire XML in target/surefire-reports. Failure PNGs in target/screenshots; generated outputs are ignored. Screenshots only exist after a failure.

## CI/CD

GitHub Actions uses a Java 17 Linux runner with Chrome. Always uploads reports/screenshots. Jenkinsfile expects a Linux Java/Maven/browser agent and JUnit plugin.

## Sample Results

See [verification record](docs/VERIFICATION.md). Only observed runs belong in this record; CI configuration does not imply a successful CI run.

## Future Improvements

Add remote-grid execution, browser matrix, an actual local API/persistence layer, and mutation checks for failure-evidence handling.
