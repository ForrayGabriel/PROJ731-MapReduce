package projet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Reduce {
	
	boolean free = true;
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	int nb_machines_tot;
	int nb_machine = 0;
	String start;
	long strt;
	
	public Reduce(int nb_machines) {
		this.nb_machines_tot = nb_machines;
		start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		strt = Calendar.getInstance().getTimeInMillis();
	}

	public boolean take() {
		if (this.free) {
			this.free = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void free() {
		free = true;
		nb_machine++;
		if (nb_machine == nb_machines_tot) {
			System.out.println(map);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
			System.out.println("Start : " + start);
			System.out.println("End : " + timeStamp);
			System.out.println("Processing time : " + (Calendar.getInstance().getTimeInMillis()-strt));
		}
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

}
