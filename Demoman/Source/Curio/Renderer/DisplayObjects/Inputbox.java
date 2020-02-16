package Curio.Renderer.DisplayObjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Renderer.Interface.HUD;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class Inputbox extends HUD implements Renderer {
	public static int ONLYDIGIT = 1;
	public static int ONLYDIGITANDLETTER = 0;

	private boolean active = false;
	public boolean Completed = false;

	private String text;
	private String outputText = "";
	private TrueTypeFont trueTypeFont = super.getTTF();

	private int mode;
	private boolean ClearWhenCompleted;
	private boolean CompleteWhenFocusLoss;

	public Inputbox(int mode) {
		super();
		this.mode = mode;
	}

	public Inputbox setText(String text) {
		this.text = text;
		return this;
	}

	public Inputbox setTransform(Transform transform) {
		super.setTransform(transform);
		return this;
	}

	public Inputbox setPosition(Vector vector) {
		super.transform.position = vector;
		return this;
	}

	public Inputbox setSize(float width, float height) {
		super.setSize(width, height);
		return this;
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
				if (CompleteWhenFocusLoss == true) {
					this.Completed = true;
				}
			}
		}
	}

	public String getInput() {
		String out = this.outputText;
		if (ClearWhenCompleted == true) {
			outputText = "";
		}
		return out;
	}

	@Override
	public void loopStart() {
	}

	@Override
	public void loopEnd() {
		this.Completed = false;
	}

	public Inputbox setClearWhenCompleted(boolean b) {
		ClearWhenCompleted = b;
		return this;
	}

	public Inputbox setCompleteWhenFocusLoss(boolean b) {
		CompleteWhenFocusLoss = b;
		return this;
	}

	@Override
	public void keyPressed(int key, char chr) {
		if (active == true) {
			if (key == Input.KEY_ENTER) {
				this.outputText = outputText.trim();
				this.Completed = true;
				this.active = false;
			}

			else if (key == 14) {
				// backspace keycode
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