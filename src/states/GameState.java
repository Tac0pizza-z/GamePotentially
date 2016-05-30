package states;

import java.awt.Color;
import java.awt.Graphics;

import entities.creatures.Player;
import entities.statics.Spikes;
import input.MouseManager;
import runGame.Game;
import runGame.Handler;
import world.World;

public class GameState extends State{
	
	private Player player;
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/world/room1.txt");
		handler.setWorld(world);
	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		if(MouseManager.isRightPressed()){
			g.setColor(Color.RED);
		}else if(MouseManager.isLeftPressed()){
			g.setColor(Color.BLUE);
		}else if(MouseManager.isMidPressed()) {
			g.setColor(Color.BLACK);
		}else{
			g.setColor(Color.WHITE);
		}
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
	}
}