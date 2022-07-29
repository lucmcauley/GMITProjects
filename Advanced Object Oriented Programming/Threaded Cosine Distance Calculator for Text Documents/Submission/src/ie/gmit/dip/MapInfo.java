package ie.gmit.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the information for the maps from search key to number of times the
 * key appears in a text. {@link Map}
 * <p>
 * Currently contains maps for <code>String, Integer</code> and
 * <code>Integer to Integer.</code>
 * </p>
 * 
 * @author Luc McAuley
 * 
 */
public class MapInfo {
	private Map<Integer, Integer> integerMap = new HashMap<>();
	private Map<String, Integer> stringMap = new HashMap<>();

	/**
	 * 
	 * @return <code>Integer</code> to <code>Integer</code> <code>HashMap.</code>
	 */
	public Map<Integer, Integer> getIntegerMap() {
		return integerMap;
	}

	/**
	 * 
	 * @param integerMap Sets the <code>Integer</code> to <code>Integer</code>
	 *                   <code>HashMap.</code>
	 */
	public void setIntegerMap(Map<Integer, Integer> integerMap) {
		this.integerMap = integerMap;
	}

	/**
	 * 
	 * @return <code>String</code> to <code>Integer</code> <code>HashMap.</code>
	 */
	public Map<String, Integer> getStringMap() {
		return stringMap;
	}

	/**
	 * 
	 * @param stringMap Sets the <code>Integer</code> to <code>Integer</code>
	 *                  <code>HashMap.</code>
	 */
	public void setStringMap(Map<String, Integer> stringMap) {
		this.stringMap = stringMap;
	}
}
