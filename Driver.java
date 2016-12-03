import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
    public static int RESTOCK = 0;
    
    public static void main(String[] args) throws IOException {

    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	
    	
    	
        System.out.println("Welcome to the Shopping Center!!!");
        System.out.println("Please specify stock.\n" +
                           "How many unique items do you have: ");
        int numItems = Integer.parseInt(bf.readLine().trim());
        System.out.println(numItems); //echo
        System.out.print("Please specify restocking amount: ");
        final int RESTOCK = Integer.parseInt(bf.readLine().trim());
        //INCOMPLETE//////////////////////
        System.out.print("Please select the checkout line that should check out customers first (regular1/regular2/express): ");
        String nextRegister = bf.readLine().trim();
        System.out.println(nextRegister);
        ///////////////////////////////////
        ShoppingCenter shoppingCenter = new ShoppingCenter(RESTOCK);
        
        String item = "";
        int quantity = 0;
        while (numItems > 0) {
            System.out.print(">>Enter item name : ");
            item = bf.readLine().trim();
            System.out.println(item); //echo
            System.out.print("How many " + item + "? ");
            quantity = Integer.parseInt(bf.readLine().trim());
            System.out.println(quantity);
            shoppingCenter.setStock(item, quantity);
            numItems--;
        }
        
        int input = 0;
        boolean run = true;
        while (run) {
            System.out.println("Select an operation from the following menu" +
                               "\t1.Customer enters Shopping Center.\n" +
                               "\t2.Customer picks an item and places it the shopping cart.\n" +
                               "\t3.Customer removes an item from the shopping cart.\n" +
                               "\t4.Customer is done shopping.\n" +
                               "\t5.Customer checks out.\n" +
                               "\t6.Print info about customers who are shopping.\n" +
                               "\t7.Print info about customers in checkout lines.\n" +
                               "\t8.Print info about items at or below re-stocking level.\n" +
                               "\t9.Reorder an item.\n" +
                               "\t10.Close the Shopping Center.");
            System.out.print(">>Make your menu selection now: ");
            input = Integer.parseInt(bf.readLine().trim());
            
            String stringInput = "";
            String stringInput2 = "";
            switch (input) {
                case 1:
                	System.out.print("Enter customer name : ");
                	//add new customer 
                	stringInput = bf.readLine().trim();
                	shoppingCenter.addCustomer(stringInput);
                	System.out.println(stringInput); //echo
                	System.out.println("Customer " + stringInput + " is now in the Shopping Center.");
                    break;
                case 2:
                	if (shoppingCenter.getCustomers().isEmpty()) {
                		System.out.println("No one is in the Shopping Center!");
                	}
                	else {                		
		            	System.out.print(">>Enter customer name : ");
		            	stringInput = bf.readLine().trim();
		            	System.out.println(stringInput); //echo
		            	
		            	Customer tempCustomer = shoppingCenter.findCustomer(stringInput);
		            	
		            	if(tempCustomer == null) { System.out.println("Customer not in shopping center.\n"); }
		            	else {
		            		System.out.print(">>What item does " + stringInput + " want? ");
			            	stringInput2 = bf.readLine().trim();
			            	System.out.println(stringInput2); //echo     
			            	
			            	Item pickupItem = shoppingCenter.findItem(stringInput2);
			            	
			            	if(pickupItem == null) { System.out.println("Item not in stock.\n"); }
			            	else {
			            		try {
				            		pickupItem.decItem();
				            		tempCustomer.addItem();	
				            		shoppingCenter.incrementTime();
				            		System.out.println(tempCustomer.toString() + "\n");
			            		} catch(Exception e) {
			            			System.out.println(e.getMessage() + "\n");
			            		}
			            	}
		            	}  	      	      	
                	}
                    break;
                case 3:
                    //Customer removes item from shopping cart
                    //If no one is in the Shopping Center
                    if (shoppingCenter.isCustomersEmpty()) {
                        System.out.println("No one is in the Shopping Center!");
                    }
                    else {
                        System.out.println(">>Enter customer name : ");
                        stringInput = bf.readLine().trim();
                        Customer customer = shoppingCenter.findCustomer(stringInput);
                        
                        //if customer with that name wasn't found...
                        if (customer == null) {
                            System.out.println("There is no customer named " + stringInput + "\n");
                        }
                        else {
                            if (customer.getItemsInCart()==-1) {
                                System.out.println("Customer " + stringInput + 
                                        " is in a checkoutline !\n");
                            }
                            else {
                                try {
                                    customer.removeItem();
                                } catch (Exception e) {System.out.println(e.getMessage());}
                                
                                System.out.println("Customer " + stringInput + " has now " + 
                                        customer.getItemsInCart() + 
                                        " item(s) in the shopping cart.\n");
                                //increment everyones time
                                shoppingCenter.incrementTime();
                            }
                        }
                    }
                    break;
                case 4:
                    if (shoppingCenter.isCustomersEmpty()) {
                        System.out.println("No one is in the Shopping Center!\n");
                    }
                    else {
                        shoppingCenter.addCustomerToCheckout();
                    }
                    break;
                case 5:
                    if (shoppingCenter.areLinesEmpty()) {
                        System.out.println("No customers in any line.\n");
                    }
                    else {
                        Customer cutomerCheckingOut = shoppingCenter.checkOutCustomer(nextRegister);
                        String customerName = cutomerCheckingOut.getName();
                        
                        if (cutomerCheckingOut.getItemsInCart()==0) {
                            System.out.print("Should customer " + customerName +
                                    " check out or keep on shopping? Checkout?(Y/N): ");
                            
                            if (bf.readLine().trim().equalsIgnoreCase("y")) {
                                System.out.println("Customer " + customerName +
                                        " is now leaving the Shopping Center.");
                                shoppingCenter.removeCustomer(customerName);
                            }
                        }
                    }
                    break;
                case 6: 
                	System.out.println(shoppingCenter.printCustomersList());
                	break;
                case 7:
                	System.out.println(shoppingCenter.printCheckoutLines());
                    break;
                case 8:
                	System.out.println(shoppingCenter.printRestockLevels());
                    break;
                case 9:
                	System.out.print("Enter item name to be re-ordered : ");
                	stringInput = bf.readLine().trim();
                	System.out.println(stringInput);
                	
                	Item tempItem = shoppingCenter.findItem(stringInput);
                	
                	if(tempItem == null) { System.out.println("Item not in stock.\n"); }
                	else {
                		System.out.print("Enter number of " + stringInput + " to be re-ordered : ");
                    	input = Integer.parseInt(bf.readLine().trim());
                    	System.out.println(input);
                    	
                    	shoppingCenter.restockItems(tempItem, input);
                    	
                    	System.out.println( "Item " + stringInput + " restocked by quantity " + input + "\n" );
                	}
                    break;
                case 10:
                    System.out.println("The Shopping Center is closing...come back tomorrow.");
                    run = false;
                    break;
                default:
                    System.out.println("ERROR: Invalid input.\n");
                    break;
            }
        }
    }
}
/*
Enter item name to be re-ordered : soap
Enter number of soaps to be re-ordered : 10
Stock has now 10 soaps.
*/
