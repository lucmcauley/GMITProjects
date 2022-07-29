import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CombinationTest {
	public static InsuranceProgramRefactored insuranceProgram;

	@BeforeAll
	public static void announce() {
		System.out.println("Combination Test Start");
		System.out.println("*************************");
	}

	@BeforeEach
	public void start() { // As all the methods are static in the java file this actually does nothing
		insuranceProgram = new InsuranceProgramRefactored();
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 3, 5 })
	public void twentyOneVaryAccidentsPass(int nbrAccidents) { // age of 21 with varying number of accidents between 0
																// and 5 which should get a quote
		assertEquals(InsuranceProgramRefactored.setSurcharges(21, nbrAccidents), true);
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 6, 7 })
	public void twentyOneVaryAccidentsFail(int nbrAccidents) {// age of 21 with varying number of accidents below 0 and
																// above 5 which should fail to get a quote
		assertEquals(InsuranceProgramRefactored.setSurcharges(21, nbrAccidents), false);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 5 })
	public void twentyFiveVaryAccidentsPass(int numberOfAccidents) { // age of 25 with varying number of accidents
																		// between 0 and 5 which should get a quote
		assertEquals(InsuranceProgramRefactored.setSurcharges(25, numberOfAccidents), true);
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 6, 7 })
	public void twentyFiveVaryAccidentsFail(int numberOfAccidents) {// age of 25 with varying number of accidents below
																	// 0 and above 5 which should fail to get a quote
		assertEquals(InsuranceProgramRefactored.setSurcharges(25, numberOfAccidents), false);
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 24, 25, 26 })
	public void noAccidentsVaryAge(int age) {// No accidents with varying age, should return a quote
		assertEquals(InsuranceProgramRefactored.setSurcharges(age, 0), true);
	}

	@Test
	public void noAccidentsNegativeAge() { // No accidents with negative age which should return true as age below 25
		assertEquals(InsuranceProgramRefactored.setSurcharges(-1, 0), true);
	}

	@AfterAll
	public static void endTest() {
		System.out.println("Test Complete");
	}

}
