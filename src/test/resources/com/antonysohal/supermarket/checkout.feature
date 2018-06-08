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