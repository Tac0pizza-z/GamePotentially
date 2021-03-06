package runGame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import displayGame.Display;
import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable{
	
	//Game Window
	private Display display;
	private int width, height;
	public String title;
	
	//run Game
	private boolean running = false;
	private Thread thread;
	
	//Graphics
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//camera
	private GameCamera gameCamera;
	
	//handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		//handler
		handler = new Handler(this);
		
		//camera starting position
		gameCamera = new GameCamera(handler, 0, 0);
		
		//initialize states
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
	}
	
	private void tick(){
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//reset screen
		g.clearRect(0, 0, width, height);
		//make images
		if(State.getState() != null)
			State.getState().render(g);
		//show images
		bs.show();
		g.dispose();
	}
	
	public void run(){
		init();
		
		int fps = 60;
		double effFps = 1000000000 / fps;
		long now;
		double delta = 0;
		long lastTime = System.nanoTime();
		long seconds = 0;
		int frames = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / effFps;
			seconds += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				tick();
				render();
				frames++;
				delta--;
			}
			
			if (seconds >= 1000000000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				seconds = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(!running){
			running = true;
			thread = new Thread(this);
			thread.start();
			System.out.println("Game Opening");
		}
	}
	
	public synchronized void stop(){
		if(running){
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
