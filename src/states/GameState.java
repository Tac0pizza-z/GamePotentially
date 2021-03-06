package states;

import java.awt.Graphics;

import entities.creatures.Player;
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
	}

	public Player getPlayer() {
		return player;
	}
}