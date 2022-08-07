package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Incoming file is parsed and divided according to user input stored in the
 * <code>FileInfoManager</code>.
 * 
 * @author LucMcAuley
 * 
 */
public class FileDividor {
	private LineDivisionInfo ldi;
	private PopulateMap pm = new PopulateMap();
	private String filePath;

	/**
	 * Starting point for the files to be divided and inserted into maps with their
	 * associated keys.
	 * 
	 * @param filePath Sets the file path that is going to be divided into keys for
	 *                 the hash map.
	 * @param ldi      Sets the division and comparison methods.
	 * @return PopulateMap which contains the correct <code>HashMap</code> used for
	 *         comparison.
	 */
	public PopulateMap start(String filePath, LineDivisionInfo ldi) {
		this.ldi = ldi;
		this.filePath = filePath;

		parseFile();

		return pm;
	}

	/**
	 * Parses the file and uses the locally stored LineDivisionInfo to divide the
	 * lines accordingly.
	 */
	private void parseFile() {
		BufferedReader br;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
			String line = null;
			while ((line = br.readLine()) != null) {
				divide(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Please make sure that the file paths are correct and try again");
			System.out.println(filePath);
		}
	}

	/**
	 * By getting the info from the <code>FileInfoManager</code>, the string will
	 * then be divided accordingly before being sent to populate the
	 * <code>HashMap</code>.
	 * 
	 * @param line <code>String</code> to be inserted into the map.
	 */
	private void divide(String line) { // Sends the data to the correct division method

		if (ldi.getComparisonType() == 1) { // Divides line into single words
			separatedByWord(line);
		} else if (ldi.getComparisonType() == 2) { // Divides line into groups of k words
			separatedByShingle(line);
		} else if (ldi.getComparisonType() == 3) {// Divides line into groups of k characters
			separatedByKMer(line);
		} else {
			System.out.println("Something went wrong, please try again");
		}

	}

	private void separatedByWord(String line) {
		String[] words = line.trim().split(" ");
		for (String word : words) {
			getMap(word);
		}
	}

	private void separatedByShingle(String line) {

		String[] words = line.trim().split(" ");

		for (int i = 0; i < words.length; i += ldi.getKmer()) {
			StringBuilder shingle = new StringBuilder();

			for (int j = i; j < Math.min(words.length, i + ldi.getKmer()); j++) {
				shingle.append(words[j] + " ");
			}
			getMap(shingle.toString().trim());
		}
	}

	private void separatedByKMer(String line) {
		// code below stripped from
		// https://www.techiedelight.com/split-string-to-equal-length-substrings-java/

		List<String> tokens = new ArrayList<>();

		for (int i = 0; i < line.length(); i += ldi.getKmer()) {
			tokens.add(line.substring(i, Math.min(line.length(), i + ldi.getKmer())));
		}

		for (String token : tokens) {
			getMap(token);
		}
	}

	/**
	 * Sends the key either as a <code>hash code</code> or a <code>String</code> to
	 * the map according to the settings from the <code>FileInfoManager</code>.
	 * 
	 * @param key <code>String</code> that is going to be sent to the
	 *            <code>HashMap</code>.
	 */
	private void getMap(String key) {
		if (ldi.getTypeOfMapping() == 1) {
			pm.populateMap(key.hashCode());
		} else if (ldi.getTypeOfMapping() == 2) {
			pm.populateMap(key);
		}
	}
}
