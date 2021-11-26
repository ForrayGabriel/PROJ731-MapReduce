package projet;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private static Logger logger;

	private Logger() {
	}

	public static Logger getInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	public void write(String str) {
		System.out.println(str);
	}

}
