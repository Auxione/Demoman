package Engine.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public interface HUD {
	
	public abstract void render(Graphics g);
	public abstract void inputEvent(Input input);
	public abstract void loopStart();
	public abstract void loopEnd();
}
