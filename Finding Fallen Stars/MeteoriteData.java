package project5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This class should be used to store all the Meteorite objects. This class
 * should make use of the BST<T> class (but it should not inherit from it; it
 * should be has-a relationship not an is-a relationship). The class needs to
 * provide a default constructor that creates an empty MeteoriteData object.
 *
 * @author Ning Yang
 *
 */
public class MeteoriteData {

	private BST<Meteorite> starNameIdTree = new BST<>();
	private BST<Meteorite> starYearTree = new BST<>(new YearComparator());
	private BST<Meteorite> starMassTree = new BST<>(new MassComparator());

	// default constructor
	public MeteoriteData() {
	}

	/**
	 * Provides comparison of Meteorite objects by mass as the primary key and then
	 * according to their natural ordering as the secondary key.
	 */
	private class MassComparator implements Comparator<Meteorite> {
		@Override
		public int compare(Meteorite o1, Meteorite o2) {
			if (o1.getMass() == o2.getMass())
				return o1.compareTo(o2);
			else
				return o1.getMass() - o2.getMass();
		}
	}

	/**
	 * Provides comparison of Meteorite objects by year as the primary key and then
	 * according to their natural ordering as the secondary key.
	 */
	private class YearComparator implements Comparator<Meteorite> {
		@Override
		public int compare(Meteorite o1, Meteorite o2) {
			if (o1.getYear() == o2.getYear())
				return o1.compareTo(o2);
			else
				return o1.getYear() - o2.getYear();
		}
	}

	/**
	 * This method should add the given Meteorite object to this collection. The
	 * performance should be O(H).
	 * 
	 * @param m
	 * @return true if an equal Meteorite object is not already present.
	 * @throw NullPointerException if m is null.
	 */
	public boolean add(Meteorite m) throws NullPointerException {

		// verify whether the object is null
		if (m == null)
			throw new NullPointerException("The object is null.");

		// add data to all three trees
		starYearTree.add(m);
		starMassTree.add(m);

		return starNameIdTree.add(m);
	}

	/**
	 * This method should compare this collection to obj. The two collections are
	 * equal if they are both MeteoriteData objects and if they contain exactly the
	 * same elements. The performance should be O(N).
	 * 
	 * @param obj
	 * @return true if two collections are equal.
	 */
	public boolean equals(Object obj) {

		// check whether the object the same as this MeteoriteData
		if (this == obj)
			return true;
		// check whether the object is null
		if (obj == null)
			return false;
		// check whether the object is BST type
		else if (!(obj instanceof MeteoriteData))
			return false;

		MeteoriteData other = (MeteoriteData) obj;

		// if three trees are the same, then the MeteoriteData should be the same,
		// return true
		if (this.starNameIdTree.equals(other.starNameIdTree) && this.starYearTree.equals(other.starYearTree)
				&& this.starMassTree.equals(other.starMassTree))
			return true;

		return false;
	}

	/**
	 * This method should return a collection of all Meteorite objects with mass
	 * within delta grams of the specified mass. Both values are specified in grams.
	 * The returned collection should be organized based on the mass from smallest
	 * to largest, and for meteorite objects with equal mass according to the
	 * natural ordering of the elements (i.e., dictated by the comparaTo method
	 * defined in the Mereorite class).This method should perform in O(K+H) in which
	 * K is the number of Meteorite objects in the returned collection and H is the
	 * height of the tree representing this collection.
	 * 
	 * @throw IllegalArgumentException with an appropriate message if either value
	 *        is less than zero.
	 * @return null if there are no elements in the collection that match the given
	 *         criteria.
	 */
	public MeteoriteData getByMass(int mass, int delta) throws IllegalArgumentException {

		// check whether inputed data are valid
		if (mass < 0 || delta < 0)
			throw new IllegalArgumentException("Mass and Delta should not be less than zero.");

		// create a new MeteoriteData to store all the Meteorites that match the
		// requirement
		MeteoriteData newMeDa = new MeteoriteData();
		// create two Meteorites to store different masses which will be compared
		Meteorite m1 = new Meteorite("\u0000", 1);
		Meteorite m2 = new Meteorite("\uffff", 10000000);
		m1.setMass(mass - delta);
		m2.setMass(mass + delta);

		// use getRange to get the Meteorites that have masses within the range
		ArrayList<Meteorite> arrM = this.starMassTree.getRange(m1, m2);

		// if the size is zero which means that there are no matches, return null
		if (arrM.size() == 0)
			return null;

		// store these selected Meteorites in the created newMeDa
		for (int i = 0; i < arrM.size(); i++)
			newMeDa.add(arrM.get(i));
		return newMeDa;
	}

	/**
	 * If the current collection is empty, this method should return null. This
	 * method should perform in O(N) in which N is the total number of Meteorite
	 * objects stored in this collection.
	 * 
	 * @throws IllegalArgumentException with an appropriate message if loc is null.
	 * @return a Meteorite object whose landing site is nearest to the specified
	 *         location loc.
	 */
	public Meteorite getByLocation(Location loc) {

		// check whether the location is valid
		if (loc == null)
			throw new IllegalArgumentException("Location cannot be null.");
		// check whether the current collection is empty
		if (this.starNameIdTree.size() == 0)
			return null;

		// use toArray method to get an array of all Meteorites
		Object[] arr = this.starNameIdTree.toArray();
		// set the Meteorite which has the smallest location be the first element
		Meteorite min = (Meteorite) arr[0];

		// compare the rest Meteorites' locations with the first one
		// if the first one is bigger, switch the min
		for (int i = 1; i < this.starNameIdTree.size(); i++) {
			if (min.getLocation().getDistance(loc) > ((Meteorite) arr[i]).getLocation().getDistance(loc))
				min = (Meteorite) arr[i];
		}
		return min;
	}

	/**
	 * The returned collection should be organized based on the year from earliest
	 * to most recent, and for meteorite objects with the same year according to the
	 * natural ordering of the elements (i.e., dictated by the comparaTo method
	 * defined in the Mereorite class). This method should perform in O(K+H) in
	 * which K is the number of Meteorite objects in the returned collection and H
	 * is the height of the tree representing this collection (not O(N) where N is
	 * the total number of all Meteorite objects).
	 * 
	 * 
	 * @throws IllegalArgumentException with an appropriate message if the value of
	 *                                  year is less than zero.
	 * @return a collection of all Meteorite objects that landed on Earth on the
	 *         year specified.
	 */
	public MeteoriteData getByYear(int year) {

		// check whether the input year is valid
		if (year < 0)
			throw new IllegalArgumentException("Year should not be less than zero.");

		// create a new MeteoriteData to store all the Meteorites that match the
		// requirement
		MeteoriteData newMeDa = new MeteoriteData();
		// create two Meteorites to store different masses which will be compared
		Meteorite m1 = new Meteorite("\u0000", 1);
		Meteorite m2 = new Meteorite("\uffff", 10000000);
		m1.setYear(year);
		m2.setYear(year);

		// use getRange to get the Meteorites that have the same year
		ArrayList<Meteorite> arrM = this.starYearTree.getRange(m1, m2);

		// if the size is zero which means that there are no matches, return null
		if (arrM.size() == 0)
			return null;

		// store these selected Meteorites in the created newMeDa
		for (int i = 0; i < arrM.size(); i++)
			newMeDa.add(arrM.get(i));
		return newMeDa;
	}

	/**
	 * @return an iterator over all Meteorite objects in this collection in order
	 *         specified by natural ordering of Meteorite objects.
	 */
	public Iterator<Meteorite> iterator() {
		return starNameIdTree.iterator();
	}

	/**
	 * This method should remove an object equal to the given Meteorite object m
	 * from this collection and return true such an object was present. This method
	 * should perform in O(H) in which H is the height of the tree representing this
	 * collection.
	 * 
	 * @throws NullPointerException if m is null.
	 * @return false if m is not in this collection.
	 */
	public boolean remove(Meteorite m) throws NullPointerException {

		// check whether the m is null
		if (m == null)
			throw new NullPointerException("Null Meteorite.");

		return starNameIdTree.remove(m);
	}

	/**
	 * This method returns a string representation of all match. The string
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
		Iterator<Meteorite> itr4 = this.iterator();
		String str = "";
		for (int i = 0; i < this.starNameIdTree.size(); i++) {
			str = str + String.valueOf(itr4.next());
		}
		return str;
	}
}
