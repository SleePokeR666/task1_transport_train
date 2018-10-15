package util;

import entity.Comfort;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilTest {

	@Test(invocationCount = 100)
	public void getRandomIntTest() {
		int tested = Randomizer.getRandomInt(2);
		Assert.assertTrue(tested >= 0 && tested < 2);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void getRandomIntExceptionTest() {
		Randomizer.getRandomInt(-1);
	}

	@Test(invocationCount = 100)
	public void chooseRandomComfortTest() {
		Comfort testedComfort = Randomizer.chooseRandomComfort();
		switch (testedComfort) {
			case LUGGAGE:
			case FREIGHT:
				Assert.fail();
				break;
			default:
				Assert.assertTrue(true);
		}
	}
}
