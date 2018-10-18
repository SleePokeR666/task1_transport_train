import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.AbstractCarriage;
import entity.AbstractCarriageAdapter;
import entity.LuggageCar;
import entity.PassengerCar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.InputOutputUtils;
import util.Randomizer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code RailwayOperation} class provides the main logic of a task.
 *
 * @author Denis Sinkevich
 */
class RailwayOperations {

	/**
	 * The value contains logger.
	 */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Method creates a train as {@code List} of {@link AbstractCarriage} objects.
	 * The length of a list will not equal to the numberOfPassengerCars because
	 * algorithm also adds {@code LuggageCars} for passengers's luggage.
	 *
	 * @param numberOfPassengerCars {@code int} number of {@code PassengerCars} to create.
	 * @return {@code List} of {@code AbstractCarriage} objects
	 */
	static List<AbstractCarriage> createTrain(int numberOfPassengerCars) {
		List<AbstractCarriage> train = new ArrayList<>();
		var luggageCar = new LuggageCar();
		//first cycle creates given number of PassengerCars
		for (int i = 0; i < numberOfPassengerCars; i++) {
			var passengerCar = new PassengerCar(Randomizer.chooseRandomComfort());
			int maxCapacity = passengerCar.getMaxCapacity();
			//randomly defines number of passengers in each car
			int numberOfPassengers = Randomizer.getRandomInt(maxCapacity + 1);
			//second cycle fills the car with passengers one by one because
			//we define for each passenger does he have the luggage
			for (int j = 0; j < numberOfPassengers; j++) {
				passengerCar.loadCar();
				if (hasPassengerLuggage()) {
					//checks for a maximum load, if true we add full LuggageCar to the train
					//and create the new one
					if (!luggageCar.loadCar()) {
						LOG.info("{} загружен. Количество багажа: {}.",
								luggageCar.getName(), luggageCar.getNumberOfLuggage());
						train.add(luggageCar);
						luggageCar = new LuggageCar();
					}
				}
			}
			LOG.info("{} пассажиров сели в {}.",
					passengerCar.getNumberOfPassengers(), passengerCar.getName());
			train.add(passengerCar);
		}
		train.add(luggageCar);
		LOG.info("Поезд успешно создан и готов к отправлению! " +
				"Количество вагонов: {}.", train.size());
		return train;
	}

	/**
	 * Method creates a train as {@code List} of {@link AbstractCarriage} objects from
	 * file in json format.
	 *
	 * @param jsonFileName full path to json file.
	 * @return {@code List} of {@code AbstractCarriage} objects.
	 */
	static List<AbstractCarriage> createTrain(String jsonFileName) {
		String json = InputOutputUtils.readFile(jsonFileName);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type collectionType = new TypeToken<ArrayList<AbstractCarriage>>() {
		}.getType();
		gsonBuilder.registerTypeAdapter(AbstractCarriage.class, new AbstractCarriageAdapter());
		Gson gson = gsonBuilder.create();
		List<AbstractCarriage> train = gson.fromJson(json, collectionType);
		printTrain(train);
		LOG.info("Поезд успешно создан и готов к отправлению! " +
				"Количество вагонов: {}.", train.size());
		return train;
	}

	/**
	 * Method logs current state of the train.
	 *
	 * @param train {@code List} of {@code AbstractCarriage} objects.
	 */
	private static void printTrain(List<AbstractCarriage> train) {
		for (AbstractCarriage railwayCarriage : train) {
			LOG.info(railwayCarriage);
		}
	}

	/**
	 * Method is used to define that passenger has luggage.
	 *
	 * @return {@code true} if passenger has luggage, {@code false} if hasn't.
	 */
	private static boolean hasPassengerLuggage() {
		return Randomizer.getRandomInt(2) == 1;
	}

	/**
	 * The method counts total number of passengers in the train.
	 *
	 * @param train the list of cars, where we count passengers.
	 * @return {@code int} value of total passengers on the train.
	 */
	static int countTotalNumberOfPassengers(List<AbstractCarriage> train) {
		int total = 0;
		for (AbstractCarriage railwayCarriage : train) {
			if (railwayCarriage instanceof PassengerCar) {
				total += railwayCarriage.getCurrentLoad();
			}
		}
		LOG.info("Общее количество пассижиров поезда: {}.", total);
		return total;
	}

	/**
	 * The method counts total number of luggage in the train.
	 *
	 * @param train the list of cars, where we count luggage.
	 * @return {@code int} value of total luggage on the train.
	 */
	static int countTotalNumberOfLuggage(List<AbstractCarriage> train) {
		int total = 0;
		for (AbstractCarriage railwayCarriage : train) {
			if (railwayCarriage instanceof LuggageCar) {
				total += railwayCarriage.getCurrentLoad();
			}
		}
		LOG.info("Общее количество багажа в поезде: {}.", total);
		return total;
	}

	/**
	 * Sorts the train by natural order of the cars.
	 *
	 * @param train {@code List} of different cars.
	 */
	static void sortTrainByComfort(List<AbstractCarriage> train) {
		LOG.info("Поезд до сортировки!\n");
		printTrain(train);

		Collections.sort(train);

		LOG.info("Поезд после сортировки!\n");
		printTrain(train);
	}

	/**
	 * This method finds {@code PassengerCars} within given range of {@code int} numbers of
	 * passengers. Minimum and maximum values are included.
	 *
	 * @param train given list to search.
	 * @param min   {@code int} minimum value. Including the value itself.
	 * @param max   {@code int} maximum value. Including the value itself.
	 * @return new {@code list} of cars, that match condition. If there is no matches,
	 *         empty list is returned.
	 */
	static List<AbstractCarriage>
	findCarsWithPassengersWithinRange(List<AbstractCarriage> train, int min, int max) {
		LOG.info("Список вагонов, в которых пассажиров больше {} и меньше {}.\n",
				min, max);
		List<AbstractCarriage> result = new ArrayList<>();
		for (AbstractCarriage railwayCarriage : train) {
			if (railwayCarriage instanceof PassengerCar) {
				int numberOfPassengers = railwayCarriage.getCurrentLoad();
				if (numberOfPassengers >= min && numberOfPassengers <= max) {
					result.add(railwayCarriage);
				}
			}
		}
		printTrain(result);
		return result;
	}
}
