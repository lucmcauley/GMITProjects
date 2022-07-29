package ie.gmit.dip;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Verification {

	public boolean canProceed(RailCipher rc) {
		boolean check = true;

		if (rc.getEncryptionKeys() == null) { // Checks encryption keys are set
			System.out.println();
			System.out.println("Please enter the Encryption Keys to continue");
			check = false;
		}

		if (rc.getSource() == null) { // Checks there is a source to cipher set
			System.out.println();
			System.out.println("Please enter the source text to continue");
			check = false;
		}

		if (rc.getOutputFile() == null) { // Checks there is an output file set
			System.out.println();
			System.out.println("Please enter the output file to continue");
			check = false;
		}

		if (check == false) // One or more failed tests
			return false;
		else if (check == true) // All tests passed
			return true;
		else {
			System.out.println();
			System.out.println("Oops, something went wrong, please try again");
			System.out.println();
			return false;
		}
	}

	public boolean checkEncryptionKeys(int[] encryptionKeys) {

		boolean check = true;

		if (encryptionKeys[0] <= 1) { // Checks the encryption key is positive and greater than 1
			System.out.println();
			System.out.println("Please make sure that the encryption key is a greater than 1");
			check = false;
		}

		if (encryptionKeys[1] >= encryptionKeys[0] || encryptionKeys[1] < 0) { // Checks the offset is valid
			System.out.println();
			System.out.println("Please make sure the offset is greater than 0 and less than the encryption key");
			check = false;
		}

		if (check == true)
			return true;
		else if (check == false)
			return false;
		else {
			System.out.println();
			System.out.println("Oops, something went wrong, please try again");
			System.out.println();
			return false;
		}
	}

	// Adapted from:
	// https://alvinalexander.com/java/java-file-exists-directory-exists/#:~:text=To%20test%20to%20see%20if,directory%20exists%2C%20and%20false%20otherwise.
	public boolean doesPathExists(String file) {
		File tempFile = new File(file);
		boolean exists = tempFile.exists(); // Checks that the path to the file exists

		return exists;
	}

	// Adapted from:
	// https://stackoverflow.com/questions/18134718/java-quickest-way-to-check-if-url-exists/18135030
	public boolean doesUrlConnect(String source) {
		try {
			System.out.println("Connecting...");
			
			int responseCode;
			final URL url = new URL(source);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			responseCode = huc.getResponseCode();

			if (responseCode == 200) { // OK response code
				return true;
			} else {
				System.out.println();
				System.out.println("HTTP error code: " + responseCode);
				System.out.println("Please check that the URL is formatted correctly and is still live");
				System.out.println();
			}

		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("Please check that the URL is formatted correctly and is still live");
			System.out.println();
		} catch (IOException e) {
			System.out.println();
			System.out.println("Oops, something went wrong");
			System.out.println("Please check that the URL is formatted correctly and is still live");
			System.out.println();
		}

		return false;
	}

}
