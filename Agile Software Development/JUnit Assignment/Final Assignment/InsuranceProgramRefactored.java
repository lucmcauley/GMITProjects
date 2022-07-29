import java.util.Scanner;


public class InsuranceProgramRefactored {
	private static Scanner input = new Scanner(System.in);
	private static int accidentSurcharge;
	private static int ageSurcharge;
	private static int basicInsurance = 500;
	
	public static void menu() {
		start(setAge(), getNumberOfAccidents());
	}
	
	public static int setAge() {
		int age;
		do {
			System.out.print("Enter your age: ");
			age = input.nextInt();
		} while (age < 0);
		return age;
	}
	
	public static int getNumberOfAccidents(){
		int nmbrAccidents;
		do {
			System.out.print("How many accidents did you have? ");
			nmbrAccidents = input.nextInt();
		} while (nmbrAccidents < 0);
		return nmbrAccidents;
	}

	public static int start(int age, int nmbrAccidents) {

		if (setSurcharges(age, nmbrAccidents)) {

			return finalStatement(accidentSurcharge, ageSurcharge);
		} else {

			return -9999; // end-user will never see this value but it is bad form to use, although in
							// this case there is no possibility that the value could be below 500
		}

	}

	public static boolean setSurcharges(int age, int nmbrAccidents) {
		if (isEligible(nmbrAccidents)) {
			setAgeSurcharge(age);
			setAccidentSurcharge(nmbrAccidents);
			return true;
		} else {
			System.out.println("No Insurance");
			return false;
		}

	}

	public static boolean isEligible(int nmbrAccidents) {
		if (nmbrAccidents < 6 && nmbrAccidents >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int setAgeSurcharge(int age) {
		if (age < 25) {
			ageSurcharge = 100;
			System.out.println("Additional age surcharge " + ageSurcharge);
			return ageSurcharge;
		} else {
			ageSurcharge = 0;
			System.out.println("No additional age surcharge");
			return ageSurcharge;
		}
	}

	public static int setAccidentSurcharge(int nmbrAccidents) {
		switch (nmbrAccidents) {
		case 0:
			accidentSurcharge = 0;
			System.out.println("No accident surcharge");
			return accidentSurcharge;
		case 1:
			accidentSurcharge = 50;
			break;
		case 2:
			accidentSurcharge = 125;
			break;
		case 3:
			accidentSurcharge = 225;
			break;
		case 4:
			accidentSurcharge = 375;
			break;
		case 5:
			accidentSurcharge = 575;
			break;
		}
		System.out.println("Additional surcharge for accident: " + accidentSurcharge);
		return accidentSurcharge;
	}

	public static int finalStatement(int accidentSurcharge, int ageSurcharge) {
		int total = basicInsurance + accidentSurcharge + ageSurcharge;
		String output = "Total amount to pay: " + total;
		System.out.println(output);
		return total;
	}
	
	
    public static void main(String[] args) {
    	menu();
    }
        
        
}