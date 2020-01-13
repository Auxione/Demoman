package Curio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Console {
	public ArrayList<String> commandHistory;
	public ArrayList<Integer> commandType;

	private Calendar cal = Calendar.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public Console() {
		commandHistory = new ArrayList<String>();
		commandType = new ArrayList<Integer>();
	}

	public void Add(int typ3, String input) {
		String timestamp = sdf.format(cal.getTime());
		String type = "";
		if (typ3 == 0) {
			type = "INFO:";
		} else if (typ3 == 1) {
			type = "WARNING:";
		} else if (typ3 == 2) {
			type = "MESSAGE:";
		}
		String command = timestamp + " " + type + " " + input;
		commandHistory.add(command);
		commandType.add(typ3);
		System.out.println(command);
	}
}
