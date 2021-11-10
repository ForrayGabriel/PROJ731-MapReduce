package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter file name");

	    String fileName = myObj.nextLine();
	    
	    System.out.println("Enter number of Map threads");

	    String nbMapThreadStr = myObj.nextLine();
	    
	    int nbMapThread = Integer.parseInt(nbMapThreadStr);

	    System.out.println("Processing "+ fileName + " with " + nbMapThread + " threads...");
	    
		//File file = new File("test.txt");
	    File file = new File(fileName);
		String str = "";
		String st;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				while ((st = br.readLine()) != null) {
					str += st;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		String[] words = str.split("\\P{L}+");
		List<String> list = Arrays.asList(words);

		int nb_words_per_machines = (int) list.size() / nbMapThread;

		System.out.println(words.length);
		System.out.println(nb_words_per_machines);

		ArrayList<List<String>> arrays_per_machine = new ArrayList<List<String>>();

		for (int i = 0; i < nbMapThread - 1; i++) {
			List<String> temp_list = list.subList(i * nb_words_per_machines, (i + 1) * nb_words_per_machines);
			arrays_per_machine.add(temp_list);
		}
		List<String> temp_list = list.subList((nbMapThread - 1) * nb_words_per_machines, list.size());
		arrays_per_machine.add(temp_list);

		
		Reduce reduce = new Reduce(nbMapThread);
		
		int i = 0;
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		Logger logger = Logger.getInstance();
		logger.write(timeStamp);
		
		for (List<String> array : arrays_per_machine) {
			System.out.println(array);
			Thread t = new Thread(new Map(array, reduce, i));
			t.start();
			i++;
		}

		
	}

}
