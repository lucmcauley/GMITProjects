package ie.gmit.dip;

import java.util.Scanner;

public class SerialisationMenu {
	private Scanner sc;

	public SerialisationMenu() {
		sc = new Scanner(System.in);
	}

	public RailCipher start(RailCipher rc) throws Exception {
		RailCipherUtils rcu = new RailCipherUtils();

		showOptions();

		int choice;
		try {
			choice = Integer.parseInt(sc.next());

		if (choice == 1) { // Save the Current State
			rcu.save(rc);
		} else if (choice == 2) { // Load from a previously saved state
			RailCipher loadedRailCipher = rcu.load();
			if (loadedRailCipher != null)
				rc = loadedRailCipher;
		} else if (choice == 3) // Exit to Main Menu
			System.out.println("Exiting to Main Menu");
		else {
			System.out.println();
			System.out.println("Please select an option 1 - 3");
		}
		} catch (NumberFormatException e) {
			System.out.println("Please enter a numeric value and try again");
		}
		return rc;

	}

	private void showOptions() {
		System.out.println();
		System.out.println("Please select from the options below");
		System.out.println();
		System.out.println("[1] Save the current state");
		System.out.println("[2] Load a previously saved state");
		System.out.println("[3] Exit back to main menu");
		System.out.println();
	}
}
