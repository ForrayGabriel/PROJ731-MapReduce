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

	public Map(List<String> words_list, Reduce reduce, int num) {
		super();
		this.words_list = words_list;
		this.reduce = reduce;
		this.num = num;
	}

	public void run() {
		
		Logger logger = Logger.getInstance();
		
		
		System.out.println("Salut je run");

		HashMap<String, Integer> mapping = new HashMap<String, Integer>();

		for (String word : words_list) {
			//System.out.println(num + " - " + word);
			if (mapping.get(word) == null) {
				mapping.put(word, 1);
			}
			else {
				mapping.put(word, mapping.get(word)+1);
			}
		}
		
		while (reduce.take() == false) {
			try {
				System.out.println("Déjà utilisé, je sleep");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(num + " - " + mapping);
		reduce.add(mapping);
		reduce.free();

	}

}
