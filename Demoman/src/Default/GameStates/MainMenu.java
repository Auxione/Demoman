package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.HUD.Button;
import Default.Main;

public class MainMenu {
	private Button sp, mp;

	public MainMenu() {
		sp = new Button(20, 20, 200, 50, "sp");
		mp = new Button(20, 80, 200, 50, "mp");
	}

	public void update(Input input) {
		sp.inputEvent(input);
		mp.inputEvent(input);

		if (sp.pressed) {
			Main.GameState = 1;
		}
		else if (mp.pressed) {
			Main.GameState = 2;
		}
	}

	public void render(Graphics g) {
		sp.render(g);
		mp.render(g);
	}
}
