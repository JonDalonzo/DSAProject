# DSAProject

Write a program that models a simplified Shopping Center environment with the following options:

1.  Customer enters Shopping Center.

2.  Customer picks an item and places it the shopping cart.

3.  Customer removes an item from the shopping cart.

4.  Customer is done shopping.

5.  Customer checks out.

6.  Print info about customers who are shopping.

7.  Print info about customers in checkout lines.

8.  Print info about items at or below re-stocking level.

9.  Reorder an item.

10.Close the Shopping Center.

 

Here are the Shopping Center environment rules:

1.       When the program starts, the shopping environment is initialized by prompting the user to specify the available items and their quantities in the shopping center.

2.       The customers enter the Shopping Center (option 1). The names for the customers are unique identifiers.

3.       There are 3 checkout lines. One for express checkout (for customers with the number of items <=5) and 2 regular checkout lines. The names are express, regular1 and regular2.

4.       The customers inside the Shopping Center can pick up items and place them in their shopping cart (option 2) or can remove items from their cart (option 3). When a customer picks up an item, the program should ask for the name of the item and the amount of stock for that item decreases by one. For the customer, only the number of items in the cart matters, not the specific items. When a customer removes and item from the cart (option 3), the number of items in the shopping cart decreases, but the item that was removed does not go back into the stock (!!!). In order to simulate the passing of time, you should assume that every time a customer places an item into or removes an item from the shopping cart, a minute passes (for everybody in the Shopping Center). At any moment, just one customer can perform such an operation.

5.       The first customer who finishes shopping (option 4) is the one who has been in the Shopping Center the longest. When the customer finishes shopping (s)he gets into one of the three checkout lines. If the customer has <=5 items, (s)he can use the express checkout line. Otherwise (s)he chooses the shortest regular line. If the express line is twice as long as a regular line a customer with <=5 items will choose the shortest regular line for checkout instead.

6.       Customers who are at the front of the lines can check out and leave or can decide to return and shop some more (option 5). Customers check out in a fair manner: all three checkout lines take turns in checking out customers.  If there is no customer in the line whose turn it is to check out a customer, then the next checkout line that has customers in line will check out the first customer in line. If the customer decides to return to shopping, the time spent in the Shopping Center is reset.

7.       The Shopping Center system should offer information about the customers who are shopping (option 6), about the customers who are waiting in the three checkout lines to leave the Shopping Center (option 7) and about the items in the shopping center that are at or below re-stocking level (option 8).

8.       The Shopping Center system should offer the option to restock a specified item (option 9). The item name and restocking amount should be specified.

9.       If menu option 4 is chosen (Customer is done shopping) and the customer who has been in the Shopping Center the longest has no items in the cart, then the customer should be given the choice to leave or to return to shopping. The customer does not need to stand in line to check out. If the customer returns to shopping, the time spent in the Shopping Center will be reset.

 

Your solution should not waste memory. Choose the most efficient ADT(s) from your OWN implementation of List, Stack, Queue, or Deq ADT and document the rationale for your choices in a write-up. You are not allowed to use arrays, ArrayLists and other Java Class Libraries.

You can assume correct input data. You are welcome to add input error-checking, but it is not required.

You have to comment your code and generate Java Documentation using Javadoc (on elvis). Use the guidelines in http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html. Documentation is 20% of the grade. Include in your final submission the small-format prints of the Java Docs for all the classes that are part of your project (including ADT implementations, but not interfaces or exceptions).

 

Use an extensive testing suite for your program, including the following: input1 -> output1; input2 -> output2; input3 -> output3. DO NOT print THESE GIVEN files and waste paper. You have to run your program on the 3 input data files that have: express, regular1, regular2 as the first lane to check out a customer (the files have other differences, too). Include in your submission the sample runs of your program on this input suite as well as any input that you consider important to test your solution.
