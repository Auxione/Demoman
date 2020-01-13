package Curio.HUD;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

public class TextDisplay extends HUD implements HUDInterface{
	private Color backgroundColor = Color.lightGray;
	private ArrayList<String> displayStringArray;
	private TrueTypeFont trueTypeFont = super.getTTF();
	private int xOffset = 2;
	private int yOffset = 2;

	public TextDisplay(int Xposition, int Yposition) {
		super(Xposition, Yposition);
		displayStringArray = new ArrayList<String>();

	}

	public TextDisplay(int Xposition, int Yposition, String text) {
		super(Xposition, Yposition);
		displayStringArray = new ArrayList<String>();
		updateString(text);
	}

	public TextDisplay() {
		super();
		displayStringArray = new ArrayList<String>();
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(super.transform.position.x, super.transform.position.y);

		g.setLineWidth(1);
		// background color
		g.setColor(backgroundColor);
		for (int i = 0; i < displayStringArray.size(); i++) {
			int width = trueTypeFont.getWidth(displayStringArray.get(i)) + 2 * xOffset;
			int height = trueTypeFont.getHeight() + 2 * yOffset;
			g.fillRect(0, i * trueTypeFont.getHeight(), width, height);

			trueTypeFont.drawString(xOffset, i * trueTypeFont.getHeight() + yOffset, displayStringArray.get(i),
					Color.black);
		}
		g.popTransform();
	}

	public void updateString(String value) {
		displayStringArray.removeAll(displayStringArray);
		String[] split = splitText(value);
		for (int i = 0; i < split.length; i++) {
			displayStringArray.add(split[i]);
		}
	}
	
	private String[] splitText(String text) {
		String[] texts = text.split("\\r?\\n");
		for (String s : texts) {
			s.trim();
		}
		return texts;
	}

	@Override
	public void inputEvent(Input input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char chr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int key, char chr) {
		// TODO Auto-generated method stub
		
	}
}
