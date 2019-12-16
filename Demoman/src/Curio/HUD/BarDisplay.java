package Curio.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Utilities.Vector;

public class BarDisplay extends HUD {
	private Color barColor;
	public float ratio = 1;
	private int xsize = 2;
	private int ysize = 2;

	public BarDisplay(int Xposition, int Yposition, int _width, int _height, Color _color) {
		super(Xposition, Yposition,_width, _height);
		barColor = _color;
	}

	public BarDisplay(Vector _vector, int _width, int _height, Color _color) {
		super(_vector.x, _vector.y,_width, _height);
		barColor = _color;
	}

	@Override
	public void render(Graphics g) {
		g.flush();
		g.setLineWidth(0);
		g.setColor(Color.black);
		g.fillRect(Position.x, Position.y, width, height);

		g.setColor(barColor);

		g.fillRect(Position.x + xsize, Position.y + ysize, (width - xsize * 2) * ratio, height - ysize * 2);
	}

	public void Percentage(float currentValue, float maxValue) {
		ratio = currentValue / maxValue;
		if (ratio < 0) {
			ratio = 0;
		}
	}

	@Override
	public void loopStart() {
	}

	@Override
	public void loopEnd() {
	}

	@Override
	public void inputEvent(Input input) {
		// TODO Auto-generated method stub
		
	}

}
