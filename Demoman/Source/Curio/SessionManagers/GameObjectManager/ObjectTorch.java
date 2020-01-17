package Curio.SessionManagers.GameObjectManager;

import static Curio.Functions.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.GameObject.GameObject;
import Curio.Physics.WorldTime;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import static Curio.Functions.*;
import Default.Constants;

public class ObjectTorch extends GameObject implements WorldObject {
	public Image alphaMaskImage = Constants.CircularAlphaMask;
	private WorldTime worldTime;

	public Boolean active = true;
	public int radius;

	public float alphaValue = 0.3f;
	public float redValue = 1.0f;
	public float greenValue = 1.0f;
	public float blueValue = 0.0f;

	public int burnTargetHour;

	private int lightRadius = 128;
	private int currentlightRadius = lightRadius;
	private int maxlightRadius = 32;
	private int minlightRadius = -32;
	private int updateDelay = 200;
	private int updateCurrentTime;

	public ObjectTorch(WorldTime worldTime, Transform transform, int burnTime) {
		super.setTransform(transform);
		this.worldTime = worldTime;
		this.burnTargetHour = this.worldTime.getHour() + burnTime;
		this.alphaMaskImage = alphaMaskImage.getScaledCopy(currentlightRadius, currentlightRadius);
	}

	@Override
	public void renderToAlphaMap(Graphics g) {
		g.pushTransform();
		g.translate(super.transform.position.x, super.transform.position.y);
		alphaMaskImage.setImageColor(redValue, greenValue, blueValue, alphaValue);
		alphaMaskImage.drawCentered(0, 0);
		g.popTransform();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAllDay() {
		if (millis() > updateCurrentTime) {
			currentlightRadius = lightRadius + random(minlightRadius, maxlightRadius);
			alphaMaskImage = alphaMaskImage.getScaledCopy(currentlightRadius, currentlightRadius);
			updateCurrentTime = updateDelay + updateCurrentTime;
		}
	}

	@Override
	public void updateNight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDayTime() {
		// TODO Auto-generated method stub

	}
}
