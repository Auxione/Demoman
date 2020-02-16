package Curio.Utilities.Math;

public class Parametric {
	private Vector point1;
	private Vector deltaDistance;

	public Parametric(Vector point1, Vector point2) {
		this.point1 = point1;
		this.deltaDistance = point2.distance(point1);
	}

	public Vector getVector(float t) {
		return new Vector(point1.x + t * deltaDistance.x, point1.y + t * deltaDistance.y);
	}
}
