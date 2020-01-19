package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import static Curio.Functions.*;
import Curio.Physics.WorldTime;
import Curio.Renderer.Interface.HUD;
import Curio.Renderer.Interface.Renderer;
import Default.Constants;

public class WorldTimeDisplay extends HUD implements Renderer {
	private WorldTime worldTime;
	private Image image = Constants.clock;
	
	private float minutesInAngle = 0.0f;
	private float hourInAngle = 0.0f;
	private float secondsInAngle = 0.0f;

	private int minuteHandLength = 48;
	private int hourHandLength = 32;
	private int secondsHandLength = 56;

	private int minuteHandSize = 4;
	private int hourHandSize = 6;
	private int secondsHandSize = 2;

	public WorldTimeDisplay(int Xposition, int Yposition, int width, int height, WorldTime worldTime) {
		super(Xposition, Yposition, width, height);
		this.worldTime = worldTime;
		this.image = image.getScaledCopy(width,height);
	}

	@Override
	public void render(Graphics g) {
		secondsInAngle = map(worldTime.getTime().second, 0.0f, 60.0f, 0.0f, 360.0f);
		minutesInAngle = map(worldTime.getTime().minute, 0.0f, 60.0f, 0.0f, 360.0f);
		hourInAngle = map(worldTime.getTime().hour, 0.0f, 24.0f, 0.0f, 360.0f);

		g.pushTransform();
		g.setLineWidth(2);
		g.translate(super.transform.position.x, super.transform.position.y);
		g.drawImage(image,0,0);
		

		g.translate(width / 2, height / 2);
		// seconds
		g.pushTransform();
		g.rotate(0, 0, secondsInAngle);
		g.fillRect(-secondsHandSize / 2, 0, secondsHandSize, -secondsHandLength);
		g.popTransform();

		// minutes
		g.pushTransform();
		g.rotate(0, 0, minutesInAngle);
		g.fillRect(-minuteHandSize / 2, 0, minuteHandSize, -minuteHandLength);
		g.popTransform();

		// hours
		g.pushTransform();
		g.rotate(0, 0, hourInAngle);
		g.fillRect(-hourHandSize / 2, 0, hourHandSize, -hourHandLength);
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
