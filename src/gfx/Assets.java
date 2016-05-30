package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage player, enemy, wall, grass, ice;
	
	public static void init(){
		Sprites sheet = new Sprites(ImageLoader.loadImage("/textures/sprites.png"));
		
		player = sheet.crop(0, 0, width, height);
		enemy = sheet.crop(width, 0, width, height);
		wall = sheet.crop(width * 2, 0, width, height);
		grass = sheet.crop(width * 3, 0, width, height);
		ice = sheet.crop(width * 4, 0, width, height);
	}
}