import entity.AbstractCarriage;
import entity.Comfort;
import entity.LuggageCar;
import entity.PassengerCar;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RailwayOperationsTest {

	private List<AbstractCarriage> train;

	@DataProvider
	public static Object[][] rangeTestData() {
		int min = 10;
		int max = 25;
		List<AbstractCarriage> data = new ArrayList<>();

		PassengerCar passengerCar = new PassengerCar(Comfort.PLACKART);
		passengerCar.setNumberOfPassengers(20);
		data.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.SEATING);
		passengerCar.setNumberOfPassengers(25);
		data.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.SLEEPING);
		passengerCar.setNumberOfPassengers(10);
		data.add(passengerCar);
		return new Object[][]{{data, min, max}};
	}

	@Test
	public void countTotalNumberOfPassengersTest() {
		int tested = RailwayOperations.countTotalNumberOfPassengers(train);
		Assert.assertEquals(tested, 135);
	}

	@Test
	public void countTotalNumberOfLuggageTest() {
		int tested = RailwayOperations.countTotalNumberOfLuggage(train);
		Assert.assertEquals(tested, 110);
	}

	@Test(dataProvider = "rangeTestData")
	public void findCarsWithPassengersWithinRangeTest(List<AbstractCarriage> expected,
													  int min, int max) {
		List<AbstractCarriage> tested = RailwayOperations.
				findCarsWithPassengersWithinRange(train, min, max);

		Assert.assertEquals(tested, expected);
	}

	@BeforeMethod
	public void setTrain() {
		train = new ArrayList<>();

		LuggageCar luggageCar = new LuggageCar();
		luggageCar.setNumberOfLuggage(10);
		train.add(luggageCar);

		luggageCar = new LuggageCar();
		luggageCar.setNumberOfLuggage(100);
		train.add(luggageCar);

		PassengerCar passengerCar = new PassengerCar(Comfort.GENERAL);
		passengerCar.setNumberOfPassengers(50);
		train.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.PLACKART);
		passengerCar.setNumberOfPassengers(20);
		train.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.COUPE);
		passengerCar.setNumberOfPassengers(30);
		train.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.SEATING);
		passengerCar.setNumberOfPassengers(25);
		train.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.SLEEPING);
		passengerCar.setNumberOfPassengers(10);
		train.add(passengerCar);
	}

	@AfterMethod
	public void resetTrain() {
		train = null;
	}
}
