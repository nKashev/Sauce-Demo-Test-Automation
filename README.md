# Sauce-Demo-Test-Automation

Automation test task using the following tools:
1. Maven
2. Selenium
3. TestNG
4. Java 17
5. Cucumber

Test works on Chrome browser: [SauceDemo](https://www.saucedemo.com/)

Test scenario:
1. Login with standard_user.
2. Verify you are on products page.
3. Add two products of your choice.
4. Verify they are successfully added to the cart (without going to the cart, verification should be done from the products page).
5. Remove them from cart and verify products were successfully removed from cart (without going to the cart, verification should be done from the products page).
6. Add two products by your choice to the cart.
7. Verify they are successfully added (without going to the cart, verification should be done from the products page).
8. Go to cart and verify the products you selected in step 6 are displayed properly on this screen.
9. Continue to next screen and try to submit the empty form and validate the errors.
10. Clear form, fill it in and click continue.
11. Verify that information on the last preview screen is shown properly.
a. Price
b. Items
c. Buttons
12. Click continue and verify success message for successful purchase.
13. Logout

I created workflows to run daily tests on the latest Windows, Mac and Ubuntu operating systems.

[![Open Actions](https://img.shields.io/badge/Open-Actions-brightgreen?style=for-the-badge)](https://github.com/nKashev/Sauce-Semo-Test-Automation/actions)
