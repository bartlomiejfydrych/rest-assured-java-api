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

## 🌐API covered with tests

### Link

[Trello](https://developer.atlassian.com/cloud/trello/rest/api-group-actions/#api-group-actions)

### Description

**Trello** – a web application for managing kanban boards, created in 2011 by two programmers: Joel Spolsky
and Michael Pryor. The software is currently managed and developed by Trello Enterprise – a subsidiary of Atlassian.

I won't copy here what mechanisms their API consists of. Everything is in the documentation, in the above URL.

## 📊Test Statistics

**Summary:**
  - Number of tests: 🔴SOON
  - Average test execution time: 🔴SOON
  - 🔴\<dodać opis gdzie jest rozpisane pokrycie\>

## 🏆Certificates - test automation course (Sii Poland)

In the catalogue:  
&emsp;📁documents  
&emsp;&emsp;📁certificates

The catalogue contains certificates of completion of the test automation course conducted by "Sii Poland".

The course consisted of 3 parts:

- **Part 1:** Basics of programming in `Java`
- **Part 2:** Writing frontend tests in `Selenium`
- **Part 3:** Writing API tests in `REST Assured`

## 🧰Frameworks and technologies used

### General

- IntelliJ IDEA
- Java
- JDK - Amazon Corretto 21.0.6
- Dotenv Java
- ChatGPT (for refactor and complicated methods)

### Backend (API tests)

- REST Assured
- JSONassert
- JSON Schema Validator (From REST Assured)

### Tests

- JUnit
- JUnit Platform Suite
- Java Faker
- AssertJ
- Allure Report

## 🎯What I learned and what I practiced

### General

#### Project

- Project setup (JDK etc.)
- Setting `.gitignore` file for Java files and more
- Adding dependencies from Maven repository to `pom.xml` file
- Setting variables to dependency versions in Maven
- Finding out what each framework/dependencies is responsible for
- Generating the project structure in the console using the `tree` command
- Refactor and optimize code with `ChatGPT`
- Installing and using plugins for IDE:
    - .ignore
    - Rainbow Brackets
    - Key Promoter X
    - Allure Report

#### Java

- Using environment variables (`.env`) with Dotenv Java
- Using `Builder`
- Using `config.properties` file
- Using `Enum` variables
- Managing file paths with `Paths.get()` methods
- Directory and class naming convention

#### Tests

- Generating random test data with `JavaFaker`
- 🟡(check)Configuring `Allure Report` and generating a test report
- 🟡(check)Setting tests to run in a specific order with `JUnit Suite`
- 🟡(check)Using assertions from the `AssertJ` framework

### API tests (REST Assured)

- Splitting `base URL` into configurable variables
- Configuring common settings for all requests with `RequestSpecBuilder()`
- Configuring logging only for failed tests with `RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();`
- Configuring logging of all request data (e.g. for debugging) with `RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());`
- Comparing objects with omitting ID and other parameters with `RecursiveComparisonConfiguration();`
- Creating methods that call requests with the option of passing parameters or payload as an argument
- Organize your file structure to be as consistent as possible with your organization's API documentation format
- Test Documentation management
- Creating classes/builders to manage payload/queryParameters
- 🔴SOON

### Solved problems

- 🔴SOON
