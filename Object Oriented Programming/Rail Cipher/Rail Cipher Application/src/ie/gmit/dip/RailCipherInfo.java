package ie.gmit.dip;

import java.util.Arrays;

public class RailCipherInfo {

	// Used to display the RailFence
	public void displayRailFence(RailCipher rc) {
		char[][] railFence = rc.getRailFence();
		String outputText = rc.getOutputText();

		System.out.println();
		System.out.println("Most recent Rail Fence: ");

		try {
			for (int i = 0; i < railFence.length; i++)
				System.out.println(Arrays.toString(railFence[i]));
		} catch (Exception NullPointerException) {
			System.out.println("There is no Rail Fence"); // No Rail Cipher with no Encryption Keys
		}

		if (railFence != null) {
			System.out.println();
			System.out.println("The most recent " + rc.getEncryptionStatus() + " text from this rail fence is");
			System.out.println(outputText);
			System.out.println();
		}
	}

	// Used to show all of the information about the rail cipher
	public void getRailCipherInfo(RailCipher rc) {
		try {
			System.out.println();
			System.out.println("Below is the information of the Rail Cipher");
			System.out.println();
			System.out.println("The selected input source is: " + rc.getSource());
			System.out.println("The selected input source was a " + rc.getSourceType());
			System.out.println("The selected output file is : " + rc.getOutputFile());
			System.out.println("The encryption status of the most recent output text is " + rc.getEncryptionStatus());
			System.out.println("The most recent input text is: " + rc.getInputText());
			System.out.println("The encryption parameters are a key of " + rc.getEncryptionKeys()[0]
					+ " and an offset of " + rc.getEncryptionKeys()[1]);
			displayRailFence(rc);
		} catch (Exception NullPointerException) {// No encryption keys there can be no Rail Cipher
			System.out.println("There are no Encryption Keys or Rail Fence"); 
		}

	}
}
