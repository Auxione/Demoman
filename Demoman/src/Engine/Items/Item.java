package Engine.Items;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;

public abstract class Item {
	private static int[][][] itemMap;

	public static ArrayList<Stimpack> stimpackList = new ArrayList<Stimpack>();;
	public static ArrayList<Medpack> medpackList = new ArrayList<Medpack>();
	public static ArrayList<bombFlameUpgrade> bombFlameUpgradeList = new ArrayList<bombFlameUpgrade>();
	public static ArrayList<bombSizeUpgrade> bombSizeUpgradeList = new ArrayList<bombSizeUpgrade>();

	public static void createItemMap(int sizex, int sizey) {
		// coordinate system x,y
		// itemMap[][][0] = item id;
		// itemMap[][][1] = item count;
		itemMap = new int[sizex][sizey][2];

		// set value of the all channels to 0
		for (int x = 0; x < itemMap.length; x++) {
			for (int y = 0; y < itemMap[0].length; y++) {
				itemMap[x][y][0] = 0;
				itemMap[x][y][1] = 0;
			}
		}
	}

	public static void mainRender(Graphics g) {
		for (Stimpack s : stimpackList) {
			s.render(g);
		}
		for (Medpack s : medpackList) {
			s.render(g);
		}
		for (bombFlameUpgrade s : bombFlameUpgradeList) {
			s.render(g);
		}
		for (bombSizeUpgrade s : bombSizeUpgradeList) {
			s.render(g);
		}

	}

	public static void put_Stimpack(int x, int y) {
		stimpackList.add(new Stimpack(x, y));
	}

	public static Stimpack get_Stimpack(int x, int y) {
		for (Stimpack s : stimpackList) {
			if (s.transform.equals(x, y) == true) {
				return s;
			}
		}
		return null;
	}

	public static void put_Medpack(int x, int y) {
		medpackList.add(new Medpack(x, y));
	}

	public static Medpack get_Medpack(int x, int y) {
		for (Medpack m : medpackList) {
			if (m.transform.equals(x, y) == true) {
				return m;
			}
		}
		return null;
	}

	public static void put_bombFlameUpgrade(int x, int y) {
		bombFlameUpgradeList.add(new bombFlameUpgrade(x, y));
	}

	public static bombFlameUpgrade get_bombFlameUpgrade(int x, int y) {
		for (bombFlameUpgrade b : bombFlameUpgradeList) {
			if (b.transform.equals(x, y) == true) {
				return b;
			}
		}
		return null;
	}

	public static void put_bombSizeUpgrade(int x, int y) {
		bombSizeUpgradeList.add(new bombSizeUpgrade(x, y));
	}

	public static bombSizeUpgrade get_bombSizeUpgradeList(int x, int y) {
		for (bombSizeUpgrade b : bombSizeUpgradeList) {
			if (b.transform.equals(x, y) == true) {
				return b;
			}
		}
		return null;
	}

	public abstract void render(Graphics g);
}
