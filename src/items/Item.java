package items;

import java.awt.image.BufferedImage;

import runGame.Handler;

public class Item {

	public static final int itemWidth = 32, itemHeight = 32, pickedUp = -1;
	protected Handler handler;
	protected BufferedImage pic;
	protected String name;
	protected final int id;
	
	protected int x, y;
	
	public Item(BufferedImage pic, String name, int id) {
		this.pic = pic;
		this.name = name;
		this.id = id;
	}
}
