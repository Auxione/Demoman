package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Renderer.Interface.HUD;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class Button extends HUD implements Renderer {
	private String buttonText = "";
	private Color buttonColor= Color.red;
	private Color defaultColor = Color.gray;
	private Color waitingInputColor = Color.lightGray;
	private Color pressedColor = Color.darkGray;

	private TrueTypeFont trueTypeFont = super.getTTF();

	public boolean pressed = false;

	public Button() {
		super();
	}
	
	public Button setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
		return this;
	}
	
	public Button setButtonText(String buttonText) {
		this.buttonText = buttonText;
		return this;
	}
	
	public Button setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
		return this;
	}

	public Button setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
		return this;
	}

	public Button setWaitingInputColor(Color waitingInputColor) {
		this.waitingInputColor = waitingInputColor;
		return this;
	}

	public Button setTransform(Transform transform) {
		super.setTransform(transform);
		return this;
	}

	public Button setPosition(Vector vector) {
		super.transform.position = vector;
		return this;
	}

	public Button setSize(float width, float height) {
		super.setSize(width, height);
		return this;
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

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}
}
