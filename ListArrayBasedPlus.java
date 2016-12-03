



/**Purpose: Data Structure and Algorithms Lab 2 Problem 2
 * Status: Complete and thoroughly tested
 * Last update: 09/15/16
 * Submitted:  09/13/16
 * Comment: test suite and sample run attached.
 * @author: Jonathan D'Alonzo (Jon D)
 * @version: 2016.09.15
 */
public class ListArrayBasedPlus<T> extends ListArrayBased<T> {
    
    /**
     * Constructors call to super class.
     */
    public ListArrayBasedPlus() {
        super();
    }
    /**
     * This method adds an Object into a specified position in the array.
     * @param pos pos index of newly inserted Object 
     * @param item Object that will be placed into the array 
     */
    public void add(int pos, T item) {
        if (numItems == items.length) {
            resize();
        }
        super.add(pos, (T) item);
    }
    
    /**
     * This method will make the current array 50% larger than previously.
     */
    public void resize() {
        T[] temp = (T[]) new Object[(numItems+(2*numItems))]; // using numItems as an incrementation
        for (int i = 0; i < numItems; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }
    
    /**
     * This method will return a String representation of each Object in the array.
     * @return each Object as a String representation
     */
    public String toString() {
        String itemsString = "";
        for(int i = 0; i < numItems; i++) {
            itemsString += items[i].toString() + " ";
        }
        return itemsString;
    }
    
    /**
     * This method will take the initial array, create a new temp array, and then
     * place the elements into the temp array in a reverse fashion and then
     * change their references.
     */
    public void reverse() {
        T[] temp = (T[]) new Object[numItems];
        int j = numItems-1;
        for(int i = 0; i < numItems; i++) {
            temp[i] = items[j]; 
            j--;
        }
        items = temp;
    }
    
    
}
