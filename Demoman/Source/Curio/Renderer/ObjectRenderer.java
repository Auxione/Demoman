package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.GameObject;
import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Geometry.Circle;
import Curio.Utilities.Math.Geometry.Rectangle;
import Curio.Utilities.Math.Geometry.Shape;

public class ObjectRenderer implements Renderer, AlphaMaskRenderer {
	private float gameObjectImageSize = 5;

	private GameObject gameObject;
	private Shape shape;

	public ObjectRenderer(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	public ObjectRenderer setObjectImageSize(float size) {
		this.gameObjectImageSize = size;
		return this;
	}

	public ObjectRenderer setObjectShape(Shape shape) {
		this.shape = shape;
		return this;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(gameObject.transform.position.x, gameObject.transform.position.y);
		g.rotate(0, 0, gameObject.transform.rotation.degrees());
		if (gameObject.getObjectImage() != null) {
			gameObject.getObjectImage().drawCentered(0, 0);
		}

		else if (shape != null) {
			g.setColor(Color.black);
			if (shape instanceof Circle) {
				Circle c = (Circle) shape;
				g.fillOval(-c.radius, -c.radius, c.radius * 2, c.radius * 2);
				g.fillRect(-2, 0, 4, c.radius * 2);
			}

			else if (shape instanceof Rectangle) {
				Rectangle r = (Rectangle) shape;
				g.fillRect(-r.width / 2, -r.height / 2, r.width, r.height);
			}
		}

		g.popTransform();
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		g.pushTransform();
		g.translate(gameObject.transform.position.x, gameObject.transform.position.y);
		if (gameObject.getAlphaMaskImage() != null) {
			gameObject.getAlphaMaskImage().rotate(gameObject.transform.rotation.degrees());
			gameObject.getAlphaMaskImage().drawCentered(0, 0);
		}
		g.popTransform();
	}
}
