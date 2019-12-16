package Curio.Utilities;

public class Transform {
	private int[] Position;
	private float Rotation;

	public Transform() {
		Position = new int[2];
	}

	public Transform(int x, int y) {
		Position = new int[2];
		Position[0] = x;
		Position[1] = y;
		Rotation = 0;
	}

	public Transform(float x, float y) {
		Position = new int[2];
		Position[0] = (int) x;
		Position[1] = (int) y;
		Rotation = 0;
	}

	public Transform(Transform tr) {
		this.Position = tr.Position;
		this.Rotation = tr.Rotation;
	}

	public Transform multiply(int mul) {
		Transform out = new Transform(Position[0] * mul, Position[1] * mul);
		return out;
	}

	public boolean equals(int x, int y) {
		if (x == this.Position[0] && y == this.Position[1]) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Transform transform) {
		if (transform.get_x() == this.Position[0] && transform.get_y() == this.Position[1]) {
			return true;
		} else {
			return false;
		}
	}

	public int get_x() {
		return Position[0];
	}

	public int get_y() {
		return Position[1];
	}
	
	public float get_rotation() {
		return Rotation;
	}

	public void set_x(int x) {
		Position[0] = x;
	}

	public void set_y(int y) {
		Position[1] = y;
	}

	public void set_rotation(float rot) {
		Rotation = rot;
	}
}
