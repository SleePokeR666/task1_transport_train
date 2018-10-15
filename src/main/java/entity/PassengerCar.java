package entity;

/**
 * The {@code PassengerCar} class represents a railway car with passengers.
 *
 * @author Denis Sinkevich
 * @see AbstractCarriage
 */
public class PassengerCar extends AbstractCarriage {

	/**
	 * The value contains current {@code int} number of passengers in the car.
	 */
	private int numberOfPassengers;

	/**
	 * Empty constructor.
	 */
	public PassengerCar() {

	}

	/**
	 * Initializes {@code PassengerCar} with default values for a given {@code Comfort} type.
	 *
	 * @param comfort type of car comfort.
	 */
	public PassengerCar(Comfort comfort) {
		super(comfort);
		numberOfPassengers = 0;
	}

	@Override
	public int getCurrentLoad() {
		return numberOfPassengers;
	}

	@Override
	public boolean loadCar() {
		if (numberOfPassengers < getMaxCapacity()) {
			numberOfPassengers++;
			return true;
		}
		return false;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
}
