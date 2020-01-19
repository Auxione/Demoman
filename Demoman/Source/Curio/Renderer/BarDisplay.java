package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Renderer.Interface.HUD;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Transform;

public class BarDisplay extends HUD implements Renderer {
	private Color barColor;
	private Color backgroundColor = Color.lightGray;
	public float ratio = 1;
	private int xsize = 2;
	private int ysize = 2;

	public BarDisplay(int Xposition, int Yposition, int _width, int _height, Color _color) {
		super(Xposition, Yposition, _width, _height);
		barColor = _color;
	}

	public BarDisplay(Transform transform, int _width, int _height, Color _color) {
		super(transform, _width, _height);
		barColor = _color;
	}

	public void Percentage(float currentValue, float maxValue) {
		ratio = currentValue / maxValue;
		if (ratio < 0) {
			ratio = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.setLineWidth(0);
		g.setColor(Color.black);
		g.fillRect(transform.position.x, transform.position.y, width, height);

		g.setColor(backgroundColor);
		g.fillRect(transform.position.x + xsize, transform.position.y + ysize, (width - xsize * 2), height - ysize * 2);

		g.setColor(barColor);
		g.fillRect(transform.position.x + xsize, transform.position.y + ysize, (width - xsize * 2) * ratio,
				height - ysize * 2);
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
