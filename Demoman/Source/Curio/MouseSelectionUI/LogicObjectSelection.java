package Curio.MouseSelectionUI;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Renderer.DisplayObjects.Button;
import Curio.Renderer.DisplayObjects.TextDisplay;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.Math.Vector;

public class LogicObjectSelection implements SelectionInterface{
	private MouseSelect mouseSelect;
	public TextDisplay infoDisplay = new TextDisplay();
	public ArrayList<Button> buttons;

	public LogicObjectSelection(MouseSelect mouseSelect) {
		this.mouseSelect = mouseSelect;
		this.buttons = new ArrayList<Button>();
		this.buttons.add(new Button().setSize(100, 20).setButtonText("Connect"));
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		for (Button b : buttons) {
			b.render(g);
		}
		infoDisplay.render(g);
		g.popTransform();
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
	@Override
	public void LastUpdate() {
		for (Button b : buttons) {
			b.loopEnd();
		}
	}

	@Override
	public void FirstUpdate(Object o, Input input) {
		if (o instanceof LogicObject) {
			LogicObject p = (LogicObject) o;
			for (int i = 0; i < this.buttons.size(); i++) {
				this.buttons.get(i).setPosition(new Vector(p.transform.position.x, p.transform.position.y + 24 * i));
				this.buttons.get(i).vectorInputEvent(mouseSelect.mouseWorldPosition, input);
			}

			if (this.buttons.get(0).pressed == true) {
			}

			this.infoDisplay
					.setPosition(new Vector(p.transform.position.x, p.transform.position.y + 24 * this.buttons.size()));
			this.infoDisplay.setText(getLogicObjectStats(p));
		}
	}
}
