package Curio.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class TextDisplay extends HUD {
	private Color backgroundColor = Color.lightGray;

	private String displayText;
	private String displayValue;
	private String displayString;

	private int fontWidth = 5;
	private int fontHeight = 8;

	public TextDisplay(int Xposition, int Yposition, int _width, int _height, String _displayText,
			String _displayValue) {
		super(Xposition, Yposition, _width, _height);

		displayText = _displayText;
		displayValue = _displayValue;
	}


	@Override
	public void render(Graphics g) {
		g.flush();
		g.setLineWidth(0);
		// background color
		g.setColor(backgroundColor);
		g.fillRect(Position.x, Position.y, width, height);
		// text color
		g.setColor(Color.black);
		displayString = displayText + " : " + displayValue;

		g.drawString(displayString, Position.x + width / 2 - displayString.length() * fontWidth,
				Position.y + height / 2 - fontHeight);
	}

	public void updateValue(String value) {
		displayValue = value;
	}

	public void updateText(String text) {
		displayText = text;
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
}
