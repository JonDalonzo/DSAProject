

public class Item implements Comparable<Item>{

    private int quantity;
    private boolean restock;
    private String name;
    
    public Item(String name, int quantity) {
    	this.quantity = quantity;
    	this.name = name;    	
    	restock = (this.quantity < 3) ? true:false;
    }
    
    public void restockItem(int quantity) {
    	this.quantity += quantity;
    	restock = (this.quantity < 3) ? true:false;
    }
    
    public int getQuantity() {
    	return( this.quantity );
    }
    
    public void decItem() throws Exception {
    	if(quantity == 0) { throw new Exception("Item not in stock."); }
    	
    	this.quantity--;
    	restock = (this.quantity < 3) ? true:false;
    }
    
    public boolean getRestockFlag() {
    	return( this.restock );
    	
    }
    
    public String printRestockItem() {
    	return( name + " with " + quantity + " items.");
    }
    
    public String getName() {
    	return( this.name );
    }

    @Override
    public int compareTo(Item o) {
            return( this.name.compareTo(o.getName()) );
    }
    
}
