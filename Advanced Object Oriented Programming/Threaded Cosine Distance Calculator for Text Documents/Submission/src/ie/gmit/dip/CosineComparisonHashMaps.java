package ie.gmit.dip;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Compares two <code>hash maps</code> using a <code>TreeSet</code> of
 * <code>integers</code> using cosine comparison.
 * <p>
 * This class implements the interface MapComparable.
 * </p>
 * 
 * @author Luc McAuley
 * 
 */
public class CosineComparisonHashMaps implements MapComparable {// Logic to compare two maps
	private Set<Integer> integerTerms = new TreeSet<Integer>();

	private Map<Integer, Integer> queryMap;
	private Map<Integer, Integer> searchMap;

	private double countASquared = 0;
	private double countBSquared = 0;
	private double dotProduct = 0;

	/**
	 * Starts <code>hash code</code> based comparison of two texts.
	 * 
	 * @param incomingMim to set the <code>searchMap</code> and
	 *                    <code>queryMap</code> to compare.
	 */
	public void start(MapInfoManager incomingMim) {

		this.queryMap = incomingMim.getQueryMap().getIntegerMap();
		this.searchMap = incomingMim.getSearchMap().getIntegerMap();

		mergeSet();

		compare();

		displayComparisonPercentage();
	}

	@Override
	public void mergeSet() {// Merges the keys from the query and search texts
		integerTerms.addAll(queryMap.keySet());
		integerTerms.addAll(searchMap.keySet());
	}

	@Override
	public void compare() {// Probably a way to reuse this section with CompareStringMaps but ran out of
							// time to figure it out

		for (int term : integerTerms) {
			if (queryMap.get(term) == null) {
				countBSquared += Math.pow(searchMap.get(term), 2);
			} else if (searchMap.get(term) == null) {
				countASquared += Math.pow(queryMap.get(term), 2);
			} else {
				countASquared += Math.pow(queryMap.get(term), 2);
				countBSquared += Math.pow(searchMap.get(term), 2);
				dotProduct += (queryMap.get(term) * searchMap.get(term));
			}
		}
	}

	/**
	 * Sends the variables from <code>compare()</code> to be calculated and
	 * displayed to the user.
	 */
	private void displayComparisonPercentage() {
		double result = new CalculateResult().cosineCalculator(dotProduct, countASquared, countBSquared);
		new DisplayAnswer().displayCosineComparisonResult(result);
	}
}
