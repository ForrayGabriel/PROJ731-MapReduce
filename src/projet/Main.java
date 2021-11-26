package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		new DragAndDropFrame();
		
		/**Logger log = Logger.getInstance();

		//Scanner for getting user input and get the file name and the number of threads
		Scanner myObj = new Scanner(System.in); // Create a Scanner object

		log.write("Enter file name");

		String fileName = myObj.nextLine();

		log.write("Enter number of Map threads");

		String nbMapThreadStr = myObj.nextLine();

		int nbMapThread = Integer.parseInt(nbMapThreadStr);

		//Make the starting times
		String timeStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		long millisStart = Calendar.getInstance().getTimeInMillis();

		log.write("Processing " + fileName + " with " + nbMapThread + " threads...");

		//Reading the file and loading it into a String
		String str = new String(Files.readAllBytes(Paths.get(fileName + ".txt")), StandardCharsets.UTF_8);

		//Split the words and put them in a list
		String[] words = str.split("\\P{L}+");
		List<String> list = Arrays.asList(words);

		//Calculate the number of words to be send to each threads
		int nb_words_per_machines = (int) list.size() / nbMapThread;

		//Create an array of array of words to send to the threads
		ArrayList<List<String>> arrays_per_machine = new ArrayList<List<String>>();

		//Split the list of words in multiple lists and add it the the main list
		for (int i = 0; i < nbMapThread - 1; i++) {
			List<String> temp_list = list.subList(i * nb_words_per_machines, (i + 1) * nb_words_per_machines);
			arrays_per_machine.add(temp_list);
		}
		List<String> temp_list = list.subList((nbMapThread - 1) * nb_words_per_machines, list.size());
		arrays_per_machine.add(temp_list);

		//Create the reducer thread and launch it
		Reduce reduce = new Reduce(nbMapThread, fileName, timeStart, millisStart);

		Thread r = new Thread(reduce);
		r.start();

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		Logger logger = Logger.getInstance();
		logger.write(timeStamp);

		//Id of the next thread to be created
		int i = 0;
		
		//For each list of words, create a thread, give it the list  and its id and launch it
		for (List<String> array : arrays_per_machine) {
			Thread t = new Thread(new Map(array, reduce, i));
			t.start();
			i++;
		}*/

	}

}
