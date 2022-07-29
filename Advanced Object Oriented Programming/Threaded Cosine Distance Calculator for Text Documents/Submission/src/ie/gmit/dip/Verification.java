package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class containing all of the verification methods used in the program.
 * 
 * @author Luc McAuley
 *
 */
public class Verification {

	/**
	 * Verifies the incoming <code>integer</code> value is positive.
	 * 
	 * @param valueToCheck Integer to be checked.
	 * @return <code>true</code> if the value is greater than zero, otherwise
	 *         <code>false.</code>
	 */
	public boolean verifyPositiveInteger(int valueToCheck) {
		if (valueToCheck > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifies the file path is correct with a <code>BufferedReader.</code>
	 * 
	 * @param filePath Path of the file to verify.
	 * @return <code>false</code> if an error occurs with the
	 *         <code>BufferedReader</code>. Otherwise returns <code>true.</code>
	 */
	public boolean verifyFilePath(String filePath) {
		BufferedReader br;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
			br.close();
			return true;

		} catch (IOException e) {
			System.out.println("Please make sure that the file path is correct and try again");
			return false;
		}
	}
}
