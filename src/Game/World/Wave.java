package Game.World;

import java.util.List;

import Game.Entity.Enemy;

public class Wave 
{
	private int difficulty;
	private double healthPool;
	private List<Enemy> enemies;
	
	public Wave(int diff)
	{
		difficulty = diff;
		healthPool = 0;
		calculateHealthPool();
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
	
	/* END setters and getters */
	
	public void calculateHealthPool()
	{
		healthPool = Math.pow(difficulty, 3D);
	}
	
	public void generateWave()
	{
		
	}
}
