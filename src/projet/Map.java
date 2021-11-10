package projet;

import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Map implements Runnable {

	private List<String> words_list;
	private Reduce reduce;
	private int num;
	private HashMap<String, Integer> mapping;

	public Map(List<String> words_list, Reduce reduce, int num) {
		super();
		this.words_list = words_list;
		this.reduce = reduce;
		this.num = num;
		this.mapping = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> getMapping() {
		return mapping;
	}

	public void run() {
		
		Logger log = Logger.getInstance();
		
		log.write("Salut je run");

		for (String word : words_list) {
			//log.write(num + " - " + word);
			if (mapping.get(word) == null) {
				mapping.put(word, 1);
			}
			else {
				mapping.put(word, mapping.get(word)+1);
			}
		}
		
		log.write(num + " - " + mapping);
		
		reduce.addMap(this);

	}

}
