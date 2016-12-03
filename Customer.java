
public class Customer {
    
    private int itemsInCart;
    private int time;
    private String name;
    
    public Customer(String name) {
        this.name = name;
        itemsInCart = 0;
        time = 0;
    }
    
    public void addItem() {
    	itemsInCart++;
    }
    
    public void incrementTime() {
        time++;
    }
    
    public void removeItem() throws Exception{
    	if (itemsInCart==0) { throw new Exception("No items in cart to remove"); }
        itemsInCart--;
    }
    
    public String getName() {
    	return( this.name );
    }
    
    public int getTime() {
    	return( this.time );
    }
    
    public int getItemsInCart() {
    	return( this.itemsInCart );
    }

    public String toString() {
    	return( "Customer " + name + " has " + itemsInCart + 
    						" items in the shopping basket." );
    }
    
    public String printCustomerInfo() {
    	return( "Customer " + name + " with " + itemsInCart + 
				" item present for " + time + " minutes."
    	);
    }   
}
