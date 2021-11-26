package projet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;

public class test {

	public static void main(String[] args) throws IOException {
		
		
		String str = new String(Files.readAllBytes(Paths.get("big.txt")),
                StandardCharsets.UTF_8);
		System.out.println(str);
		System.out.println(str.length());
		
		

	}

}
