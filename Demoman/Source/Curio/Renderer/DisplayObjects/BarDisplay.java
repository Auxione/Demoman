package Curio.Renderer.DisplayObjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Renderer.Interface.HUD;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class BarDisplay extends HUD implements Renderer {
	private Color barColor = Color.darkGray;
	private Color backgroundColor = Color.lightGray;
	private int xsize = 2;
	private int ysize = 2;

	private float ratio = 1.0f;

	public BarDisplay() {
		super();
	}

	public BarDisplay setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	public BarDisplay setBarColor(Color barColor) {
		this.barColor = barColor;
		return this;
	}

	public BarDisplay setTransform(Transform transform) {
		super.setTransform(transform);
		return this;
	}

	public BarDisplay setPosition(Vector vector) {
		super.transform.position = vector;
		return this;
	}

	public BarDisplay setSize(float width, float height) {
		super.setSize(width, height);
		return this;
	}

	public void setPercentage(float ratio) {
		if (ratio < 0.0f) {
			this.ratio = 0.0f;
		}

		else if (ratio > 1.0f) {
			this.ratio = 1.0f;
		}

		else {
			this.ratio = ratio;
		}
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(transform.position.x, transform.position.y);
		g.setLineWidth(0);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		g.setColor(backgroundColor);
		g.fillRect(0 + xsize, 0 + ysize, (width - xsize * 2), height - ysize * 2);

		g.setColor(barColor);
		g.fillRect(0 + xsize, 0 + ysize, (width - xsize * 2) * ratio, height - ysize * 2);
		g.popTransform();
	}

	@Override
	public void inputEvent(Input input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char chr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int key, char chr) {
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
