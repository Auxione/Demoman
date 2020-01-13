package Curio.LogicSystem.Trigger;

import org.newdawn.slick.Graphics;

import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class MoveTrigger implements Logic {
	final String debugcode = "<MoveTrigger>: ";
	final boolean debugActive = false;

	public CellCoordinate objectCellPosition;
	public CellCoordinate outputCellPosition;

	public boolean activated = false;

	public MoveTrigger(int x, int y, CellCoordinate outputCellPosition) {
		this.objectCellPosition = new CellCoordinate(x, y);
		this.outputCellPosition = outputCellPosition;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		g.drawImage(Constants.movetrigger, 0, 0);
		g.popTransform();
	}

	@Override
	public void keyEvent() {
	}

	@Override
	public void update(LogicMap logicMap) {
		if (activated == true) {
			logicMap.sendTick(outputCellPosition, activated);
			activated = false;
		}
	}
}
