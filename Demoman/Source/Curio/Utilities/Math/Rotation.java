package Curio.Utilities.Math;

public class Rotation {
	private float angle = 0.0f;

	public Rotation() {
	}

	public Rotation(float angle) {
		this.angle = angle;
	}

	public float degrees() {
		return angle;
	}

	public void setAngle(float degrees) {
		if (degrees >= 0 && degrees < 360.0f) {
			angle = degrees;
		}
	}

	public void setAngle(Rotation rotation) {
		this.angle = rotation.degrees();
	}
}
