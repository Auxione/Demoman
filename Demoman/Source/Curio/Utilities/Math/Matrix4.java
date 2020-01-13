package Curio.Utilities.Math;

import static java.lang.Math.*;

public class Matrix4 {
	public float[] matrix = new float[4 * 4];

	public static Matrix4 identity() {
		Matrix4 result = new Matrix4();
		for (int i = 0; i < 4 * 4; i++) {
			result.matrix[i] = 0.0f;
		}
		result.matrix[0 + 0 * 4] = 1.0f;
		result.matrix[1 + 1 * 4] = 1.0f;
		result.matrix[2 + 2 * 4] = 1.0f;
		result.matrix[3 + 3 * 4] = 1.0f;

		return result;
	}

	public Matrix4 multiply(Matrix4 matrix) {
		Matrix4 result = new Matrix4();

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				float sum = 0.0f;
				for (int e = 0; e < 4; e++) {
					sum += this.matrix[e + y * 4] * matrix.matrix[x + e * 4];
				}
				result.matrix[x + y * 4] = sum;
			}
		}
		return result;
	}

	public Matrix4 translate(Vector vector) {
		Matrix4 result = new Matrix4();
		result.matrix[0 + 3 * 4] = vector.x;
		result.matrix[1 + 3 * 4] = vector.y;
		result.matrix[2 + 3 * 4] = vector.z;
		return result;
	}

	public static Matrix4 rotate(float angle) {
		Matrix4 result = identity();
		float r = (float) toRadians(angle);
		float cos = (float) cos(r);
		float sin = (float) sin(r);

		result.matrix[0 + 0 * 4] = cos;
		result.matrix[1 + 0 * 4] = -sin;

		result.matrix[0 + 1 * 4] = sin;
		result.matrix[1 + 1 * 4] = cos;
		return result;
	}

	public static Matrix4 orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4 result = identity();

		result.matrix[0 + 0 * 4] = (float) 2.0f / (right - left);
		result.matrix[0 + 3 * 4] = (float) (left + right) / (left - right);

		result.matrix[1 + 1 * 4] = (float) 2.0f / (top - bottom);
		result.matrix[1 + 3 * 4] = (float) (top + bottom) / (top - bottom);
		
		result.matrix[2 + 2 * 4] = (float) 2.0f / (near-far);
		result.matrix[2 + 3 * 4] = (float) (near+far) / (near-far);

		return result;
	}
}
