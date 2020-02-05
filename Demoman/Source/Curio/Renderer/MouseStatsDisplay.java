package Curio.Renderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Functions;
import Curio.Viewport;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.PlayerManager;
import Curio.SessionManagers.ItemManager.ItemList;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObject;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Vector;
import Default.Player;

public class MouseStatsDisplay extends TextDisplay implements Renderer {
	private ItemMap itemMap;

	private PlantManager plantManager;
	private WorldManager worldManager;
	
	private Vector worldPosition;
	private Vector screenPosition;
	private CellCoordinate cellPosition;
	private boolean tileDisplayActive = true;
	

	public MouseStatsDisplay(Viewport viewPort, WorldManager worldManager, PlantManager plantManager, ItemMap itemMap) {
		super();
		this.itemMap = itemMap;
		this.plantManager = plantManager;
		this.worldManager = worldManager;

		this.screenPosition = new Vector();
		this.worldPosition = new Vector();
		this.cellPosition = new CellCoordinate();
	}

	public void getCellData() {
		String data = "";
		for (Player p : PlayerManager.playerList) {
			if (worldPosition.dist(p.transform.position) < p.size) {
				data += " \n";
				data += "Player: \n";
				data += "HP: " + p.currentHealth + "\n";
				data += "Food: " + p.currentFood + "\n";
			}
		}
		for (WorldObject wo : WorldObjectManager.worldObjects) {
			if (worldPosition.dist(wo.transform.position) < 5) {
				data += " \n";
				data += "WorldObject: \n";
				data += "Name: " + wo.getName() + "\n";
			}
		}
		int itemID = itemMap.getCell(cellPosition, 0);
		if (itemID != 0) {
			data += " \n";
			data += "Item ID: " + itemID + "\n";
			data += "Name: " + ItemList.list.get(itemID).getName() + "\n";
			data += "Description: " + ItemList.list.get(itemID).getDescription() + "\n";
		}
		int plantID = plantManager.plantMap.getCell(cellPosition, 0);
		if (plantID != 0) {
			data += " \n";
			data += "Plant ID:" + plantID + "\n";
			data += "Name: " + plantManager.getName(plantID) + "\n";
			data += "Description: " + plantManager.getDescription(plantID) + "\n";
			data += "HP: " + plantManager.getHealth(cellPosition.getCellX(), cellPosition.getCellY()) + "\n";
		}
		if (tileDisplayActive == false) {
			int tileID = worldManager.tileMap.getCell(cellPosition, 0);
			data += " \n";
			data += "Tile ID: " + tileID + "\n";
			data += "Name: " + TileList.getTile(tileID).getName() + "\n";
			data += "canBreak: " + TileList.getTile(tileID).getUnbreakable() + "\n";
			data += "canBurn: " + TileList.getTile(tileID).isFlammable() + "\n";
			data += "canMove: " + TileList.getTile(tileID).isSolid() + "\n";
			data += "HP: " + TileList.getTile(tileID).getTileMaxHP() + "\n";

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

		worldPosition = Viewport.ScreenToWorldPosition(screenPosition);
		cellPosition = Functions.worldPostoCellPosition(worldPosition);
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
