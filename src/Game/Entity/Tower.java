package Game.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Application;
import Game.Gfx.AnimatedTile;
import Game.Gfx.Animation;
import Game.World.Level;
import Game.World.Position;

public class Tower extends Entity
{
	private int cost;
	private int damage;
	private int health;
	private float range;
	private float attackSpeed;
	private DamageTypes damageType;
	
	private BufferedImage currentImage;
	
	private Tower upgrade;
	
	public Tower(String newName, int newId, int newX, int newY, int cost, int damage, DamageTypes type, int range, int attackSpeed, int health)
	{
		super(newName, newId, newX, newY);
		this.cost = cost;
		this.damage = damage;
		this.health = health;
		this.damageType = type;
		this.range = range;
		this.attackSpeed = attackSpeed;

		try {
			currentImage = ImageIO.read(AnimatedTile.class.getResourceAsStream("/Towers/archerI.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* START setters and getters */
	
	public int getCost()
	{
		return cost;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public float getRange()
	{
		return range;
	}
	
	public BufferedImage getImage()
	{
		return currentImage;
	}
	
	public float getAttackSpeed()
	{
		return attackSpeed;
	}
	
	public DamageTypes getDamageType()
	{
		return damageType;
	}
	
	protected void setCost(int cost)
	{
		this.cost = cost;
	}
	
	public void setRange(float range)
	{
		this.range = range;
	}
	
	public void setAttackSpeed(int attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	/* END setters and getters */
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(currentImage, position.getTileX(), position.getTileY(), currentImage.getWidth(), currentImage.getHeight(), null);
	}
	
	public static Tower getTowerById(int id)
	{
		return PremadeTowers.values()[id].getTower();
	}
	
	public static enum PremadeTowers
	{
		ArcherI{
			@Override
			public Tower getTower()
			{
				return new Tower("Archer Tower I", 0, 0, 0, 10, 2, DamageTypes.Single, 20, 2, 30);
			}
		},
		ArcherII{
			@Override
			public Tower getTower()
			{
				return new Tower("Archer Tower II", 1, 0, 0, 20, 4, DamageTypes.Single, 20, 3, 30);
			}
		},
		ArcherIII{
			@Override
			public Tower getTower()
			{
				return new Tower("Archer Tower III", 2, 0, 0, 100, 8, DamageTypes.Single, 20, 5, 30);
			}
		};

		public abstract Tower getTower();
	}
	
	public static enum DamageTypes
	{
		Single,
		Splash,
		Slow,
		Freeze,
		Fire
	}
}
