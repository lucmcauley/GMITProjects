package ie.gmit.dip;

import java.io.*;
import java.util.Scanner;

public class RailCipherUtils {
	private Scanner sc;
	private Verification verify = new Verification();

	public void save(RailCipher rc) throws Exception {
		try {
			sc = new Scanner(System.in); // Setting the output file path
			System.out.println();
			System.out.println("Please enter the name of the file you would like to save to");
			String fileName = sc.nextLine();

			if (!verify.doesPathExists(fileName)) { // Makes sure there is no file at that path
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
				out.writeObject(rc);
				System.out.println("Current State Successfully Saved");
				
				out.close();
			} else
				System.out.println("This file already exists, please select another");

		} catch (IOException e) {
			System.out.println("Oops, something went wrong");
			System.out.println("Please check the selected path and try again");
		}

	}

	public RailCipher load() throws Exception {
		RailCipher rc;
		try {
			sc = new Scanner(System.in);
			System.out.println("Please enter the name of the file you would like to load from");
			String fileName = sc.nextLine();

			if (verify.doesPathExists(fileName)) { // Makes sure the file path is correct
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(fileName)));
				rc = (RailCipher) in.readObject();

				in.close();

				System.out.println();
				System.out.println("Previous Save State Successfully Loaded");
				
				return rc;
			} else {
				System.out.println("This file does not exist, please select another");
			}

		} catch (StreamCorruptedException e) {
			System.out.println();
			System.out.println("The selected file is not a previous save state");
			System.out.println("Please make sure that the selected file is a previous save state and try again");
		} catch (IOException e) {
			System.out.println();
			System.out.println("Oops, something went wrong.");
			System.out.println("Please make sure that the selected file is a previous save state and try again");
		}
		return null;

	}
}
