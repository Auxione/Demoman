package Curio.Renderer.Interface;

import java.awt.Font;

import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public abstract class HUD {
	protected Transform transform;
	protected float width;
	protected float height;
	protected int textSize = 16;
	
	private Font font = new Font("Arial", Font.BOLD, textSize);
	private TrueTypeFont ttf = new TrueTypeFont(font, true);

	protected HUD() {
		this.transform = new Transform();
		this.width = 0;
		this.height = 0;
	}

	protected HUD setTransform(Transform transform) {
		this.transform = new Transform(transform);
		return this;
	}
	
	protected HUD setPosition(Vector vector) {
		this.transform.position = vector;
		return this;
	}
	
	protected HUD setSize(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	protected HUD setTextSize(int textSize) {
		this.textSize = textSize;
		return this;
	}

	public TrueTypeFont getTTF() {
		return ttf;
	}
	
	protected boolean inRange(float x, float y) {
		if (x > this.transform.position.x && x < this.transform.position.x + width && y > this.transform.position.y
				&& y < this.transform.position.y + height) {
			return true;
		}
		return false;
	}

	public abstract void inputEvent(Input input);

	public abstract void keyPressed(int key, char chr);

	public abstract void keyReleased(int key, char chr);

	public abstract void loopStart();

	public abstract void loopEnd();
}
