import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	CombinationTest.class, //Tests a number of combinations of age and accident numbers
	CalculationTest.class //Ensures that the calculated quote is the correct one for inputted age and accidents
})

class RunnerTest {

}
