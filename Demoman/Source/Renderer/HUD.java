package Curio.HUD;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

import Curio.Utilities.Math.Transform;

public class HUD {
	protected Transform transform;
	protected float width;
	protected float height;

	protected int textSize = 16;
	private Font font = new Font("Arial", Font.BOLD, textSize);
	private TrueTypeFont ttf = new TrueTypeFont(font, true);

	HUD(float xPosition, float yPosition, float width, float height) {
		this.transform = new Transform(xPosition, yPosition, 0);
		this.width = width;
		this.height = height;
	}

	HUD(Transform transform, float width, float height) {
		this.transform = transform;
		this.width = width;
		this.height = height;
	}

	HUD(float xPosition, float yPosition) {
		this.transform = new Transform(xPosition, yPosition, 0);
		this.width = 0;
		this.height = 0;
	}

	HUD() {
		transform = new Transform();
		this.width = 0;
		this.height = 0;
	}

	public void setPosition(Transform transform) {
		this.transform = transform;
	}

	public TrueTypeFont getTTF() {
		return ttf;
	}

	public void resize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	protected boolean inRange(float x, float y) {
		if (x > this.transform.position.x && x < this.transform.position.x + width && y > this.transform.position.y
				&& y < this.transform.position.y + height) {
			return true;
		}
		return false;
	}
}
