package Curio.Renderer;

import org.newdawn.slick.Input;

public interface HUDInterface {
	public void inputEvent(Input input);

	public void keyPressed(int key, char chr);

	public void keyReleased(int key, char chr);

	public void loopStart();

	public void loopEnd();

}
