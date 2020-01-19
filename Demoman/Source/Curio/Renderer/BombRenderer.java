package Curio.Renderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import Curio.Renderer.Interface.AnimationRenderer;
import Curio.SessionManagers.BombManager.Bomb;
import Default.Constants;

public class BombRenderer extends ObjectRenderer implements AnimationRenderer {
	private Bomb bomb;
	private SpriteSheet ExplosionSS = new SpriteSheet(Constants.ExplosionSprite, 32, 32);
	public Animation ExplosionAnimation = new Animation(ExplosionSS, 100);

	public BombRenderer(Bomb bomb) {
		super(bomb);
		this.bomb = bomb;
		this.ExplosionAnimation.setLooping(false);
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		if (bomb.isTicking == true) {
			g.pushTransform();
			g.translate(bomb.transform.position.x, bomb.transform.position.y);
			g.drawImage(bomb.getObjectImage(), 0, 0);
			g.popTransform();
		}
	}

	@Override
	public void renderAnimation(Graphics g) {
		if (bomb.isExploded == true) {
			ExplosionAnimation.start();
			g.pushTransform();
			g.translate(bomb.transform.position.x, bomb.transform.position.y);
			g.drawAnimation(ExplosionAnimation, 0, 0);
			g.popTransform();
		}
	}
}
