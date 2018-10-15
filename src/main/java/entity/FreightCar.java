package entity;

/**
 * The {@code FreightCar} class represents a railway car with cargo.
 *
 * @author Denis Sinkevich
 * @see AbstractCarriage
 */
public class FreightCar extends AbstractCarriage {

	/**
	 * The value contains current {@code int} number of cargo in the car.
	 */
	private int numberOfCargo;

	/**
	 * Initializes {@code FreightCar} with default values.
	 */
	public FreightCar() {
		super(Comfort.FREIGHT);
		numberOfCargo = 0;
	}

	@Override
	public int getCurrentLoad() {
		return numberOfCargo;
	}

	@Override
	public boolean loadCar() {
		if (numberOfCargo < getMaxCapacity()) {
			numberOfCargo++;
			return true;
		}
		return false;
	}

	public int getNumberOfCargo() {
		return numberOfCargo;
	}

	public void setNumberOfCargo(int numberOfCargo) {
		this.numberOfCargo = numberOfCargo;
	}
}
