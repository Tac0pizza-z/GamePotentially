package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//easy access
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile wallTile = new WallTile(1);
	public static Tile iceTile = new IceTile(2);


	
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	
	protected BufferedImage sprite;
	protected final int id;
	
	public Tile(BufferedImage sprite, int id){
		this.sprite = sprite;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(sprite, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
}
