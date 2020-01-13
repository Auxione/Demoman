package Curio.Lighting;

import static Curio.Functions.map;

import org.newdawn.slick.Image;

import Curio.GameObject.GameObject;
import Default.Constants;

public class RoundLightSource extends GameObject {
	public Image alphaMapImage = Constants.CircularAlphaMap;

	public Boolean active = true;
	public int radius;

	private float alphaValue = 0.7f;
	private float redValue = 1.0f;
	private float greenValue = 1.0f;
	private float blueValue = 1.0f;

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
		float redValue = map(red, 0, 255, 0.0f, 1.0f);
		float greenValue = map(green, 0, 255, 0.0f, 1.0f);
		float blueValue = map(blue, 0, 255, 0.0f, 1.0f);
		float alphaValue = map(alpha, 0, 255, 0.0f, 1.0f);
		alphaMapImage.setImageColor(redValue, greenValue, blueValue, alphaValue);
		return this;
	}

}
