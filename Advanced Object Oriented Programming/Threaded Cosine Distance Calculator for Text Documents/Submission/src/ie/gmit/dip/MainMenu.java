package ie.gmit.dip;

/**
 * Main menu where the user is guided through the selection process to then
 * start comparing the text files.
 * 
 * @author Luc McAuley
 * 
 */
public class MainMenu {
	private FileComparisonManager fcm = new FileComparisonManager();

	/**
	 * Starting point for user selection as well as comparison.
	 */
	public void start() {
		getUserInfo();

		new DisplayLoadingScreen().displayLoadingInformation();// Displays a loading screen

		new CompareFiles().start(fcm);// Begins the comparison
	}

	/**
	 * Collection of menus used to collect the information necessary to compare two
	 * files.
	 */
	private void getUserInfo() {
		fcm = new InputFilesSelection().start();// Sets the search and query files

		fcm.setLineDivisionInfo(new LineDivisionInfo());

		fcm.getLineDivisionInfo().setComparisonInfo(new TextDivisionSelection().start());// Sets the way in which the
																							// files is divided for
		// comparison

		fcm.getLineDivisionInfo().setTypeOfMapping(new ComparisonTypeSelection().start());// Sets the way in which the
																							// files are compared,
		// hash code or string
	}

}
