package ie.gmit.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * Populates a <code>HashMap</code> with the <code>key</code> as either a
 * <code>String</code> or <code>integer</code> from the text and maps it to an
 * <code>integer</code> being the number of times the <code>key</code> appears
 * in the text.
 * 
 * @author Luc McAuley
 * 
 */
public class PopulateMap {

	private Map<String, Integer> stringMap = new HashMap<>();
	private Map<Integer, Integer> integerMap = new HashMap<>();

	private int count;

	/**
	 * Populates the <code>HashMap</code> with the <code>key</code> and increments
	 * the associated <code>value</code> if the <code>key</code> is already present.
	 * 
	 * @param hashValue Compares the <code>Map</code> and is either inserted or the
	 *                  associated counter <code>value</code> is increased by 1.
	 */
	public void populateMap(int hashValue) {

		if (integerMap.get(hashValue) == null) {
			integerMap.put(hashValue, 1);
		} else {
			count = integerMap.get(hashValue);
			count++;
			integerMap.put(hashValue, count);
		}
	}

	/**
	 * Populates the <code>HashMap</code> with the <code>key</code> and increments
	 * the associated <code>value</code> if the <code>key</code> is already present.
	 * 
	 * @param text Compares the <code>Map</code> and is either inserted or the
	 *             associated counter <code>value</code> is increased by 1.
	 */
	public void populateMap(String text) {
		if (stringMap.get(text) == null) {
			stringMap.put(text, 1);
		} else {
			count = stringMap.get(text);
			count++;
			stringMap.put(text, count);
		}
	}

	/**
	 * Returns the populated <code>integer</code> to <code>integer Map.</code>
	 * 
	 * @return Map From a <code>hash code</code> which appears in the text to the
	 *         number of times the <code>integer</code> was present in the text.
	 */
	public Map<Integer, Integer> getIntegerMap() {
		return integerMap;
	}

	/**
	 * Returns the populated <code>string</code> to <code>integer Map.</code>
	 * 
	 * @return Map From a <code>string</code> which appears in the text to the
	 *         number of times the <code>string</code> was present in the text.
	 */
	public Map<String, Integer> getStringMap() {
		return stringMap;
	}

}
