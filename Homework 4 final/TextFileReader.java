import java.io.*;

/**
 * A simple class for reading lines one at a time from text files The path
 * (stream) to the file is created by the constructor The client MUST close the
 * path using the close() method once reading is finished
 * 
 * The usual algorithm for reading data from a file is such open stream attempt
 * to read a string while the attempt worked process the string attempt to read
 * another string close stream
 * 
 * @authors Adam Przybilla and Francisco Gomez
 * @version 1.0
 * CSC 143, Lepeintre
 * May 08, 2005.
 */


public class TextFileReader {
	private BufferedReader in; // the stream for input

	/**
	 * TextFileReader constructor creates a object for reading data from a text
	 * file.
	 * 
	 * @param filename
	 *            the name of the file to be opened and read The Full path to
	 *            file should be specified by the parameter. Don't forget that
	 *            the backslash ( \ ) is a special character. You must either
	 *            use two backslashes (\\) or the forward slash (/).
	 * @throws RuntimeException
	 *             if the file cannot be opened
	 */

	public TextFileReader(String filename) {
		try {
			in = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// for anyone who is in here reading this, I've chosen to throw
			// a Runtime exception because students don't necessarily know the
			// try-catch yet. So we'll throw unchecked instead of checked
			// exceptions.
			throw new RuntimeException("Could not open file " + filename);
		}
	}

	/**
	 * closes the open data file
	 * 
	 * @throws RuntimeException
	 *             if the file cannot be closed
	 */
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not close file");
		}
	}

	/**
	 * reads one line of the data file,
	 * 
	 * @return the String line from the data file, or null if end-of-file
	 * @throws RuntimeException
	 *             if the file cannot be read
	 */
	public String readLine() {
		try {
			return in.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Cannot read from the file");
		}
	}
}
