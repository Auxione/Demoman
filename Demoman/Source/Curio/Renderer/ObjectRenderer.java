package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.GameObject;
import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.Renderer;

public class ObjectRenderer implements Renderer, AlphaMaskRenderer {
	private float gameObjectImageSize = 5;

	private GameObject gameObject;

	public ObjectRenderer(GameObject gameObject) {
		this.gameObject = gameObject;

	}

	public ObjectRenderer setObjectImageSize(float size) {
		this.gameObjectImageSize = size;
		return this;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(gameObject.transform.position.x, gameObject.transform.position.y);
		g.rotate(0, 0, gameObject.transform.rotation.degrees());
		if (gameObject.getObjectImage() != null) {
			gameObject.getObjectImage().drawCentered(0, 0);
		} else {
			g.setColor(Color.black);
			g.fillOval(-gameObjectImageSize, -gameObjectImageSize, gameObjectImageSize * 2, gameObjectImageSize * 2);
			g.fillRect(-2, 0, 4, gameObjectImageSize * 2);
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
