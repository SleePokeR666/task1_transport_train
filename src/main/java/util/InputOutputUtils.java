package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for different input and output operations.
 *
 * @author Denis Sinkevich
 */
public class InputOutputUtils {

	/**
	 * The value contains logger.
	 */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Reads the file and returns full containment as {@code String}.
	 *
	 * @param fileName full path to file.
	 * @return {@code String} value with full file containment.
	 */
	public static String readFile(String fileName) {
		StringBuilder result = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while (reader.ready()) {
				result.append(reader.readLine());
			}
		} catch (FileNotFoundException exception) {
			LOG.error("Файл не найден!", exception);
		} catch (IOException exception) {
			LOG.error("Ошибка чтения файла!", exception);
		}
		return result.toString();
	}

}
