package util;

import entity.Comfort;

import java.util.Random;

/**
 * Utility class that provides methods for getting random values.
 *
 * @author Denis Sinkevich
 * @see Comfort
 */
public class Randomizer {

	/**
	 * Initialise instance of {@link Random} as constant to avoid unnecessary
	 * usage of memory in loops.
	 */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * The value is used for all {@link Comfort} values storage.
	 */
	private static final Comfort[] COMFORTS = Comfort.values();

	/**
	 * The method returns {@code int} value between zero (inclusive)
	 * and {@code bound} (exclusive).
	 *
	 * @param bound the upper bound (exclusive).  Must be positive.
	 * @return the next {@code int} value between zero (inclusive)
	 *         and {@code bound} (exclusive)
	 * @throws IllegalArgumentException if bound is not positive
	 */
	public static int getRandomInt(int bound) {
		return RANDOM.nextInt(bound);
	}

	/**
	 * The method returns one randomly chosen {@code Comfort} type within
	 * {@code PassengerCar} types of comfort described in {@link Comfort} enum.
	 *
	 * @return one randomly chosen {@code Comfort} type
	 */
	public static Comfort chooseRandomComfort() {
		return COMFORTS[getRandomInt(COMFORTS.length - 2)];
	}
}
