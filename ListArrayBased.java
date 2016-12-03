
// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************
public class ListArrayBased<T> implements ListInterface<T>
{
    protected T []items;  // an array of list items
    protected int numItems;  // number of items in list

    public ListArrayBased()
    {
        items = (T[]) new Object[3];
        numItems = 0;
    }

    /**
     * Overridden method of isEmpty (in ListArrayBased).
     * @return 
     */
    @Override
    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty

    @Override
    public int size()
    {
        if (this.isEmpty()) {
            return 0;  
        }
        return numItems;
    }  // end size

    @Override
    public void removeAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items = (T[]) new Object[3];
        numItems = 0;
    } // end removeAll

    /**
     *
     * @param index
     * @param item
     * @throws ListIndexOutOfBoundsException
     */
    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems == items.length)   // fixes implementation error and programming style 
                                        // must be the length of the item not MAX of the list
        {
            throw new ListException("ListException on add");
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "Position specified is out of range!\n");
        }  // end if
    } //end add

    @Override
    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "Position specified is out of range!\n");
        }  // end if
    } // end get

    @Override
    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException

            {
                items[pos-1] = items[pos];
            }  // end for
		items[--numItems] = null;    // fixes memory leak
                                             // if this line was absent items[11] would still point to the object in items[10]. 
                                             // Also items.length = 11 not 10 (as we would have wrong in numItems)
					     // because items[11] it pointing to an object.
            
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "Position specified is out of range!\n");
        }  // end if
    } //end remove
}
