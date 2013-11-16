package Game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.sun.awt.AWTUtilities;

import Game.Gfx.AnimatedTile;
import Game.Gui.GamePanel;
import Game.Gui.Gui;
import Game.World.Level;

public class Application implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 128;
	private static final int HEIGHT = WIDTH/12*9;
	private static final int SCALE = 8;
	public static final String NAME = "Tower Defense";
	
	public static Gui myGui = new Gui();
	public static GamePanel myGame = new GamePanel();

	private static Level level;
	private static Application instance;
	private static boolean playing;
	
	private static int tickCount = 0;
	
	protected Application()
	{
		level = new Level("Player 1");
		instance = this;
		playing = true;
		
		JFrame frame = new JFrame(NAME);
		JLayeredPane jlp = new JLayeredPane();
		
		frame.add(jlp);
		
		frame.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		jlp.setOpaque(false);
		jlp.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		myGui.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		myGame.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		jlp.add(myGame, JLayeredPane.DEFAULT_LAYER);
		jlp.add(myGui, JLayeredPane.PALETTE_LAYER);
		
		myGui.setBounds(0,0,WIDTH*SCALE,HEIGHT*SCALE);
		myGame.setBounds(0,0,WIDTH*SCALE,HEIGHT*SCALE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		frame.pack();
	}
	
	/* START setters and getters */
	
	public static boolean Playing()
	{
		return playing;
	}
	
	public static Application getInstance()
	{
		return instance;
	}
	
	public static int getWidth()
	{
		return WIDTH*SCALE;
	}
	
	public static int getHeight()
	{
		return HEIGHT*SCALE;
	}
	
	public static void setPlaying(boolean play)
	{
		playing = play;
	}
	
	public static Level getLevel()
	{
		return level;
	}
	
	/* END setters and getters */
	
	public synchronized void start()
	{
		playing = true;
		new Thread(this).start();
	}
	
	public synchronized void stop()
	{
		
	}
	
	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();
		long now;
		double nsPerTick = 1000000000D/60D;						//How many nano seconds in 60 ticks
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		boolean shouldRender = false;
		
		while(playing)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender)
			{
				frames++;
				myGame.render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick()
	{
		tickCount++;
		
		for(AnimatedTile at : AnimatedTile.animatedTiles)
		{
			at.tick();
		}
		
		Level.tick();
	}
	
	/*
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		Level.render(g);
		
		g.dispose();
		bs.show();
	}
	*/
}
