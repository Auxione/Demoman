package Curio;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Renderer.TextDisplay;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.PlayerManager;
import Curio.SessionManagers.LogicManager.LogicManager;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.Math.Vector;
import Default.Constants;
import Default.Player;

public class MouseSelect implements Renderer {
	private Vector worldPosition;
	private Vector screenPosition;
	private Object selectedObject;

	private int rectSize = 32;

	private TextDisplay infoDisplay = new TextDisplay().setPosition(new Vector(16,16));

	public MouseSelect(Viewport viewPort) {
		this.screenPosition = new Vector();
		this.worldPosition = new Vector();
	}

	public void update() {
		if (selectedObject instanceof Player) {
			Player p = (Player) selectedObject;
			infoDisplay.setText(getPlayerStats(p));
		}

		if (selectedObject instanceof LogicObject) {
			LogicObject lo = (LogicObject) selectedObject;
			infoDisplay.setText(getLogicObjectStats(lo));
		}
	}

	private String getLogicObjectStats(LogicObject lo) {
		String data = "";
		data += "Name: " + lo.getName() + "\n";
		if (lo.getCustomInfo() != null) {
			data += "Info: " + lo.getCustomInfo() + "\n";
		}
		data += "State: " + lo.getState() + "\n";
		return data;
	}

	private String getPlayerStats(Player p) {
		String data = "";
		data += "Player: \n";
		data += "HP: " + p.currentHealth + "\n";
		data += "Food: " + p.currentFood + "\n";
		return data;
	}
	
	public void selectObject(Input input) {
		screenPosition.x = input.getMouseX();
		screenPosition.y = input.getMouseY();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) == true) {
			this.selectedObject = null;
			worldPosition = Viewport.ScreenToWorldPosition(screenPosition);

			for (Player p : PlayerManager.playerList) {
				if (worldPosition.dist(p.transform.position) < p.size) {
					this.selectedObject = p;
				}
			}

			for (LogicObject b : LogicManager.logicObjectList) {
				if (b.cellCoordinate.equals(Functions.worldPostoCellPosition(worldPosition))) {
					this.selectedObject = b;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.setLineWidth(2);
		g.setColor(Color.cyan);
		if(this.selectedObject instanceof LogicObject) {
			g.translate(Constants.CellSize/2, Constants.CellSize/2);
		}
		if (this.selectedObject instanceof GameObject) {
			GameObject obj = (GameObject) this.selectedObject;
			g.translate(obj.transform.position.x, obj.transform.position.y);
			g.drawRect(-rectSize / 2, -rectSize / 2, rectSize, rectSize);
			infoDisplay.render(g);
		}
		g.popTransform();
	}
}
