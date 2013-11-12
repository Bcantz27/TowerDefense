package Game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Game.Entity.Enemy;
import Game.Entity.Player;
import Game.Entity.Tower;
import Game.Gfx.AnimatedTile;
import Game.Gfx.Animation;
import Game.Gfx.SpriteSheet;
import Game.Gui.MainPane;
import Game.World.Level;

public class Application extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 128;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 8;
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
        JComponent newContentPane = new MainPane(this);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
		
		frame.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

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
		
		for(AnimatedTile at : AnimatedTile.animatedTiles)
		{
			at.tick();
		}
		
		Level.tick();
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
		
		Level.render(g);
		
		g.dispose();
		bs.show();
	}
}
