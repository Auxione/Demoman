package Engine.Utilities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Default.Constants;
import Engine.Functions;

public class Animation {
	private int frames;
	private int timer_goal = 0;
	private int timer_current_t = 0;
	private int i = 0;
	private Image currimg;
	private Image[] frameimg;
	private boolean active = false;
	public boolean Finished = true;

	/*
	 * initialize animation from sprite image parameters: Sprite image,frame
	 * width,frame height,how many frames sprite have,overall time to play anim.
	 */
	public Animation(Image img, int sx, int sy, int frm, int as) {
		// create frame image array
		frameimg = new Image[frm];
		// count how much delay need to show one frame after another in overall time.
		timer_goal = as / frm;

		frames = frm;
		// cut sprite image into frames
		for (int i = 0; i < frames; i++) {
			frameimg[i] = img.getSubImage(i * sx, 0, sx, sy);

		}
	}

	// start animation
	public void Play() {
		active = true;
	}

	// display animation on position
	public void render(Graphics g, Transform transform) {
		// check if the animation started
		if (active == true) {
			// return false every time
			Finished = false;
			// if animation finishes
			if (i == frames) {
				// return to start
				i = 0;
				// disable active flag
				active = false;
				// pass true
				Finished = true;
			}
			// check each frame
			if (Functions.millis() > timer_current_t) {
				// if goal achieved skip to other frame
				currimg = frameimg[i];
				// advance once
				i++;
				// set the next goal again
				timer_current_t = Functions.millis() + timer_goal;
			}
			// display image if its active
			g.drawImage(currimg, transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize);

		}
	}

	public void render(Graphics g, int x,int y) {
		// check if the animation started
		if (active == true) {
			// return false every time
			Finished = false;
			// if animation finishes
			if (i == frames) {
				// return to start
				i = 0;
				// disable active flag
				active = false;
				// pass true
				Finished = true;
			}
			// check each frame
			if (Functions.millis() > timer_current_t) {
				// if goal achieved skip to other frame
				currimg = frameimg[i];
				// advance once
				i++;
				// set the next goal again
				timer_current_t = Functions.millis() + timer_goal;
			}
			// display image if its active
			g.drawImage(currimg, x * Constants.CellSize, y * Constants.CellSize);
		}
	}
}