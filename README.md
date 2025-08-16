SwagLabsTest 🚀

An automated test framework for SwagLabs (saucedemo.com), built using Java, Selenium WebDriver, TestNG, and Maven.
This project follows the Page Object Model (POM) design pattern and integrates Allure Reports and Log4j for logging and reporting.

📂 Project Structure
SwagLabsTest/
├── src/
│   ├── main/java
│   │   ├── DriverFactory/       # WebDriver setup and teardown
│   │   ├── Pages/              # Page Object Model classes
│   │   └── Utilities/          # Helper methods (logging, data utils, screenshots, etc.)
│   ├── main/resources          # Configs (log4j, allure)
│   ├── test/java
│   │   ├── Listener/           # TestNG listeners for reporting & screenshots
│   │   └── Tests/              # Test classes (Login, Cart, Checkout, etc.)
│   └── test/resources/TestData # Test data (JSON & properties)
├── Test Runner/                # TestNG XML suites
├── Test-outputs/               # Screenshots & reports
└── pom.xml                     # Maven dependencies & build config

⚙️ Tech Stack

Java 22

Selenium 4.33.0

TestNG 7.10.2

Allure Reports 2.29.0

Log4j2 for logging

Maven (build & dependency management)

Page Object Model (POM) pattern

🧪 Test Coverage

✅ Login Tests – Valid & invalid login

✅ Landing Page Tests – Adding products to cart (all & random)

✅ Cart Tests – Price verification in cart

✅ Checkout Tests – Form filling and navigation

✅ Overview Tests – Price verification with tax

✅ Finishing Order Tests – Completing the order successfully

▶️ How to Run

Clone the repository

git clone github.com/IbrahimDeif20/SwagLab-project
cd SwagLabsTest


Run tests with Maven

mvn clean test


Run a specific suite

mvn test -DsuiteXmlFile="Test Runner/LoginSuite.xml"


Generate Allure Report

allure serve Test-outputs/target/allure-results

📊 Reports

Allure Report → Provides interactive test reports

Screenshots → Captured automatically on failures (Test-outputs/ScreenShots/)

Log files → Stored under src/test/resources/Logs/

🏗️ Design Pattern

The project implements Page Object Model (POM) for:

Better readability

Easy maintenance

Reusability of page methods

📌 Future Enhancements

✅ Add CI/CD integration (GitHub Actions / Jenkins)

✅ Parallel execution support

✅ Extend test coverage

👤 Author

Ibrahim Deif
📧 ibrahim.deif123@gmil.com
💼 https://www.linkedin.com/in/ibrahim-deif-1aaa43259/
