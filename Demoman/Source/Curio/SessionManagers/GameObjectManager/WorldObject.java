package Curio.SessionManagers.GameObjectManager;

import org.newdawn.slick.Graphics;

public interface WorldObject {
	public void updateAllDay();
	
	public void updateNight();
	
	public void updateDayTime();

	public void render(Graphics g);

	public void renderToAlphaMap(Graphics g);

}
