package Curio.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Renderer.Renderer;

public class ObjectRenderer implements Renderer {
	private GameObject gameObject;
	private Image objectImage = null;
	private float size = 10;

	public ObjectRenderer() {
	}

	public ObjectRenderer(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	public ObjectRenderer setSize(float size) {
		this.size = size;
		return this;
	}

	public ObjectRenderer setImage(Image objectImage) {
		this.objectImage = objectImage;
		return this;
	}

	public void render(Graphics g) {
		g.pushTransform();
		if (objectImage == null) {
			g.translate(gameObject.transform.position.x - size, gameObject.transform.position.y - size);
			g.setColor(Color.black);
			g.fillOval(0, 0, size * 2, size * 2);
		} else {
			g.translate(gameObject.transform.position.x, gameObject.transform.position.y);
			g.drawImage(objectImage, 0, 0);
		}
		g.popTransform();
	}
}
