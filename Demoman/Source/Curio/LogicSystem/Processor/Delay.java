package Curio.LogicSystem.Processor;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class Delay implements Logic {
	final String debugcode = "<TimedTrigger>:";
	final boolean debugActive = false;

	public CellCoordinate objectCellPosition;
	public CellCoordinate outputCellPosition;

	private float goal, timer;
	private boolean activated = false;

	public Delay(int x, int y, CellCoordinate outputCellPosition, int t) {
		this.objectCellPosition = new CellCoordinate(x, y);
		this.outputCellPosition = outputCellPosition;

		timer = (float) (t);
	}

	private boolean startTimer(float goal) {
		if (Functions.millis() > goal) {
			return true;
		} else {
			return false;
		}
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		g.setColor(Color.orange);
		g.fillRect(0, 0, Constants.CellSize, Constants.CellSize);
		g.popTransform();
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LogicMap logicMap) {
		if (logicMap.getState(objectCellPosition) == true) {
			activated = true;
			goal = Functions.millis() + timer;
		}

		if (startTimer(goal) == true && activated == true) {
			logicMap.sendTick(outputCellPosition, true);
			activated = false;
		}
	}
}