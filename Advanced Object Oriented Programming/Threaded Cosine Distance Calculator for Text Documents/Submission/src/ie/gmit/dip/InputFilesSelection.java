package ie.gmit.dip;

import java.util.Scanner;

/**
 * Asks the user to input the file paths for the query and search files.
 * 
 * @author Luc McAuley
 * 
 */
public class InputFilesSelection {
	private Scanner scanner;
	private FileComparisonManager fcm = new FileComparisonManager();
	private Verification verify = new Verification();

	/**
	 * Starting point for collecting the source files.
	 * <p>
	 * Guides user through the path selection for query and search file through
	 * other methods paths.
	 * </p>
	 * 
	 * @return FileInfoManager Contains the file paths for search and query files.
	 */
	public FileComparisonManager start() {
		scanner = new Scanner(System.in);

		getUserInput();

		return fcm;
	}

	private void getUserInput() {
		FileInfo queryFilePath = new FileInfo();
		FileInfo searchFilePath = new FileInfo();

		boolean queryFileRun = false;
		boolean searchFileRun = false;

		while (!queryFileRun) {
			System.out.println("Please enter query file");
			queryFilePath.setFilePath(scanner.next());
			queryFileRun = verify.verifyFilePath(queryFilePath.getFilePath());
		}

		while (!searchFileRun) {
			System.out.println("Please enter search file");
			searchFilePath.setFilePath(scanner.next());
			searchFileRun = verify.verifyFilePath(searchFilePath.getFilePath());
		}

		fcm.setQueryFile(queryFilePath);
		fcm.setSearchFile(searchFilePath);
	}

}
