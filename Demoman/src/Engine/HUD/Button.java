package Engine.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Engine.Utilities.Transform;

public class Button implements HUD {
	private Transform transform;
	private int width, height;
	private String buttonText;

	private Color buttonColor;
	private Color defaultColor = Color.gray;
	private Color waitingInputColor = Color.lightGray;
	private Color pressedColor = Color.darkGray;

	private int fontWidth = 5;
	private int fontHeight = 8;

	public boolean pressed = false;

	public Button(int Xposition, int Yposition, int _width, int _height, String _text) {
		transform = new Transform(Xposition, Yposition);
		width = _width;
		height = _height;
		buttonText = _text;
	}

	public Button(Transform _transform, int _width, int _height, String _text) {
		transform = _transform;
		width = _width;
		height = _height;
		buttonText = _text;
	}

	public void render(Graphics g) {
		g.setColor(buttonColor);
		g.fillRoundRect(transform.get_x(), transform.get_y(), width, height, 10);
		int textx = transform.get_x() + width / 2 - buttonText.length() * fontWidth; // Subtract the length of string
																						// multiplied
		// by char size in pixel from middle
		// point.
		int texty = transform.get_y() + height / 2 - fontHeight;

		g.setColor(Color.black);
		g.setLineWidth(width);
		g.drawString(buttonText, textx, texty);
	}

	@Override
	public void loopStart() {

	}

	@Override
	public void loopEnd() {
		if (pressed == true) {
			pressed = false;
		}
	}

	@Override
	public void inputEvent(Input input) {
		if (inRange(input.getMouseX(), input.getMouseY()) == true) {
			buttonColor = waitingInputColor;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) == true) {
				pressed = true;
				buttonColor = pressedColor;
			}
		} else {
			buttonColor = defaultColor;
		}
	}

	private boolean inRange(float x, float y) {
		if (x > transform.get_x() && x < transform.get_x() + width && y > transform.get_y()
				&& y < transform.get_y() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setPosition(Transform tr) {
		transform = tr;
	}

}
