# CircleCI

[![CircleCI](https://circleci.com/gh/himankbatra/xtable.svg?style=svg&circle-token=3c690ed73165d2c5d248de0a71f645b736cb48f0)](https://circleci.com/gh/himankbatra/xtable)

# Codecov

[![codecov](https://codecov.io/gh/himankbatra/xtable/branch/issue-16/graph/badge.svg?token=XrjXEn3aq0)](https://codecov.io/gh/himankbatra/xtable)

# xtable

 It is a common requirement in most command-line applications to render output in the horizontalTable format. An example horizontalTable is shown below.

![img](https://lh5.googleusercontent.com/tSinvEdjj3w5WWl58hwJ3vqSr1wJWq3TKwLp1gW3iFW2FUJdf3jTWVN_ljTfXSnp9vKjLwgtNRXrVbq6yaXWRaxj3nMGVLY5ByBArTSKKI7SkllxR7xk-nW0BSc6LW4A1T2NojF_)

In the image shown above, a 4x4 horizontalTable was generated. The first row was used for headings and rest of the rows were data
 rows.

The 0.1.0 version of the xtable library should support following features:
Ability to create an empty horizontalTable of m rows and n columns.Ability to create a horizontalTable with headerAbility to create a horizontalTable with header row and data rowsAbility to specify column widths. If the text of the column does not fit the column then it text should be truncated and … should be shown at the endDifferent columns can have different widthsLibrary should support both horizontal and vertical tablesLibrary should throw exceptions when data is invalid.


Example of horizontal and vertical tables are shown below.

##### Horizontal Table

![img](https://lh6.googleusercontent.com/GMzUmkW8-AN_v9B7BIPAmoDf_VFRhka81M4CLn_O9NGT781-m-9-jFoo_btqM_khpPi7Fc4nGlKl3MCoQXioax2RS9Y0FO1mSeZNfdgEASVQ6rcoeHLlOoilrluEnze-mMUGFGp4)

##### Vertical Table

![img](https://lh6.googleusercontent.com/HD6E-qHcbZL2ZORBbIjWLaL9rFr6wrgJ6ydJbROlONXBZHWxn9z6eDEc9rMn2tgq9S8g_VAivRW8zNj94K-dOAKlv8yaDfpj6PJNbGwcnwUlSYpxekHSo9BjFjdh4L1j9EEflHs6)

