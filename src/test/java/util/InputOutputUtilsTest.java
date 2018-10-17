package util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InputOutputUtilsTest {

	@Test
	public void readFileTest() {
		String expected = InOutTestData.JSON_FILE_CONTENT;

		String tested = InputOutputUtils.readFile("src/test/resources/train_test.json");

		Assert.assertEquals(tested, expected);
	}

	@Test
	public void readFileExceptionTest() {
		InputOutputUtils.readFile("src/test/resources/train_not_test.json");
	}
}
