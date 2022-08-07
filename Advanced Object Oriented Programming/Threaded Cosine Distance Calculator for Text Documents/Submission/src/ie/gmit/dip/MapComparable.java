package ie.gmit.dip;

/**
 * Used to ensure that the two Maps are merged and compared separately.
 * 
 * @author Luc McAuley
 *
 */
public interface MapComparable {

	/**
	 * Merges the sets together in order to have a set of keys to compare.
	 */
	void mergeSet();

	/**
	 * Compares the maps.
	 */
	void compare();
}
