package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class GradeTest {

	@Test
	public void test_constructor_gradeAsDouble() {
		Grade g = new Grade(12.34, 50, .5);
		double actualValue = g.getWeightedPercentageGrade();
		double expectedValue = 12.34;
		assertEquals("Tested constructor with 12.34 as grade, 50 as max value and .5 weight", expectedValue, actualValue, 0.00001);
	}
	
	@Test
	public void test_constructor_gradeAsString_validGrade() {
		Grade g;
		try {
			g = new Grade("12.34", 50, .5);
			double actualValue = g.getWeightedPercentageGrade();
			double expectedValue = 12.34;
			assertEquals("Tested constructor with 12.34 as grade, 50 as max value and .5 weight", expectedValue, actualValue, 0.00001);
		} catch (InvalidGradeException e) {
			fail("Did not expect error for grade '12.34' but got error " + e.getMessage());
		}
	}

	@Test
	public void test_constructor_gradeAsString_nonNumericValueInGrade() {
		try {
			Grade g = new Grade("1a2.34", 50, .5);
			fail("Expected an InvalidGradeExcption thrown for grade '1a2.34'");
		} catch (InvalidGradeException e) {
			// do nothing, test passsed
		}
	}

	@Test
	public void test_constructor_gradeAsString_twoDecimalPoints() {
		try {
			Grade g = new Grade("12.34.56", 50, .5);
			fail("Expected an InvalidGradeExcption thrown for grade '1a2.34'");
		} catch (InvalidGradeException e) {
			// do nothing, test passsed
		}
	}

	@Test
	public void test_constructor_gradeAsString_tooLarge() {
		try {
			Grade g = new Grade("51", 50, .5);
			fail("Expected an InvalidGradeException thrown for grade '51' when max value is 50");
		} catch (InvalidGradeException e) {
			// do nothing, test passsed
		}
	}

}
