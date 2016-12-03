import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
	
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static String nextRegister;
    
    public static void main(String[] args) throws IOException {

    	
        System.out.println("Welcome to the Shopping Center!!!");
        System.out.print("Please specify stock.\n" +
                           "How many unique items do you have: ");
        int numItems = Integer.parseInt(bf.readLine().trim());
        System.out.println(numItems); //echo
        
        System.out.print("Please specify restocking amount: ");
        int restock = Integer.parseInt(bf.readLine().trim());
        System.out.println(restock);
        
        ShoppingCenter shoppingCenter = new ShoppingCenter(restock);
        
        String item = "";
        int quantity = 0;
        while (numItems > 0) {
            System.out.print(">>Enter item name : ");
            item = bf.readLine().trim();
            System.out.println(item); //echo
            System.out.print("How many " + item + "? ");
            quantity = Integer.parseInt(bf.readLine().trim());
            System.out.println(quantity);
            shoppingCenter.setStock(item, quantity, restock);
            numItems--;
        }
        
        //INCOMPLETE//////////////////////
        System.out.print("Please select the checkout line that should check out customers first (regular1/regular2/express): ");
        nextRegister = bf.readLine().trim();
        System.out.println(nextRegister);
        ///////////////////////////////////
        
        int input = 0;
        boolean run = true;
        while (run) {
            System.out.println("Select an operation from the following menu\n" +
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
            System.out.println(input);
            
            String stringInput = "";
            String stringInput2 = "";
            switch (input) {
                case 1:              
                	
                	System.out.print("Enter customer name : ");
                	//add new customer 
                	stringInput = bf.readLine().trim();
                	System.out.println(stringInput);
                	
                	Customer customerToAdd = shoppingCenter.findCustomer(stringInput);
                	
                	if(customerToAdd != null) {
	                	while(customerToAdd != null) {
	                		
	                		System.out.println("Customer " + stringInput + " is already in the Shopping Center!");
	                		
	                		System.out.print("Enter customer name : ");
	                    	//add new customer 
	                    	stringInput = bf.readLine().trim();
	                    	System.out.println(stringInput);
	                    	
	                    	customerToAdd = shoppingCenter.findCustomer(stringInput);
	                    	
	                    	if(customerToAdd == null) {
	                    		shoppingCenter.addCustomer(stringInput);
	                        	System.out.println(stringInput); //echo
	                        	System.out.println("Customer " + stringInput + " is now in the Shopping Center.\n");
	                    	}         	
	                	}
                	} else {
                		shoppingCenter.addCustomer(stringInput);
                    	System.out.println(stringInput); //echo
                    	System.out.println("Customer " + stringInput + " is now in the Shopping Center.\n");
                	}
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
                        System.out.print(">>Enter customer name : ");
                        stringInput = bf.readLine().trim();
                        System.out.println(stringInput);
                        
                        Customer customer = shoppingCenter.findCustomer(stringInput);
                        
                        //if customer with that name wasn't found...
                        if (customer == null) {
                            System.out.println("There is no customer named " + stringInput + "\n");
                        }
                        else {
                            if (customer.getItemsInCart() < 0) {
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
                    	Customer customerToCheckout = shoppingCenter.findLargestTime();
                    	//Customer customerToCheckout = (Customer) shoppingCenter.getCustomers().get(0);
                    	
                    	if(customerToCheckout.getItemsInCart() == 0) {
                    		shoppingCenter.removeCustomer(customerToCheckout.getName());
                    		shoppingCenter = runCustomerDecision(shoppingCenter, customerToCheckout);
                    	} else { 
	                        System.out.println(customerToCheckout.printCustomerToCheckout() 
	                        		+ shoppingCenter.addCustomerToCheckout(customerToCheckout));
                    	}
                    }
                    break;
                case 5:
                    if (shoppingCenter.areLinesEmpty()) {
                        System.out.println("No customers in any line.\n");
                    }
                    else {
                    	Customer customerCheckingOut = shoppingCenter.checkOutCustomer(nextRegister);
                    	shoppingCenter = runCustomerDecision(shoppingCenter, customerCheckingOut);                            
                    }
                    break;
                case 6: 
                	System.out.println(shoppingCenter.printCustomersList() + "\n");
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
                    	
                    	System.out.println( "Stock has now " + tempItem.getQuantity() + " " + tempItem.getName() + "s.\n");
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
    private static ShoppingCenter runCustomerDecision(ShoppingCenter shoppingCenter, Customer customer) throws IOException {
    	String customerName = customer.getName();
    	System.out.print("Should customer " + customerName +
                " check out or keep on shopping? Checkout?(Y/N): ");
        String reply = bf.readLine().trim();
        System.out.println(reply);
        
        if (reply.equals("y")) {
            System.out.println("Customer " + customerName +
                    " is now leaving the Shopping Center.\n");       
        } else {  
        	System.out.println("Customer " + customer.getName() + 
            		" with " + customer.getItemsInCart() + 
            		" items returned to shopping.\n" );
        	customer.resetTime();
        	shoppingCenter.addCustomer(customer);
        }
      	return shoppingCenter;
    }
}
