package ie.gmit.dip;

/**
 * Calculates the result of a comparison to be displayed elsewhere.
 * 
 * @author Luc McAuley
 *
 */
public class CalculateResult {

	/**
	 * Calculates the distance between two texts using cosine calculation.
	 * 
	 * @param dotProduct    Product of the number of instances each <code>key</code>
	 *                      appears in a <code>Map.</code>
	 * @param countASquared Sum of the squared number of instances each
	 *                      <code>key</code> appears in the search <code>Map.</code>
	 * @param countBSquared Sum of the squared number of instances each
	 *                      <code>key</code> appears in the query <code>Map.</code>
	 * @return Cosine calculation, 0 being most dissimilar and 1 being identical.
	 */
	public double cosineCalculator(double dotProduct, double countASquared, double countBSquared) {
		return ((dotProduct) / (Math.sqrt(countASquared) * Math.sqrt(countBSquared)));
	}

}
