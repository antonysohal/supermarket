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