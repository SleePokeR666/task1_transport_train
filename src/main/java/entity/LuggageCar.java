package entity;

/**
 * The {@code LuggageCar} class represents a railway car with luggage.
 *
 * @author Denis Sinkevich
 * @see AbstractCarriage
 */
public class LuggageCar extends AbstractCarriage {

	/**
	 * The value contains current {@code int} number of luggage in the car.
	 */
	private int numberOfLuggage;

	/**
	 * Initializes {@code LuggageCar} with default values.
	 */
	public LuggageCar() {
		super(Comfort.LUGGAGE);
		numberOfLuggage = 0;
	}

	@Override
	public int getCurrentLoad() {
		return numberOfLuggage;
	}

	@Override
	public boolean loadCar() {
		if (numberOfLuggage < getMaxCapacity()) {
			numberOfLuggage++;
			return true;
		}
		return false;
	}

	public int getNumberOfLuggage() {
		return numberOfLuggage;
	}

	public void setNumberOfLuggage(int numberOfLuggage) {
		this.numberOfLuggage = numberOfLuggage;
	}
}
