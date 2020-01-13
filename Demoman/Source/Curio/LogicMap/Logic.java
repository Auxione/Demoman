package Curio.LogicMap;

import org.newdawn.slick.Graphics;

public interface Logic {
	public abstract void keyEvent();

	public abstract void update(LogicMap logicMap);

	public abstract void render(Graphics g);

}
