package ie.gmit.dip;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class InputText {
	private Scanner sc;

	public InputText() {
		sc = new Scanner(System.in);
	}

	public void setUserText(String sourceFile) throws Exception {
		System.out.println();
		System.out.println("Please enter the text you would like to use with the Rail Fence");
		System.out.println("NOTE: Only one line can be encrypted");
		System.out.println();

		FileWriter fw = new FileWriter(new File(sourceFile));
		fw.write(sc.nextLine());

		fw.flush();
		fw.close();

		System.out.println("Text set successfully!");
	}

}
