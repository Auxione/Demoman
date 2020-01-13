package Curio.Utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
			e.printStackTrace();
		}
		return out;
	}

	public void saveObject(String path, Object object) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(object);
		oos.close();
	}

	public Object loadObject(String path) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Object object = ois.readObject();
		ois.close();
		return object;
	}
}
