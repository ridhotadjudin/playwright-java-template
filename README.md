# 🎭 Playwright Java Template

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Playwright](https://img.shields.io/badge/Playwright-1.43.0-2EAD33?style=flat-square&logo=playwright&logoColor=white)](https://playwright.dev/java/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.16.1-23D96C?style=flat-square&logo=cucumber&logoColor=white)](https://cucumber.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-FF7300?style=flat-square&logo=testng&logoColor=white)](https://testng.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-43B02A?style=flat-square&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![Appium](https://img.shields.io/badge/Appium_Java_Client-8.3.0-6C2DC7?style=flat-square&logo=appium&logoColor=white)](https://appium.io/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)](LICENSE)

A production-ready boilerplate for browser and multi-platform test automation built with **Playwright for Java**, **Cucumber BDD**, and **TestNG**. Designed as a scalable starting point for teams adopting modern end-to-end testing with clean architecture and industry-standard design patterns.

---

## 📑 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Test Scenarios](#-test-scenarios)
- [Design Patterns](#-design-patterns)
- [Author](#-author)

---

## ✨ Features

- **Playwright-first browser automation** with auto-wait, network interception, and tracing
- **Cucumber BDD** for human-readable feature files and living documentation
- **TestNG** test runner with parallel execution and flexible suite configuration
- **Page Object Model (POM)** for maintainable, reusable page abstractions
- **Multi-platform ready** — Selenium and Appium Java Client included for web, mobile, and hybrid coverage
- **Visual testing support** via imgscalr image scaling for screenshot comparison workflows
- **Structured logging** with Log4j for detailed execution traces and debugging
- **Maven lifecycle integration** for CI/CD-friendly build, test, and report generation

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java (OpenJDK) | 17 | Language runtime |
| Maven | 3.9+ | Build and dependency management |
| Playwright for Java | 1.43.0 | Browser automation engine |
| Cucumber | 7.16.1 | BDD framework and feature file execution |
| TestNG | 7.9.0 | Test runner with parallel support |
| Selenium | 4.18.1 | Cross-browser WebDriver automation |
| Appium Java Client | 8.3.0 | Mobile and hybrid app automation |
| Log4j | 2.x | Application and test logging |
| imgscalr | 4.2 | Image scaling for visual testing |

---

## 📁 Project Structure

```
playwright-java-template/
├── pom.xml
├── testng.xml
├── log4j2.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── id/nexchief/
│   │           ├── pages/
│   │           │   └── LoginPage.java
│   │           ├── utils/
│   │           │   ├── BrowserManager.java
│   │           │   ├── ConfigReader.java
│   │           │   └── ScreenshotHelper.java
│   │           └── config/
│   │               └── AppConfig.java
│   └── test/
│       ├── java/
│       │   └── id/nexchief/
│       │       ├── runners/
│       │       │   └── TestRunner.java
│       │       ├── steps/
│       │       │   └── LoginSteps.java
│       │       └── hooks/
│       │           └── Hooks.java
│       └── resources/
│           └── features/
│               └── Login.feature
└── README.md
```

---

## 📋 Prerequisites

| Requirement | Minimum Version |
|---|---|
| Java JDK | 17 |
| Apache Maven | 3.9+ |
| Node.js (for Playwright browser install) | 18+ |

Playwright browsers are installed automatically via the Maven lifecycle. No manual browser download is needed.

---

## 🚀 Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/ridhotadjudin/playwright-java-template.git
cd playwright-java-template
```

**2. Install dependencies**

```bash
mvn clean install -DskipTests
```

**3. Install Playwright browsers**

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

**4. Run all tests**

```bash
mvn test
```

**5. Run tests by tag**

```bash
mvn test -Dcucumber.filter.tags="@Regression"
mvn test -Dcucumber.filter.tags="@Login"
```

**6. Run via TestNG suite**

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 🧪 Test Scenarios

| Feature | File | Tags | Description |
|---|---|---|---|
| Login | `Login.feature` | `@Regression` `@Login` | Validates login flows including valid credentials, invalid credentials, empty fields, and session persistence |

### Sample Feature

```gherkin
@Regression @Login
Feature: Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid credentials
    And the user clicks the login button
    Then the user should be redirected to the dashboard

  Scenario: Login fails with invalid credentials
    Given the user is on the login page
    When the user enters invalid credentials
    And the user clicks the login button
    Then an error message should be displayed
```

---

## 🏗 Design Patterns

### Page Object Model (POM)

Each page in the application is represented by a dedicated class under `id.nexchief.pages`. Page objects encapsulate element locators and user interactions, keeping step definitions clean and test logic reusable.

```java
public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate(ConfigReader.get("base.url") + "/login");
    }

    public void login(String username, String password) {
        page.fill("#username", username);
        page.fill("#password", password);
        page.click("#login-button");
    }
}
```

### Behaviour-Driven Development (BDD)

Cucumber feature files serve as the single source of truth for test scenarios. Step definitions in `id.nexchief.steps` map Gherkin steps to Playwright interactions through page objects, enabling collaboration between technical and non-technical stakeholders.

### Multi-Platform Architecture

The template includes Selenium WebDriver and Appium Java Client dependencies alongside Playwright, allowing teams to extend coverage to legacy browser grids, Android, and iOS targets without restructuring the project. Shared utilities in `id.nexchief.utils` abstract driver management across all platforms.

---

## 👤 Author

**Ridho Tadjudin**

- 🌐 [ridhotadjudin.id](https://ridhotadjudin.id)
- 🐙 [github.com/ridhotadjudin](https://github.com/ridhotadjudin)

---

<p align="center">
  Built with ☕ Java and 🎭 Playwright
</p>
