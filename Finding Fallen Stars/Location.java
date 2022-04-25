package project5;

/**
 * This class should represent the location(latitude, longitude).
 * 
 * This class should provide a two parameter constructor, getDistance method,
 * haversine method, getLatitude method, getLongitude method, and equals method.
 *
 * @author Ning Yang
 *
 */
public class Location {

	private double latitude, longitude;

	/**
	 * Location constructor provides a two parameter constructor that validates and
	 * sets the latitude and longitude.
	 * 
	 * @throws IllegaArgumentException if latitude or longitude are out of range.
	 */
	public Location(double latitude, double longitude) throws IllegalArgumentException {

		// check whether latitude is in the range[-90.0,90.0]
		if (latitude < -90.0 || latitude > 90.0)
			throw new IllegalArgumentException("Invalid latitude");
		else
			this.latitude = latitude;

		// check whether longitude is in the range[-180.0,180.0]
		if (longitude < -180.0 || longitude > 180.0)
			throw new IllegalArgumentException("Invalid longitude");
		else
			this.longitude = longitude;
	}

	/**
	 * haversine method determines the distance between two points on a sphere given
	 * their latitudes and longitudes.
	 * 
	 * @return the distance.
	 */
	static double haversine(double lat1, double lon1, double lat2, double lon2) {

		// distance between latitudes and longitudes
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);

		// convert to radians
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// apply formulae
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c;
	}

	/**
	 * getDistance method provides computes and returns distance between this
	 * location and the one provided as the parameter.
	 * 
	 * @throws IllegaArgumentException if parameter is null.
	 * @return distance calculated in haversine.
	 */
	double getDistance(Location loc) throws IllegalArgumentException {

		// check whether the parameter is null
		if (loc == null)
			throw new IllegalArgumentException("Null location");

		return haversine(this.latitude, this.longitude, loc.latitude, loc.longitude);
	}

	/**
	 * Return the latitude value representing the Location object.
	 * 
	 * @return the double latitude value.
	 */
	double getLatitude() {
		return this.latitude;
	}

	/**
	 * Return the longitude value representing the Location object.
	 * 
	 * @return the double longitude value.
	 */
	double getLongitude() {
		return this.longitude;
	}

	/**
	 * Compare two Location objects l1 and l2.
	 * 
	 * @return true if abs(l1.latitude - l2.latitude) < 0.00001 && abs(l1.longitude
	 *         - l2.longitude) < 0.00001.
	 */
	@Override
	public boolean equals(Object obj) {

		// check whether the object is null
		if (obj == null)
			return false;
		// check whether the two objects are the same
		else if (this == obj)
			return true;
		// check whether the object is Location type
		else if (!(obj instanceof Location))
			return false;

		Location other = (Location) obj;

		if (Math.abs(latitude - other.latitude) < 0.00001 && Math.abs(longitude - other.longitude) < 0.00001)
			return true;
		else
			return false;
	}
}
