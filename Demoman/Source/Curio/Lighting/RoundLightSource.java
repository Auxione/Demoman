package Curio.Lighting;

import static Curio.Functions.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.GameObject;
import Curio.SessionManagers.WorldObjectManager.WorldObject;
import Default.Constants;

public class RoundLightSource extends WorldObject {
	public Image alphaMapImage = Constants.CircularAlphaMask;

	public Boolean active = true;
	public int radius;

	public float alphaValue = 0.7f;
	public float redValue = 1.0f;
	public float greenValue = 1.0f;
	public float blueValue = 1.0f;

	public RoundLightSource() {
		this.alphaMapImage.setImageColor(redValue, greenValue, blueValue, alphaValue);
	}

	public RoundLightSource setState(Boolean active) {
		this.active = active;
		return this;
	}

	public RoundLightSource setRadius(int radius) {
		this.radius = radius;
		alphaMapImage = alphaMapImage.getScaledCopy(radius, radius);
		return this;
	}

	public RoundLightSource setColor(int red, int green, int blue, int alpha) {
		this.redValue = map(red, 0, 255, 0.0f, 1.0f);
		this.greenValue = map(green, 0, 255, 0.0f, 1.0f);
		this.blueValue = map(blue, 0, 255, 0.0f, 1.0f);
		this.alphaValue = map(alpha, 0, 255, 0.0f, 1.0f);
		return this;
	}

	@Override
	public void updateNight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDayTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
