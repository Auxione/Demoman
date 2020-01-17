package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import static Curio.Functions.*;
import Curio.Physics.WorldTime;

public class WorldTimeDisplay extends HUD implements HUDInterface, Renderer {
	private WorldTime worldTime;
	private float minutesInAngle = 0.0f;
	private float hourInAngle = 0.0f;

	private int minuteHandLength = 48;
	private int hourHandLength = 32;

	private int minuteHandSize = 3;
	private int hourHandSize = 5;

	public WorldTimeDisplay(int Xposition, int Yposition, int width, int height, WorldTime worldTime) {
		super(Xposition, Yposition, width, height);
		this.worldTime = worldTime;
	}

	@Override
	public void render(Graphics g) {
		minutesInAngle = map(worldTime.getMinutes(), 0.0f, 60.0f, 0.0f, 360.0f);
		hourInAngle = map(worldTime.getHour(), 0.0f, 24.0f, 0.0f, 360.0f);

		g.pushTransform();
		g.setLineWidth(2);
		g.translate(super.transform.position.x, super.transform.position.y);
		g.setColor(Color.lightGray);
		g.fillOval(0, 0, width, height);
		g.setColor(Color.black);
		g.drawOval(0, 0, width, height);
		
		// minutes
		g.pushTransform();
		g.rotate(width / 2, height / 2, minutesInAngle);
		g.fillRect(width / 2 - minuteHandSize / 2, height / 2, minuteHandSize, - minuteHandLength);
		g.popTransform();

		// hours
		g.pushTransform();
		g.rotate(width / 2, height / 2, hourInAngle);
		g.fillRect(width / 2 - hourHandSize / 2, height / 2, hourHandSize,- hourHandLength);
		g.popTransform();

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
