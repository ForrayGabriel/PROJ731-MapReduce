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
		try {
			FileWriter myWriter = new FileWriter("filename.txt");
			myWriter.write(str);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
