package Curio.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class MouseStatsDisplay extends HUD {

	MouseStatsDisplay(float x, float y, float w, float h) {
		super(x, y, w, h);
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(super.Position.x, super.Position.y);
		g.fillRect(0, 0, super.width, super.height);

		g.popTransform();
	}

	@Override
	public void inputEvent(Input input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

}
