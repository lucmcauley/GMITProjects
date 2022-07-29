package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class RailCipherManager {
	private Cipher cipher = new Cipher();

	private RailCipher encrypt(RailCipher input) throws Exception {
		return cipher.start(input, true);
	}

	private RailCipher decrypt(RailCipher input) throws Exception {
		return cipher.start(input, false);
	}

	// Used to send data from a file to encrypt or decrypt methods above
	private void fileHandler(RailCipher rc, boolean encrypt) throws Exception {

		try {
			FileWriter fw = new FileWriter(new File(rc.getOutputFile()));

			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(rc.getSource().toString()))));
			String line = null;

			while ((line = br.readLine()) != null) {
				rc.setInputText(line);
				rc = encrypt ? encrypt(rc) : decrypt(rc);
				fw.write(rc.getOutputText() + "\n");
			}

			br.close();
			fw.flush();
			fw.close();

		} catch (FileNotFoundException e) {
			System.out.println("Please check that the path set is to a file e.g. a .txt file");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Oops something went wrong");
			e.printStackTrace();
		}
	}

	// Used to send data from a file to encrypt or decrypt methods above
	private void urlHandler(RailCipher rc, boolean encrypt) throws Exception {
		URL connection = new URL(rc.getSource());

		FileWriter fw = new FileWriter(new File(rc.getOutputFile()));

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.openStream()));
		String line = null;

		while ((line = br.readLine()) != null) {
			rc.setInputText(line);
			rc = encrypt ? encrypt(rc) : decrypt(rc);
			fw.write(rc.getOutputText() + "\n");
		}

		br.close();
		fw.flush();
		fw.close();

	}

	public RailCipher start(RailCipher input, boolean encrypt) throws Exception {

		if (input.getSourceType().toString().compareTo("File") == 0
				|| input.getSourceType().toString().compareTo("User Generated File") == 0) {
			fileHandler(input, encrypt);
			System.out.println("Completed!");
			System.out.println("Output located at " + input.getOutputFile());
		} else if (input.getSourceType().toString().compareTo("URL") == 0) {
			urlHandler(input, encrypt);
			System.out.println("Completed!");
			System.out.println("Output located at " + input.getOutputFile());
		} else
			System.out.println("There was an error");
		
		return input;
	}

}
