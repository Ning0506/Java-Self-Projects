package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The FallenStars class is the actual program. This is the class that should
 * contain the main method. It is responsible for opening and reading the data
 * file, obtaining user input, performing some data validation and handling all
 * errors that may occur (in particular, it should handle any exceptions thrown
 * by your other classes and terminate gracefully, if need be, with a friendly
 * error message presented to the user: The program should never just reprint
 * the exception message as a way of handling an exception. These messages are
 * rarely readable to the ordinary user and make it seem like the program
 * crashed in response to the exception.
 *
 * @author Ning Yang
 *
 */
public class FallenStars {

	/**
	 * This main method for storing data from the file and for interaction mode.
	 */
	public static void main(String[] args) {

		// verify that the command line argument exists
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		File starFile = new File(args[0]);

		// verify that command line argument contains a name of an existing file
		if (!starFile.exists()) {
			System.err.println("Error: the file " + starFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		// verify that the file can be opened for reading
		if (!starFile.canRead()) {
			System.err.println("Error: the file " + starFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// open the file for reading
		Scanner inStars = null;

		try {
			inStars = new Scanner(starFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + starFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// read the content of the file and save the data in a list of named stars
		String name;
		String mass, year;
		int mass1, year1,id;
		String line;
		Location loc = null;
		ArrayList<String> myList = new ArrayList<String>();
		MeteoriteData stars = new MeteoriteData();

		// when the text has next lines
		while (inStars.hasNextLine()) {
			try {
				line = inStars.nextLine();
				myList = splitCSVLine(line);

				if (myList.get(0).isEmpty() || myList.get(1).isEmpty()) {
					continue;
				}

				// name should be the first element in the arraylist
				name = myList.get(0);
				id = Integer.parseInt(myList.get(1));

				Meteorite oneStar = new Meteorite(name, id);
				
				if (!(myList.get(4).isEmpty()))
					oneStar.setMass(Integer.parseInt(myList.get(4)));
				
				if (!(myList.get(6).isEmpty())) {
					String time = myList.get(6);
					String t = time.replace("/", " ");
					String t1[] = t.split(" ");
					String ryear = t1[2];
					oneStar.setYear(Integer.parseInt(ryear));
				}

				if (!(myList.get(9).isEmpty())) {
					String location1 = myList.get(9);
					String[] s1 = location1.replaceAll("\\)", "").replaceAll("\\(", "").split(",");
					if (!(s1[0].isEmpty())&&!(s1[1].isEmpty()))
						oneStar.setLocation(new Location(Double.parseDouble(s1[0]), Double.parseDouble(s1[1])));
				}
				
				stars.add(oneStar);

			} catch (Exception ex) {
				// caused by an incomplete or miss-formatted line in the input file
				// ignore this exception and skip to the next line
				continue;
			}
		}
		inStars.close();

		// interactive mode:
		Scanner user = new Scanner(System.in);
		String userInput = "";

		// ask user to enter
		System.out.println("Search the database by using one of the following queries.\n"
				+ "  To search for meteorite nearest to a given goe-location, enter\n"
				+ "    location LATITUDE LONGITUDE\n" + "  To search for meteorites that fell in a given year, enter\n"
				+ "    year YEAR\n" + "  To search for meteorites with weights MASS +/- 10 grams, enter\n"
				+ "    mass MASS\n" + "  To finish the program, enter\n" + "    quit\n");

		// assign values
		do {
			System.out.println("\nEnter your search query:\n\n");
			// get value of from the user
			userInput = user.nextLine();
			try {
				if (!userInput.equalsIgnoreCase("quit")) {
					String[] ans = userInput.split(" ");

					// check whether the user enters location
					if (ans[0].equalsIgnoreCase("Location")) {

						if (ans.length != 3) {
							System.out.println("This is not a valid geolocation. Try again");
							continue;
						} else {
							Double latitude = Double.parseDouble(ans[1]);
							Double longitude = Double.parseDouble(ans[2]);
							Location inputLocation = new Location(latitude, longitude);
							System.out.println((stars.getByLocation(inputLocation)));
						}
					}
					// check whether the user enters year
					else if (ans[0].equalsIgnoreCase("YEAR")) {

						if (ans.length != 2) {
							System.out.println("This is not a valid geolocation. Try again");
							continue;
						} else {
							int inputYear = Integer.parseInt(ans[1]);
							if (stars.getByYear(inputYear) == null) {
								System.out.println("No matched found. Try again");
								continue;
							} else
								System.out.println(stars.getByYear(inputYear));
						}
					}
					// check whether the user enters mass
					else if (ans[0].equalsIgnoreCase("MASS")) {

						if (ans.length != 2) {
							System.out.println("This is not a valid geolocation. Try again");
							continue;
						} else {
							int userMass = Integer.parseInt(ans[1]);
							if (stars.getByMass(userMass, 10) == null) {
								System.out.println("No matched found. Try again");
								continue;
							} else
								System.out.println(stars.getByMass(userMass, 10));
						}
					} else
						System.out.println("This is not a valid query. Try again.");
				}
			} catch (IllegalArgumentException ex) {
				// ignore this exception and skip to the next line
				System.out.println("This is not a valid query. Try again.");
				continue;
			}
		} while (!userInput.equalsIgnoreCase("quit"));

		user.close();
	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may
	 * contain commas)
	 * 
	 * @author Joanna Klukowska
	 * @param textLine a line of text from a CSV file to be parsed
	 * @return an ArrayList object containing all individual entries found on that
	 *         line; any missing entries are indicated as empty strings; null is
	 *         returned if the textline passed to this function is null itself.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {

		if (textLine == null)
			return null;

		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {

				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				} else {
					insideQuotes = true;
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					// add it to the current entry
					nextWord.append(nextChar);
				} else { // skip all spaces between entries
					continue;
				}
			} else if (nextChar == ',') {
				if (insideQuotes) { // comma inside an entry
					nextWord.append(nextChar);
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}

		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
}
