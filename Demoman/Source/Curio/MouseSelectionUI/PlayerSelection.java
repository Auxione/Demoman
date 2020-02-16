package Curio.MouseSelectionUI;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Renderer.DisplayObjects.Button;
import Curio.Renderer.DisplayObjects.TextDisplay;
import Curio.SessionManagers.PlayerManager;
import Curio.Utilities.Math.Vector;
import Default.Player;

public class PlayerSelection implements SelectionInterface {
	private MouseSelect mouseSelect;
	public TextDisplay infoDisplay = new TextDisplay();
	public ArrayList<Button> buttons;
	private PlayerManager playerManager;
	private Player selfPlayer;

	public PlayerSelection(Player selfPlayer, MouseSelect mouseSelect, PlayerManager playerManager) {
		this.selfPlayer = selfPlayer;
		this.playerManager = playerManager;
		this.mouseSelect = mouseSelect;
		this.buttons = new ArrayList<Button>();
		this.buttons.add(new Button().setSize(100, 20).setButtonText("kill"));
		this.buttons.add(new Button().setSize(100, 20).setButtonText("UseItemTo"));
		this.buttons.add(new Button().setSize(100, 20).setButtonText("-HP"));
	}

	@Override
	public void select() {

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

	public String getPlayerStats(Player p) {
		String data = "";
		data += "Player: \n";
		data += "HP: " + p.currentHealth + "\n";
		data += "Food: " + p.currentFood + "\n";
		return data;
	}

	@Override
	public void FirstUpdate(Object o, Input input) {
		if (o instanceof Player) {
			Player p = (Player) o;
			for (int i = 0; i < this.buttons.size(); i++) {
				this.buttons.get(i).setPosition(new Vector(p.transform.position.x, p.transform.position.y + 24 * i));
				this.buttons.get(i).vectorInputEvent(mouseSelect.mouseWorldPosition, input);
			}

			if (this.buttons.get(0).pressed == true) {
				p.setHealthValue(0);
			}

			else if (this.buttons.get(1).pressed == true) {
				playerManager.useItem(selfPlayer, p);
			}

			else if (this.buttons.get(2).pressed == true) {
				p.applyDamage(10);
			}

			this.infoDisplay
					.setPosition(new Vector(p.transform.position.x, p.transform.position.y + 24 * this.buttons.size()));
			this.infoDisplay.setText(getPlayerStats(p));
		}
	}

	@Override
	public void LastUpdate() {
		for (Button b : buttons) {
			b.loopEnd();
		}
	}

}
