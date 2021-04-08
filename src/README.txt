ENEL409 Principles of Software Development (W2021)
Term Project - README
**************************************************************
Group 16:
Michael Tagg
Omar Erak
Dylan Mah
Darsh Shah
**************************************************************
Functional Overview:

This project includes a package "edu.ucalgary.ensf409" where a main class:
InventorySystem.java, will be found. Compiling this file will compile the package.
Running the compiled InventorySystem will prompt the user in terminal to provide
MySQL database credentials (user/password), it is assumed that an inventory database
is available. Following authentication, three user inputs are required to create 
an order, furniture category, type, and quantity. The program will calculate the cheapest
combination of partial furniture to fulfil the order request. In the case where
there are no possible combinations, a message will appear in terminal with some
suggested manufacturers, otherwise upon success, an order form will be created in
your working directory with the name, "orderform.txt". The inventory database will
be updated following the creation of an order form.

**************************************************************
Example Commands using a working directory one layer above "edu\" and a "lib\" 
directory holding "mysql-connector-java-8.0.23.jar":

1. 
javac -cp ;lib\mysql-connector-java-8.0.23.jar; edu\ucalgary\ensf409\InventorySystem.java

2. 
java -cp ;lib\mysql-connector-java-8.0.23.jar; edu.ucalgary.ensf409.InventorySystem

**************************************************************
fin