package Curio.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Utilities.Math.Vector;

public class Button extends HUD {
	private String buttonText;

	private Color buttonColor;
	private Color defaultColor = Color.gray;
	private Color waitingInputColor = Color.lightGray;
	private Color pressedColor = Color.darkGray;

	private int fontWidth = 5;
	private int fontHeight = 8;

	public boolean pressed = false;

	public Button(int Xposition, int Yposition, int _width, int _height, String _text) {
		super(Xposition,Yposition,_width,_height);
		buttonText = _text;
	}

	public Button(Vector _position, int _width, int _height, String _text) {
		super(_position.x,_position.y,_width,_height);
		buttonText = _text;
	}

	public void render(Graphics g) {
		g.flush();
		g.setColor(buttonColor);
		g.fillRoundRect(Position.x, Position.y, width, height, 10);
		float textx = Position.x + width / 2 - buttonText.length() * fontWidth; // Subtract the length of string
																						// multiplied
		// by char size in pixel from middle
		// point.
		float texty =Position.y + height / 2 - fontHeight;

		g.setColor(Color.black);
		g.setLineWidth(width);
		g.drawString(buttonText, textx, texty);
	}

	@Override
	public void loopStart() {

	}

	@Override
	public void loopEnd() {
	}
	@Override
	public void inputEvent(Input input) {
		if (inRange(input.getMouseX(), input.getMouseY()) == true) {
			buttonColor = waitingInputColor;
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				pressed = true;
				buttonColor = pressedColor;
			}
		} else {
			buttonColor = defaultColor;
		}
	}
	public void setPosition(Vector _position) {
		Position = _position;
	}
}
