package Curio;

import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.AnimationRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Vector;

public class Viewport{
	public static Vector position, ScreenMiddle;
	public static int screenSizeY;
	public static int screenSizeX;

	public Viewport(int screenSizeX, int screenSizeY) {
		Viewport.position = new Vector();
		Viewport.ScreenMiddle = new Vector(screenSizeX / 2, screenSizeY / 2);
		Viewport.screenSizeX = screenSizeX;
		Viewport.screenSizeY = screenSizeY;
	}

	public void move(float x, float y) {
		position.x = x - screenSizeX / 2;
		position.y = y - screenSizeY / 2;
		ScreenMiddle.x = position.x + screenSizeX / 2;
		ScreenMiddle.y = position.y + screenSizeY / 2;
	}

	public void move(Vector vector) {
		move(vector.x, vector.y);
	}

	public void renderOnHUD(Renderer Object, Graphics g) {
		g.pushTransform();
		Object.render(g);
		g.popTransform();
	}

	public void renderOnWorld(Renderer Object, Graphics g) {
		g.pushTransform();
		g.translate(-position.x, -position.y);
		g.setWorldClip(position.x, position.y, screenSizeX, screenSizeY);
		Object.render(g);
		g.popTransform();
	}

	public void renderAnimationOnHUD(AnimationRenderer Object, Graphics g) {
		g.pushTransform();
		Object.renderAnimation(g);
		g.popTransform();
	}

	public void renderAnimationOnWorld(AnimationRenderer Object, Graphics g) {
		g.pushTransform();
		g.translate(-position.x, -position.y);
		g.setWorldClip(position.x, position.y, screenSizeX, screenSizeY);
		Object.renderAnimation(g);
		g.popTransform();
	}

	public void renderAlphaMaskOnHUD(AlphaMaskRenderer Object, Graphics g) {
		g.pushTransform();
		Object.renderAlphaMask(g);
		g.popTransform();
	}

	public void renderAlphaMaskOnWorld(AlphaMaskRenderer Object, Graphics g) {
		g.pushTransform();
		g.translate(-position.x, -position.y);
		g.setWorldClip(position.x, position.y, screenSizeX, screenSizeY);
		Object.renderAlphaMask(g);
		g.popTransform();
	}

	public static Vector ScreenToWorldPosition(float x, float y) {
		return new Vector(x + position.x, y + position.y);
	}

	public static Vector WorldToScreenPosition(float x, float y) {
		return new Vector(x - position.x, y - position.y);
	}

	public static Vector WorldToScreenPosition(Vector vector) {
		return WorldToScreenPosition(vector.x, vector.y);
	}

	public static Vector ScreenToWorldPosition(Vector vector) {
		return ScreenToWorldPosition(vector.x, vector.y);
	}
}