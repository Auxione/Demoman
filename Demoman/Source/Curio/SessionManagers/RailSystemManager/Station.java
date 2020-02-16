package Curio.SessionManagers.RailSystemManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Renderer.PathRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;
import Default.Constants;

public class Station implements Renderer {
	private PathRenderer pathrenderer;
	public Path line;
	public Vector statingStation;
	public Vector endStation;

	private int nodeSize = 16;
	private Color StationColor = Color.black;

	public Station() {
		this.line = new Path();
		pathrenderer = new PathRenderer(line);
	}

	public void checkStatus() {
		if (statingStation == null) {
			this.statingStation = line.getFirstNode();
		}
		if (endStation == null) {
			this.endStation = line.getLastNode();
		}
	}

	public Station createNode(Vector vector) {
		this.line.addPoint(vector);
		return this;
	}

	public Station createNode(CellCoordinate cellCoordinate) {
		Vector vector = new Vector(cellCoordinate.getWorldX() + Constants.CellSize / 2,
				cellCoordinate.getWorldY() + Constants.CellSize / 2);
		return createNode(vector);
	}

	public Station setColor(Color color) {
		if (StationColor != color) {
			this.StationColor = color;
			this.pathrenderer.setColor(color);
		}
		return this;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		pathrenderer.render(g);
		g.setColor(StationColor);
		if (statingStation != null) {
			g.fillOval(statingStation.x - nodeSize / 2, statingStation.y - nodeSize / 2, nodeSize, nodeSize);
		}
		if (endStation != null) {
			g.fillOval(endStation.x - nodeSize / 2, endStation.y - nodeSize / 2, nodeSize, nodeSize);
		}
		g.popTransform();
	}
}
