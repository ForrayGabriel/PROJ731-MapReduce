package projet;

import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Map implements Runnable {

	// The list of words that have to be processed
	private List<String> words_list;

	// The reducer thread
	private Reduce reduce;

	// The number identifying the thread
	private int id;

	// The Hashmap containing the result
	private HashMap<String, Integer> mapping;

	/**
	 * Constructor for a map
	 * 
	 * @param words_list List of words to be processed
	 * @param reduce     The reducer to send the result to
	 * @param num        The id of the thread
	 */
	public Map(List<String> words_list, Reduce reduce, int id) {
		super();
		this.words_list = words_list;
		this.reduce = reduce;
		this.id = id;
		this.mapping = new HashMap<String, Integer>();
	}

	/**
	 * Launch the thread, process the data
	 */
	public void run() {

		// Get the logger and log the beggining of the thread
		Logger log = Logger.getInstance();
		log.write(this.id + " - I'm runnning");

		/**
		 * For each word from the list : If the word is in the Hashmap, add it and set
		 * its value to 1 occurrence Else, get its old value and increase it by 1
		 */
		for (String word : words_list) {
			if (mapping.get(word) == null) {
				mapping.put(word, 1);
			} else {
				mapping.put(word, mapping.get(word) + 1);
			}
		}

		//log.write(this.id + " - " + mapping);

		// Send this thread's map to the reducer
		reduce.addMap(this);

	}

	public HashMap<String, Integer> getMapping() {
		return mapping;
	}

}
