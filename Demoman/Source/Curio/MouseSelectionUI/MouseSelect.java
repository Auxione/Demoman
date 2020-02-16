package Curio.MouseSelectionUI;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Functions;
import Curio.GameObject;
import Curio.Viewport;
import Curio.Physics.Interfaces.FLUpdate;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.PlayerManager;
import Curio.SessionManagers.LogicManager.LogicManager;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Math.Geometry.Circle;
import Default.Constants;
import Default.Player;

public class MouseSelect implements Renderer, FLUpdate {
	public Vector mouseWorldPosition;
	public Object selectedObject;

	private int rectSize = 32;
	private ArrayList<SelectionInterface> SelectionInterfaces = new ArrayList<SelectionInterface>();
	private Player selfPlayer;

	private int reach = 100;
	private Input input;

	public MouseSelect(Player selfPlayer, Viewport viewPort, PlayerManager playerManager, Input input) {
		this.selfPlayer = selfPlayer;
		this.mouseWorldPosition = new Vector();
		this.SelectionInterfaces.add(new PlayerSelection(selfPlayer, this, playerManager));
		this.SelectionInterfaces.add(new LogicObjectSelection(this));
		this.input = input;
	}

	public void selectObject() {
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) == true) {
			this.selectedObject = null;
			for (Player p : PlayerManager.playerList) {
				if (p.shape instanceof Circle) {
					Circle c = (Circle) p.shape;
					if (mouseWorldPosition.distanceSQRT(p.transform.position) < c.radius) {
						if (p.transform.position.distanceSQRT(selfPlayer.transform.position) <= reach) {
							this.selectedObject = p;
							this.SelectionInterfaces.get(0).select();
						}
					}
				}
			}

			for (LogicObject p : LogicManager.logicObjectList) {
				if (p.cellCoordinate.equals(Functions.worldPostoCellPosition(mouseWorldPosition))) {
					if (p.transform.position.distanceSQRT(selfPlayer.transform.position) <= reach) {
						this.selectedObject = p;
						this.SelectionInterfaces.get(1).select();
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.setLineWidth(2);
		g.setColor(Color.cyan);
		if (this.selectedObject instanceof LogicObject) {
			g.translate(Constants.CellSize / 2, Constants.CellSize / 2);
		}
		if (this.selectedObject instanceof GameObject) {
			GameObject obj = (GameObject) this.selectedObject;
			g.translate(obj.transform.position.x, obj.transform.position.y);
			g.drawRect(-rectSize / 2, -rectSize / 2, rectSize, rectSize);
		}
		g.popTransform();

		if (this.selectedObject instanceof Player) {
			SelectionInterfaces.get(0).render(g);
		}

		else if (this.selectedObject instanceof LogicObject) {
			SelectionInterfaces.get(1).render(g);
		}
	}

	@Override
	public void FirstUpdate() {
		if (selectedObject instanceof GameObject) {
			GameObject g = (GameObject) selectedObject;
			if (g.transform.position.distanceSQRT(selfPlayer.transform.position) > reach) {
				this.selectedObject = null;
			}
		}

		this.mouseWorldPosition = Viewport.ScreenToWorldPosition(new Vector(input.getMouseX(), input.getMouseY()));
		for (SelectionInterface ie : SelectionInterfaces) {
			ie.FirstUpdate(selectedObject, input);
		}
		selectObject();
	}

	@Override
	public void LastUpdate() {
		for (SelectionInterface ie : SelectionInterfaces) {
			ie.LastUpdate();
		}
	}
}
