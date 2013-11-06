package Game.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Game.Application;
import Game.Entity.Tower.DamageTypes;
import Game.Gfx.Animation;
import Game.World.Level;

public class Enemy extends Entity
{
	private int speed;
	private int health;
	
	private BufferedImage currentImage;
	
	private List<Animation> animations = new ArrayList<Animation>();
	
	public Enemy(String newName, int newId, int newX, int newY,int health,int speed) 
	{
		super(newName, newId, newX, newY);
		this.health = health;
		this.speed = speed;
	}
	
	/* START setters and getters */
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public BufferedImage getImage()
	{
		return currentImage;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	/* END setters and getters */
	
	public void tick()
	{
		for(Animation a : animations)
		{
			if(a.getPlaying())
			{
				currentImage = a.getCurrentImage();
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(currentImage, position.getX(), position.getY(), currentImage.getWidth(), currentImage.getHeight(), null);
	}
	
	public static void spawnEnemy(int id)
	{
		Enemy e;
		
		e = PremadeEnemies.values()[id].getEnemy();
		e.setPosition(Level.enemySpawnPoint);
		
		Level.addEnemy(e);
		
	}
	
	public static enum PremadeEnemies
	{
		Rat{
			@Override
			public Enemy getEnemy()
			{
				return new Enemy("Rat", 0, 0, 0, 10, 3);
			}
		};
		
		public abstract Enemy getEnemy();
	}
}
