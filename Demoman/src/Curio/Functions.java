package Curio;

import Curio.Utilities.Transform;
import Default.Main;

public class Functions {
	// game runtime in milliseconds
	public static int millis() {
		int out = (int) (System.currentTimeMillis() - Main.millis_start_time);
		return out;
	}

	// check if the two positions is in the same tile
	public static boolean isOnTop(Transform tr1, Transform tr2) {
		if ((tr1.get_x() == tr2.get_x()) && (tr1.get_y() == tr2.get_y())) {
			return true;
		} else {
			return false;
		}
	}

	// line to line intersection from:
	// https://www.wikizeroo.org/index.php?q=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvTGluZeKAk2xpbmVfaW50ZXJzZWN0aW9u

	// this function returns if the two lines intersect each other
	public static boolean lineToLineIntersectionBool(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		float uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

		float uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

		if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
			return true;
		}
		return false;
	}
	
//this function returns coordinates of the intersection
	public static float[] lineToLineIntersectionCord(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		float uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
		float uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

		float iX = x1 + (uA * (x2 - x1));
		float iY = y1 + (uA * (y2 - y1));

		float[] out = { iX, iY };

		if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
			return out;
		}
		return null;
	}

	public static final float map(float value, float start1, float stop1, float start2, float stop2) {
		float out = start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
		return out;
	}

}
