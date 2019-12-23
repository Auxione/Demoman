package Curio.HUD;

import java.util.ArrayList;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

public class ConsoleDisplay extends HUD {
	int textSize = 16;
	boolean active = false;
	private float barSize = 4.0f;

	ArrayList<String> commandHistory = new ArrayList<String>();
	ArrayList<Integer> commandType = new ArrayList<Integer>();

	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	private TrueTypeFont trueTypeFont = super.getTTF();

	public ConsoleDisplay(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	public void Add(int typ3, String input) {
		String timestamp = sdf.format(cal.getTime());
		String type = "";
		if (typ3 == 0) {
			type = "INFO:";
		} else if (typ3 == 1) {
			type = "WARNING:";
			active = true;
		}
		String command = timestamp + " " + type + " " + input;
		commandHistory.add(command);
		commandType.add(typ3);
		System.out.println(command);
	}

	public void render(Graphics g) {
		if (active) {
			g.pushTransform();
			// background
			g.translate(super.Position.x, super.Position.y);
			g.setColor(new Color(100, 100, 100, 190));
			g.fillRect(super.Position.x, super.Position.y, super.width, super.height);
			// texts
			g.setWorldClip(super.Position.x, super.Position.y, super.width, super.height);
			for (int i = commandHistory.size() - 1; i >= 0; i--) {
				Color c = null;
				if (commandType.get(i) == 0) {
					c = (Color.black);
				} else if (commandType.get(i) == 1) {
					c = (Color.red);
				}

				int stringLineWidth = trueTypeFont.getWidth(commandHistory.get(i));
				int stringLineHeight = trueTypeFont.getHeight(commandHistory.get(i));
				
				float Stringx = super.Position.x;
				float Stringy = super.Position.y + super.height + i * textSize - commandHistory.size() * (textSize + 1)
						- barSize;

				trueTypeFont.drawString(Stringx, Stringy, commandHistory.get(i), c);
			}
			// bottombar
			g.setColor(Color.black);
			g.fillRect(super.Position.x, super.Position.y + super.height - barSize, super.width, barSize);

			g.clearWorldClip();
			g.popTransform();

		}
	}

	@Override
	public void inputEvent(Input input) {
		if (input.isKeyPressed(Input.KEY_GRAVE)) {
			active = !active;
		}
	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

}
