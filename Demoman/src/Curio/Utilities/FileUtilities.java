package Curio.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilities {

	private FileUtilities() {
	}

	public static String loadAsString(String file) {
		String out = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String Buffer = "";
			while ((Buffer = reader.readLine()) != null) {
				out += Buffer + "/n";
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
}
