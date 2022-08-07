package ie.gmit.dip;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
	private Scanner sc;
	private boolean keepRunning = true;
	private RailCipher rc = new RailCipher(null, null, null, null, null, null, null, null);
	private RailCipherManager rcm = new RailCipherManager();
	private RailCipherInfo rci = new RailCipherInfo();
	private InputMenu im = new InputMenu();
	private Verification verify = new Verification();

	public MainMenu() {
		sc = new Scanner(System.in);
	}

	public void start() throws Exception {
		while (keepRunning) {
			showOptions();
			try {
				int choice = Integer.parseInt(sc.next());

				if (choice == 1) { // Select File, String or URL
					input();
				} else if (choice == 2) { // Enter Rail Fence Key
					setEncryptionKeys();
				} else if (choice == 3) { // Encrypt
					doCipher(true);
				} else if (choice == 4) { // Decrypt
					doCipher(false);
				} else if (choice == 5) { // Display Rail Fence
					displayRailFence();
				} else if (choice == 6) { // Display the information of the rail cipher
					displayRailFenceInfo();
				} else if (choice == 7) { // Save or load current state
					serialisationMenu();
				} else if (choice == 8) { // Quit
					System.out.println("[Info] Shutting down");
					keepRunning = false;
					System.exit(0);
				} else {
					System.out.println("Error Invalid Input");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please use numbers");
			}
		}
	}

	// Goes to the InputMenu Class which handles the source and source type
	private void input() throws Exception {
		rc = im.start(rc);

		pause();
	}

	private void setEncryptionKeys() {
		boolean keepRunning = true;
		int[] encryptionValues = new int[2];

		while (keepRunning) {
			try {
				System.out.println("Please enter the encryption key");
				int key = Integer.parseInt(sc.next());
				encryptionValues[0] = key;
				System.out.println("Please enter the offset value");
				int offset = Integer.parseInt(sc.next());
				encryptionValues[1] = offset;
				keepRunning = false;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a numeric value and try again");
			}
		}

		if (verify.checkEncryptionKeys(encryptionValues)) {
			rc.setEncryptionKeys(encryptionValues);
			System.out.println();
			System.out.println("Encryption keys set!");
		}

		pause();
	}

	// Sends the rail cipher to be encrypted when true or decrypted when false
	private void doCipher(boolean encrypt) throws Exception {
		if (verify.canProceed(rc)) {
			System.out.println("Working away...");
			System.out.println();
			rc = rcm.start(rc, encrypt);
		}
		pause();
	}

	private void displayRailFence() {
		rci.displayRailFence(rc);

		pause();
	}

	private void displayRailFenceInfo() {
		rci.getRailCipherInfo(rc);

		pause();
	}

	// This sends the most recent rail cipher to be saved or loaded from a file
	private void serialisationMenu() throws Exception {
		SerialisationMenu serial = new SerialisationMenu();

		rc = serial.start(rc);

		pause();
	}

	// Adapted from:
	// https://coderanch.com/t/394959/java/Press-key-continue
	private void pause() {
		System.out.println();
		System.out.println("Press Enter Key to Continue to MAIN MENU");

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showOptions() {
		System.out.println("############################");
		System.out.println("## Rail Cipher Encryption ##");
		System.out.println("############################");
		System.out.println("[1] Select File, String or URL");
		System.out.println("[2] Enter Rail Fence Key");
		System.out.println("[3] Encrypt");
		System.out.println("[4] Decrypt");
		System.out.println("[5] Display Rail Fence");
		System.out.println("[6] Display Rail Fence Information");
		System.out.println("[7] Save Current or Load Previous State");
		System.out.println("[8] Quit");
		System.out.println("Select an option");
	}
}
