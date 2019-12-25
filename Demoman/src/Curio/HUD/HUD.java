package Curio.HUD;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Utilities.Math.Vector;

public abstract class HUD {
	public Vector Position;
	public float width;
	public float height;

	int textSize = 16;
	private Font font = new Font("Arial", Font.BOLD, textSize);
	private TrueTypeFont ttf = new TrueTypeFont(font, true);

	HUD(float xPosition, float yPosition, float width, float height) {
		Position = new Vector(xPosition, yPosition);
		this.width = width;
		this.height = height;
	}

	HUD(float xPosition, float yPosition) {
		Position = new Vector(xPosition, yPosition);
		this.width = 0;
		this.height = 0;
	}

	HUD() {
		Position = new Vector();
		this.width = 0;
		this.height = 0;
	}

	public void setPosition(Vector _position) {
		Position = _position;
	}

	public TrueTypeFont getTTF() {
		return ttf;
	}

	public void resize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	protected boolean inRange(float x, float y) {
		if (x > Position.x && x < Position.x + width && y > Position.y && y < Position.y + height) {
			return true;
		} else {
			return false;
		}
	}

	public abstract void render(Graphics g);

	public abstract void inputEvent(Input input);

	public abstract void loopStart();

	public abstract void loopEnd();

}
