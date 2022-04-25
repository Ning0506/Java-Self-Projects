package project3;

/**
 * MyStack class implements the interface Stack<E> using a singly reference structure.
 * It overrides methods in the interface and adds details.
 * This class should provide Node, push, pop, top, equals and toString methods.
 * 
 * @author Ning Yang
 *
 */
public class MyStack<E> implements Stack<E> {
	
     //@SuppressWarnings("hiding")
	class Node<E> {       
	   E data;
       Node<E> next;
       
       // Create a Node constructor with parameter of E type data
       Node (E data) {
    	   this.data = data;
       }
	}
	
	// Set the default top and bottom to null
	public Node<E> top = null;
	public Node<E> bottom = null;
	
	MyStack() {
	}

	/**
	* Add an element to the top of this stack
	* @param item to be added to this stack
	* throws IllegalArgumentException if `item == null`
	*/
	public void push(E item) throws IllegalArgumentException {
		if (item == null)
			throw new IllegalArgumentException("Invalid.");
		
		// Create a current pointer to help iterate the stack
		Node<E> current = top;
		// Create a new Node with the data item
		Node<E> n = new Node<E>(item);
		
		// Check whether the stack is empty
		if (top == null) {
			top = n;
		    bottom = n;
		}
		// If the stack is not empty
		else {
			n.next = current;
			top = n;
		}	
	}

	/**
	* Remove and return the element from the top of this stack
	* @return the element from the top of this stack or null if this stack is empty
	*/
	public E pop() {
		
		// Check whether the stack is empty
		if (top == null)
			return null;
		// If the stack is not empty
		else {
			Node<E> current = top;
			top = top.next;
			return current.data;
		}
	}
	
	/** Return the element from the top of this stack.
	* @return  the element from the top of this stack or null if this stack is empty
	*/
	public E top() {
		
		// Check whether the stack is empty
		if (top == null)
			return null;
		// If the stack is not empty
		else {
			return top.data;
		}
	}
	
	/**
	* Determines if this stack is equal to `obj`.
	* @obj an Object that is compared to this stack for equality
	* @return true if this stack is equal to `obj` (same elements in the same order)
	*         false, otherwise
	*/
	public boolean equals(Object obj) {
		
		// Check whether the stack or the obj is empty
		if (obj == null)
			return false;
		// Check whether the obj is the stack
        if (this == obj) 
        	return true;
        
        // Check whether the obj is Node type
        if (!(obj instanceof MyStack))
            return false;
        
        // Set a current1 pointer to iterate
        Node<E> current1 = top;
        // Transform the obj to <E> type
		MyStack<E> test = (MyStack<E>) obj;
		
		Node<E> testcurrent =  test.top;
        
		// Compare obj with each element in the stack
        while (current1 != null) {
        	if (!(current1.data.equals(testcurrent.data)))
        		return false;

	        current1 = current1.next;
			testcurrent = testcurrent.next;
        }
        
        if (current1 == null && testcurrent == null)
    	    return true;
        else
            return false;
    }

	/**
	* Returns a string representation of this stack. The string is constructed by
	* concatenating all elements of this stack separated by a comma and a single space.
	* The bottom of the stack should be the leftmost element and the top of the stack
	* should be the rightmost element. There should be no comma after the last element.
	* @return a string representation of this stack.
	*/
	public String toString() {
		String str = "";
		Node<E> current2 = top;
		
		// Check whether the stack is empty
		if (current2 == null)
			return str;
		
		// Check whether stack only has one element
		if (current2.next == null)
			return current2.data.toString();
		
		// Store the top in the str first to ensure no comma
		str = current2.data + str;
		// set current2 to the next element of it
		current2 = current2.next;
		
		// iterate through the stack and store elements in the str
		while (current2!= null) {
			str = current2.data + ", " + str;
		    current2 = current2.next;					
		}		
		
		return str;
	}

}
