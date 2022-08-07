package ie.gmit.dip;

import java.util.Scanner;

/**
 * The user inputs the type of comparison they would like to use, e.g. compare
 * using <code>hash code</code> or using <code>string.</code>
 * 
 * @author Luc McAuley
 *
 */
public class ComparisonTypeSelection implements Menuable {
	private Scanner scanner;
	int selection = 0;

	public ComparisonTypeSelection() {
		scanner = new Scanner(System.in);
	}

	/**
	 * User selects the way they would like to compare the files, e.g.
	 * <code>hash code, string.</code>
	 * 
	 * @return Returns an <code>integer</code> with the selection of the type of
	 *         comparison, 1 for <code>hash code</code> and 2 for
	 *         <code>string</code>.
	 */
	public int start() {
		displayOptions();
		getUserSelection();
		return selection;
	}

	@Override
	public void displayOptions() {
		System.out.println("1: Compare using HashCodes");
		System.out.println("\t This is faster but less accurate");
		System.out.println("2: Compare using Strings");
		System.out.println("\t This is slower but more accurate");
	}

	@Override
	public void getUserSelection() {
		boolean run = true;
		while (run) {
			try {
				int choice = Integer.parseInt(scanner.next());

				if (choice == 1) { // HashCode Comparison
					selection = 1;
					run = false;
				} else if (choice == 2) { // Word Shingle Comparison
					selection = 2;
					run = false;
				} else {
					System.out.println("Error Invalid Input");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please use numbers");
			}
		}
	}
}
