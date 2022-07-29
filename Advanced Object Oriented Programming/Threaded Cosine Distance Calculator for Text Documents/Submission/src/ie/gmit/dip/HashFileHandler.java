package ie.gmit.dip;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Used for the <code>executorService</code> to return an <code>integer</code>
 * to <code>integer</code> {@link Map} through the <code>FileDividor</code>
 * class.
 * 
 * @author LucMcAuley
 * 
 */
public class HashFileHandler implements Callable<Map<Integer, Integer>> {
	private LineDivisionInfo ldi = new LineDivisionInfo();
	private String filePath;

	/**
	 * Constructor used for the method call()
	 * 
	 * @param ldi      <code>LineDivisionInfo</code> used to contain the file
	 *                 comparison information.
	 * @param filePath Path to the file to be parsed and compared.
	 */
	HashFileHandler(LineDivisionInfo ldi, String filePath) {
		this.ldi = ldi;
		this.filePath = filePath;
	}

	@Override
	/**
	 * @return Map containing the search terms and the number of times they appear
	 *         in the text.
	 */
	public Map<Integer, Integer> call() { // Sends each line of the selected file to be inserted and counted in a
											// HashMap<Integer, Integer>
		PopulateMap pm;

		pm = new FileDividor().start(filePath, ldi);

		return pm.getIntegerMap();
	}

}
