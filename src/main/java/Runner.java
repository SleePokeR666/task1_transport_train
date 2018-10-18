import entity.AbstractCarriage;
import entity.Comfort;

import java.util.List;

/**
 * Main class of a task. Four methods in the main() method represent different parts of the task.
 */
public class Runner {

	/**
	 * Starting point of a program.
	 *
	 * @param args arguments of a program.
	 */
	public static void main(String[] args) {
		List<AbstractCarriage> train = createTrain(args);

		countTotalNumberOfLuggageAndPassengers(train);

		sortTrainByComfort(train);

		findCarsByPassengersWithinRange(train);
	}

	/**
	 * First part of a task. Creates and initializes the train as {@code List}
	 * of {@link AbstractCarriage} objects. If the first argument of a program
	 * arguments equals to {@code String} "json" train is created from a json
	 * file. In other case, train is always created by using random variables.
	 *
	 * @param args program arguments. Define the creation way of the train.
	 * @return fully initialized train.
	 */
	private static List<AbstractCarriage> createTrain(String[] args) {
		List<AbstractCarriage> train;
		if (args.length == 1 && args[0].equals("-json")) {
			String jsonFileName = "src/main/resources/train_example.json";
			train = RailwayOperations.createTrain(jsonFileName);
		} else {
			int numberOfPassengerCars = 10;
			train = RailwayOperations.createTrain(numberOfPassengerCars);
		}
		return train;
	}

	/**
	 * Second part of a task. Counts total number of passengers and luggage in the train.
	 * Prints the result.
	 *
	 * @param train {@code List} of different cars.
	 */
	private static void countTotalNumberOfLuggageAndPassengers(List<AbstractCarriage> train) {
		RailwayOperations.countTotalNumberOfPassengers(train);

		RailwayOperations.countTotalNumberOfLuggage(train);
	}

	/**
	 * Third part of task. Sorts the train by natural order of the cars.
	 *
	 * @param train {@code List} of different cars.
	 */
	private static void sortTrainByComfort(List<AbstractCarriage> train) {
		RailwayOperations.sortTrainByComfort(train);
	}

	/**
	 * Fourth part of a task. Finds all the {@code PassengerCars} in the train, where current
	 * number of passenger is more and equal of the minimum {@code int} value and less and equal
	 * of the maximum {@code int} value.
	 *
	 * @param train {@code List} of different cars.
	 */
	private static void findCarsByPassengersWithinRange(List<AbstractCarriage> train) {
		int min = Comfort.SLEEPING.getMaxCapacity() / 2;
		int max = Comfort.SLEEPING.getMaxCapacity() + min;
		RailwayOperations.findCarsWithPassengersWithinRange(train, min, max);
	}
}
