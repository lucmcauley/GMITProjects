package ie.gmit.dip;

/**
 * Contains the information for the way in which the files will be divided for
 * comparison.
 * 
 * @author Luc McAuley
 *
 */
public class LineDivisionInfo {
	private int kmer;
	private int comparisonType;
	private int typeOfMapping;

	/**
	 * Sets the comparison type and kmer in one action. Used in
	 * <code>MainMenu</code> when returning from
	 * <code>ComparisonInfoSelection.</code>
	 * 
	 * @param comparisonInfo integer array of size 2,
	 *                       <code>[comparisonType, kmer]</code>
	 */
	public void setComparisonInfo(int[] comparisonInfo) {
		this.comparisonType = comparisonInfo[0];
		this.kmer = comparisonInfo[1];
	}

	/**
	 * 
	 * @return Integer for the type of mapping selected, 1 for single word, 2 for
	 *         shingle word, 3 for k-mer.
	 */
	public int getTypeOfMapping() {
		return typeOfMapping;
	}

	/**
	 * 
	 * @param typeOfMapping Integer for the type of mapping selected, 1 for single
	 *                      word, 2 for shingle word, 3 for k-mer.
	 */
	public void setTypeOfMapping(int typeOfMapping) {
		this.typeOfMapping = typeOfMapping;
	}

	/**
	 * 
	 * @return Integer for the comparison type, 1 for hash code and 2 for string.
	 */
	public int getComparisonType() {
		return comparisonType;
	}

	/**
	 * 
	 * @param typeOfMapping Integer for the comparison type, 1 for hash code and 2
	 *                      for string.
	 */
	public void setComparisonType(int typeOfMapping) {
		this.comparisonType = typeOfMapping;
	}

	/**
	 * 
	 * @return Integer with the size of the word shingle or the k-mer.
	 */
	public int getKmer() {
		return kmer;
	}

	/**
	 * 
	 * @param kmer Integer with the size of the word shingle or the k-mer.
	 */
	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

}
