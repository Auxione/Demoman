package Curio;

import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.AnimationRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Vector;

public class Viewport {
	public static Vector position, ScreenMiddle;
	public Vector velocity, acceleration;

	public static int screenSizeY;
	public static int screenSizeX;
	private int PhysicsTime = 60;

	public Viewport(int screenSizeX, int screenSizeY) {
		Viewport.position = new Vector();
		Viewport.ScreenMiddle = new Vector(screenSizeX / 2, screenSizeY / 2, 0);

		this.velocity = new Vector(0, 0);
		this.acceleration = new Vector(0, 0);

		Viewport.screenSizeX = screenSizeX;
		Viewport.screenSizeY = screenSizeY;
	}

	public void Update(int deltaTime) {
		float time = PhysicsTime / deltaTime;
		position.x = (float) (position.x + velocity.x / time + 0.5 * acceleration.x / (time * time));
		position.y = (float) (position.y + velocity.y / time + 0.5 * acceleration.y / (time * time));
		// calculate the middle of the displayscreen
		ScreenMiddle.x = position.x + screenSizeX / 2;
		ScreenMiddle.y = position.y + screenSizeY / 2;
	}

	public void move(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public void move(Vector vector) {
		position.x = vector.x - screenSizeX / 2;
		position.y = vector.y - screenSizeY / 2;
		ScreenMiddle.x = position.x + screenSizeX / 2;
		ScreenMiddle.y = position.y + screenSizeY / 2;
	}

	public void renderOnHUD(Object Object, Graphics g) {
		g.pushTransform();
		if (Object instanceof Renderer) {
			((Renderer) Object).render(g);
		}
		if (Object instanceof AlphaMaskRenderer) {
			((AlphaMaskRenderer) Object).renderAlphaMask(g);
		}
		if (Object instanceof AnimationRenderer) {
			((AnimationRenderer) Object).renderAnimation(g);
		}
		g.popTransform();
	}

	public void renderOnWorld(Object Object, Graphics g) {
		g.pushTransform();
		g.translate(-position.x, -position.y);
		g.setWorldClip(position.x, position.y, screenSizeX, screenSizeY);
		if (Object instanceof Renderer) {
			((Renderer) Object).render(g);
		}
		if (Object instanceof AlphaMaskRenderer) {
			((AlphaMaskRenderer) Object).renderAlphaMask(g);
		}
		if (Object instanceof AnimationRenderer) {
			((AnimationRenderer) Object).renderAnimation(g);
		}
		g.popTransform();
	}

	public static Vector ScreenToWorldPosition(float x, float y) {
		return new Vector(x + position.x, y + position.y);
	}

	public static Vector ScreenToWorldPosition(Vector vec) {
		return new Vector(vec.x + position.x, vec.y + position.y);
	}
}