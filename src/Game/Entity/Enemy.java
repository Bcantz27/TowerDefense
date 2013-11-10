package Game.Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	private static final float speedModifer = 0.5f;
	private static final int offset = 32;
	
	private float speed;
	private int health;
	private boolean walking;
	
	private BufferedImage currentImage;
	
	private Direction dir;
	
	private Animator animator = new Animator(2);
	// 0 = Idle
	// 1 = Walk
	
	public Enemy(String newName, int newId, int newX, int newY,int health,float speed) 
	{
		super(newName, newId, newX, newY);
		
		this.health = health;
		this.speed = speed;
		dir = Direction.East;
		walking = false;
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
	
	public Animator getAnimator()
	{
		return animator;
	}
	
	/* END setters and getters */
	
	public void tick()
	{
		if(alive)
		{
			walk();
		}
	}
	
	public void render(Graphics g)
	{
		currentImage = animator.getCurrentAnimation().getCurrentFrame();
        
		if(dir.equals(Direction.North))
		{
			currentImage = Tile.rotate(currentImage, -90);
		}
		else if(dir.equals(Direction.South))
		{
			currentImage = Tile.rotate(currentImage, 90);
		}
		
		if(alive)
		{
			g.drawImage(currentImage, (int)position.getX() + Level.tileSize/4, (int)position.getY() + Level.tileSize/4, currentImage.getWidth(), currentImage.getHeight(), null);
		}
	}
	
	public void walk()
	{
		animator.playAnimation("Walk",true);
		if(dir == Direction.North)
		{
			if((Level.getTileAtPos(position.getX(), (position.getY() - offset)).getType().equals(Tile.TileType.Road) || position.getY()%Level.tileSize != 0) && position.getY() > 1)
			{
				position.shiftPosition(0, -speedModifer*speed);
			}
			else
			{
				dir = Direction.East;
			}
		}
		else if(dir == Direction.East)
		{
			if(position.getTileX() >= 31)
			{
				Level.getPlayer().removeLive();
				destroy();
				return;
			}
			if(Level.getTileAtPos((position.getX() + offset), position.getY()).getType().equals(Tile.TileType.Road) && position.getY() < Application.WIDTH*Application.SCALE - 1)
			{
				System.out.println("Move east");
				position.shiftPosition(speedModifer*speed, 0);
			}
			else
			{
				if(Level.getTileAtPos(position.getX(), (position.getY() + offset)).getType().equals(Tile.TileType.Road))
				{
					dir = Direction.South;
				}
				else
				{
					dir = Direction.North;
				}
			}
		}
		else if(dir == Direction.South)
		{
			if((Level.getTileAtPos(position.getX(), (position.getY() + offset)).getType().equals(Tile.TileType.Road) || position.getY()%Level.tileSize != 0) && position.getY() < Application.HEIGHT*Application.SCALE - 1)
			{
				System.out.println("Move south");
				position.shiftPosition(0, speedModifer*speed);
			}
			else
			{
				System.out.println("Turn West");
				dir = Direction.East;
			}
		}
		else if(dir == Direction.West)
		{
			if(Level.getTileAtPos((position.getX() - offset), position.getY()).getType().equals(Tile.TileType.Road))
			{
				System.out.println("Move west");
				position.shiftPosition(-speedModifer*speed, 0);
			}
			else
			{
				System.out.println("Turn East");
				dir = Direction.East;
			}
		}
	}
	
	public void destroy()
	{
		alive = false;
		Level.removeEnemy(this);
	}
	
	public enum Direction
	{
		North,
		South,
		East,
		West;
	}

	public static enum PremadeEnemies
	{
		Rat{
			@Override
			public Enemy getEnemy()
			{
				e = new Enemy("Rat", 0, 0, 0, 10, 2);
				e.animator.loadAnimation("Idle", "/Animations/Mobs/Rat/idle.png", 0);
				e.animator.loadAnimation("Walk","/Animations/Mobs/Rat/walk.png", 500);
				return e;
			}
		};
		
		Enemy e = null;
		
		public abstract Enemy getEnemy();
	}
	
	
}
