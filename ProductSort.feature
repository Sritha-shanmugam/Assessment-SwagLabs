Feature: Product Sorting on the SWAGLabs page

  Scenario: Sort products from Name (A to Z)
    Given User is on the SWAGLabs product page
    When User sorts products by Name ascending


  Scenario: Sort products from Name (Z to A)
    Given User is on the SWAGLabs product page
    When User sorts products by Name decending


  Scenario: Sort products from Price (Low to High)
    Given User is on the SWAGLabs product page
    When User sorts products by Price ascending

  Scenario: Sort products from Price (High to Low)
    Given User is on the SWAGLabs product page
    When User sorts products by Price desending

