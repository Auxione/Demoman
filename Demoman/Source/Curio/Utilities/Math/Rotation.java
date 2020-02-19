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
		angleInDegrees = convertAndClampAngle(degrees);
	}

	private float convertAndClampAngle(float angle) {
		float newAngle = angle;
		if (angle <= 0) {
			newAngle = 360 + angle % 360;
		}
		if (angle > 360) {
			newAngle = angle % 360;
		}
		return newAngle;
	}
	
	public void setAngle(Rotation rotation) {
		this.angleInDegrees = rotation.degrees();
	}
}
