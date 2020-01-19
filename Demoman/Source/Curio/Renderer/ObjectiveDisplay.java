package Curio.Renderer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.ObjectiveSystem.Objective;
import Curio.Renderer.Interface.HUD;
import Curio.Renderer.Interface.Renderer;

public class ObjectiveDisplay extends HUD implements Renderer {

	private Objective objective;
	private TrueTypeFont trueTypeFont = super.getTTF();
	private String[] goalText;
	private int goalTextWidth;
	private int goalTextHeight;

	private int xOffset = 0;
	private int yOffset = 0;

	public ObjectiveDisplay(float xPosition, float yPosition, Objective objective) {
		super(xPosition, yPosition);
		this.objective = objective;
		this.goalText = splitText(objective.goalText());

		this.goalTextWidth = 200;// trueTypeFont.getWidth(arg0);
		this.goalTextHeight = trueTypeFont.getHeight();

		super.resize(goalTextWidth, goalTextHeight * goalText.length);
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(super.transform.position.x, super.transform.position.y);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, super.width, super.height);

		for (int i = 0; i < goalText.length; i++) {
			trueTypeFont.drawString(xOffset, i * goalTextHeight + yOffset, goalText[i], Color.black);
		}
		g.popTransform();
	}

	private String[] splitText(String text) {
		String[] texts = text.split("\\r?\\n");
		return texts;
	}

	@Override
	public void inputEvent(Input input) {
		// TODO Auto-generated method stub

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
