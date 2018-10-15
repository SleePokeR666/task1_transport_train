package entity;

/**
 * The {@code Comfort} class represents all available types of {@link AbstractCarriage} comforts.
 *
 * @author Denis Sinkevich
 * @see AbstractCarriage
 * @see PassengerCar
 */

public enum Comfort {

	/**
	 * Represents sleeping car type of comfort.
	 */
	SLEEPING("Спальный вагон", 18),

	/**
	 * Represents compartment car type of comfort.
	 */
	COUPE("Купированный вагон", 36),

	/**
	 * Represents cheaper compartment car type of comfort.
	 */
	PLACKART("Плацкартный вагон", 54),

	/**
	 * Represents seating car type of comfort.
	 */
	SEATING("Сидячий вагон", 62),

	/**
	 * Represents general car type of comfort.
	 */
	GENERAL("Общий вагон", 81),

	/**
	 * Represents luggage car comfort.
	 */
	LUGGAGE("Багажный вагон", 100),

	/**
	 * Represents freight car type of comfort.
	 */
	FREIGHT("Товарный вагон", 1000);

	/**
	 * The value contains full name of a comfort.
	 */
	private String name;

	/**
	 * Stores the default maximum number of passenger for a given type of comfort.
	 */
	private int maxCapacity;

	/**
	 * Initializes a {@code Comfort} type with default values.
	 *
	 * @param name        The name of comfort
	 * @param maxCapacity The maximum number of passengers
	 */
	Comfort(String name, int maxCapacity) {
		this.name = name;
		this.maxCapacity = maxCapacity;
	}

	public String getName() {
		return name;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

}
