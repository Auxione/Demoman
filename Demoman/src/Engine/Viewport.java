package Engine;

import org.newdawn.slick.Graphics;

import Engine.Utilities.Vector;

public class Viewport {
	Vector Position, Velocity, Acceleration, ScreenMid;
	private int Screen_Size_Y, Screen_Size_X;
	private int ScrollSpeed = 15;
	private int PhysicsTime = 60;

	public Viewport(int screensizex, int screensizey) {
		Position = new Vector(0, 0);
		Velocity = new Vector(0, 0);
		Acceleration = new Vector(0, 0);
		ScreenMid = new Vector(Screen_Size_X / 2, Screen_Size_Y / 2);

		Screen_Size_X = screensizex;
		Screen_Size_Y = screensizey;
	}

	public void Update(int deltaTime) {
		float time = PhysicsTime / deltaTime;
		Position.x = (float) (Position.x + Velocity.x / time + 0.5 * Acceleration.x / (time * time));
		Position.y = (float) (Position.y + Velocity.y / time + 0.5 * Acceleration.y / (time * time));
		// calculate the middle of the displayscreen
		ScreenMid.x = Position.x + Screen_Size_X / 2;
		ScreenMid.y = Position.y + Screen_Size_Y / 2;
	}

	public void scroll(float x, float y) {
		Acceleration.x = Acceleration.x + x - ScreenMid.x;
		Acceleration.y = Acceleration.y + y - ScreenMid.y;
		Acceleration.multiply(ScrollSpeed);
	}
	
	public void move(float x, float y) {
		Position.x  = x;
		Position.y  = y;
	}
	
	public void renderStart(Graphics g) {
		// Scroll with screen
		g.pushTransform();
		g.translate(-Position.x, -Position.y);
	}

	public void renderEnd(Graphics g) {
		g.popTransform();
		// stay on screen
	}

	public float[] getScreenToWorldPos(int x, int y) {
		float[] out = { x + Position.x, y + Position.y };
		return out;
	}
}