package ie.gmit.dip;

public class Cipher {
	private char[][] textArray = null;
	private RailCipher rc = new RailCipher(null, null, null, null, null, null, null, null);

	private void getArraySize(String s, int[] encryptionKeys) {
		this.textArray = new char[encryptionKeys[0]][s.length()];
	}

	private void doArrayDiagonally(String s, int[] encryptionKeys, boolean decrypt) {
		StringBuilder sb = new StringBuilder();
		int row = encryptionKeys[1];
		boolean ascending = true;

		for (int col = 0; col < s.length(); col++) {
			if (row == 0) // Test to see if the value ascends or descends in the rows
				ascending = true;
			else if (row == (encryptionKeys[0] - 1))
				ascending = false;

			int c = (int) textArray[row][col];

			if (decrypt) { // Only for outputting the decrypted text
				if (c != 0)
					sb.append((char) c);
			} else { // Fill array diagonally with string
				this.textArray[row][col] = s.charAt(col);
			}

			row = ascending ? row + 1 : row - 1;
		}

		rc.setRailFence(textArray);
		rc.setOutputText(sb.toString());
	}

	public void doArrayRows(String s, boolean overwrite) {
		StringBuilder sb = new StringBuilder();
		int i = 0;

		for (int row = 0; row < textArray.length; row++) {
			for (int col = 0; col < textArray[0].length; col++) {
				int c = (int) textArray[row][col];
				if (c != 0) {
					if (overwrite) { // Used for decryption, replaces temp values with correct ones
						this.textArray[row][col] = s.charAt(i);
						i++;
					} else
						sb.append((char) c);
				}
			}
		}

		rc.setOutputText(sb.toString());
	}

	private void encrypt(String s, int[] encryptionKeys) {
		doArrayDiagonally(s, encryptionKeys, false); // Fills the array diagonally with the String

		doArrayRows(s, false); // Goes row by row adding the letters to a StringBuilder for output

		rc.setEncryptionStatus("encrypted");
	}

	private void decrypt(String s, int[] encryptionKeys) {
		doArrayDiagonally(s, encryptionKeys, false);// Fills the array with the string as placeholder values

		doArrayRows(s, true); // Goes row by row changing placeholder for the cipher values

		doArrayDiagonally(s, encryptionKeys, true); // Goes through diagonally adding letters to String Builder for
													// output

		rc.setEncryptionStatus("decrypted");
	}

	public RailCipher start(RailCipher incomingRc, boolean encrypt) {
		this.rc = incomingRc;
		getArraySize(rc.getInputText(), rc.getEncryptionKeys());

		if (encrypt)
			encrypt(rc.getInputText(), rc.getEncryptionKeys());
		else
			decrypt(rc.getInputText(), rc.getEncryptionKeys());

		return rc;
	}
}
