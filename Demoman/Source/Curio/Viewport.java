package Curio;

import org.newdawn.slick.Graphics;

import Curio.GameObject.GameObject;
import Curio.Renderer.Renderer;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class Viewport {
	public Transform transform, ScreenMiddle;
	public Vector velocity, acceleration;

	public int screenSizeY, screenSizeX;
	private int ScrollSpeed = 15;
	private int PhysicsTime = 60;

	public Viewport(int screenSizeX, int screenSizeY) {
		this.transform = new Transform();
		this.ScreenMiddle = new Transform(screenSizeX / 2, screenSizeY / 2, 0);

		this.velocity = new Vector(0, 0);
		this.acceleration = new Vector(0, 0);

		this.screenSizeX = screenSizeX;
		this.screenSizeY = screenSizeY;
	}

	public void Update(int deltaTime) {
		float time = PhysicsTime / deltaTime;
		transform.position.x = (float) (transform.position.x + velocity.x / time
				+ 0.5 * acceleration.x / (time * time));
		transform.position.y = (float) (transform.position.y + velocity.y / time
				+ 0.5 * acceleration.y / (time * time));
		// calculate the middle of the displayscreen
		ScreenMiddle.position.x = ScreenMiddle.position.x + screenSizeX / 2;
		ScreenMiddle.position.y = ScreenMiddle.position.y + screenSizeY / 2;
	}

	public void scroll(float x, float y) {
		acceleration.x = acceleration.x + x - ScreenMiddle.position.x;
		acceleration.y = acceleration.y + y - ScreenMiddle.position.y;
		acceleration.multiply(ScrollSpeed);
	}

	public void move(float x, float y) {
		transform.position.x = x;
		transform.position.y = y;
	}

	public void move(Vector vector) {
		transform.position.x = vector.x - screenSizeX / 2;
		transform.position.y = vector.y - screenSizeY / 2;

	}

	public void renderOnHUD(Object Object, Graphics g) {
		if (Object instanceof Renderer) {
			((Renderer) Object).render(g);
		}
	}

	public void renderOnWorld(Object Object, Graphics g) {
		if (Object instanceof Renderer) {
			g.pushTransform();
			g.translate(-transform.position.x, -transform.position.y);
			g.setWorldClip(transform.position.x, transform.position.y, screenSizeX, screenSizeY);
			((Renderer) Object).render(g);
			g.popTransform();
		}
	}

	public void renderOnWorld(WorldObjectManager wom, Graphics g) {
		g.pushTransform();
		g.translate(-transform.position.x, -transform.position.y);
		g.setWorldClip(transform.position.x, transform.position.y, screenSizeX, screenSizeY);

		wom.render(g);
		wom.RenderToAlphaMap(g);
		g.popTransform();
	}

	public Transform ScreenToWorldPosition(float x, float y) {
		Transform out = new Transform(x + transform.position.x, y + transform.position.y, 0);
		return out;
	}
}