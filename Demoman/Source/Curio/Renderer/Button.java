package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Utilities.Math.Transform;

public class Button extends HUD implements HUDInterface, Renderer {
	private String buttonText;

	private Color buttonColor;
	private Color defaultColor = Color.gray;
	private Color waitingInputColor = Color.lightGray;
	private Color pressedColor = Color.darkGray;

	private TrueTypeFont trueTypeFont = super.getTTF();

	public boolean pressed = false;

	public Button(int Xposition, int Yposition, int _width, int _height, String _text) {
		super(Xposition, Yposition, _width, _height);
		buttonText = _text;
	}

	public Button(Transform transform, int _width, int _height, String _text) {
		super(transform, _width, _height);
		buttonText = _text;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(transform.position.x, transform.position.y);
		g.setColor(buttonColor);
		g.fillRoundRect(0, 0, width, height, 10);

		float textx = width / 2 - trueTypeFont.getWidth(buttonText) / 2;
		float texty = height / 2 - trueTypeFont.getHeight(buttonText) / 2;

		trueTypeFont.drawString(textx, texty, buttonText);
		g.popTransform();
	}

	@Override
	public void loopStart() {

	}

	@Override
	public void loopEnd() {
		pressed = false;
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

	@Override
	public void keyPressed(int key, char chr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int key, char chr) {
		// TODO Auto-generated method stub

	}
}
