

/**
 * Purpose: Data Structure and Algorithms Lab 6 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 10/29/16
 * Submitted:  1/1/16
 * Comment: test suite and sample run attached
 * @author: Jonathan D'Alonzo (Jon D)
 * @version: 2016.10.29
 */
public class Queue<T> implements QueueInterface<T> {
    
    protected T[] items;
    protected int front;
    protected int back;
    protected int numItems;
    
    public Queue() {
        items = (T[]) new Object[3];
        front = 0;
        back = -1;
        numItems = 0;
    }
    
    public boolean isEmpty() {
        return items[front]==null; //changed from front==back to this.
    }
   
    public void enqueue(T newItem) throws QueueException {
        back = (back+1) % items.length;
        if (numItems==items.length) { // the list is empty
            resize();
        }
            items[back] = newItem;
            numItems++;
    }

    public T dequeue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("\nThe list is empty");
        }
        T removed = items[front];
        items[front] = null;
        front = (front+1) % items.length;
        numItems--;
        return removed;
    }

    public void dequeueAll() {
        items = (T[]) new Object[3];
        front = 0;
        back = -1;
        numItems = 0;
    }

    public T peek() throws QueueException {
        if (!isEmpty()) {
            return (T) items[front];
        }
        else {
            throw new QueueException ("\nThe list is empty.");
        }
    }

    public String toString() {
        String str = "";
        int i = front;
        int count = 0;
        do {
            str += items[i].toString() + " ";
            i = (++i) % items.length;
            count++;
        } while (count<numItems);
        return str;
    }
    
    protected void resize() {
        T[] temp = (T[]) new Object[(items.length<<1)];
        int i = front;
        int count = 0;
        while (count < numItems) {
            temp[count] = items[i];
            i = (i+1) % items.length;
            count++;
        }
        items = temp;
        back = numItems;
        front = 0;
    }
}
