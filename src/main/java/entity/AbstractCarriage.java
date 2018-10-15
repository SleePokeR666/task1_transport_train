package entity;

import java.util.Objects;

/**
 * The {@code AbstractCarriage} class is a parent class for different types of the railway cars.
 * Contains common fields and abstract methods, which should be implemented in inherited classes.
 *
 * @author Denis Sinkevich
 * @see PassengerCar
 * @see LuggageCar
 * @see FreightCar
 */

public abstract class AbstractCarriage implements Comparable<AbstractCarriage> {

	/**
	 * The value contains {@link Comfort} type of a car. It is used as a parameter for
	 * sorting a collection of {@code AbstractCarriage} objects in a natural order.
	 */
	private Comfort comfort;

	/**
	 * The value contains name of a car. It is defined by {@code Comfort} type by default.
	 */
	private String name;

	/**
	 * The value contains maximum capacity of a car. It is defined by {@code Comfort} type
	 * by default. For different cars capacity can define different entities.
	 * I.e. passengers for a {@link PassengerCar}, or luggage for a {@link LuggageCar}.
	 */
	private int maxCapacity;

	/**
	 * Empty constructor.
	 */
	public AbstractCarriage() {

	}

	/**
	 * Initializes car with default values for a given {@code Comfort} type.
	 *
	 * @param comfort type of car comfort.
	 */
	AbstractCarriage(Comfort comfort) {
		this.comfort = comfort;
		name = comfort.getName();
		maxCapacity = comfort.getMaxCapacity();
	}

	/**
	 * The method returns current load of car. For different cars capacity can define different
	 * entities. I.e. passengers for a {@link PassengerCar}, or luggage for a {@link LuggageCar}.
	 *
	 * @return {@code int} value of current load of a car
	 */
	public abstract int getCurrentLoad();

	/**
	 * The method increases current load of a car by one unit.
	 *
	 * @return {@code boolean} true if operation is successful.
	 * {@code boolean} false if current load of a car equals maximum capacity.
	 */
	public abstract boolean loadCar();

	@Override
	public int compareTo(AbstractCarriage o) {
		return comfort.compareTo(o.comfort);
	}

	public Comfort getComfort() {
		return comfort;
	}

	public void setComfort(Comfort comfort) {
		this.comfort = comfort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	@Override
	public String toString() {
		return String.format("%s. Вместимость %d мест. Занято %d мест.",
				name, maxCapacity, getCurrentLoad());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractCarriage carriage = (AbstractCarriage) o;
		return comfort == carriage.comfort &&
				getCurrentLoad() == carriage.getCurrentLoad();
	}

	@Override
	public int hashCode() {
		return Objects.hash(comfort, getCurrentLoad());
	}
}
