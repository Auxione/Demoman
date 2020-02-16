package Curio.Utilities.Navigation;

import java.util.ArrayList;
import Curio.Utilities.Math.Parametric;
import Curio.Utilities.Math.Vector;

public class Path {
	private ArrayList<Vector> nodes;

	public Path() {
		this.nodes = new ArrayList<Vector>();
	}

	public void addPoint(Vector vector) {
		nodes.add(vector);
	}

	public void removePoint(int point) {
		for (int i = point; i < nodes.size(); i++) {
			nodes.remove(i);
		}
	}

	public Vector getFirstNode() {
		return nodes.get(0);
	}

	public Vector getLastNode() {
		return nodes.get(nodes.size() - 1);
	}

	public ArrayList<Vector> getNodes() {
		return nodes;
	}

	public Vector getVector(float t) {
		int min = (int) Math.floor(t);
		int max = (int) Math.ceil(t);
		if (nodes.size() > 0) {
			if (t >= min && t <= max) {
				return new Parametric(nodes.get(min), nodes.get(max)).getVector(t - min);
			}
		}
		return null;
	}
}
