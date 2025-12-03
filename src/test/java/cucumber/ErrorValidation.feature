@error
Feature: Error validation
I want to use this template for my feature file

@errovalidation
Scenario Outline: Title of your scenario outline
Given I landed on Ecommerce Page
When Login with username <name> and password <password>
Then "Incorrect email or password." message is displayed

Examples: 
|name               |password|
|karb@yopmail.com |Tesy@123|