package ie.gmit.dip;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculation results passed in and displayed to the user.
 * 
 * @author Luc McAuley
 *
 */
public class DisplayAnswer {

	/**
	 * Used to display the result of a cosine calculation.
	 * 
	 * @param cosineResult Result from a cosine calculation.
	 */
	public void displayCosineComparisonResult(double cosineResult) {
		BigDecimal bd = BigDecimal.valueOf(cosineResult);
		bd = bd.setScale(2, RoundingMode.HALF_UP);

		System.out.println("****************************");
		System.out.println("Similarity with cosine calculation: " + bd.doubleValue());
		System.out.println("****************************");
	}
}
