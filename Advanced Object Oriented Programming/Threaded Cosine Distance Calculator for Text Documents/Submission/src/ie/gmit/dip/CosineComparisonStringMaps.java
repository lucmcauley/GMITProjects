package ie.gmit.dip;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Compares two <code>hash maps</code> using a <code>TreeSet</code> of
 * <code>String</code> using cosine comparison.
 * <p>
 * This class implements the interface MapComparable.
 * </p>
 * 
 * @author Luc McAuley
 * 
 */
public class CosineComparisonStringMaps implements MapComparable {
	private Set<String> stringTerms = new TreeSet<String>();

	private Map<String, Integer> queryMap;
	private Map<String, Integer> searchMap;

	private volatile double countBSquared = 0;
	private volatile double countASquared = 0;
	private volatile int dotProduct = 0;

	/**
	 * Starts <code>String</code> based comparison of two texts.
	 * 
	 * @param incomingMim to set the <code>searchMap</code> and
	 *                    <code>queryMap</code> to compare.
	 */
	public void start(MapInfoManager incomingMim) {

		this.queryMap = incomingMim.getQueryMap().getStringMap();
		this.searchMap = incomingMim.getSearchMap().getStringMap();

		mergeSet();

		compare();

		displayComparisonPercentage();

	}

	@Override
	public void mergeSet() {// Merges the keys from the query and search texts
		stringTerms.addAll(queryMap.keySet());
		stringTerms.addAll(searchMap.keySet());
	}

	@Override
	public void compare() { // Probably a way to reuse this section with CompareHashMaps but ran out of time
							// to figure it out

		for (String term : stringTerms) {
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
