package Curio.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public interface HUDInterface {
	public abstract void render(Graphics g);

	public abstract void inputEvent(Input input);

	public abstract void keyPressed(int key, char chr);

	public abstract void keyReleased(int key, char chr);

	public abstract void loopStart();

	public abstract void loopEnd();

}
