package Engine.Tilemap.Logic.Controller;

import org.newdawn.slick.Graphics;

import Engine.Tilemap.Tilemap;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

public class DynamicWall extends Logic{
    private Boolean state = false;
    		
    private Transform transform;
    private int changedTileID, originalTileID;

    private Tilemap lvl;
    /*Create dynamic wall which is tile id in that position can be changed with user interaction in game
     parameters: level,x position, y position,changed tile id*/

    public DynamicWall(Tilemap l, int x, int y, int changeID) {
      lvl = l;
      transform = new Transform(x,y);

      originalTileID = lvl.get_Tile(transform.get_x(), transform.get_y());
      changedTileID = changeID;
    }

    public void update() {
      //get current tile id first to process it on checktile()
      //if tile is activated and not broken 
        //check the tile if its not changed outside our func.
        //change the state
    	
        state = getState(transform);
        
        if (state == true) {
          //change the tile id on the current level to tileid1
          lvl.set_Tile(transform.get_x(), transform.get_y(), changedTileID);
          
        } else if (state == false) {
          //change the tile id on the current level to tileid2
          lvl.set_Tile(transform.get_x(), transform.get_y(), originalTileID);
          
        }
      }

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
    }