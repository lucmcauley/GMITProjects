package ie.gmit.dip;

/**
 * Interface which ensures that the displaying of options and the user selection
 * are separated.
 * 
 * @author Luc McAuley
 *
 */
public interface Menuable {

	/**
	 * The options are displayed to the user.
	 */
	void displayOptions();

	/**
	 * The user enters their selection of the options displayed.
	 */
	void getUserSelection();

}
