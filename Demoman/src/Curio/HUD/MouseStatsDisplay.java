package Curio.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Tileset;
import Curio.Viewport;
import Curio.ItemMap.ItemMap;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.FluidMap;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;
import Default.Player;

public class MouseStatsDisplay extends TextDisplay {
	private Viewport viewPort;
	private Player player;
	private PlantMap plantMap;
	private ItemMap itemMap;
	private TileMap tileMap;
	private FluidMap fluidMap;

	private Vector worldPosition;
	private Vector screenPosition;
	private Transform cellPosition;

	private boolean tileDisplayActive = false;

	public MouseStatsDisplay(Viewport viewPort, Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap,
			FluidMap fluidMap) {
		super();
		this.viewPort = viewPort;
		this.player = player;
		this.tileMap = tileMap;
		this.itemMap = itemMap;
		this.plantMap = plantMap;
		this.fluidMap = fluidMap;

		this.screenPosition = new Vector();
		this.worldPosition = new Vector();
		this.cellPosition = new Transform();
	}

	public void getCellData() {
		String data = "";
		if (worldPosition.dist(player.Position) < player.psize) {
			data += " \n";
			data += "Player: \n";
			data += "HP: " + player.getCurrentHealth() + " \n";
			data += "Food: " + player.getCurrentFood() + " \n";
		}
		int itemID = itemMap.get(cellPosition.get_x(), cellPosition.get_y());
		if (itemID != 0) {
			data += " \n";
			data += "Item: \n";
			data += "ID: " + itemID + " \n";
			data += "Name: " + itemMap.getItemName(itemID) + " \n";
			data += "Description: " + itemMap.getItemDesc(itemID) + " \n";
		}
		int plantID = plantMap.get_Cell(cellPosition.get_x(), cellPosition.get_y());
		if (plantID != 0) {
			data += " \n";
			data += "Plant: \n";
			data += "ID: " + plantID + " \n";
			data += "Name: " + plantMap.getName(plantID) + " \n";
			data += "Description: " + plantMap.getDescription(plantID) + " \n";
			data += "HP: " + plantMap.getHealth(cellPosition.get_x(), cellPosition.get_y()) + " \n";
		}
		int fluidCount = fluidMap.get(cellPosition.get_x(), cellPosition.get_y());
		if (fluidCount > 0) {
			data += " \n";
			data += "Fluid: \n";
			data += "Count:" + fluidCount + " \n";
		}
		if (tileDisplayActive == true) {
			int tileID = tileMap.get_Tile(cellPosition);
			data += " \n";
			data += "Tile: \n";
			data += "ID: " + tileID + " \n";
			data += "Name: " + Tileset.getname(tileID) + " \n";
			data += "canBreak: " + Tileset.canBreak(tileID) + " \n";
			data += "canBurn: " + Tileset.canBurn(tileID) + " \n";
			data += "canMove: " + Tileset.canMove(tileID) + " \n";
			data += "HP: " + Tileset.getHP(tileID) + " \n";
		}
		super.updateString(data);
		super.setPosition(screenPosition);

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public void inputEvent(Input input) {
		screenPosition.x = input.getMouseX();
		screenPosition.y = input.getMouseY();

		worldPosition = viewPort.ScreenToWorldPos(tileMap, screenPosition.x, screenPosition.y);
		cellPosition = tileMap.worldPostoMapPos(worldPosition);
	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

}
