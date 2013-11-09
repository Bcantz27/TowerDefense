package Game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Game.Entity.Enemy;
import Game.Entity.Player;
import Game.Entity.Tower;
import Game.Gfx.AnimatedTile;
import Game.Gfx.Animation;
import Game.Gfx.SpriteSheet;
import Game.World.Level;

public class Application extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 6;
	public static final String NAME = "Tower Defense";

	private static Level level;
	private static Application instance;
	private static boolean playing;
	
	private static int tickCount = 0;
	
	protected Application()
	{
		level = new Level("Player 1");
		instance = this;
		playing = true;
		
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		Level.spawnEnemy(0, Level.enemySpawnPoint.getTileX(),Level.enemySpawnPoint.getTileY());
	}
	
	/* START setters and getters */

	public Application instance()
	{
		return instance;
	}
	
	public boolean Playing()
	{
		return playing;
	}
	
	public void setPlaying(boolean playing)
	{
		this.playing = playing;
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
				render();
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
		
		Level.getPlayer().tick();
		
		for(AnimatedTile i : AnimatedTile.animatedTiles)
		{
			i.tick();
		}
		
		for(Enemy e : Level.enemies)
		{
			e.tick();
		}
		
		for(Tower t : Level.towers)
		{
			t.tick();
		}
		
		for(Enemy e : Level.enemies)
		{
			e.walk();
		}
		
	}
	
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
		
		Level.getPlayer().render(g);
		
		Level.render(g);
		
		for(Enemy e : Level.enemies)
		{
			e.render(g);
		}
		
		for(Tower t : Level.towers)
		{
			t.render(g);
		}
		
		g.dispose();
		bs.show();
	}
}
