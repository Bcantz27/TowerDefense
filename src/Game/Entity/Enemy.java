package Game.Entity;

import Game.Application;
import Game.Entity.Tower.DamageTypes;

public class Enemy extends Entity
{
	private int speed;
	private int health;
	
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
		
	}
	
	public static void spawnEnemy(int id)
	{
		Enemy e;
		
		e = PremadeEnemies.values()[id].getEnemy();
		e.setPosition(Application.getLevel().enemySpawnPoint);
		
		Application.getLevel().addEnemy(e);
		
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
