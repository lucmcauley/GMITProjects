package ie.gmit.dip;

import java.util.Scanner;

/**
 * User inserts their choice for the way in which the text file is divided for
 * subsequent comparison.
 * 
 * @author LucMcAuley
 *
 */
public class TextDivisionSelection implements Menuable {
	private int[] comparisonInfo = new int[2]; // [comparisonType, kmer]
	private Scanner scanner;

	public TextDivisionSelection() {
		scanner = new Scanner(System.in);
	}

	/**
	 * User selects the way they would like to divide the lines, e.g. <code>word,
	 * shingles, k-mer.</code>
	 * 
	 * @return <code>Integer array</code> of size 2,
	 *         <code>[comparison type, k-mer value].</code>
	 */
	public int[] start() {
		displayOptions();
		getUserSelection();
		return comparisonInfo;
	}

	@Override
	public void getUserSelection() {
		boolean run = true;

		while (run) {
			try {
				int choice = Integer.parseInt(scanner.next());

				if (choice == 1) { // HashCode Comparison
					setComparisonType(1);
					run = false;
				} else if (choice == 2) { // Word Shingle Comparison
					setComparisonType(2);
					run = false;
				} else if (choice == 3) { // K-mer Comparison
					setComparisonType(3);
					run = false;
				} else {
					System.out.println("Error Invalid Input");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please use numbers");
			}
		}
	}

	@Override
	public void displayOptions() {
		System.out.println("Option 1: Use word comparisons");
		System.out.println("\t This is fast but not as accurate");
		System.out.println("Option 2: Use word shingles");
		System.out.println("\t This is slower but more accurate");
		System.out.println("Option 3: Use k-mer comparision");
		System.out.println("\t This is even slower but provides greater accuracy");
	}

	private void getUserKmer() { // Set the kmer value for option 2 and 3
		boolean run = true;

		System.out.println("Please enter the shingle or kmer number");

		while (run) {
			try {
				int choice = Integer.parseInt(scanner.next());
				if (new Verification().verifyPositiveInteger(choice)) { // Verifies the input
					comparisonInfo[1] = (choice);
					run = false;
				} else {
					System.out.println("Please enter a number greater than 0");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please try a valid number");
			}
		}
	}


	private void setComparisonType(int choice) {// The choice here is the choice of comparison type
		comparisonInfo[0] = (choice);

		if (choice == 1) {
			comparisonInfo[1] = 1;// Kmer value of 1 with choice 1 gives single word shingle
		} else {
			getUserKmer();
		}
	}
}
