package Curio.LogicSystem.Processor;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class PushToSwitch implements Logic {
	final String debugcode = "<PushToSwitch>:";
	final boolean debugActive = false;

	public CellCoordinate objectCellPosition;
	public CellCoordinate outputCellPosition;

	private boolean state = false;
	private boolean activated = false;

	public PushToSwitch(int x, int y, CellCoordinate outputCellPosition) {
		this.objectCellPosition = new CellCoordinate(x, y);
		this.outputCellPosition = outputCellPosition;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		g.setColor(Color.blue);
		g.fillRect(0, 0, Constants.CellSize, Constants.CellSize);
		g.popTransform();
	}

	@Override
	public void keyEvent() {

	}

	@Override
	public void update(LogicMap logicMap) {
		if (logicMap.getState(objectCellPosition) == true) {
			state = !state;
			activated = true;
		}

		if (activated == true) {
			logicMap.setState(outputCellPosition, state);
			activated = false;
		}
	}
}