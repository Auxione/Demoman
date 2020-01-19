package Curio.SessionManagers.WorldObjectManager.WorldObjects;

import static Curio.Functions.*;

import Curio.Physics.WorldTime;
import Curio.SessionManagers.WorldObjectManager.WorldObject;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class ObjectTorch extends WorldObject {
	private WorldTime worldTime;
	final private String name = "Torch";
	
	public Boolean active = true;
	public int radius;

	public float alphaValue = 0.4f;
	public float redValue = 1.0f;
	public float greenValue = 1.0f;
	public float blueValue = 0.0f;

	public int burnTargetHour;

	private int lightRadius = 196;
	private int currentlightRadius = lightRadius;
	private int maxlightRadius = 8;
	private int minlightRadius = -8;
	private int updateDelay = 200;
	private int updateCurrentTime;

	public ObjectTorch(WorldTime worldTime, Transform transform, int burnTime) {
		super.setTransform(transform);
		super.setAlphaMaskImage(Constants.CircularAlphaMask);

		this.worldTime = worldTime;
		this.burnTargetHour = this.worldTime.getTime().hour + burnTime;
		this.objectAlphaMaskImage = objectAlphaMaskImage.getScaledCopy(currentlightRadius, currentlightRadius);
	}

	@Override
	public void updateNight() {
		if (millis() > updateCurrentTime) {
			currentlightRadius = lightRadius + random(minlightRadius, maxlightRadius);
			objectAlphaMaskImage = objectAlphaMaskImage.getScaledCopy(currentlightRadius, currentlightRadius);
			objectAlphaMaskImage.setImageColor(redValue, greenValue, blueValue, alphaValue);
			updateCurrentTime = updateDelay + updateCurrentTime;
		}
	}

	@Override
	public void updateDayTime() {
		if (millis() > updateCurrentTime) {
			currentlightRadius = lightRadius + random(minlightRadius, maxlightRadius);
			objectAlphaMaskImage = objectAlphaMaskImage.getScaledCopy(currentlightRadius, currentlightRadius);
			objectAlphaMaskImage.setImageColor(redValue, greenValue, blueValue, alphaValue);
			updateCurrentTime = updateDelay + updateCurrentTime;
		}
	}

	@Override
	public String getName() {
		return name;
	}
}
