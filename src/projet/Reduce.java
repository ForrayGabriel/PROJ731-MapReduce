package projet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Reduce implements Runnable {

	/**
	 * The final map with the result
	 */
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	/**
	 * The total number of machine and the actual number of finisshed one
	 */
	private int nb_machines_tot;
	private int nb_machine = 0;
	/**
	 * Variable for time analysis
	 */
	private String start;
	private long strt;
	private String middle;
	private long middl;
	/**
	 * Arraylist of the results of the threads
	 */
	private ArrayList<Map> listMap;
	// Name of the file
	private String fileName;
	private Logger log;

	/**
	 * Constructor for the reducer
	 * 
	 * @param nb_machines The number of thread created
	 * @param fileName    Name of the file
	 * @param start       Time of beggining of the map as a string
	 * @param strt        Time of beggining of the map in millis as a long
	 */
	public Reduce(int nb_machines, String fileName, String start, long strt) {
		this.nb_machines_tot = nb_machines;
		this.start = start;
		this.strt = strt;
		this.middle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		this.middl = Calendar.getInstance().getTimeInMillis();
		this.listMap = new ArrayList<Map>();
		this.fileName = fileName;
		this.log = Logger.getInstance();
	}

	/**
	 * Function to give the result of a thread to the reducer
	 * 
	 * @param map
	 */
	public void addMap(Map map) {
		nb_machine++;
		System.out.println("Recu 1");
		this.listMap.add(map);

		// If we received as many maps as they are threads, start calculating
		if (nb_machine == nb_machines_tot) {
			this.concatenateMaps();
		}
	}

	/**
	 * Function that launch the calculation and display the result
	 */
	private void concatenateMaps() {
		for (Map m : this.listMap) {
			this.add(m.getMapping());
		}
		long processingTime = Calendar.getInstance().getTimeInMillis() - strt;
		long processingTimeMapReduce = Calendar.getInstance().getTimeInMillis() - middl;
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		try {
			this.writeAndDisplayMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.log.write("Reading start : " + start);
		this.log.write("Map start : " + middle);
		this.log.write("End   : " + timeStamp);
		this.log.write("Processing time with reading: " + processingTime + " ms");
		this.log.write("Processing time without reading: " + processingTimeMapReduce + " ms");
		
		String message = "Reading start : " + start + "\nMap start : " + middle + "\nEnd   : " + timeStamp + "\nProcessing time with reading: " + processingTime + " ms" + "\nProcessing time without reading: " + processingTimeMapReduce + " ms";

JOptionPane.showMessageDialog(new JFrame(), message, "Resulat", JOptionPane.INFORMATION_MESSAGE);
		
		
	}

	/**
	 * Function that combine the maps together
	 * 
	 * @param mapping A map of data from a thread
	 */
	public void add(HashMap<String, Integer> mapping) {

		// For each word, if it's not in the map, add it, else add its value together
		mapping.forEach((k, v) -> {
			if (map.get(k) == null) {
				map.put(k, v);
			} else {
				map.put(k, map.get(k) + v);
			}
		});
	}

	/**
	 * Function to display in the console and write to a file
	 * 
	 * @throws IOException
	 */
	public void writeAndDisplayMap() throws IOException {

		// Open a file with the same name and "_res"
		FileWriter myWriter = new FileWriter(this.fileName + "_res.txt");
		
		// For each word in the Hashmap, write it in the file and log it in the console
		this.map.forEach((k, v) -> {
			String str = v + " : " + k;
			try {
				myWriter.write(str + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.log.write(str);
		});
		
		myWriter.close();

	}

	@Override
	public void run() {

	}

}
