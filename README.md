## Supermarket 

A simple supermarket pricing solution. It is inspired on Pragmatic Dave’s Supermarket Kata - http://codekata.com/kata/kata01-supermarket-pricing/.

This code prices a supermarket shopping basket. Some items available are based on price per item, some items are based on weight. There are also group discounts to be aware of:
```
Three cans of beans for the price of two.
Two cans of Coke, for £1.
```


Use the following example of a receipt to know what data needs to be captured. There is no requirement to actually format or print a receipt.

```
Beans                0.50
Beans                0.50
Beans                0.50
Coke                 0.70
Coke                 0.70
Oranges
0.200 kg @  £1.99/kg 0.40
                    -----
Sub-total            3.30

Savings
Beans 3 for 2       -0.50
Coke 2 for £1       -0.40
                    -----
Total savings       -0.90
-------------------------
Total to Pay         2.40
```

### Scenario

The scenarios are defined in [checkout.feature](com/antonysohal/supermarket/checkout.feature).

The scenario steps and tests are in [src/test/java/com/antonysohal/supermarket/CheckoutStepsDefs.java](../src/test/java/com/antonysohal/supermarket/CheckoutStepsDefs.java) 

```
Feature: Checkout

  Scenario: Apply quantity discount
    Given I have shopping basket
    And the following products exist:
      | name        | price  |
      | Coke 330ml  | 0.99   |
      | Milk 2l     | 1.79   |
      | Apple       | 0.5    |
    And the following discounts exist:
      | name                  | product    | qty | discount |
      | 4 Coke for price of 3 | Coke 330ml | 4   | -0.99    |
      | 2 Milk 2l for £3      | Milk 2l    | 2   | -0.58    |
    And I add the following to my basket:
      | name        | qty |
      | Coke 330ml  | 6   |
      | Milk 2l     | 2   |
      | Apple       | 1   |
    When I checkout
    Then my total discount should be -1.57
    And my total should be 8.45


  Scenario: Apply quantity discount for combo products
    Given I have shopping basket
    And the following products exist:
      | name        | price  |
      | Starter     | 2.79   |
      | Main Course | 6.49   |
      | Dessert     | 1.99   |
      | 3 x Beer    | 4.99   |
    And the following discounts exist:
      | name                  | product                                  | qty | discount |
      | Meal for 2 for £10    | Starter, Main Course, Dessert, 3 x Beer  | 1   | -6.26    |
    And I add the following to my basket:
      | name        | qty |
      | Starter     | 1   |
      | Main Course | 1   |
      | Dessert     | 1   |
      | 3 x Beer    | 2   |
    When I checkout
    Then my total discount should be -6.26
    And my total should be 14.99
```

### My Approach

1. First designed class diagram to around responsibulyt of the objetcs. Created code as interfaces.
2. Set up test placeholder for Checkout
3. Using TDD approach, first I wrote a Scenarios using BDD via Gherkin
4. All tests failed and then implemented code to pass each step of scenario


## How to run
Run maven to run tests.
```bash
mvn package
```