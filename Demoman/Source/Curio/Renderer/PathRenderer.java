package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;

public class PathRenderer implements Renderer {
	private int nodeSize = 8;
	private Color lineColor = Color.black;
	private Path path;

	public PathRenderer(Path path) {
		this.path = path;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.setColor(lineColor);
		for (Vector v : path.getNodes()) {
			g.fillOval(v.x - nodeSize / 2, v.y - nodeSize / 2, nodeSize, nodeSize);
		}

		g.setLineWidth(1);
		for (int i = 0; i < this.path.getNodes().size() - 1; i++) {
			g.drawLine(this.path.getNodes().get(i).x, this.path.getNodes().get(i).y, this.path.getNodes().get(i + 1).x,
					this.path.getNodes().get(i + 1).y);
		}
		g.popTransform();
	}
	
	public PathRenderer setNodeSize(int nodeSize) {
		if (this.nodeSize != nodeSize) {
			this.nodeSize = nodeSize;
		}
		return this;
	}
	
	public PathRenderer setColor(Color color) {
		if (lineColor != color) {
			this.lineColor = color;
		}
		return this;
	}
}