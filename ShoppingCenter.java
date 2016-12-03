public class ShoppingCenter<T> {
    
    private ListArrayBasedPlus<Customer> customers;
    private ListArrayBasedPlus<Item> stock;
    private Queue<Customer> reg1;
    private Queue<Customer> reg2;
    private Queue<Customer> express;
    
    private int reg1Count;
    private int reg2Count;
    private int expressCount;
    private final int REGNUM = 3;
    
    public ShoppingCenter(int restock) {
        this.customers = new ListArrayBasedPlus<Customer>();
        this.stock = new ListArrayBasedPlus<Item>();
        this.reg1 = new Queue<Customer>();
        this.reg2 = new Queue<Customer>();
        this.express = new Queue<Customer>();
        
        this.reg1Count = 0;
        this.reg2Count = 0;
        this.expressCount = 0;
    }
    
    public void addCustomer(String name) {
    	Customer newCustomer = new Customer(name);
    	customers.add(customers.size(), newCustomer);
    }
    public void addCustomer(Customer customer) {
    	customers.add(customers.size(), customer);
    }
    
    public void removeCustomer(String name) {
    	//customers.remove(0);
        customers.remove(findCustomerIndex(name));
    }
    
   public int findCustomerIndex(String name) {
    	int size = customers.size();
    	for(int i = 0; i < size; i++) {
    		Customer customer = customers.get(i);
    		
    		if(customer.getName().equals(name)) {
    			return(i);
    		}
    	}
    	return -1;
    }
    
    public void incrementTime() {
        int size = customers.size();
        for (int i = 0; i < size; i++) {
            customers.get(i).incrementTime();
        }
    }
    
    public void setStock(String itemName, int quantity, int restock) {
    	stock.add(stock.size(), new Item(itemName, quantity, restock));
    }
    
    public void restockItems(Item item, int amount) {
    	item.restockItem(amount);
    }
    
    public Customer findCustomer(String name) {
        int size = customers.size();
        int index;
        for (index = 0; index < size; index++) {
        	Customer customer = customers.get(index);
    
            if (name.equals(customer.getName())) {
                return customer;
            }
        }
        return null;
    }
    
    public Item findItem(String name) {
    	int size = stock.size();
    	for(int i = 0; i < size; i++) {
    		Item item = stock.get(i);
    		
    		if(name.equals(item.getName())) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public Customer findLargestTime() {
    	//get customer with largest time
    	int size = customers.size();
    	int largestIndex = 0;
    	Customer custLargestTime = customers.get(largestIndex);
    	for(int i = 1; i < size; i++) {
    		Customer next = customers.get(i);
    		if(custLargestTime.getTime() < next.getTime()) {
    			custLargestTime = next;
    			largestIndex = i;
    		}
    	}
    	return(custLargestTime);
    }
    
    public String addCustomerToCheckout(Customer custLargestTime) {    	
    	String line = "";
    	//customers.remove(0);
    	removeCustomer(custLargestTime.getName());
    	
    	//add customer to proper line
    	int numItems = custLargestTime.getItemsInCart();
    	if(numItems <= 5) {
                //Jon edit: changed >= to >
                //when all lines are empty and customer has <=5 did not go into express
    		if( (expressCount > (reg1Count << 1) ) || 
                    (expressCount > (reg2Count << 1) ) ) {
    			if(reg1Count < reg2Count) {
        			reg1.enqueue(custLargestTime);
    				line = "first check out line.\n";
    				reg1Count++;
    			}
        		else {
        			reg2.enqueue(custLargestTime);
    				line = "second check out line.\n";
    				reg2Count++;
        		}
    		}
    		else {
    			express.enqueue(custLargestTime);
    			line = "express line.\n";
    			expressCount++;
    		}
    	}
    	else {
    		if(reg1Count < reg2Count) { 
    			reg1.enqueue(custLargestTime); 
    			line = "first check out line.\n";
    			reg1Count++;
    		}
    		else { 
    			reg2.enqueue(custLargestTime); 
    			line = "second check out line.\n";
    			reg2Count++;
    		}
    	}
    	return(line);
    }
    
    public Customer checkOutCustomer(String nextRegister) {

    	int i = 0;
    	while ( i++ < REGNUM ) {
    		if(nextRegister.equals("express") && !express.isEmpty()) {
        		return( express.dequeue() );
        	}
    		nextRegister = "regular1";
    		
        	if(nextRegister.equals("regular1") && !reg1.isEmpty()) {		
        		return( reg1.dequeue() );
        	}
        	nextRegister = "regular2";
        	
        	if(nextRegister.equals("regular2") && !reg2.isEmpty()) {
        		return( reg2.dequeue() );
        	}
        	nextRegister = "express";
    	}
    	return null;
    	
    	/*
        switch(nextRegister) {
            case "express": 
                if (express.isEmpty()) { return null; }
                nextRegister = "register1";
                return express.dequeue();
            case "register1":
                if (reg1.isEmpty()) { return null; }
                nextRegister = "register2";
                return reg1.dequeue();
            case "register2":
                if (reg2.isEmpty()) { return null; }
                nextRegister = "express";
                return reg2.dequeue();
        }
        return null;
        */
    }
    
    public String printCustomersList() {
    	String output = "";
    
    	if(customers.isEmpty()) { output = "No customers are in the Shopping Center!"; }
    	else {
	    	int size = customers.size();
	    	for (int i = 0; i < size; i++) { 
	    		Customer customer = customers.get(i);
	    		if(customer.getItemsInCart() >= 0) {
	    			output += customer.printCustomerInfo() + "\n";
	    		}
	    	}
    	}
    	return( output );
    }
    
    public String printRestockLevels() {
    	String output = "Items at restocking level:\n";
    	
    	int size = stock.size(); 
    	for(int i = 0; i < size; i++) {
    		Item item = stock.get(i);
    		
    		if( item.getRestockFlag() ) {
    			output += "\t" + item.printRestockItem() + "\n";
    		}
    	}
    	return( output );
    }
    
    public String printCheckoutLines() {
    	String output = "";
    	
    	if (reg1.isEmpty()) {output += "No customers are in the first checkout line!\n";}
    	else {
    		int size = customers.size();
    		output += (size > 1) ? "The following customers are in the first checkout line: \n" :
    							   "The following customer is in the first checkout line: \n";		   	
    		output += reg1.toString() + "\n";//Jon edit:added  + "\n" & "\t" +
    	}
    	
    	if (reg2.isEmpty()) {output += "No customers are in the second checkout line!\n";}
    	else {
    		int size = customers.size();
    		output += (size > 1) ? "The following customers are in the second checkout line: \n" :
    							   "The following customer is in the second checkout line: \n";		   	
        	output += reg2.toString() + "\n";//Jon edit:added  + "\n" & "\t" +
    	}
    	
    	if (express.isEmpty()) {output += "No customers are in the express line!\n";}
    	else {
    		int size = customers.size();
    		output += (size > 1) ? "The following customers are in the express checkout line: \n" :
    							   "The following customer is in the express checkout line: \n";		   	
    		output += express.toString() + "\n"; //Jon edit:added  + "\n" & "\t" +
    	}	
    	return( output );
    }
    public ListArrayBasedPlus<Item> getStock() {
    	return stock;
    }
    
    public ListArrayBasedPlus<Customer> getCustomers() {
        return customers;
    }
    
    public boolean isCustomersEmpty() {
        return customers.isEmpty();
    }
    
    public boolean areLinesEmpty() {
        return express.isEmpty() && reg1.isEmpty() && reg2.isEmpty();
    }
  
}
