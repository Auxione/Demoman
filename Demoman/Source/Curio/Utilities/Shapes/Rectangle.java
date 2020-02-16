package Curio.Utilities.Shapes;

public class Rectangle implements Shape {
	public float width = 0.0f;;
	public float height = 0.0f;;

	public Rectangle() {

	}

	public Rectangle setSize(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}
}
