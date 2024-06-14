Feature: Product Search And Check the subtotal

  Scenario: Validate the user is able to search a product and check the subtotal of the products
    Given the user launches the argos website
    And the user validates the correct website is displayed
    When the user searches for a product "chair" in searchbox
    Then the user verifies the correct search results of the "chair" is displayed
    And  the user opens the product  from the search result
    And  the user add the product to the basket
    And the user validates the details of the product added in the basket
    When the user increase the quantity to "2" in the basket
    Then the user validates the subtotal is updated

