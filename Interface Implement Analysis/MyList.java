package project3;

import java.util.NoSuchElementException;

import project3.MyList.Node;

/**
 * MyList class implements the interface List<E> using a doubly linked list.
 * It overrides methods in the interface and adds details.
 * This class should provide Node, push, pop, top, equals and toString methods.
 *
 * @author Ning Yang
 *
 */
public class MyList<E> implements List<E> {

    class Node<E> {
        E data;
        Node<E> next;
        Node<E> pre;

        // Create a Node constructor with parameter of E type data
        Node (E data) {
            this.data = data;
        }
    }

    // Set default values of head and tail
    Node<E> head = null;
    Node<E> tail = null;
    int size;

    // Create a constructor
    MyList() {
    }

    /**
     * Adds an element 'item' at position `pos`, shifts elements starting at `pos` by
     * one position to the right (higher position values)
     * @param item the element to be added to this list
     * @param pos postion at which `item` should be added
     * throws NoSuchElementException if `pos` < 0 or `pos` > size
     * throws IllegalArgumentException if `item == null`
     */
    public boolean add(E item, int pos) throws NoSuchElementException {
    	// Create a pointer current beginning at head
        Node<E> current = head;
        // Put item in a new Node
        Node<E> input = new Node<E>(item);

        // Check if pos is valid
        if (pos < 0 || pos > size)
            throw new NoSuchElementException("Invalid position.");

        // Check whether the item is null
        if (item == null)
            throw new IllegalArgumentException("Invalid item.");

        // If the list is empty
        if (pos == 0 && size == 0) {
            head = input;
            tail = input;
            size++;
            return true;
        }

        // If the list has size 1
        // Add the item to the front of the list
        if (pos == 0 && size == 1) {
            input.pre = null;
            input.next = current;
            head = input;
            current.pre = input;
            tail = current;
            size++;
            return true;
        }

        // If add the item to the end of the list
        if (pos == size){
            tail.next = input;
            input.pre = tail;
            tail = input;
            input.next = null;
            size++;
            return true;
        }
        else {
            if (size > 1) {
            	// Add item to the front of the list
                if (pos == 0) {
                    input.next = current;
                    head = input;
                    current.pre = input;
                    size++;
                    return true;
                }
                else {
                	// Add within the list
                    for (int i = 0; i < size; i++) {
                        if (i != pos)
                            current = current.next;
                        else {
                            input.pre = current.pre;
                            current.pre.next = input;
                            input.next = current;
                            current.pre = input;
                            size++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes and returns an element at position `pos`, shifts elements starting
     * at `pos+1` by one to the left (lower position values)
     * @param pos the position from which the element should be removed
     * @ return the element removed from the list
     * throws NoSuchElementException if pos < 0 or pos >= size
     */
    public E remove(int pos) throws NoSuchElementException {
    	// Set pointers current to the head
        Node<E> current1 = head;
        Node<E> c1 = tail;

        // Check if the pos is valid
        if (pos < 0 || pos >= size)
            throw new NoSuchElementException("Invalid position.");

        // add item to the head when size is 1
        if (pos == 0 && size == 1) {
            head = null;
            tail = null;
            size--;
            return current1.data;
        }
        else {
        	// Add to the head
            if (pos == 0) {
                head = head.next;
                head.pre = null;
                size--;
                return current1.data;
            }

            // Add to the end
            if (pos == size-1) {
                tail = tail.pre;
                tail.next = null;
                size--;
                return c1.data;
            }
            else {
            	// Add within the list
                for (int i = 0; i < size; i++) {
                    if (i == pos) {
                        current1.pre.next = current1.next;
                        current1.next.pre = current1.pre;
                        size--;
                        return current1.data;
                    }
                    else
                        current1 = current1.next;
                }
            }
        }
        return null;
    }

    /**
     * Removes and returns an element equal to `item`, shifts elements starting
     * at the next position by one to the left (lower position values)
     * @param item element to remove
     * @return the removed element, or null if element equal to `item` is not in this list
     */
    public E remove(E item) {
    	// Set the pointer
        Node<E> current1 = head;
        Node<E> input = new Node<E>(item);

        // Check whether the head is null
        if (head == null)
            return null;

        // Check whether the item is null
        if (item == null)
            return null;

        if (size == 1 && head.data == input.data) {
            head = null;
            tail = null;
            size--;
            return current1.data;
        }
        else {
            if (head.data == input.data){
                head = head.next;
                head.pre = null;
                size--;
                return current1.data;
            }
            else {
                for (int i = 0; i < size - 1; i++) {
                    if (current1.next != null && current1.data.equals(input.data)) {
                        current1.pre.next = current1.next;
                        current1.next.pre = current1.pre;
                        size--;
                        return current1.data;
                    }
                    else if (input.data == tail.data) {
                        tail = tail.pre;
                        tail.next = null;
                        size--;
                        return current1.data;
                    }
                    else
                        current1 = current1.next;
                }
                return null;
            }
        }
    }


    /**
     * Determines if 'item' is in the list and if so returns its position
     * @param item to locate in this list
     * @return position of `item` in this list or -1 if `item` is not found in this list
     */
    public int find(E item) {
        int counter = 0;

        // Check whether the item is null
        if (item == null)
            throw new IllegalArgumentException("Invalid item.");

        Node<E> current2 = head;

        // Check whether the list is empty
        if (head == null)
            return -1;

        for (int i = 0; i < size; i++) {
            if (current2.data == item) {
                return counter;
            }
            else {
                current2 = current2.next;
                counter ++;
            }

            if (counter == size)
                return -1;
        }
        return -1;
    }

    /**
     * Retrieves and returns an element from position `pos`
     * @param pos the position of item to return
     * @return element stored at position `pos`
     * throws NoSuchElementException if pos < 0 or pos >= size
     */
    public E get(int pos) throws NoSuchElementException {
        int counter = 0;

        // Check whether the pos is valid
        if (pos < 0 || pos >= size()) {
            throw new NoSuchElementException("Invalid position.");
        }

        Node<E> current2 = head;

        for (int i = 0; i < size; i++) {
            if (i == pos)
                return current2.data;
            else
                current2 = current2.next;
        }
        return null;
    }

    /**
     * Returns the current number of elements in this list
     * @return the number of elements in this list
     */
    public int size() {
        int counter = 0;
        Node<E> current3 = head;

        // Check whether the list is null
        if (head == null)
            return 0;
        else if (current3.next == null)
            return 1;

        while(current3 != null) {
            current3 = current3.next;
            counter ++;
        }
        return counter;
    }

    /**
     * Removes all elements from this list, so it is once again empty.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            head = head.next;
            if (head == null) {
                tail = null;
                size = 0;
            }
        }
    }

    /**
     * Determines if this list is equal to `obj`.
     * @obj an Object that is compared to this list for equality
     * @return true if this list is equal to `obj` (same elements in the same order)
     *         false, otherwise
     */
    public boolean equals(Object obj) {
        // Check whether the stack or the obj is empty
        if (this == null || obj == null)
            return false;

        // Check whether the obj is the stack
        if (this == obj)
            return true;

        // Check whether the obj is Node type
        if (!(obj instanceof MyList))
            return false;

        // Set a current1 pointer to iterate
        Node<E> current4 = head;
        // Transform the obj to <E> type
        MyList<E> test = (MyList<E>) obj;
        Node<E> testcurrent = test.head;

        if (!(this.size() == test.size())) {
            return false;
        }
        // Compare obj with each element in the stack
        while (current4 != null) {
            if (!(current4.data.equals(testcurrent.data)))
                return false;
            current4 = current4.next;
            testcurrent = testcurrent.next;
        }
        return true;
    }

    /**
     * Returns a string representation of this list. The string is constructed by
     * concatenating all elements of this list separated by a comma and a single space.
     * There should be no comma after the last element.
     * @return a string representation of this list.
     */
    public String toString () {
        String str = "";
        Node<E> current5 = head;

        // Check whether the stack is empty
        if (current5 == null)
            return str;

        // Check whether stack only has one element
        if (current5.next == null)
            return current5.data.toString();

        // iterate through the stack and store elements in the str
        while (current5.next!= null) {
            str = str + current5.data + ", ";
            current5 = current5.next;
        }

        str += current5.data;

        return str;
    }
    
    
    int SizeLinkedListRecursionW () {
		
		Node<E> current = head;
		int counter = 0;
		
		return SizeLinkedListRecursion (current, counter);	
	}
	
	int SizeLinkedListRecursion (Node<E> current, int counter) {
		if (current == null)
			return counter;
		
		if (current != null) {
			counter ++;
			current = current.next;
			return SizeLinkedListRecursion (current, counter);
		}
		else
			return counter++;
	}
}