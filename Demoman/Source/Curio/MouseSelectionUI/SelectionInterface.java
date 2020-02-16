package Curio.MouseSelectionUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Renderer.Interface.Renderer;

public interface SelectionInterface extends Renderer{
	public void select();

	public void render(Graphics g);

	public void FirstUpdate(Object o, Input input);

	public void LastUpdate();
}
