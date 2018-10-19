import entity.AbstractCarriage;
import entity.Comfort;
import entity.LuggageCar;
import entity.PassengerCar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

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

	@DataProvider
	public static Object[][] sortTrainData() {
		List<AbstractCarriage> data = new ArrayList<>();

		PassengerCar passengerCar = new PassengerCar(Comfort.SLEEPING);
		passengerCar.setNumberOfPassengers(10);
		data.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.COUPE);
		passengerCar.setNumberOfPassengers(30);
		data.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.PLACKART);
		passengerCar.setNumberOfPassengers(20);
		data.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.SEATING);
		passengerCar.setNumberOfPassengers(25);
		data.add(passengerCar);

		passengerCar = new PassengerCar(Comfort.GENERAL);
		passengerCar.setNumberOfPassengers(50);
		data.add(passengerCar);

		LuggageCar luggageCar = new LuggageCar();
		luggageCar.setNumberOfLuggage(10);
		data.add(luggageCar);

		luggageCar = new LuggageCar();
		luggageCar.setNumberOfLuggage(100);
		data.add(luggageCar);
		return new Object[][]{{data}};
	}

	@Test
	public void createTrainFromJsonTest() {
		List<AbstractCarriage> tested = RailwayOperations.
				createTrain("src/test/resources/railway_operations_test.json");
		assertEquals(tested, train);
	}

	@Test
	public void countTotalNumberOfPassengersTest() {
		int tested = RailwayOperations.countTotalNumberOfPassengers(train);
		assertEquals(tested, 135);
	}

	@Test
	public void countTotalNumberOfLuggageTest() {
		int tested = RailwayOperations.countTotalNumberOfLuggage(train);
		assertEquals(tested, 110);
	}

	@Test(dataProvider = "sortTrainData")
	public void sortTrainByComfortTest(List<AbstractCarriage> expected) {
		RailwayOperations.sortTrainByComfort(train);
		assertEquals(train, expected);
	}

	@Test(dataProvider = "rangeTestData")
	public void findCarsWithPassengersWithinRangeTest(List<AbstractCarriage> expected,
													  int min, int max) {
		List<AbstractCarriage> tested = RailwayOperations.
				findCarsWithPassengersWithinRange(train, min, max);

		assertEquals(tested, expected);
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
