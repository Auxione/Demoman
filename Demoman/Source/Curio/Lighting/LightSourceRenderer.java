package Curio.Lighting;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;

import Curio.Renderer.Renderer;

public class LightSourceRenderer implements Renderer {
	private RoundLightSource roundLightSource;
	private float redValue;
	private float greenValue;
	private float blueValue;
	private float alphaValue;

	public LightSourceRenderer(RoundLightSource roundLightSource) {
		this.roundLightSource = roundLightSource;
		this.redValue = roundLightSource.redValue;
		this.greenValue = roundLightSource.greenValue;
		this.blueValue = roundLightSource.blueValue;
		this.alphaValue = roundLightSource.alphaValue;
	}

	@Override
	public void render(Graphics g) {
		// check if the light is active
		if (roundLightSource.active == true) {
			g.clearAlphaMap();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // Blends the alpha map.
			roundLightSource.alphaMapImage.setImageColor(redValue, greenValue, blueValue, alphaValue);
			roundLightSource.alphaMapImage.drawCentered(roundLightSource.transform.position.x,
					roundLightSource.transform.position.y);

			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA); // Resets the blending factor.
			g.setDrawMode(Graphics.MODE_NORMAL); // Resets the Graphics draw mode.
			g.clearWorldClip();
		}
	}
}
