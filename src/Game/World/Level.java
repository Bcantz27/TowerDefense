package Game.World;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Game.Application;
import Game.Entity.Enemy;
import Game.Entity.Player;
import Game.Entity.Tower;
import Game.Entity.Enemy.PremadeEnemies;
import Game.Entity.Tower.PremadeTowers;
import Game.Generators.LevelGenerator;
import Game.Gfx.SpriteSheet;
import Game.Gfx.Tile;

public class Level 
{
	public static final int tileSize = 32;
	
	private static Player player;
	public static List<Tower> towers = new ArrayList<Tower>();
	public static List<Enemy> enemies = new ArrayList<Enemy>();
	public static Position enemySpawnPoint;
	public static Tile[][] tiles;
	public static Wave[] waves;
	public static List<Enemy> enemiesToRemove = new ArrayList<Enemy>();
	public static int currentWave;
	public static int maxWave;
	
	public Level(String name)
	{
		currentWave = 0;
		maxWave = 0;
		player = new Player(name);
		enemySpawnPoint = new Position(0,0);
		tiles = new Tile[(Application.WIDTH*Application.SCALE)/tileSize][((Application.HEIGHT*Application.SCALE)/tileSize) + 1];
		LevelGenerator.generateLevel();
		
		waves = new Wave[10];
		addWave(new Wave(0,10));
		addWave(new Wave(1,3));
		
	}
	
	/* START setters and getters */
	
	public static Player getPlayer()
	{
		return player;
	}
	
	/* END setters and getters */
	
	public static void tick()
	{
		player.tick();
		if(currentWave >= 0 && currentWave < maxWave)
			waves[currentWave].tick();
		
		for(Tower t : towers)
		{
			t.tick();
		}
		
		for(Enemy e : enemies)
		{
			e.tick();
		}
		
		for(Enemy e : enemiesToRemove)
		{
			enemies.remove(e);
		}
		
		enemiesToRemove = new ArrayList<Enemy>();
	}
	
	public static void render(Graphics g)
	{
		for(int i = 0; i < tiles.length; i++)//COLS
		{
			for(int j = 0; j < tiles[0].length; j++)//ROWS
			{
				g.drawImage(tiles[i][j].getImage(), i*tileSize, j*tileSize, tileSize, tileSize, null);
			}
		}
		
		for(Enemy e : enemies)
		{
			e.render(g);
		}
		
		for(Tower t : towers)
		{
			t.render(g);
		}
	}
	
	public static void startNextWave()
	{
		if(currentWave < maxWave)
		{
			if(!waves[currentWave].getStarted())
			{
				System.out.println("Starting Wave " + (currentWave+1) + " out of " + maxWave);
				waves[currentWave].start();
			}
		}
		else
		{
			currentWave = maxWave;
		}
	}
	
	public static void addWave(Wave w)
	{
		waves[maxWave] = w;
		maxWave++;
	}
	
	public static void spawnEnemy(int id)
	{
		Enemy e;
		
		e = PremadeEnemies.values()[id].getEnemy();
		e.setWalking(true);
		e.setPosition(new Position(Level.enemySpawnPoint.getX(),Level.enemySpawnPoint.getY()));
		
		addEnemy(e);
	}
	
	public static void spawnEnemy(Enemy e)
	{
		e.setWalking(true);
		e.setPosition(new Position(Level.enemySpawnPoint.getX(),Level.enemySpawnPoint.getY()));
		addEnemy(e);
	}
	
	public static void spawnEnemy(int id, int amount)
	{
		Enemy e = null;
		
		for(int i = 0; i < amount; i++)
		{
			e = PremadeEnemies.values()[id].getEnemy();
			e.setWalking(true);
			e.setPosition(new Position(Level.enemySpawnPoint.getX(),Level.enemySpawnPoint.getY()));
			
			if(e != null)
				addEnemy(e);
		}
	}
	
	public static void spawnEnemy(Enemy e, int amount)
	{
		for(int i = 0; i < amount; i++)
		{
			e.setPosition(new Position(Level.enemySpawnPoint.getX(),Level.enemySpawnPoint.getY()));
			e.setWalking(true);
			addEnemy(e);
		}
	}
	
	public static void spawnTower(int id, float x, float y)
	{
		Tower tower;

		tower = PremadeTowers.values()[id].getTower();
		tower.setPosition(new Position(x,y));
		
		addTower(tower);;
	}
	
	public static Tile getTileAtPos(float x, float y)
	{
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x > Application.WIDTH*Application.SCALE) x = Application.WIDTH*Application.SCALE;
		if(y > Application.HEIGHT*Application.SCALE) y = Application.HEIGHT*Application.SCALE;
		
		
		//System.out.println("X: " + (int)x/tileSize + " Y:" + (int)y/tileSize);
		//System.out.println("Type: " + tiles[(int) (x/tileSize)][(int) (y/tileSize)].getType().toString());
		return tiles[(int) (x/tileSize)][(int) (y/tileSize)];
	}
	
	public static void addTower(Tower t)
	{
		towers.add(t);
	}
	
	public static void addEnemy(Enemy e)
	{
		enemies.add(e);
	}
	
	public static void removeTower(Tower t)
	{
		
	}
	
	public static void removeEnemy(Enemy e)
	{
		enemiesToRemove.add(e);
	}
}
