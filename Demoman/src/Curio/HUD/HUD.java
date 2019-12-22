package Curio.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Utilities.Math.Vector;

public abstract class HUD {
	public Vector Position;
	public float width;
	public float height;

	HUD(float x, float y, float w, float h) {
		Position = new Vector(x, y);
		width = w;
		height = h;
	}

	public void setPosition(Vector _position) {
		Position = _position;
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
