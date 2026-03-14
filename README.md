<div align="center">
  <img src="project_banner.jpeg" alt="project banner, rest assured and java logo, project name">
</div>

# 🚧DO NOT ENTER! – Repository under construction🚧

# 📑Information about this repository (for recruiters)

## 📄Description

### Introduction

Welcome to my repository, recruiters. Feel free to review its contents at your convenience.🙋‍♂️

This repository was created to structure and reinforce the knowledge I acquired during **the test automation course
conducted by "Sii Poland"**.

A separate repository will be allocated for **Frontend testing using Selenium.**

### About repository

This repository contains **API Tests using REST Assured and Java.**

I covered with tests **API** of the website **Trello**.

A description of the site and a list of things I learned are in the later sections of this document.

### Additional notes

Some of the comments in the repository are in Polish, so that when I come back to it, I can more easily remember and
understand what a given piece of code was about.

There are also comments in English to explain to you (recruiters) what a given piece of code is about and why I wrote
a certain method/test this way and not another.

### Note for recruiters

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

## 🌐API covered with tests

### Link

[Trello](https://developer.atlassian.com/cloud/trello/rest/api-group-actions/#api-group-actions)

### Description

**Trello** – a web application for managing kanban boards, created in 2011 by two programmers: Joel Spolsky
and Michael Pryor. The software is currently managed and developed by Trello Enterprise – a subsidiary of Atlassian.

I won't copy here what mechanisms their API consists of. Everything is in the documentation, in the above URL.

## 📊Test Statistics

I know I could do more, but I also don't want to stay on this project too long because I also want to learn other
things/languages/technologies.

### Summary

| Tests type   | Quantity | Average execution time |
|--------------|----------|------------------------|
| API (Trello) | 101      | 52 sec 675 ms          |
| Unit tests   | 45       | 2 sec 747 ms           |

### Details (tests structure) 

```
├───📁tests
│   ├───📁api_trello
│   │   ├───📂boards
│   │   │   ├───©️DEL_DeleteBoardTest
│   │   │   │   ├───✅Positive: (1)
│   │   │   │   └───❌Negative: (0)
│   │   │   ├───©️POST_CreateBoardTest
│   │   │   │   ├───✅Positive: (5)
│   │   │   │   └───❌Negative: (12)
│   │   │   └───©️PUT_UpdateBoardTest
│   │   │   │   ├───✅Positive: (5)
│   │   │   │   └───❌Negative: (13)
│   │   ├───📂emoji
│   │   │   └───©️GET_ListAvailableEmojiTest
│   │   │   │   ├───✅Positive: (4)
│   │   │   │   └───❌Negative: (1)
│   │   ├───📂labels
│   │   │   ├───©️DEL_DeleteLabelTest
│   │   │   │   ├───✅Positive: (1)
│   │   │   │   └───❌Negative: (0)
│   │   │   ├───©️POST_CreateLabelTest
│   │   │   │   ├───✅Positive: (6)
│   │   │   │   └───❌Negative: (7)
│   │   │   ├───©️PUT_UpdateFieldOnLabelTest
│   │   │   │   ├───✅Positive: (7)
│   │   │   │   └───❌Negative: (2)
│   │   │   └───©️PUT_UpdateLabelTest
│   │   │   │   ├───✅Positive: (4)
│   │   │   │   └───❌Negative: (1)
│   │   └───📂lists
│   │       ├───©️POST_CreateNewListTest
│   │       │   ├───✅Positive: (6)
│   │       │   └───❌Negative: (11)
│   │       └───©️PUT_UpdateListTest
│   │           ├───✅Positive: (8)
│   │           └───❌Negative: (7)
```

### Coverage and endpoint documentation

- 📁[documentation](src/test/documentation)
    - 📁[endpoints](src/test/documentation/endpoints)
        - 📂[boards](src/test/documentation/endpoints/boards)
            - ©️[POST_CreateBoard.md](src/test/documentation/endpoints/boards/POST_CreateBoard.md)
            - ©️[PUT_UpdateBoard.md](src/test/documentation/endpoints/boards/PUT_UpdateBoard.md)
        - 📂[emoji](src/test/documentation/endpoints/emoji)
            - ©️[GET_ListAvailableEmoji.md](src/test/documentation/endpoints/emoji/GET_ListAvailableEmoji.md)
        - 📂[labels](src/test/documentation/endpoints/labels)
            - ©️[POST_CreateLabel.md](src/test/documentation/endpoints/labels/POST_CreateLabel.md)
            - ©️[PUT_UpdateFieldOnLabel.md](src/test/documentation/endpoints/labels/PUT_UpdateFieldOnLabel.md)
            - ©️[PUT_UpdateLabel.md](src/test/documentation/endpoints/labels/PUT_UpdateLabel.md)
        - 📂[lists](src/test/documentation/endpoints/lists)
            - ©️[POST_CreateNewList.md](src/test/documentation/endpoints/lists/POST_CreateNewList.md)
            - ©️[PUT_UpdateList.md](src/test/documentation/endpoints/lists/PUT_UpdateList.md)

### Sample test steps (positive)

1. Resources from the `@BeforeAll` section are added.
2. Resources from the `@BeforeEach` section are added.
3. **TEST – START**
4. We define `variables, data, parameters/payload`.
5. `POST request` is called.
6. We check if it has status `200`.
7. We convert the response to a `DTO object` and validate all its fields at the same time (something like `JsonSchema`).
8. We are checking some `unique, unusual data` over which we have no influence.
9. We prepare the `expected response` object and substitute it with the data we used.
10. We `compare` the data from the request with our expected response, excluding unique fields.
11. We call a `GET request` and convert it into a DTO object with field validation.
12. We `compare` the previously sent response with the GET response.
13. **TEST – END**
14. Created resources are cleaned/deleted in the `@AfterEach` section.
15. Created resources are cleaned/deleted in the `@AfterAll` section.

## 🏆Certificates - test automation course (Sii Poland)

In the catalogue:  
&emsp;📁documents  
&emsp;&emsp;📁[certificates](documents/certificates)

The catalogue contains certificates of completion of the test automation course conducted by "Sii Poland".

The course consisted of 3 parts:

- **Part 1:** Basics of programming in `Java` – [Test Automation Certificate (Sii) - Part 1.pdf](documents/certificates/Test%20Automation%20Certificate%20%28Sii%29%20-%20Part%201.pdf)
- **Part 2:** Writing frontend tests in `Selenium` – [Test Automation Certificate (Sii) - Part 2.pdf](documents/certificates/Test%20Automation%20Certificate%20%28Sii%29%20-%20Part%202.pdf)
- **Part 3:** Writing API tests in `REST Assured` – [Test Automation Certificate (Sii) - Part 3.pdf](documents/certificates/Test%20Automation%20Certificate%20%28Sii%29%20-%20Part%203.pdf)

## 🧰Frameworks and technologies used

### General

- IntelliJ IDEA
- Java
- JDK - Amazon Corretto
- Dotenv Java
- ChatGPT (for refactor and complicated methods)
- Logback Classic (In order to get rid of excess logs/warnings)

### Backend (API tests)

- REST Assured
- To validate response:
  - Jackson Databind
  - Hibernate Validator Engine
  - Jakarta Validation API
  - Jakarta Expression Language Implementation
  - Jakarta Expression Language API

### Tests

- Test runner:
  - JUnit Jupiter
  - JUnit Platform Suite
- Java Faker
- AssertJ
- Allure Report:
  - Allure Junit5
  - Allure Rest Assured
- Jansi (Colored JSON in the console)

## 🎯What I learned and what I practiced

### General

- Managing your work by writing a list of steps/goals to accomplish
- Generating the project structure in the console using the `tree` command
- Refactor and optimize code with `ChatGPT`
- Noting down information that I consider important, both in notes (README)
  and in the code (sometimes it's necessary)

### Project

- Project setup (JDK etc.)
- Setting `.gitignore` file for Java files and more
- Adding dependencies from Maven repository to `pom.xml` file
- Setting variables to dependency versions in Maven
- Finding out what each framework/dependencies is responsible for
- Using environment variables (`.env`) with Dotenv Java
- Using `config.properties` file
- Installing and using plugins for IDE:
  - .ignore
  - Rainbow Brackets
  - Key Promoter X
  - Allure Report

### Java

- Using `Builder`
- Using `Enum`
- Managing file paths with `Paths.get()` methods
- Directory and class naming convention  
  - I know my file names don't indicate this, but I think it's clearer and the AI said that if it's just tests,
    there's no problem with it
- How to declare a variable of type `Long`  
  - It's about adding `L` to the end of the number and that underlining can be added to large numbers to improve
    readability, e.g.`140_737_488_322_560L`)
- Reading data from configuration and .env files
- Creating my own exceptions

### Tests

- Generating random test data with `JavaFaker`
- Using tags in `JUnit` to run specific groups of tests or in a specific order
- Running tests in a specific order using the "suite" class in `JUnit`
- Where possible, extract request parameters and their values into `enums`
- Configuring `Allure Report` and generating a test report
- Changing the look of the `Allure Report`
- JSON Coloring in `Allure Report`
- Setting certain things to be done before ALL tests, e.g. changing the appearance of the `Allure Report`
- Using assertions from the `AssertJ` framework
- Adding comments/logs to assertions in `AssertJ` framework
- Writing unit tests
- Separating API tests from unit tests
- Organization of tests for positive and negative
- Managing supporting resources in tests using `@BeforeAll`, `@BeforeEach`, `@AfterAll`, `@AfterEach` annotations
- Getting rid of redundant logs and warnings in the console using `Logback Classic` and its configuration
- Test documentation management:
  - Basic information about the endpoint
  - Test coverage tracking
  - Payload example
  - Response example

### API tests (REST Assured)

- Splitting `base URL` into configurable variables
- Configuring common settings for all requests with `RequestSpecBuilder()`
- Configuring logging of all request data (e.g. for debugging) with `RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());`
- Creating my own logger and colorizing JSON in the console
- Comparing objects (responses) with omitting ID and other parameters with `RecursiveComparisonConfiguration();` from `Assertj`
- Creating methods that call requests with the option of passing parameters or payload as an argument
- Organize my file structure to be as consistent as possible with your organization's API documentation format
- Creating classes/builders to manage payload/queryParameters/small expected responses
- Converting response to `DTO`
- Validating response fields using `Jackson` and `Jakarta` validation instead of JsonSchema
- Comparing two responses/JSONs without having to create objects for them in the code (mainly for negative tests)
- Reading and comparing the expected response from a file (for large JSONs)
