package Engine.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Engine.Utilities.Transform;
import Engine.Utilities.Vector;

public class BarDisplay implements HUD {

	private Vector position;
	private int width, height;
	private Color barColor;

	public float ratio = 1;

	private int xsize = 2;
	private int ysize = 2;

	public BarDisplay(int Xposition, int Yposition, int _width, int _height, Color _color) {
		position = new Vector(Xposition, Yposition);
		width = _width;
		height = _height;
		barColor = _color;
	}

	public BarDisplay(Vector _vector, int _width, int _height, Color _color) {
		position = _vector;
		width = _width;
		height = _height;
		barColor = _color;
	}

	@Override
	public void render(Graphics g) {
		g.flush();
		g.setLineWidth(0);
		g.setColor(Color.black);
		g.fillRect(position.x, position.y, width, height);

		g.setColor(barColor);

		g.fillRect(position.x + xsize, position.y + ysize, (width - xsize * 2) * ratio, height - ysize * 2);
	}

	public void Percentage(float currentValue, float maxValue) {
		ratio = currentValue / maxValue;
		if (ratio < 0) {
			ratio = 0;
		}
	}

	public void setPosition(Vector _vector) {
		position = _vector;
	}

	@Override
	public void inputEvent(Input input) {
	}

	@Override
	public void loopStart() {
	}

	@Override
	public void loopEnd() {
	}

}
