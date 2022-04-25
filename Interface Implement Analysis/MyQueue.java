package project3;

/**
 * MyQueue class implements the interface Queue<E> using an array-based structure.
 * It overrides methods in the interface and adds details.
 * This class should provide enqueue, dequeue, peek, equals and toString methods.
 * 
 * @author Ning Yang
 *
 */
public class MyQueue<E> implements Queue<E> {
	
	// Create default values
	// Create small methods for usage in the later code
	private int capital;
	private int size;
	private Object[] myArray;
    
    public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Object[] getMyArray() {
		return myArray;
	}

	public void setMyArray(Object[] myArray) {
		this.myArray = myArray;
	}

	// Create the constructor
	// The default size is 10
	MyQueue() {
    	this.size = 10;
    	this.myArray = new Object[size];
    }
	
	/**
	* Add an element to the back of this queue
	* @param item to be added to this queue
	* throws IllegalArgumentException if `item == null`
	*/
	public void enqueue(E item) {
		// Check whether the item is null
		if (item == null)
			throw new IllegalArgumentException("Invalid item.");
		
		// If the array is full
		if(capital >= size) {
			Object[] arr = new Object[(size = size << 1)];
			System.arraycopy(myArray, 0, arr, 0, myArray.length);
			myArray = arr;
		}
		myArray[capital] = item;
		capital++;
	}                                                                                                      

	/**
	* Remove and return the element from the front of this queue.
	* @return the element from the front of this queue or null if this queue is empty
	*/
	public E dequeue() {
		if (capital == 0)
			return null;
		else {
			E d = (E)myArray[0];
			myArray[0] = null;
			for (int i = 0; i < size - 1; i++) {
				myArray[i] = myArray[i+1];
			}
			capital--;
			return d;
		}
	}

	/** Return the element from the front of this queue.
	* @return  the element from the top of this queue or null if this queue is empty
	*/
	public E peek() {
		if (capital == 0)
			return null;
		else
		    return (E) myArray[0];
	}

	/**
	* Determines if this queue is equal to `obj`.
	* @obj an Object that is compared to this queue for equality
	* @return true if this queue is equal to `obj` (same elements in the same order)
	*         false, otherwise
	*/
	public boolean equals(Object obj) {
		//Determines whether it is null or of the same type
		if(obj == null || !(obj instanceof MyQueue)) {
			return false;
		}
		//Determine if the queue length is the same
		Object[] objs = ((MyQueue)obj).getMyArray();
		if(objs.length != this.myArray.length) {
			return false;
		}
		boolean flag = false;
		for(int i = 0;i < objs.length;i++) {
			if(objs[i] != this.myArray[i]) {
				flag = true;
				break;
			}
		}
		if(flag) {
			return false;
		}
		return true;
	}

	/**
	* Returns a string representation of this queue. The string is constructed by
	* concatenating all elements of this queue separated by a comma and a single space.
	* The front of the queue should be the leftmost element and the back of the queue
	* should be the rightmost element. There should be no comma after the last element.
	* @return a string representation of this queue.
	*/
	public String toString () {
		StringBuffer str = new StringBuffer("");
		
		// If the array is empty
		if (capital == 0)
			return str.toString();
		for(int i = 0; i < capital;i++) {
			str.append(myArray[i]);
			if(i != capital - 1) {
				str.append(", ");
			}
		}
		return str.toString();
	}
}