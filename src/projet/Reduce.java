package projet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Reduce implements Runnable {
	
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private int nb_machines_tot;
	private int nb_machine = 0;
	private String start;
	private long strt;
	private String middle;
	private long middl;
	private ArrayList<Map> listMap;
	private String fileName;
	private Logger log;
	
	public Reduce(int nb_machines, String fileName, String start, long strt) {
		this.nb_machines_tot = nb_machines;
		this.start = start;
		this.strt = strt;
		middle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		middl = Calendar.getInstance().getTimeInMillis();
		this.listMap = new ArrayList<Map>();
		this.fileName = fileName;
		this.log = Logger.getInstance();
	}

	public void addMap(Map m) {
		nb_machine++;
		this.listMap.add(m);
		if (nb_machine == nb_machines_tot) {
			this.getMaps();
		}
	}
	
	private void getMaps() {
		for (Map m : this.listMap) {
			this.add(m.getMapping());
		}
		long processingTime = Calendar.getInstance().getTimeInMillis()-strt;
		long processingTimeMapReduce = Calendar.getInstance().getTimeInMillis()-middl;
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
	}
	
	public void add(HashMap<String, Integer> mapping) {
		
		mapping.forEach((k, v) -> {
			if (map.get(k) == null) {
				map.put(k, v);
			}
			else {
				map.put(k, map.get(k)+v);
			}
        });
	}
	
	public void writeAndDisplayMap() throws IOException {
		
	
		FileWriter myWriter = new FileWriter(this.fileName+"_res.txt");
		
		this.map.forEach((k, v) -> {
			String str = v + " : " + k;
			try {
				myWriter.write(str+ "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.log.write(str);
		});

	}

	@Override
	public void run() {
		
		
	}

}
