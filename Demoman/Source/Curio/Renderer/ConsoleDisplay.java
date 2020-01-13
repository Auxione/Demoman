package Curio.Renderer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Console;

public class ConsoleDisplay extends HUD implements HUDInterface, Renderer {
	int textSize = 16;
	boolean active = false;
	private float barSize = 4.0f;
	private TrueTypeFont trueTypeFont = super.getTTF();

	private Console console;

	public ConsoleDisplay(float x, float y, float w, float h, Console console) {
		super(x, y, w, h);
		this.console = console;
	}

	public void render(Graphics g) {
		lastCommandDisplay(g, 0, 0);

		if (active) {
			g.pushTransform();
			// background
			g.translate(super.transform.position.x, super.transform.position.y);
			g.setColor(new Color(100, 100, 100, 190));
			g.fillRect(super.transform.position.x, super.transform.position.y, super.width, super.height);
			// texts
			g.setWorldClip(super.transform.position.x, super.transform.position.y, super.width, super.height);
			for (int i = console.commandHistory.size() - 1; i >= 0; i--) {

				Color c = Color.orange;
				if (console.commandType.get(i) == 0) {
					c = (Color.black);
				} else if (console.commandType.get(i) == 1) {
					c = (Color.red);
				} else if (console.commandType.get(i) == 2) {
					c = (Color.yellow);
				}

				float Stringx = super.transform.position.x;
				float Stringy = super.transform.position.y + super.height + i * textSize
						- console.commandHistory.size() * (textSize + 1) - barSize;

				trueTypeFont.drawString(Stringx, Stringy, console.commandHistory.get(i), c);
			}
			// bottombar
			g.setColor(Color.black);
			g.fillRect(super.transform.position.x, super.transform.position.y + super.height - barSize, super.width,
					barSize);

			g.clearWorldClip();
			g.popTransform();
		}
	}

	private void lastCommandDisplay(Graphics g, float x, float y) {
		String lastCommand = console.commandHistory.get(console.commandHistory.size() - 1);
		g.pushTransform();
		// background
		g.translate(x, y);
		g.setColor(new Color(220, 220, 220));

		float sizeX = trueTypeFont.getWidth(console.commandHistory.get(console.commandHistory.size() - 1));
		float sizeY = trueTypeFont.getHeight();
		g.fillRect(0, 0, sizeX, sizeY);

		trueTypeFont.drawString(0, 0, lastCommand, Color.black);
		g.popTransform();
	}

	@Override
	public void inputEvent(Input input) {
		if (input.isKeyPressed(Input.KEY_GRAVE)) {
			active = !active;
		}
	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char chr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int key, char chr) {
		// TODO Auto-generated method stub

	}

}
