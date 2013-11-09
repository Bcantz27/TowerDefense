package Game.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Application;
import Game.Entity.Tower.DamageTypes;
import Game.Gfx.AnimatedTile;
import Game.Gfx.Animation;
import Game.Gfx.Animator;
import Game.Gfx.Tile;
import Game.Gfx.Tile.TileType;
import Game.World.Level;
import Game.World.Position;

public class Enemy extends Entity
{
	private int speed;
	private int health;
	
	private BufferedImage currentImage;
	
	private Animator animator = new Animator(2);
	// 0 = Idle
	// 1 = Walk
	
	public Enemy(String newName, int newId, int newX, int newY,int health,int speed) 
	{
		super(newName, newId, newX, newY);
		
		this.health = health;
		this.speed = speed;

	}
	
	/* START setters and getters */
	
	public float getSpeed()
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
		currentImage = animator.getCurrentAnimation().getCurrentFrame();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(currentImage, position.getTileX(), position.getTileY(), currentImage.getWidth(), currentImage.getHeight(), null);
	}
	
	public void walk()
	{
		
	}

	public static enum PremadeEnemies
	{
		Rat{
			@Override
			public Enemy getEnemy()
			{
				e = new Enemy("Rat", 0, 0, 0, 10, 2);
				e.animator.loadAnimation("Walk","/Animations/Mobs/Rat/walk.png", 500);
				return e;
			}
		};
		
		Enemy e = null;
		
		public abstract Enemy getEnemy();
	}
}
