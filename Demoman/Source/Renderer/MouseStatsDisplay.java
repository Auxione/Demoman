package Curio.HUD;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Tileset;
import Curio.Viewport;
import Curio.ItemMap.ItemMap;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.FluidMap;
import Curio.Tilemap.TileMap;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Player;

public class MouseStatsDisplay extends TextDisplay {
	private Viewport viewPort;
	private Player player;
	private PlantMap plantMap;
	private ItemMap itemMap;
	private TileMap tileMap;
	private FluidMap fluidMap;

	private Transform worldPosition;
	private Transform screenPosition;
	private CellCoordinate cellPosition;

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

		this.screenPosition = new Transform();
		this.worldPosition = new Transform();
		this.cellPosition = new CellCoordinate();
	}

	public void getCellData() {
		String data = "";
		if (worldPosition.dist(player.transform) < player.psize) {
			data += " \n";
			data += "Player: \n";
			data += "HP: " + player.getCurrentHealth() + "\n";
			data += "Food: " + player.getCurrentFood() + "\n";
		}
		int itemID = itemMap.getItemID(cellPosition.getCellX(), cellPosition.getCellY());
		if (itemID != 0) {
			data += " \n";
			data += "Item ID: " + itemID + "\n";
			data += "Name: " + itemMap.getItem(itemID).getName() + "\n";
			data += "Description: " + itemMap.getItem(itemID).getDescription() + "\n";
		}
		int plantID = plantMap.get_Cell(cellPosition.getCellX(), cellPosition.getCellY());
		if (plantID != 0) {
			data += " \n";
			data += "Plant ID:" + plantID + "\n";
			data += "Name: " + plantMap.getName(plantID) + "\n";
			data += "Description: " + plantMap.getDescription(plantID) + "\n";
			data += "HP: " + plantMap.getHealth(cellPosition.getCellX(), cellPosition.getCellY()) + "\n";
		}
		int fluidCount = fluidMap.get(cellPosition.getCellX(), cellPosition.getCellY());
		if (fluidCount > 0) {
			data += " \n";
			data += "Fluid: \n";
			data += "Count:" + fluidCount + "\n";
		}
		if (tileDisplayActive == true) {
			int tileID = tileMap.get_Tile(cellPosition);
			data += " \n";
			data += "Tile ID: " + tileID + "\n";
			data += "Name: " + Tileset.getname(tileID) + "\n";
			data += "canBreak: " + Tileset.canBreak(tileID) + "\n";
			data += "canBurn: " + Tileset.canBurn(tileID) + "\n";
			data += "canMove: " + Tileset.canMove(tileID) + "\n";
			data += "HP: " + Tileset.getHP(tileID) + "\n";
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
		screenPosition.position.x = input.getMouseX();
		screenPosition.position.y = input.getMouseY();

		worldPosition = viewPort.ScreenToWorldPos(tileMap, screenPosition.position.x, screenPosition.position.y);
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
