package entity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AbstractCarriageTest {

	@DataProvider
	public static Object[][] positiveData() {
		return new Object[][]{
				{new PassengerCar(Comfort.GENERAL)},
				{new LuggageCar()},
				{new FreightCar()}};
	}

	@DataProvider
	public static Object[][] negativeData() {
		var passengerCar = new PassengerCar(Comfort.GENERAL);
		passengerCar.setNumberOfPassengers(passengerCar.getMaxCapacity());
		var luggageCar = new LuggageCar();
		luggageCar.setNumberOfLuggage(luggageCar.getMaxCapacity());
		var freightCar = new FreightCar();
		freightCar.setNumberOfCargo(freightCar.getMaxCapacity());
		return new Object[][]{{passengerCar}, {luggageCar}, {freightCar}};
	}

	@Test(dataProvider = "positiveData")
	public void loadCarTest(AbstractCarriage car) {
		int expected = 1;
		car.loadCar();
		Assert.assertEquals(expected, car.getCurrentLoad());
	}

	@Test(dataProvider = "negativeData")
	public void loadCarNegativeTest(AbstractCarriage car) {
		Assert.assertFalse(car.loadCar());
	}
}
