package Curio.Utilities.Math;

public class Rotation {
	private float angleInDegrees = 0.0f;

	public Rotation() {
	}

	public Rotation(float angleInDegrees) {
		this.angleInDegrees = angleInDegrees;
	}

	public float degrees() {
		return angleInDegrees;
	}

	public void setAngle(float degrees) {
		if (degrees >= 0 && degrees < 360.0f) {
			angleInDegrees = degrees;
		}
	}

	public void setAngle(Rotation rotation) {
		this.angleInDegrees = rotation.degrees();
	}
}
