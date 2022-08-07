import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculationTest {
	public static InsuranceProgramRefactored insuranceProgram;

	@BeforeEach
	public void start() { // As all the methods are static in the java file this actually does nothing
		insuranceProgram = new InsuranceProgramRefactored();
	}

	@ParameterizedTest
	@CsvSource({ "1 , 50", "2 , 125", "3 , 225", "4 , 375", "5 , 575" })
	public void checkAccidentSurcharge(int numberOfAccidents, int surcharge) { // checks that the accident surcharge is
																				// the one expected
		assertEquals(InsuranceProgramRefactored.setAccidentSurcharge(numberOfAccidents), surcharge);
	}

	@ParameterizedTest
	@CsvSource({ "0 , 100", "1 , 100", "24 , 100", "25 , 0", "26 , 0" }) // checks that the age surcharge is the one
																			// expected
	public void checkAgeSurcharge(int age, int result) {
		assertEquals(InsuranceProgramRefactored.setAgeSurcharge(age), result);
	}

	@ParameterizedTest
	@CsvSource({ "-1, false", "0, true", "1, true", "5, true", "6, false", "7, false" })
	public void checkEligibility(int numberOfAccidents, boolean result) {// checks that the number of baccidents returns
																			// the expected eligibility for a quote
		assertEquals(InsuranceProgramRefactored.isEligible(numberOfAccidents), result);
	}

	@ParameterizedTest
	@CsvSource({ "21, 0, 600", " 21, 3, 825", "21, 5, 1175", "21, -1, -9999", "21, 7, -9999", "25, 0, 500",
			"25, 3, 725", "25, 5, 1075", "25, -1, -9999", "25, 7, -9999" })
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	public void checkTotal(int age, int numberOfAccidents, int expectedQuote) {// checks combination of age and accident
																				// outputs the expected quote
		assertEquals(InsuranceProgramRefactored.start(age, numberOfAccidents), expectedQuote);
	}

	@Test
	public void ArithmeticExceptionTest() { // One of the two exception tests required
		assertThrows(ArithmeticException.class, () -> {
			InsuranceProgramRefactored.finalStatement(1 / 0, 3);
		});
	}

	@Test
	public void IndexOutOfBoundsTest() { // One of the two exception tests required
		assertThrows(IndexOutOfBoundsException.class, () -> {
			int numbers[] = { 1, 2 };
			for (int i = 0; i <= numbers.length; i++) {
				int j = numbers[i];
			}
		});
	}

	@AfterEach
	public void separator() {
		System.out.println("********************************");
	}

}
