package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(fileName), StandardCharsets.UTF_8))) {
			char[] buf = new char[1024];
			int temp;
			while ((temp = reader.read(buf)) > 0) {
				if (temp < buf.length) {
					buf = Arrays.copyOf(buf, temp);
				}
				result.append(buf);
			}
		} catch (FileNotFoundException exception) {
			LOG.error("Файл не найден!", exception);
		} catch (IOException exception) {
			LOG.error("Ошибка чтения файла!", exception);
		}
		return result.toString();
	}

}
