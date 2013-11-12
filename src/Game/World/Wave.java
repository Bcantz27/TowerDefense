package Game.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Game.Entity.Enemy;
import Game.Entity.Enemy.PremadeEnemies;

public class Wave 
{
	private int difficulty;
	private double healthPool;
	private HashMap<String, Integer> enemies = new HashMap<String, Integer>();
	public static List<Enemy> enemiesToSpawn = new ArrayList<Enemy>();
	private long lastInterationTime;
	private int waveDelay = 500;
	private boolean started = false;
	
	public Wave(int diff)
	{
		difficulty = diff;
		healthPool = 0;
		calculateHealthPool();
		initializeWave();
		lastInterationTime = System.currentTimeMillis();
	}
	
	public Wave(Enemy e, int amount)
	{
		difficulty = 0;
		healthPool = 0;
		initializeWave();
		lastInterationTime = System.currentTimeMillis();
		
		addEnemy(e,amount);
	}
	
	public Wave(int id, int amount)
	{
		difficulty = 0;
		healthPool = 0;
		initializeWave();
		lastInterationTime = System.currentTimeMillis();
		
		addEnemy(id,amount);
	}
	
	/* START setters and getters */
	
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public void setDifficulty(int diff)
	{
		difficulty = diff;
		calculateHealthPool();
	}
	
	public boolean getStarted()
	{
		return started;
	}
	
	public void setStarted(boolean start)
	{
		started = start;
	}
	
	/* END setters and getters */
	
	public void tick()
	{
		if(started)
		{
			if((System.currentTimeMillis() - lastInterationTime) >= waveDelay)
			{
				lastInterationTime = System.currentTimeMillis();
				if(enemiesToSpawn.size() > 0)
				{
					Level.spawnEnemy(enemiesToSpawn.get(0));
					enemiesToSpawn.remove(0);
					if(enemiesToSpawn.size() == 0)
					{
						started = false;
						Level.currentWave++;
					}
				}
			}
		}
	}
	
	private void initializeWave()
	{
		for(PremadeEnemies e: Enemy.PremadeEnemies.values())
		{
			Level.spawnEnemy(e.getEnemy(), new Integer(0));
		}
	}
	
	public void addEnemy(Enemy e, int amount)
	{
		enemies.put(e.Name(), amount);
	}
	
	public void addEnemy(int id, int amount)
	{
		enemies.put(Enemy.getEnemyById(id).Name(), amount);
	}
	
	public void start()
	{
		for(PremadeEnemies e: Enemy.PremadeEnemies.values())
		{
			if(enemies.get(e.getEnemy().Name())!= null)
			{
				if(enemies.get(e.getEnemy().Name()).intValue() > 0)
				{
					for(int i = 0; i < enemies.get(e.getEnemy().Name()).intValue(); i++)
					{
						enemiesToSpawn.add(e.getEnemy());
					}
				}
			}
		}
		
		started = true;
	}
	
	public void calculateHealthPool()
	{
		healthPool = Math.pow(difficulty, 3D);
	}
	
	public void generate()
	{
		
	}
}
