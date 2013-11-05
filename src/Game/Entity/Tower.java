package Game.Entity;

import java.awt.Graphics;

import Game.Application;
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
	
	public Tower(String newName, int newId, int newX, int newY, int cost, int damage, DamageTypes type, int range, int attackSpeed, int health)
	{
		super(newName, newId, newX, newY);
		this.cost = cost;
		this.damage = damage;
		this.health = health;
		this.damageType = type;
		this.range = range;
		this.attackSpeed = attackSpeed;
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
		
	}
	
	public static void createTowerAtPos(int id, int x, int y)
	{
		Tower tower;

		tower = PremadeTowers.values()[id].getTower();
		tower.setPosition(new Position(x,y));
		
		Level.addTower(tower);;
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
