package ie.gmit.dip;

import java.util.Scanner;

public class InputMenu {
	private Scanner sc;
	private boolean keepRunning = true;
	private Verification verify = new Verification();

	public InputMenu() {
		sc = new Scanner(System.in);
	}

	public RailCipher start(RailCipher rc) throws Exception {
		showOptions();
		while (keepRunning) {
			try {
				int choice = Integer.parseInt(sc.next());
				if (choice == 1) { // Select File
					return selectFile(rc);
				} else if (choice == 2) { // Select URL
					return selectUrl(rc);
				} else if (choice == 3) { // User Generated Text
					return selectTypedText(rc);
				} else if (choice == 4) { // Quit
					System.out.println("Returning to Main Menu");
					return rc;
				} else {
					System.out.println();
					System.out.println("Invalid Selection");
					System.out.println("Please use numbers between 1 and 4");
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("Invalid Selection");
				System.out.println("Please use numbers between 1 and 4");
				System.out.println();
			}

		}
		return rc;
	}

	private RailCipher selectFile(RailCipher rc) {
		sc = new Scanner(System.in);
		rc.setSourceType("File");

		System.out.println(
				"Please enter the path to the file you would like to load - Please include file type e.g. txt");

		String fileName = sc.next();

		if (verify.doesPathExists(fileName)) {// Checks that the File exists
			System.out.println();
			System.out.println("Path set successfully");
			System.out.println();
			rc.setSource(fileName);
			rc.setOutputFile(selectOutputFile());
		} else {
			System.out.println();
			System.out.println("Something went wrong, please check the file path");
			System.out.println();
		}

		return rc;

	}

	private RailCipher selectUrl(RailCipher rc) {
		sc = new Scanner(System.in);
		rc.setSourceType("URL");

		System.out.println("Please enter the URL to the file you would like to load - Please include correct HTTP protocol");
		String source = sc.next();

		if (verify.doesUrlConnect(source)) { // Checks that the URL is valid
			System.out.println("Connected to URL");
			rc.setSource(source);
			rc.setOutputFile(selectOutputFile()); // Sets the output file
		} else
			System.out.println("Something went wrong, plesae try again");

		return rc;
	}

	private RailCipher selectTypedText(RailCipher rc) throws Exception {
		sc = new Scanner(System.in);

		InputText it = new InputText();
		rc.setSourceType("User Generated File");

		System.out.println("File you would like to save your content to");

		String source = sc.nextLine();

		if (!verify.doesPathExists(source)) { // Makes sure the file exists
			System.out.println();
			System.out.println("Path available");
			rc.setSource(source);
			it.setUserText(rc.getSource().toString()); // User inserts their text
			rc.setOutputFile(selectOutputFile());
		} else {
			System.out.println();
			System.out.println("Something went wrong");
		}

		return rc;
	}

	private String selectOutputFile() {
		sc = new Scanner(System.in);

		System.out.println("Please enter the path of the output file");

		String outputFile = sc.next();

		if (!verify.doesPathExists(outputFile)) { // Verifies that the file doesn't already exist
			System.out.println();
			System.out.println("Path selected");
			return outputFile.toString();
		} else {
			System.out.println();
			System.out.println("There was an error...");
			System.out.println(
					"Please ensure that there are no files of the same name in the destination folder and try again");
			System.out.println();
			return null;
		}
	}

	private void showOptions() {
		System.out.println();
		System.out.println("Please select from one of the options below");
		System.out.println();
		System.out.println("[1] Select File");
		System.out.println("[2] Select URL");
		System.out.println("[3] Type in your own text");
		System.out.println("[4] Return to Main Menu");
		System.out.println();
	}

}
