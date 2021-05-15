# CWSEcom
It is a app designed to sell electronics products like mobile phone ,laptop,
headphones etc.
● It contains a login and a signup page .
● Payment gateway of Paypal is provide for purchasing of items

Php  files are used to interact with the database created in localhost run by Xampp application.

Below are the functions of php files

cwsecomtable.php: This file is used to create a new account of the User in the database. Also, it checks if the user has already made an account.
deleteorder.php: This file deletes orders from the cart by sending the email and product id to the database.
fetchallproducts.php: This file is used to fetch all the products as a JSON object from the prodtable table created in the database.
fetchproductdetails.php: This file fetches the details of the product by the product id.
fetchproducts.php: This file fetches the products of a particular brand.
fetchtemporders.php: This file fetches the Orders of the user from placeorder table to be listed in the cart by sending the email. 
login.php: This file checks whether an account is already created by the user or not. If Created then the login is successful else throws an error.
placetemporder.php: This file insert values in the placeorder table when the user adds an item to the cart.
prodtable.php: This file fetches the different types of brands present in the prodtable.

Below are functions of Java files


