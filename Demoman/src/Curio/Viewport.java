package Curio;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;
import Default.Constants;
import Default.Main;

public class Viewport {
	public Vector Position, Velocity, Acceleration, ScreenMid;
	public int Screen_Size_Y, Screen_Size_X;
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
		Position.x = x;
		Position.y = y;
	}
	
	public void move(Vector v) {
		Position.x = v.x - Screen_Size_X / 2 ;
		Position.y = v.y - Screen_Size_Y / 2;
	}
	
	public void move(Transform tr) {
		Position.x = tr.get_x()*Constants.CellSize - Screen_Size_X / 2 ;
		Position.y = tr.get_y()*Constants.CellSize - Screen_Size_Y / 2;
	}
	
	public void renderStart(Graphics g) {
		// Scroll with screen
		g.pushTransform();
		g.translate(-Position.x, -Position.y);
		g.setWorldClip(Position.x, Position.y, Screen_Size_X, Screen_Size_Y);
	}

	public void renderEnd(Graphics g) {
		g.popTransform();
		// stay on screen
	}

	public Vector ScreenToWorldPos(TileMap level, float x, float y) {
		float outx = Functions.map(x + Position.x, 0, Main.DisplayWidth, 0, level.get_MaxCellX());
		float outy = Functions.map(y + Position.y, 0, Main.DisplayHeight, 0, level.get_MaxCellY());
		Vector out = new Vector(outx, outy);
		return out;
	}
}