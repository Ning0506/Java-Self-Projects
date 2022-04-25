package project5;

import java.util.Calendar;

/**
 * This class should represent a meteorite with all of its characteristics.
 * 
 * This class should provide a two parameter constructor, setMass method,
 * getMass method, setYear method, getYear method, setLocation method,
 * getLocation method.
 *
 * @author Ning Yang
 *
 */
public class Meteorite implements Comparable<Meteorite> {

	// set default variables name, id, mass, year and loc
	private String name;
	private int id = 0;
	private int mass = 0;
	private int year = 0;
	private Location loc;

	/**
	 * Meteorite constructor provides a two parameter constructor that validates and
	 * sets the name and id.
	 * 
	 * @throws IllegaArgumentException if name is empty or id is <=0.
	 */
	public Meteorite(String name, int id) {

		// check whether the parameter name is empty or not
		if (name.isEmpty())
			throw new IllegalArgumentException("Invalid name");
		else
			this.name = name;

		// check whether the parameter id is greater than 0 or not
		if (id <= 0)
			throw new IllegalArgumentException("Invalid id");
		else
			this.id = id;
	}

	/**
	 * setMass method has one parameter int mass to set mass.
	 * 
	 * @throws IllegaArgumentException if mass is not positive.
	 */
	public void setMass(int mass) {
		if (mass <= 0)
			throw new IllegalArgumentException("Invalid mass");
		else
			this.mass = mass;
	}

	/**
	 * @return the mass of the meteorite.
	 */
	public int getMass() {
		return this.mass;
	}

	/**
	 * setYear method has one parameter int year to set year.
	 * 
	 * @throws IllegaArgumentException if year is not positive or it is larger than
	 *                                 the current year.
	 */
	public void setYear(int year) {
		if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR))
			throw new IllegalArgumentException("Invalid year");
		else
			this.year = year;
	}

	/**
	 * @return the year of the meteorite.
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * This setLocation method set the location directly since invalid cases has
	 * been checked in Location class,
	 */
	public void setLocation(Location loc) {
		this.loc = loc;
	}

	/**
	 * @return the location of the meteorite.
	 */
	public Location getLocation() {
		return loc;
	}

	/**
	 * This compareTo method compares two Meteorite objects based on their
	 * alphanumeric names; comparison by names should be insensitive.
	 * 
	 * If two names are the same, the comparison should be performed based on the
	 * numeric id's.
	 * 
	 * @return integer comparison result of two Meteorite objects.
	 */
	@Override
	public int compareTo(Meteorite o) {

		// compare name of two Meteorites case insensitive
		// if == 0, compare id of two Meteorites
		// else, return differences
		if (this.name.compareToIgnoreCase(o.name) == 0)
			return Integer.compare(this.id, o.id);

		return this.name.compareToIgnoreCase(o.name);
	}

	/**
	 * This equals method compares two Meteorite objects based on their alphanumeric
	 * names and id's.
	 * 
	 * @return true if two Meteorite objects have identical names(case does not
	 *         matter) and id's.
	 */
	@Override
	public boolean equals(Object obj) {

		// check whether the object is null
		if (obj == null)
			return false;
		// check whether the two objects are the same
		else if (this == obj)
			return true;
		// check whether the object is Meteorite type
		else if (!(obj instanceof Meteorite))
			return false;

		Meteorite other = (Meteorite) obj;

		// check whether these two objects have the same names and id's.
		if (this.name.compareToIgnoreCase(other.name) == 0 && Integer.compare(this.id, other.id) == 0)
			return true;
		else
			return false;
	}

	/**
	 * This toString methods prints out the Meteorite following the specified width
	 * and alignment. NAME should be width 20, left; ID should be width 4, right;
	 * YEAR should be width 4, right; MASS should be width 6, right; LATITUDE should
	 * be width 10, right; LONGITUDE should be width 10, right.
	 * 
	 * @return a string matching the pattern: NAME ID YEAR MASS LATITUDE LONGITUDE
	 */
	@Override
	public String toString() {

		String blank = " ";
		String newStr = String.format("%-20s %4d", this.name, this.id);

		if (this.year == 0)
			newStr += String.format(" %4s", blank);
		else
			newStr += String.format(" %4d", this.year);

		if (this.mass == 0)
			newStr += String.format(" %6s", blank);
		else
			newStr += String.format(" %6d", this.mass);

//		if (this.loc.getLatitude()== 0 && this.loc.getLongitude()!= 0)
		if (this.getLocation() == null)
			newStr += String.format(" %10s %10s \n", blank, blank);
		else
			newStr += String.format(" %10.5f %10.5f \n", this.loc.getLatitude(), this.loc.getLongitude());

		return newStr;
	}
}
