package Curio.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

public class TextDisplay extends HUD {
	private Color backgroundColor = Color.lightGray;

	private String displayText;
	private String displayValue;
	private String displayString;

	private TrueTypeFont trueTypeFont = super.getTTF();
private int xOffset = 4;
private int yOffset = 4;
	public TextDisplay(int Xposition, int Yposition, String displayText, String displayValue) {
		super(Xposition, Yposition);

		this.displayText = displayText;
		this.displayValue = displayValue;

		this.displayString = displayText + " : " + displayValue;
		super.resize(trueTypeFont.getWidth(displayString)+xOffset*2, trueTypeFont.getHeight(displayString)+yOffset*2);
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(Position.x, Position.y);

		g.setLineWidth(1);
		// background color
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		// text color

		trueTypeFont.drawString(xOffset, yOffset, displayString, Color.black);

		g.popTransform();
	}

	public void updateValue(String value) {
		displayValue = value;
		displayString = displayText + " : " + displayValue;
	}

	public void updateText(String text) {
		displayText = text;
		displayString = displayText + " : " + displayValue;
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
