package Engine.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Engine.Utilities.Transform;

public class TextDisplay implements HUD {
	private Transform transform;
	private int width, height;
	private Color backgroundColor = Color.lightGray;

	private String displayText;
	private String displayValue;
	private String displayString;

	private int fontWidth = 5;
	private int fontHeight = 8;

	public TextDisplay(int Xposition, int Yposition, int _width, int _height, String _displayText,
			String _displayValue) {

		transform = new Transform(Xposition, Yposition);
		width = _width;
		height = _height;
		displayText = _displayText;
		displayValue = _displayValue;
	}

	public TextDisplay(int Xposition, int Yposition, int _width, int _height, String _displayText, int _displayValue) {

		transform = new Transform(Xposition, Yposition);
		width = _width;
		height = _height;
		displayText = _displayText;
		displayValue = Integer.toString(_displayValue);
	}

	public TextDisplay(int Xposition, int Yposition, int _width, int _height, String _displayText,
			float _displayValue) {

		transform = new Transform(Xposition, Yposition);
		width = _width;
		height = _height;
		displayText = _displayText;
		displayValue = Float.toString(_displayValue);
	}

	public TextDisplay(Transform _transform, int _width, int _height, String _displayText, String _displayValue) {
		transform = _transform;
		width = _width;
		height = _height;
		displayText = _displayText;
		displayValue = _displayValue;
	}

	public TextDisplay(Transform _transform, int _width, int _height, String _displayText, int _displayValue) {
		transform = _transform;
		width = _width;
		height = _height;
		displayText = _displayText;
		displayValue = Integer.toString(_displayValue);
	}

	public TextDisplay(Transform _transform, int _width, int _height, String _displayText, float _displayValue) {
		transform = _transform;
		width = _width;
		height = _height;
		displayText = _displayText;
		displayValue = Float.toString(_displayValue);
	}

	@Override
	public void render(Graphics g) {
		g.flush();
		g.setLineWidth(0);
		// background color
		g.setColor(backgroundColor);
		g.fillRect(transform.get_x(), transform.get_y(), width, height);
		// text color
		g.setColor(Color.black);
		displayString = displayText + " : " + displayValue;

		g.drawString(displayString, transform.get_x() + width / 2 - displayString.length() * fontWidth,
				transform.get_y() + height / 2 - fontHeight);
	}

	public void updateValue(String value) {
		displayValue = value;
	}

	public void updateValue(int value) {
		displayValue = Integer.toString(value);
	}

	public void updateValue(float value) {
		displayValue = Float.toString(value);
	}

	public void updateText(String text) {
		displayText = text;
	}

	public void setPosition(Transform tr) {
		transform = tr;
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
