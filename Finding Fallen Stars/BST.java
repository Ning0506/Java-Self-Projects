package project5;

import java.util.*;

/**
 * This class should represent the location(latitude, longitude).
 * 
 * This class should provide a two parameter constructor, getDistance method,
 * haversine method, getLatitude method, getLongitude method, and equals method.
 *
 * @author Ning Yang
 *
 */
public class BST<T extends Comparable<T>> {

	// reference to the root node of the tree
	private BSTNode root;
	// number of values stored in this tree
	private int size;
	// comparator object to overwrite the natural ordering of the elements
	private Comparator<T> comparator;

	// helper variable used by the remove methods
	private boolean found;
	// helper variable used by the add method
	private boolean added;

	/**
	 * Constructs a new, empty tree, sorted according to the natural ordering of its
	 * elements.
	 */
	public BST() {
		root = null;
		size = 0;
		comparator = null;
	}

	/**
	 * Constructs a new, empty tree, sorted according to the specified comparator.
	 */
	public BST(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Adds the specified element to this tree if it is not already present. If this
	 * tree already contains the element, the call leaves the tree unchanged and
	 * returns false.
	 * 
	 * @param data element to be added to this tree
	 * @return true if this tree did not already contain the specified element
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean add(T data) {
		added = false;
		if (data == null)
			return added;
		// replace root with the reference to the tree after the new
		// value is added
		root = add(data, root);
		// update the size and return the status accordingly
		if (added)
			size++;
		return added;
	}

	/*
	 * Actual recursive implementation of add.
	 *
	 * This function returns a reference to the subtree in which the new value was
	 * added.
	 *
	 * @param data element to be added to this tree
	 * 
	 * @param node node at which the recursive call is made
	 */
	private BSTNode add(T data, BSTNode node) {
		if (node == null) {
			added = true;
			return new BSTNode(data);
		}
		// decide how comparisons should be done
		int comp = 0;
		if (comparator == null) // use natural ordering of the elements
			comp = node.data.compareTo(data);
		else // use the comparator
			comp = comparator.compare(node.data, data);

		// find the location to add the new value
		if (comp > 0) { // add to the left subtree
			node.left = add(data, node.left);
		} else if (comp < 0) { // add to the right subtree
			node.right = add(data, node.right);
		} else { // duplicate found, do not add
			added = false;
			return node;
		}
		return node;
	}

	/**
	 * Removes the specified element from this tree if it is present. Returns true
	 * if this tree contained the element (or equivalently, if this tree changed as
	 * a result of the call). (This tree will not contain the element once the call
	 * returns.)
	 * 
	 * @param target object to be removed from this tree, if present
	 * @return true if this set contained the specified element
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean remove(T target) {
		// replace root with a reference to the tree after target was removed
		root = recRemove(target, root);
		// update the size and return the status accordingly
		if (found)
			size--;
		return found;
	}

	/*
	 * Actual recursive implementation of remove method: find the node to remove.
	 *
	 * This function recursively finds and eventually removes the node with the
	 * target element and returns the reference to the modified tree to the caller.
	 * 
	 * @param target object to be removed from this tree, if present
	 * 
	 * @param node node at which the recursive call is made
	 */
	private BSTNode recRemove(T target, BSTNode node) {
		if (node == null) { // value not found
			found = false;
			return node;
		}

		// decide how comparisons should be done
		int comp = 0;
		if (comparator == null) // use natural ordering of the elements
			comp = target.compareTo(node.data);
		else // use the comparator
			comp = comparator.compare(target, node.data);

		if (comp < 0) // target might be in a left subtree
			node.left = recRemove(target, node.left);
		else if (comp > 0) // target might be in a right subtree
			node.right = recRemove(target, node.right);
		else { // target found, now remove it
			node = removeNode(node);
			found = true;
		}
		return node;
	}

	/*
	 * Actual recursive implementation of remove method: perform the removal.
	 *
	 * @param target the item to be removed from this tree
	 * 
	 * @return a reference to the node itself, or to the modified subtree
	 */
	private BSTNode removeNode(BSTNode node) {
		T data;
		if (node.left == null) // handle the leaf and one child node with right subtree
			return node.right;
		else if (node.right == null) // handle one child node with left subtree
			return node.left;
		else { // handle nodes with two children
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			return node;
		}
	}

	/*
	 * Returns the information held in the rightmost node of subtree
	 *
	 * @param subtree root of the subtree within which to search for the rightmost
	 * node
	 * 
	 * @return returns data stored in the rightmost node of subtree
	 */
	private T getPredecessor(BSTNode subtree) {
		if (subtree == null) // this should not happen
			throw new NullPointerException("getPredecessor called with an empty subtree");
		BSTNode temp = subtree;
		while (temp.right != null)
			temp = temp.right;
		return temp.data;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	public String toStringTree() {
		StringBuffer sb = new StringBuffer();
		toStringTree(sb, root, 0);
		return sb.toString();
	}

	// uses preorder traversal to display the tree
	// WARNING: will not work if the data.toString returns more than one line
	private void toStringTree(StringBuffer sb, BSTNode node, int level) {
		// display the node
		if (level > 0) {
			for (int i = 0; i < level - 1; i++) {
				sb.append("   ");
			}
			sb.append("|--");
		}
		if (node == null) {
			sb.append("->\n");
			return;
		} else {
			sb.append(node.data + "\n");
		}

		// display the left subtree
		toStringTree(sb, node.left, level + 1);
		// display the right subtree
		toStringTree(sb, node.right, level + 1);
	}

	/*
	 * Node class for this BST
	 */
	private class BSTNode implements Comparable<BSTNode> {

		T data;
		BSTNode left;
		BSTNode right;

		public BSTNode(T data) {
			this.data = data;
		}

		public BSTNode(T data, BSTNode left, BSTNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public int compareTo(BSTNode other) {
			if (BST.this.comparator == null)
				return this.data.compareTo(other.data);
			else
				return comparator.compare(this.data, other.data);
		}
	}

	/**
	 * This method checks whether the tree is empty or not. This operation should be
	 * O(1).
	 * 
	 * @return true if this tree contains no elements.
	 */
	public boolean isEmpty() {

		if (root == null)
			return true;
		else
			return false;
	}

	/**
	 * Returns the first(lowest) element currently in this tree This operation
	 * should be O(H).
	 * 
	 * @return the lowest element currently in this tree.
	 * @throws NoSuchElementException if this tree is empty.
	 */
	public T first() {

		BSTNode current = root;
		if (root == null)
			throw new NoSuchElementException("This tree is empty");
		else {
			current = root;
			while (current.left != null)
				current = current.left;
		}
		return current.data;
	}

	/**
	 * Returns the last(highest) element currently in this tree This operation
	 * should be O(H).
	 * 
	 * @return the highest element currently in this tree.
	 * @throws NoSuchElementException if this tree is empty.
	 */
	public T last() {

		if (root == null)
			throw new NoSuchElementException("This tree is empty");

		return getPredecessor(root);
	}

	/**
	 * This method determines whether the tree contains a specified element.
	 * 
	 * @parameter o is the object to be checked for containment in this set.
	 * @return true if this tree contains the specified element.
	 * @throw ClassCastException if the specified object cannot be compared with the
	 *        elements currently in the set.
	 * @throw NullPointerException if the specified element is null and this tree
	 *        uses natural ordering, or its comparator does not permit null
	 *        elements.
	 */
	public boolean contains(Object o) throws ClassCastException, NullPointerException {

		T other = (T) o;
		BSTNode current = root;

		while (current != null) {
			// decide how comparisons should be done
			int comp;
			if (comparator != null) // use natural ordering of the elements
				comp = comparator.compare(other, current.data);
			else // use the comparator
				comp = other.compareTo(current.data);

			if (comp < 0) // target might be in a left subtree
				current = current.left;
			else if (comp > 0) // target might be in a right subtree
				current = current.right;
			else // target found, now remove it
				return true;
		}
		return false;
	}

	/**
	 * This method is used to help iterate by order traversal.
	 */
	private void inorder(ArrayList<T> listData, BSTNode node) {
		if (node != null) {
			inorder(listData, node.left);
			listData.add(node.data);
			inorder(listData, node.right);
		}
	}

	/**
	 * This class contains one constructor which performance should be O(N). Two
	 * other methods: hasNext and next should perform O(1).
	 */
	private class BSTIterator implements Iterator<T> {
		ArrayList<T> listData = new ArrayList<T>();
		int counter = 0;

		/**
		 * This is a constructor
		 */
		public BSTIterator(BST<T> binarySearchTree) {
			binarySearchTree.inorder(listData, binarySearchTree.root);
		}

		/**
		 * This hasNext method checks whether there is a next element.
		 */
		@Override
		public boolean hasNext() {
			return counter < size;
		}

		/**
		 * This next method prints out all elements in the iteration.
		 */
		@Override
		public T next() {
			if (hasNext()) {
				counter++;
				return listData.get(counter - 1);
			} else
				return listData.get(counter);
		}
	}

	/**
	 * This method iterate through the tree. This operation should be O(N).
	 * 
	 * @return an iterator over the elements in this tree in ascending order
	 */
	public Iterator<T> iterator() {
		BSTIterator it = new BSTIterator(this);
		return it;
	}

	/**
	 * The returned collection/list is backed by this tree, so changes in the
	 * returned list are reflected in this tree, and vice-versa (i.e., the two
	 * structures share elements. The returned collection should be organized
	 * according to the natural ordering of the elements. This operation should be
	 * O(M) where M is the number of elements in the returned list.
	 * 
	 * @arameters: fromElement - low endpoint (inclusive) of the returned collection
	 *             toElement - high endpoint (inclusive) of the returned collection
	 * @returns: a collection containing a portion of this tree whose elements range
	 *           from fromElement, inclusive, to toElement, inclusive
	 * @throws: NullPointerException if fromElement or toElement is null
	 *                               IllegalArgumentException - if fromElement is
	 *                               greater than toElement
	 */
	public ArrayList<T> getRange(T fromElement, T toElement) throws NullPointerException, IllegalArgumentException {

		if (fromElement == null || toElement == null)
			throw new NullPointerException("Invalid range");
		if (fromElement.compareTo(toElement) > 0)
			throw new IllegalArgumentException("fromElement should be smaller than toElement");

		ArrayList<T> inRange = new ArrayList<T>();
		getRangeRecursion(fromElement, toElement, root, inRange);

		return inRange;
	}

	/**
	 * This getRangeRecursion uses recursion to iterate though the tree. The
	 * performance should be O(N).
	 */
	private void getRangeRecursion(T fromElement, T toElement, BSTNode current, ArrayList<T> inRange) {

		// base case
		if (current != null) {
			// decide how comparisons should be done
			int compFrom, compTo = 0;
			if (comparator == null) { // use natural ordering of the elements
				compFrom = current.data.compareTo(fromElement);
				compTo = current.data.compareTo(toElement);
			} else { // use the comparator
				compFrom = comparator.compare(current.data, fromElement);
				compTo = comparator.compare(current.data, toElement);
			}

			// when the root is smaller than the range, out of bound
			if (compFrom < 0) {
				getRangeRecursion(fromElement, toElement, current.right, inRange);
			}
			// when the root is larger than the range, out of bound
			else if (compTo > 0) {
				getRangeRecursion(fromElement, toElement, current.left, inRange);
			}
			// when the root is inside the range
			else {
				if (compFrom >= 0 && compTo <= 0) {
					getRangeRecursion(fromElement, toElement, current.left, inRange);
					inRange.add(current.data);
					getRangeRecursion(fromElement, toElement, current.right, inRange);
				}
			}
		}
	}

	/**
	 * This equals method compares the specified object with this tree for
	 * equality.Returns true if the given object is also a tree, the two trees have
	 * the same size, and the inorder traversal of the two trees returns the same
	 * nodes in the same order. This operation should be O(N).
	 * 
	 * @overrides equals in class Object
	 * @parameters obj - object to be compared for equality with this tree
	 * @returns true if the specified object is equal to this tree
	 */
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		// check whether the object is null
		if (obj == null)
			return false;
		// check whether the object is BST type
		else if (!(obj instanceof BST))
			return false;

		BST other = (BST) obj;
		// check whether the two trees have the same size
		if (other.size() != this.size())
			return false;

		// use getRange method to check whether the inorder traversal of these two trees
		// are the same.
		if (this.root != null && other.root != null) {
			T first1 = this.first();
			T last1 = this.last();
			T first2 = (T) other.first();
			T last2 = (T) other.last();

			if (getRange(first1, last1).equals(getRange(first2, last2)))
				return true;
		} else
			return true;

		return false;
	}

	/**
	 * This method returns a string representation of this tree. The string
	 * representation consists of a list of the tree's elements in the order they
	 * are returned by its iterator (inorder traversal), enclosed in square brackets
	 * ("[]"). Adjacent elements are separated by the characters ", " (comma and
	 * space). Elements are converted to strings as by String.valueOf(Object). This
	 * operation should be O(N).
	 * 
	 * @overrides toString in class Object
	 * @returns a string representation of this collection
	 */
	public String toString() {

		// use iterator to get each element and print them out
		Iterator<T> itr = this.iterator();
		String str = "[";
		for (int i = 1; i < this.size(); i++) {
			str = str + String.valueOf(itr.next()) + ", ";
		}
		return str + String.valueOf(itr.next()) + "]";
	}

	/**
	 * This function returns an array containing all the elements returned by this
	 * tree's iterator, in the same order, stored in consecutive elements of the
	 * array, starting with index 0. The length of the returned array is equal to
	 * the number of elements returned by the iterator. This operation should be
	 * O(N).
	 * 
	 * @returns an array, whose runtime component type is Object, containing all of
	 *          the elements in this tree
	 */
	public Object[] toArray() {

		// create an empty array with size the same as the tree
		Object[] arr = new Object[this.size()];
		// use iterator to get each element and store them in the array
		Iterator<T> itr = this.iterator();
		for (int i = 0; i < this.size(); i++) {
			arr[i] = itr.next();
		}
		return arr;
	}
}
