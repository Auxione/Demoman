package Curio.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

public class Inputbox extends HUD implements HUDInterface {
	private boolean active = false;
	public boolean Completed = false;

	private String text;
	private String outputText = "";
	private TrueTypeFont trueTypeFont = super.getTTF();

	private int mode;
	private boolean ClearWhenCompleted;

	public Inputbox(float x, float y, float width, float height, String text, int mode) {
		super(x, y, width, height);

		this.text = text;
		this.mode = mode;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(transform.position.x, transform.position.y);
		g.setLineWidth(2);
		// background

		if (active == false) {
			g.setColor(Color.gray);
		} else if (active == true) {
			g.setColor(Color.lightGray);
		}

		g.fillRect(0, 0, width, height);
		// outline
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);

		float textx = width / 2 - trueTypeFont.getWidth(text + outputText) / 2;
		float texty = height / 2 - trueTypeFont.getHeight(text + outputText) / 2;

		trueTypeFont.drawString(textx, texty, text + outputText);

		g.popTransform();
	}

	public void inputEvent(Input input) {
		if (this.inRange(input.getMouseX(), input.getMouseY()) == true) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) == true) {
				this.active = true;
			}
		} else if (this.inRange(input.getMouseX(), input.getMouseY()) == false) {
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) == true) {
				this.active = false;
				this.Completed = true;
			}
		}
	}

	public String getInput() {
		return this.outputText;
	}

	@Override
	public void loopStart() {
	}

	@Override
	public void loopEnd() {
		this.Completed = false;
	}

	void setClearWhenCompleted(boolean b) {
		ClearWhenCompleted = b;
	}

	@Override
	public void keyPressed(int key, char chr) {
		if (active == true) {
			if (key == Input.KEY_ENTER) {
				this.outputText = outputText.trim();
				this.Completed = true;
				this.active = false;
				if (ClearWhenCompleted == true) {
					outputText = "";
				}
			}

			else if (key == 14) {
				//backspace keycode
				if (outputText.length() > 0) {
					this.outputText = outputText.substring(0, outputText.length() - 1);
				}
			} else {
				if (mode == 0) {
					if (Character.isLetterOrDigit(chr) == true) {
						this.outputText += chr;
					}
				} else if (mode == 1) {
					if (Character.isDigit(chr) == true) {
						this.outputText += chr;
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(int key, char chr) {
		// TODO Auto-generated method stub
	}
}